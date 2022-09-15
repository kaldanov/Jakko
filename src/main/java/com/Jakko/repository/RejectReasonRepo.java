package com.Jakko.repository;

import com.Jakko.model.custom.Reject;
import com.Jakko.model.custom.RejectReason;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RejectReasonRepo extends CrudRepository<RejectReason, Integer> {

    RejectReason findById(int id);

    List<RejectReason> findAll();

    void deleteAll();

}
