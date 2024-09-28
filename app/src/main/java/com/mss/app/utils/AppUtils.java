package com.mss.app.utils;

import com.mss.app.entity.Availability;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will have all the utility methods required by the application..
 *
 * @author Prantik Sarkar
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppUtils {

    public static List<Availability> updateAvailability(List<Availability> currentAvailability, LocalDateTime start, LocalDateTime end) {
        List<Availability> updatedAvailability = new ArrayList<>();

        for(Availability availability : currentAvailability) {
            boolean matchStart = start.isAfter(availability.getStart()) && start.isBefore(availability.getEnd());
            boolean matchEnd = end.isAfter(availability.getStart()) && end.isBefore(availability.getEnd());
            if(matchStart && matchEnd) {
                updatedAvailability.add(new Availability(availability.getStart(), start));
                updatedAvailability.add(new Availability(end, availability.getEnd()));
            } else {
                updatedAvailability.add(availability);
            }
        }
        return updatedAvailability;
    }
}
