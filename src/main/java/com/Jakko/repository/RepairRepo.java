package com.Jakko.repository;

import com.Jakko.model.custom.Repair;
import com.Jakko.model.custom.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepo extends CrudRepository<Repair, Integer> {
    List<Repair> findAllByExecutedIsFalse();

    List<Repair> findAllByExecutedIsTrue();

    Repair findById(int id);
}
