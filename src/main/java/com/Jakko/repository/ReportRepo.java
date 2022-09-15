package com.Jakko.repository;

import com.Jakko.model.custom.Report;
import com.Jakko.model.standart.Button;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReportRepo extends CrudRepository<Report, Integer> {
    List<Report> findAllByEquipmentName(String name);

    List<Report> findAllByEquipmentNameOrderByRegDateDesc(String name);
    Report findById(int id);

    List<Report> findAll();
}
