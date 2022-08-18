package com.festivals.festivals.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festivals.festivals.entities.Festival;

public interface FestivalDao extends JpaRepository<Festival, Long> {
	
}
