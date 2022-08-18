package com.festivals.festivals.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festivals.festivals.custom.exception.BusinessException;
import com.festivals.festivals.dao.FestivalDao;
import com.festivals.festivals.entities.Festival;

@Service
public class FestivalServiceImpl implements FestivalService {

	@Autowired
	private FestivalDao festivalDao;
	// List<Festival> list;

	public FestivalServiceImpl() {
//		
//		list = new ArrayList<>();
//		list.add(new Festival(1,"Ganesh Utsav","August"));
//		list.add(new Festival(2,"Diwali","October"));
	}

	@Override
	public List<Festival> getFestivals() {
		List<Festival> festivalList = null;

		try {
			festivalList = festivalDao.findAll();
		} catch (Exception e) {
			throw new BusinessException("605",
					"Something wrong happened in service layer while fetching all employees" + e.getMessage());
		}

		if (festivalList.isEmpty())
			throw new BusinessException("604", "List is completely empty");
		
		return festivalList;

	}

	@Override
	public Festival getFestival(long festivalId) {

//		Festival f = null;
//		try {
//			for(Festival festival:list) {
//				if(festival.getId() == festivalId) {
//					f = festival;
//					break;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try {
			return festivalDao.findById(festivalId).get();
		} catch (IllegalArgumentException e) {
			throw new BusinessException("606", "given festival ID is null, send other ID " + e.getMessage());
		} catch (java.util.NoSuchElementException e) {
			throw new BusinessException("607", "Given festival is not present in database " + e.getMessage());
		}
	}

	@Override
	public Festival addFestival(Festival festival) {

//		Festival f = null;
//		
//		for(Festival fEach:list) {
//			if(fEach.getId() == festival.getId()) {
//				return f;
//			} 
//		}
//		list.add(festival);	

		if (festival.getName().isEmpty() || festival.getName().length() == 0) {
			throw new BusinessException("601", "Please send proper name, It is blank");
		}
		try {
			festivalDao.save(festival);
			return festival;
		} catch (IllegalArgumentException e) {
			throw new BusinessException("602", "given festital is null " + e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("603",
					"Something went wrong in Service layer while saving the festival " + e.getMessage());
		}

	}

	@Override
	public Festival updateFestival(Festival festival) {

//		Festival f = null;
//		
//		for(Festival fEach:list) {
//			if(fEach.getId() == festival.getId()) {
//				fEach.setName(festival.getName());
//				fEach.setMonth(festival.getMonth());
//				return festival;
//				}
//		}
		festivalDao.save(festival);
		return festival;
	}

	public void deleteFestival(long parseLong) {
		// this.list.stream().filter(e->e.getId()!=parseLong).collect(Collectors.toList());
		// SuppressWarnings("deprecation")
		@SuppressWarnings("deprecation")
		Festival entity = festivalDao.getReferenceById(parseLong);
		System.out.println(entity.size());
		if(entity==new Festival()) {
			System.out.println(entity);
			throw new BusinessException("608", "given festival ID is null, send another");
		}
		try {		
			System.out.println(entity);
			festivalDao.delete(entity);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("609", "given festival ID is WRONG, send another");
		} catch (Exception e) {
			throw new BusinessException("610", "Something went wrong in Service layer while deleting the festival");
		}
	}
}
