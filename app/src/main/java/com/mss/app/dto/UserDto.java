package com.mss.app.dto;

import com.mss.app.entity.Availability;
import com.mss.app.entity.User;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

    private int id;
    private String name;
    private List<Availability> availability;

    public UserDto(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
