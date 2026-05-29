package com.btvn.hackathon.controller;

import com.btvn.hackathon.dto.ApiDataResponse;
import com.btvn.hackathon.dto.RoomDTO;
import com.btvn.hackathon.entity.Room;
import com.btvn.hackathon.service.RoomService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<Room>>> findAll(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        log.info(">>> LOGGER : Lấy danh sách phòng thành công!");
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy danh sách phòng thành công!",
                roomService.findAll(keyword,pageable),
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Room>> save(@Valid @RequestBody RoomDTO dto) {
        Room createdRoom = roomService.createRoom(dto);
        log.info(">>> LOGGER : Tạo phòng thành công!");
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Tạo phòng thành công!",
                createdRoom,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Room>> update(@Valid @RequestBody RoomDTO dto, @PathVariable Long id) {
        System.out.println("Update");
        Room updatedRoom = roomService.updateRoom(dto, id);
        log.info(">>> LOGGER : Cập nhật toàn bộ thông tin thành công!");
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Cập nhật toàn bộ thông tin thành công!",
                updatedRoom,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Room>> path(@RequestBody RoomDTO dto, @PathVariable Long id) {
        Room updatedRoom = roomService.pathRoom(dto, id);
        log.info(">>> LOGGER : Cập nhật 1 phần thông tin thành công!");
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Cập nhật 1 phần thông tin thành công!",
                updatedRoom,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Room>> delete(@PathVariable Long id) {
        roomService.deleteRoom(id);
        log.info(">>> LOGGER : Xoá phòng thành công!");
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Xoá phòng thành công!",
                null,
                HttpStatus.NO_CONTENT
        ), HttpStatus.NO_CONTENT);
    }
}
