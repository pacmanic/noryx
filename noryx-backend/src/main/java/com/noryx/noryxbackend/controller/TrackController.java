package com.noryx.noryxbackend.controller;

import com.noryx.noryxbackend.dto.TrackResponse;
import com.noryx.noryxbackend.entity.Track;
import com.noryx.noryxbackend.entity.User;
import com.noryx.noryxbackend.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tracks")
@RequiredArgsConstructor
public class TrackController{
    private final TrackService trackService;

    @PostMapping("/upload")
    public ResponseEntity<TrackResponse> uploadTrack(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("artist") String artist,
            @RequestParam(value = "album", required = false) String album,
            @AuthenticationPrincipal User user
    ) throws IOException {
        Track track = trackService.uploadTrack(file, title, artist, album, user);
        return ResponseEntity.ok(toResponse(track));
    }

    @GetMapping
    public ResponseEntity<List<TrackResponse>> getAllTracks() {
        return ResponseEntity.ok(
                trackService.getAllTracks().stream()
                        .map(this::toResponse)
                        .toList()
        );
    }

    private TrackResponse toResponse(Track track) {
        return TrackResponse.builder()
                .id(track.getId())
                .title(track.getTitle())
                .artist(track.getArtist())
                .album(track.getAlbum())
                .duration(track.getDuration())
                .filePath(track.getFilePath())
                .coverUrl(track.getCoverUrl())
                .uploadedBy(track.getUploadedBy().getUsername())
                .createdAt(track.getCreatedAt().toString())
                .build();
    }
}