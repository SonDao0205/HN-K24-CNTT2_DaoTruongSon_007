package com.btvn.hackathon.service.impl;

import com.btvn.hackathon.dto.RoomDTO;
import com.btvn.hackathon.entity.Room;
import com.btvn.hackathon.exception.NotFoundException;
import com.btvn.hackathon.exception.NotValidValue;
import com.btvn.hackathon.repository.RoomRepository;
import com.btvn.hackathon.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Slf4j
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Page<Room> findAll(String keyword, Pageable pageable) {
        if (keyword != null && !keyword.isEmpty()) {
            return roomRepository.findByIsDeletedIsFalseAndRoomNumberContainingOrIsDeletedIsFalseAndRoomTypeContaining(keyword, keyword, pageable);
        }
        return roomRepository.findAllByIsDeletedIsFalse(pageable);
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> {
            log.error(">>> LOGGER : Không tìm thấy phòng với id này!");
            return new NotFoundException("Không tìm thấy phòng với id này!");
        });
    }

    @Override
    public Room createRoom(RoomDTO dto) {
        Room room = new Room();
        room.setRoomNumber(dto.getRoomNumber());
        room.setRoomType(dto.getRoomType());
        room.setPricePerNight(dto.getPricePerNight());
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(RoomDTO dto, Long id) {
        Room room = findById(id);
        room.setRoomNumber(dto.getRoomNumber());
        room.setRoomType(dto.getRoomType());
        room.setPricePerNight(dto.getPricePerNight());
        room.setStatus(dto.getStatus());
        return roomRepository.save(room);
    }

    @Override
    public Room pathRoom(RoomDTO dto, Long id) {
        Room room = findById(id);
        if(dto.getRoomNumber() != null && !dto.getRoomNumber().isBlank()){
            room.setRoomNumber(dto.getRoomNumber());
        }
        if(dto.getRoomType() != null && !dto.getRoomType().isBlank()){
            room.setRoomType(dto.getRoomType());
        }
        if(dto.getPricePerNight() != null ){
            if(dto.getPricePerNight() <= 0){
                throw new NotValidValue("Giá phòng phải lớn hơn 0!");
            }
            room.setPricePerNight(dto.getPricePerNight());
        }
        if(dto.getStatus() != null && !dto.getStatus().isBlank()){
            room.setStatus(dto.getStatus());
        }

        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        Room room = findById(id);
        room.setIsDeleted(true);
        roomRepository.save(room);
    }
}
