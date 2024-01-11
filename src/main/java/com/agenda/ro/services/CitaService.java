package com.agenda.ro.services;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.ro.models.CitaModel;
import com.agenda.ro.models.TimeslotModel;
import com.agenda.ro.models.UsuarioModel;
import com.agenda.ro.repositories.CentroSaludMedicoRepository;
import com.agenda.ro.repositories.CitaRepository;
import com.agenda.ro.repositories.UsuarioRepository;

import Config.CompararCitas;

@Service
public class CitaService {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	CentroSaludMedicoRepository centroSaludMedicoRepository;
	@Autowired
	CitaRepository citaRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	

	public ArrayList<CitaModel> verCitasUsuario(Long idUsuario){
		ArrayList<CitaModel> citas= (ArrayList<CitaModel>) citaRepository.findAll();
		ArrayList<CitaModel> citasAux= new ArrayList<CitaModel>();
		for(CitaModel cita:citas) {
			if(cita.getUsuarioModel().getUserId()==idUsuario) {
				citasAux.add(cita);
			}
		}
		java.util.Collections.sort(citasAux, new CompararCitas());

		return citasAux;
	}
	
	public ArrayList<CitaModel> verCitasPasadaUsuario(Long idUsuario){
		ArrayList<CitaModel> citas= (ArrayList<CitaModel>) citaRepository.findAll();
		ArrayList<CitaModel> citasAux=new ArrayList<CitaModel>();
		Date date=new Date(System.currentTimeMillis());
		
		for(CitaModel cita:citas) {
			if(cita.getFecha().before(date)) {
				citasAux.add(cita);
			}
		}
		java.util.Collections.sort(citasAux, new CompararCitas());
		return citasAux;
	}
	
	public ArrayList<CitaModel> verCitasProximasUsuario(Long idUsuario){
		ArrayList<CitaModel> citas= verCitasUsuario(idUsuario);
		ArrayList<CitaModel> citasAux=new ArrayList<CitaModel>();
		Date date=new Date(System.currentTimeMillis());
		for(CitaModel cita:citas) {
			if(cita.getFecha().after(date)) {
				citasAux.add(cita);
			}
		}
		return citasAux;
	}
	
	
	public ArrayList<CitaModel> verCitasMedico(Long idMedico){
		/*
		 * ArrayList<CentroSaludMedicoModel> csms=(ArrayList<CentroSaludMedicoModel>)
		 * centroSaludMedicoRepository.findAll(); CentroSaludMedicoModel medicoCS= new
		 * CentroSaludMedicoModel(); for(CentroSaludMedicoModel csm:csms) {
		 * if(csm.getMedicoModel().getUserId()==idMedico) { medicoCS=csm; } }
		 * 
		 * @SuppressWarnings("unchecked") ArrayList<UsuarioModel> users=
		 * (ArrayList<UsuarioModel>) medicoCS.getUsuarioModel(); ArrayList<CitaModel>
		 * citas= new ArrayList<CitaModel>(); for(UsuarioModel user:users) {
		 * citas.addAll(user.getCitaModel()); }
		 */
		
		ArrayList<UsuarioModel> users = (ArrayList<UsuarioModel>) usuarioRepository.findAll();
		ArrayList<UsuarioModel> userAux = new ArrayList<UsuarioModel>();
		ArrayList<CitaModel> citas= new ArrayList<CitaModel>();
		for(UsuarioModel u:users) {
			if((usuarioService.verMedicoAsignado(u.getUserId())).getUserId()==idMedico){
				userAux.add(u);
			}
		}
		
		for(UsuarioModel u2:userAux) {
			citas.addAll(verCitasUsuario(u2.getUserId()));
		}
		
		java.util.Collections.sort(citas, new CompararCitas());
		return citas;
		
	}
	
	@SuppressWarnings("deprecation")
	public ArrayList<TimeslotModel> verTimeslotLibresMedico(Long id, Date date){
		ArrayList<CitaModel> citas = usuarioService.verDisponibilidadMedico(id, date);
		ArrayList<TimeslotModel> timeslots = new ArrayList<TimeslotModel>();
		
		
		
		for(CitaModel cita:citas) {
			System.out.println(cita.getFecha());
			System.out.println(date);
			if(cita.getFecha().getDate()== date.getDate() && cita.getFecha().getMonth()==date.getMonth() && cita.getFecha().getYear()==date.getYear()) {
				
				
				if(!(timeslots.contains(cita.getTimeslot()))) {
					timeslots.add(cita.getTimeslot());
				}
		}
		
		
	}
		return timeslots;
}
	
	
	
}
