package com.btvn.hackathon.dto;

import com.btvn.hackathon.entity.RoomStatusEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoomDTO {
    @NotBlank(message = "Số phòng không được để trống!")
    private String roomNumber;
    @NotBlank(message = "Loại phòng không được bỏ trống!")
    private String roomType;
    @NotNull(message = "Giá phòng không được bỏ trống!")
    @Min(value = 1, message = "Giá phòng phải lớn hơn 0!")
    private Double pricePerNight;
    private String status = RoomStatusEnum.AVAILABLE.toString();
}
