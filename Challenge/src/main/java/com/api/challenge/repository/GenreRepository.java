package com.api.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.challenge.models.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {

}
