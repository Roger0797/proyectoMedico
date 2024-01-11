package com.agenda.ro.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agenda.ro.models.CentroSaludMedicoModel;

@Repository
public interface CentroSaludMedicoRepository extends CrudRepository<CentroSaludMedicoModel, Long> {

}
