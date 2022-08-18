package com.alkemy.challenge.repository;

import com.alkemy.challenge.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    Optional<MovieEntity> findByTituloEquals(String titulo);

    List<Optional<MovieEntity>> findByGeneroEquals(Long idGender);

/*
    @Query(value = "SELECT * FROM pelicula ORDER BY fecha_creacion :order",nativeQuery = true)
   //@Query("SELECT m FROM MovieEntity m ORDER BY m.fechaCreacion :order")
    List<Optional<MovieEntity>> findByOrder(@Param("order") String order);
*/
     List <Optional<MovieEntity>>findByOrderByFechaCreacionAsc();
    List <Optional<MovieEntity>>findByOrderByFechaCreacionDesc();
}
