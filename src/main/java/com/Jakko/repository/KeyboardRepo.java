package com.Jakko.repository;

import com.Jakko.model.standart.Keyboard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyboardRepo extends CrudRepository<Keyboard, Integer> {


    @Query("select k.inline from Keyboard k where k.id = ?1")
    boolean isInline(int keyboardId);

    @Query("select k.buttonIds from Keyboard  k where k.id = ?1")
    String getButtonString(int id);
}
