package com.Jakko.repository;

import com.Jakko.model.custom.Reject;
import com.Jakko.model.custom.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RejectRepo extends CrudRepository<Reject, Integer> {
    List<Reject> findAllByTypeIsTrue();
    List<Reject> findAllByTypeIsFalse();
    Reject findById1(int id);
    Reject findById(int id);
}
