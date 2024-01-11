package com.agenda.ro.services;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.ro.models.TimeslotModel;
import com.agenda.ro.repositories.TimeslotRepository;

@Service
public class TimeslotService {
	
	@Autowired
	TimeslotRepository timeslotRepository;
	@Autowired
	CitaService citaService;
	
	public ArrayList<TimeslotModel> verTimeslotLibres(Long id, Date date){
		ArrayList<TimeslotModel> timeslots = (ArrayList<TimeslotModel>) timeslotRepository.findAll();
		ArrayList<TimeslotModel> timeslotCitas = citaService.verTimeslotLibresMedico(id, date);
		
		timeslots.removeAll(timeslotCitas);
		return timeslots;
	}
}
