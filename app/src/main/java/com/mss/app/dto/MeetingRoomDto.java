package com.mss.app.dto;

import com.mss.app.entity.Availability;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MeetingRoomDto {

    private Long id;
    private String name;
    private int capacity;
    private List<Availability> availability;

}
