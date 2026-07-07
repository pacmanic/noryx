package com.noryx.noryxbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TrackResponse{
    private Long id;
    private String title;
    private String artist;
    private String album;
    private Integer duration;
    private String filePath;
    private String coverUrl;
    private String uploadedBy;
    private String createdAt;
}