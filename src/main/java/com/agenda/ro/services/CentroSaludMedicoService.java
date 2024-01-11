package com.agenda.ro.services;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.ro.models.CentroSaludMedicoModel;
import com.agenda.ro.models.UsuarioModel;
import com.agenda.ro.repositories.CentroSaludMedicoRepository;

@Service
public class CentroSaludMedicoService {

	
	@Autowired
	CentroSaludMedicoRepository centroSaludMedicoRepository;
	
	public ArrayList<UsuarioModel> verUsuarios(Long id){
		ArrayList<CentroSaludMedicoModel> csms= (ArrayList<CentroSaludMedicoModel> )centroSaludMedicoRepository.findAll();
		for(CentroSaludMedicoModel csm:csms ) {
			if(csm.getCentroSaludMedicoModelid()==id) {
				ArrayList<UsuarioModel> usuarioModel = (ArrayList<UsuarioModel>) csm.getUsuarioModel();
				return usuarioModel;
			}
		}
		return null;
	}
	
	
}
