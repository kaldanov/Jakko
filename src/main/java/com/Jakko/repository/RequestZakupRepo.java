package com.Jakko.repository;

import com.Jakko.model.custom.Repair;
import com.Jakko.model.custom.RequestZakup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestZakupRepo extends CrudRepository<RequestZakup, Integer> {
    List<RequestZakup> findAllByExecutedIsFalse();

    List<RequestZakup> findAllByExecutedIsTrue();
    List<RequestZakup> findAll();

    RequestZakup findById(int id);
}
