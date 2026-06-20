package com.noryx.noryxbackend.repository;

import com.noryx.noryxbackend.entity.Playlist;
import com.noryx.noryxbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{
    List<Playlist> findByOwner(User owner);
}