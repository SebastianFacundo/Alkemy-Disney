package com.alkemy.challenge.repository;

import com.alkemy.challenge.entity.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity,Long> {

}
