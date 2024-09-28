package com.mss.app.service;

import com.mss.app.dto.MeetingRoomDto;
import com.mss.app.entity.Availability;
import com.mss.app.entity.MeetingRoom;
import com.mss.app.exception.EntityNotFoundException;
import com.mss.app.repository.MeetingRoomRepository;
import com.mss.app.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This is service layer for meeting room operations.
 *
 * @author Prantik Sarkar
 */
@Service
public class MeetingRoomService {

    @Autowired
    private MeetingRoomRepository meetingRoomRepository;

    // - create room entity.
    public MeetingRoom createRoom(MeetingRoomDto meetingRoomDto) {
        MeetingRoom room = new MeetingRoom();
        room.setName(meetingRoomDto.getName());
        room.setCapacity(meetingRoomDto.getCapacity());
        room.setAvailability(meetingRoomDto.getAvailability());
        return meetingRoomRepository.save(room);
    }

    // - Updating availability slots for room.
    public void updateRoomAvailability(int id, LocalDateTime start, LocalDateTime end) {
        MeetingRoom room = getById(id);
        List<Availability> currentAvailability = room.getAvailability();
        List<Availability> updatedAvailability = AppUtils.updateAvailability(currentAvailability, start, end);
        room.setAvailability(updatedAvailability);
        meetingRoomRepository.save(room);
    }

    public MeetingRoom getById(int id) {
        return meetingRoomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meeting Room not found with given Id."));
    }
}
