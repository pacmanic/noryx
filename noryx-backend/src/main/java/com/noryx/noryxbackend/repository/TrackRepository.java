package com.noryx.noryxbackend.repository;

import com.noryx.noryxbackend.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
    List<Track> findByArtistContainingIgnoreCase(String artist);
    List<Track> findByTitleContainingIgnoreCase(String title);
}