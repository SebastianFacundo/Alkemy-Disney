package com.alkemy.challenge.service;

import com.alkemy.challenge.dto.CharacterBasicDTO;
import com.alkemy.challenge.dto.CharacterDTO;
import com.alkemy.challenge.entity.CharacterEntity;
import com.alkemy.challenge.exception.Duplicate;
import com.alkemy.challenge.exception.ParamNotFound;
import com.alkemy.challenge.mapper.CharacterMapper;
import com.alkemy.challenge.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacterService {
    @Autowired
    CharacterRepository characterRepository;
    CharacterMapper characterMapper = new CharacterMapper();

    public List<CharacterDTO> getAllCharacters() {

        List<CharacterEntity> charactersEntity = characterRepository.findAll().stream().filter(characterEntity -> characterEntity.getBorrado().equals(Boolean.FALSE)).collect(Collectors.toList());
        List<CharacterDTO> charactersDTO = charactersEntity.stream().map(characterEntity -> characterMapper.entityToDto(characterEntity)).collect(Collectors.toList());

        return charactersDTO;
    }

    public CharacterDTO getCharacter(Long id) {

        Optional<CharacterEntity> characterEntity = characterRepository.findById(id);
        if (characterEntity.isEmpty() || characterEntity.get().getBorrado()) {
            throw new ParamNotFound("id personaje no valido");
        }

        CharacterDTO characterDTO = characterMapper.entityToDto(characterEntity.get());

        return characterDTO;
    }

    public CharacterDTO save(CharacterDTO characterDTO) {

        Optional<CharacterEntity> findCharacter = characterRepository.findByNombreEquals(characterDTO.getNombre());
        if (!findCharacter.isEmpty()) {
            throw new Duplicate("PERSONAJE YA INGRESADO");
        }
        CharacterEntity characterEntity = characterMapper.dtoToEntity(characterDTO);
        characterRepository.save(characterEntity);
        characterDTO.setId(characterEntity.getId());
        return characterDTO;
    }

    public CharacterDTO update(CharacterDTO characterDTO) {

        Optional<CharacterEntity> findCharacter = characterRepository.findById(characterDTO.getId());
        if (findCharacter.isEmpty() || findCharacter.get().getBorrado()) {
            throw new ParamNotFound("id personaje no valido");
        }

        //VERIFICO QUE EL NOMBRE NO CORRESPONDA A OTRO PERSONAJE YA INGRESADO
        Optional<CharacterEntity> findCharacterByName = characterRepository.findByNombreEquals(characterDTO.getNombre());
        if((!findCharacterByName.isEmpty())&&findCharacterByName.get().getId()!=findCharacter.get().getId()){
            throw new Duplicate("PERSONAJE YA INGRESADO");
        }
        CharacterEntity characterEntity = characterMapper.refresh(characterDTO);
        characterEntity.setPeliculas(findCharacter.get().getPeliculas());

        characterRepository.save(characterEntity);

        return characterMapper.entityToDto(characterEntity);
    }

    public CharacterDTO delete(Long id) {
        CharacterEntity characterEntity = characterRepository.findById(id).orElseThrow(() -> new ParamNotFound("id personaje no valido"));
        characterEntity.setBorrado(Boolean.TRUE);
        characterRepository.save(characterEntity);

        return characterMapper.entityToDto(characterEntity);
    }

    public CharacterBasicDTO getCharacterForName(String name) {
        Optional<CharacterEntity> findCharacter = characterRepository.findByNombreEquals(name);
        if (findCharacter.isEmpty() || findCharacter.get().getBorrado()) {
            throw new ParamNotFound("Personaje no encontrado");
        }
        return characterMapper.entityToBasicDto(findCharacter.get());
    }

    public List<CharacterBasicDTO> getCharactersForAge(int edad) {

        List<Optional<CharacterEntity>> charactersEntity = characterRepository.findByEdadEquals(edad).stream().filter(characterEntity -> characterEntity.get().getBorrado() == Boolean.FALSE).collect(Collectors.toList());
        if (charactersEntity.isEmpty()) {
            throw new ParamNotFound("Personajes no encontrados");
        }

        List<CharacterBasicDTO> charactersBasicDTO = charactersEntity.stream().map(characterEntity -> characterMapper.entityToBasicDto(characterEntity.get())).collect(Collectors.toList());

        return charactersBasicDTO;
    }

    public List<CharacterBasicDTO> getCharactersForMovie(Long idMovie) {

        List<Optional<CharacterEntity>>charactersEntity =characterRepository.findByMovie(idMovie).stream().filter(characterEntity -> characterEntity.get().getBorrado() == Boolean.FALSE).collect(Collectors.toList());
        if (charactersEntity.isEmpty()) {
            throw new ParamNotFound("Personajes no encontrados");
        }

        List<CharacterBasicDTO> characterBasicDTOS=charactersEntity.stream().map(characterEntity -> characterMapper.entityToBasicDto(characterEntity.get())).collect(Collectors.toList());

        return characterBasicDTOS;
    }


}
