package com.alkemy.challenge.service;

import com.alkemy.challenge.dto.CharacterDTO;
import com.alkemy.challenge.entity.CharacterEntity;
import com.alkemy.challenge.repository.CharacterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacterService {
    @Autowired
    CharacterRepository characterRepository;


    ModelMapper modelMapper = new ModelMapper();


    public List<CharacterDTO> getAllCharacters() {



       List<CharacterEntity> charactersEntity =characterRepository.findAll().stream().filter(characterEntity -> characterEntity.getBorrado().equals(Boolean.FALSE)).collect(Collectors.toList());
       List<CharacterDTO>  charactersDTO= (List<CharacterDTO>) charactersEntity.stream().map(characterEntity -> modelMapper.map(characterEntity,CharacterDTO.class)).collect(Collectors.toList());

        return charactersDTO;
    }

    public CharacterDTO getCharacter(Long id) {

        Optional<CharacterEntity> characterEntity = characterRepository.findById(id);
        if (characterEntity.isEmpty() || (!characterEntity.isEmpty() && characterEntity.get().getBorrado())) {
            throw new RuntimeException("NO SE ENCONTRO ID");
        }

        CharacterDTO characterDTO = modelMapper.map(characterEntity.get(), CharacterDTO.class);

        return characterDTO;

    }

    public CharacterDTO save(CharacterDTO characterDTO) {

        Optional<CharacterEntity> findCharacter = characterRepository.findByNombreEquals(characterDTO.getNombre());
        if (!findCharacter.isEmpty()) {
            throw new RuntimeException("PERSONAJE YA EXISTE");
        }
        CharacterEntity characterEntity = modelMapper.map(characterDTO, CharacterEntity.class);
        characterRepository.save(characterEntity);
        characterDTO.setId(characterEntity.getId());
        return characterDTO;
    }

    public CharacterDTO update(CharacterDTO characterDTO) {

        Optional<CharacterEntity> characterEntity = characterRepository.findById(characterDTO.getId());
        if (characterEntity.isEmpty() || (!characterEntity.isEmpty() && characterEntity.get().getBorrado())) {
            throw new RuntimeException("NO SE ENCONTRO ID");
        }

        characterEntity.get().setImagen(characterDTO.getImagen());
        characterEntity.get().setNombre(characterDTO.getNombre());
        characterEntity.get().setEdad(characterDTO.getEdad());
        characterEntity.get().setPeso(characterDTO.getPeso());
        characterEntity.get().setHistoria(characterDTO.getHistoria());

        characterRepository.save(characterEntity.get());

        return modelMapper.map(characterEntity, CharacterDTO.class);
    }


    public CharacterDTO delete(Long id) {
        CharacterEntity characterEntity = characterRepository.findById(id).orElseThrow(() -> new RuntimeException("PERSONAJE INEXISTENTE"));
        characterEntity.setBorrado(Boolean.TRUE);
        characterRepository.save(characterEntity);
        CharacterDTO characterDTO = modelMapper.map(characterEntity, CharacterDTO.class);
        return characterDTO;
    }


}
