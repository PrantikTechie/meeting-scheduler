package com.mss.app.controller;

import com.mss.app.dto.MeetingDto;
import com.mss.app.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a rest controller for handling meeting operations.
 *
 * @author Prantik Sarkar
 */
@RestController
@RequestMapping("/meetings")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping("/create-meeting")
    public ResponseEntity<String> createUser(@RequestBody MeetingDto meetingDto) {
        return new ResponseEntity<>(meetingService.createMeeting(meetingDto), HttpStatus.CREATED);
    }
}
