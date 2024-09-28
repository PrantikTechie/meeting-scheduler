package com.mss.app.dto;

import com.mss.app.entity.MeetingRoom;
import com.mss.app.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MeetingDto {

    private Long id;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Integer> participantIds;
    private Integer roomId;

}
