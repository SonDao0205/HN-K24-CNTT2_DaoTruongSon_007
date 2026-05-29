package com.btvn.hackathon.repository;

import com.btvn.hackathon.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findAllByIsDeletedIsFalse(Pageable pageable);
    Page<Room> findByIsDeletedIsFalseAndRoomNumberContainingOrIsDeletedIsFalseAndRoomTypeContaining(String keyword1, String keyword2, Pageable pageable);
}
