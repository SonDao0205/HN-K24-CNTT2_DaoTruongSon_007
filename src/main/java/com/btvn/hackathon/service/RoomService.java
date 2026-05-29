package com.btvn.hackathon.service;

import com.btvn.hackathon.dto.RoomDTO;
import com.btvn.hackathon.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomService {
    Page<Room> findAll(String keyword, Pageable pageable);
    Room findById(Long id);
    Room createRoom(RoomDTO dto);
    Room updateRoom(RoomDTO dto, Long id);
    Room pathRoom(RoomDTO dto,Long id);
    void deleteRoom(Long id);
}
