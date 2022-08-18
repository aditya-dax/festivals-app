package com.festivals.festivals.services;

import java.util.List;

import com.festivals.festivals.entities.Festival;

public interface FestivalService {
	
	public List<Festival> getFestivals();
	
	public Festival getFestival(long courseId);
	
	public Festival addFestival(Festival festival);

	public Festival updateFestival(Festival festival);

	public void deleteFestival(long parseLong);

}
