package com.noryx.noryxbackend.service;

import com.noryx.noryxbackend.entity.Track;
import com.noryx.noryxbackend.entity.User;
import com.noryx.noryxbackend.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrackService{
    private final TrackRepository trackRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public Track uploadTrack(MultipartFile file, String title, String artist, String album, User uploadedBy) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filepath = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(), filepath);

        Track track = Track.builder()
                .title(title)
                .artist(artist)
                .album(album)
                .filePath(filepath.toString())
                .uploadedBy(uploadedBy)
                .build();

        return trackRepository.save(track);
    }

    public List<Track> getAllTracks(){
        return trackRepository.findAll();
    }
}