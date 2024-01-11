package com.agenda.ro.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agenda.ro.models.HistorialMedicoModel;

@Repository
public interface HistorialMedicoRepository extends CrudRepository<HistorialMedicoModel, Long>{

}
