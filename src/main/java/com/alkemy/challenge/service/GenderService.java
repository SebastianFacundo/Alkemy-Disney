package com.alkemy.challenge.service;

import com.alkemy.challenge.dto.GenderDTO;
import com.alkemy.challenge.entity.GenderEntity;
import com.alkemy.challenge.repository.GenderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderService {

    @Autowired
    GenderRepository genderRepository;

    public GenderDTO save (GenderDTO dto){
/*
        GenderEntity genderEntity =modelMapper.map(dto, GenderEntity.class);
       dto=modelMapper.map(genderRepository.save(genderEntity), GenderDTO.class);
       */
        return dto;
    }
}
