package com.mss.app.service;

import com.mss.app.entity.Availability;
import com.mss.app.entity.MeetingRoom;
import com.mss.app.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class will be responsible to detect all the conflicts during meeting scheduling.
 *
 * @author Prantik Sarkar
 */
@Service
@Slf4j
public class ConflictManagementService {

    public boolean checkAvailability(List<User> participants, MeetingRoom room, LocalDateTime startTime, LocalDateTime endTime, List<String> messages) {
        for (User user : participants) {
            if (!isUserAvailable(user, startTime, endTime, messages)) {
                return false;
            }
        }
        return isRoomAvailable(room, startTime, endTime, messages);
    }

    // - This will check the user's availability in a particular duration from start to end.
    private boolean isUserAvailable(User user, LocalDateTime startTime, LocalDateTime endTime, List<String> messages) {
        log.info("start :: {}, end :: {}", startTime, endTime);
        log.info("Availability - {}",user.getAvailability());
        for (Availability availability : user.getAvailability()) {
            if (startTime.isAfter(availability.getStart()) && endTime.isBefore(availability.getEnd())) {
                log.info("User {} is available.", user.getName());
                return true;
            }
        }
        messages.add(String.format("User %s is not available.", user.getName()));
        log.info("User {} is not available.", user.getName());
        return false;
    }

    // - This will check the room availability in a particular duration from start to end.
    private boolean isRoomAvailable(MeetingRoom room, LocalDateTime startTime, LocalDateTime endTime, List<String> messages) {
        for (Availability availability : room.getAvailability()) {
            if (startTime.isAfter(availability.getStart()) && endTime.isBefore(availability.getEnd())) {
                log.info("Room is available.");
                return true;
            }
        }
        messages.add("Room is not available for given duration.");
        log.info("Room is not available.");
        return false;
    }

}
