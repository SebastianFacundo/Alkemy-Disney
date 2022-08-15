package com.alkemy.challenge.repository;

import com.alkemy.challenge.dto.CharacterBasicDTO;
import com.alkemy.challenge.entity.CharacterEntity;
import com.alkemy.challenge.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
    Optional<CharacterEntity> findByNombreEquals(String nombre);

    List<Optional<CharacterEntity>> findByEdadEquals(int edad);

    @Query( value = "SELECT * FROM personaje as p JOIN personaje_pelicula as pp on pp.personaje_id= p.id where pp.pelicula_id=:idMovie",nativeQuery = true)
    List<Optional<CharacterEntity>> findByMovie(@Param("idMovie") Long idMovie);
}
