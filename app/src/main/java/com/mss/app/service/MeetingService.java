package com.mss.app.service;

import com.mss.app.dto.MeetingDto;
import com.mss.app.entity.Meeting;
import com.mss.app.entity.MeetingRoom;
import com.mss.app.entity.User;
import com.mss.app.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This is service layer for meeting operations.
 *
 * @author Prantik Sarkar
 */
@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MeetingRoomService meetingRoomService;

    @Autowired
    private ConflictManagementService conflictManagementService;

    // - Scheduling a new meeting.
    public String createMeeting(MeetingDto meetingDto) {
        // - Get all participants for the meeting.
        List<User> participants = userService.getByIds(meetingDto.getParticipantIds());
        // - Get the room in which meeting has to be hosted.
        MeetingRoom room = meetingRoomService.getById(meetingDto.getRoomId());
        // - Room out of capacity.
        if(participants.size() > room.getCapacity()) {
            return String.format("Unable to schedule meeting, as the room capacity is %s and participants are %s.", room.getCapacity(), participants.size());
        }
        LocalDateTime start = meetingDto.getStartTime();
        LocalDateTime end = meetingDto.getEndTime();
        List<String> messages = new ArrayList<>();
        // - Only schedule meeting if no conflicts.
        if(conflictManagementService.checkAvailability(participants , room , start, end, messages))
        {
            Meeting meeting = new Meeting();
            meeting.setTitle(meetingDto.getTitle());
            meeting.setStartTime(start);
            meeting.setEndTime(end);
            meeting.setRoom(room);
            meeting.setParticipants(participants);
            meetingRepository.save(meeting);
            // - update room and user availabilities
            for(int id : meetingDto.getParticipantIds()) {
                userService.updateUserAvailability(id, start, end);
            }
            meetingRoomService.updateRoomAvailability(meetingDto.getRoomId(), start, end);
            return String.format("Meeting - %s scheduled successfully.", meeting.getTitle());
        } else {
            return String.format("Meeting was not scheduled due to conflicts. Reason :: %s", messages);
        }
    }
}
