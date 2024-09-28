package com.mss.app.service;

import com.mss.app.dto.UserDto;
import com.mss.app.entity.Availability;
import com.mss.app.entity.User;
import com.mss.app.exception.EntityNotFoundException;
import com.mss.app.repository.UserRepository;
import com.mss.app.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This is service layer for user operations.
 *
 * @author Prantik Sarkar
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // - Create user entity.
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setAvailability(userDto.getAvailability());
        return userRepository.save(user);
    }

    // - Updating availability slots for user.
    public void updateUserAvailability(int id, LocalDateTime start, LocalDateTime end) {
        User user = getById(id);
        List<Availability> availabilities = user.getAvailability();
        List<Availability> updatedAvailabilities = AppUtils.updateAvailability(availabilities, start, end);
        user.setAvailability(updatedAvailabilities);
        userRepository.save(user);
    }

    public User getById(int id) {
        return userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User not found with given id."));
    }

    public List<User> getByIds(List<Integer> ids) {
        return userRepository.findByIdIn(ids);
    }

    public List<UserDto> getAll() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream().map(UserDto::new).toList();
    }
}
