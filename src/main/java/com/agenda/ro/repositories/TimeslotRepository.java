package com.agenda.ro.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agenda.ro.models.TimeslotModel;

@Repository
public interface TimeslotRepository extends CrudRepository<TimeslotModel, Long> { 

}
