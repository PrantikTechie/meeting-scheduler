package com.mss.app.controller;

import com.mss.app.dto.MeetingRoomDto;
import com.mss.app.entity.MeetingRoom;
import com.mss.app.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a rest controller for handling meeting room operations.
 *
 * @author Prantik Sarkar
 */
@RestController
@RequestMapping("/rooms")
public class MeetingRoomController {

    @Autowired
    private MeetingRoomService meetingRoomService;

    @PostMapping("/create-room")
    public ResponseEntity<MeetingRoom> createRoom(@RequestBody MeetingRoomDto meetingRoomDto) {
        return new ResponseEntity<>(meetingRoomService.createRoom(meetingRoomDto), HttpStatus.CREATED);
    }

}
