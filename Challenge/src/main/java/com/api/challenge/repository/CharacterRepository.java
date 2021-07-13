package com.api.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.challenge.models.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character,Long> {
 List<Character> findByName(String name);
 List<Character> findByAge(String age);
 List<Character> findByWeight(Double weight);
 List<Character> findByMoviesId(Long id);
}
