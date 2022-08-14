package com.alkemy.challenge.repository;

import com.alkemy.challenge.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository  extends JpaRepository<MovieEntity,Long> {
}
