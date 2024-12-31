package com.airbnb.bnb.controller;

import com.airbnb.bnb.entity.Room;
import com.airbnb.bnb.repository.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {

private RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @PostMapping("/addRoom")
    public String addRoom(@RequestBody Room room){
        roomRepository.save(room);

    return "added";
    }
}
