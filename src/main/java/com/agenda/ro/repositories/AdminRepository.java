package com.agenda.ro.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agenda.ro.models.AdminModel;

@Repository
public interface AdminRepository extends CrudRepository<AdminModel, Long>{

}
