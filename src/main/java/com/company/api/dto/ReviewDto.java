package com.company.api.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private Integer id;
    private String title;
    private String content;
    private Integer stars;
}
