package com.company.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class CarResponse {
    private List<CarDto> content;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean last;
}
