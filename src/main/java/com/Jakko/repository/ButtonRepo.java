package com.Jakko.repository;

import com.Jakko.model.standart.Button;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ButtonRepo extends CrudRepository<Button, Integer> {
    Button findByName(String buttonName);
    int countByName(String name);
    boolean existsButtonByName(String text);
    Button   findById(int id);

    List<Button> findAllByNameContainingOrderById(String text);

    @Transactional
    @Modifying
    @Query("update Button set name = ?1 where id = ?2")
    void update(String name, int id);
}
