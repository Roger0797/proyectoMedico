package com.agenda.ro.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agenda.ro.models.*;

@Repository
public interface  CentroSaludRepository extends CrudRepository<CentroSaludModel, Long> {
}