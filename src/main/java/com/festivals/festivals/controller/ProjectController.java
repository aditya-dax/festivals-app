package com.festivals.festivals.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.festivals.festivals.custom.exception.BusinessException;
import com.festivals.festivals.custom.exception.ControllerException;
import com.festivals.festivals.entities.Festival;
import com.festivals.festivals.services.FestivalService;

@RestController
public class ProjectController {

	@Autowired
	private FestivalService festivalService;

	@GetMapping("/home")
	public String home() {
		return "This is Festivals <-> month storage!";
	}

	@GetMapping("/festivals")
	public ResponseEntity<List<Festival>> getFestivals() {

		try {
		List<Festival> list = festivalService.getFestivals();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.of(Optional.of(list));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	
	
	@GetMapping("/festivals/{festivalId}")
	public ResponseEntity<?> getFestival(@PathVariable("festivalId") String festivalId) {
		
		try {
			Festival festival = festivalService.getFestival(Long.parseLong(festivalId));
			return new ResponseEntity<Festival>(festival, HttpStatus.OK);
		} catch (BusinessException e ) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("612","Something went wrong in controller exception get function");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	
	}
	

	@PostMapping("/festivals")
	public ResponseEntity<?> addFestival(@Valid @RequestBody Festival festival) {

		try {
			Festival festivalSaved = festivalService.addFestival(festival);
			return new ResponseEntity<Festival>(festivalSaved,HttpStatus.CREATED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("611", "Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/festivals")
	public ResponseEntity<Festival> updateFestival(@Valid @RequestBody Festival festival) {

		Festival f = null;

		try {

			List<Festival> list = festivalService.getFestivals();
			if (list.size() <= 0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

			f = this.festivalService.updateFestival(festival);
			if (f == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

			return ResponseEntity.ok().body(f);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/festivals/{festivalId}")
	public ResponseEntity<?> deleteFestival(@PathVariable String festivalId) {
//		try {
//
//			List<Festival> list = festivalService.getFestivals();
//			if (list.size() <= 0) {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//			}
		try {
			this.festivalService.deleteFestival(Long.parseLong(festivalId));
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (BusinessException e ) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("612","Something went wrong in controller exception delete function");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}

//			this.festivalService.deleteFestival(Long.parseLong(festivalId));
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}
}
