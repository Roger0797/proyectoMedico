package com.agenda.ro.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.ro.models.TimeslotModel;
import com.agenda.ro.repositories.TimeslotRepository;
import com.agenda.ro.services.TimeslotService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/timeslot")
public class TimeslotController {

	@Autowired
	TimeslotService timService;
	@Autowired
	TimeslotRepository timeslotRepository;
	
	@GetMapping("/listar")
	public ArrayList<TimeslotModel> obtenerTimeslot(){
		return (ArrayList<TimeslotModel>) timeslotRepository.findAll();
	}
	
	@GetMapping("/buscar/{id}")
	public Optional<TimeslotModel> buscaTimeslotPorID(@PathVariable("id") Long id) {
		return timeslotRepository.findById(id);
	}
	
	@PostMapping("/crear")
	public TimeslotModel registraTimeslot(@RequestBody TimeslotModel timeslot) {
		return timeslotRepository.save(timeslot);
	}
	
	@GetMapping("/disponibilidadMedico/{id}/{fecha}")	
	public ResponseEntity<ArrayList<TimeslotModel>> verDispobilidadMedicoUsuario(@PathVariable ("id") Long id, @PathVariable ("fecha") Date date){
		ArrayList<TimeslotModel> timeslots= timService.verTimeslotLibres(id, date);
		return ResponseEntity.ok(timeslots);
	}
}
