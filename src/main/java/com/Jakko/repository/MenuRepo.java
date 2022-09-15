package com.Jakko.repository;

import com.Jakko.model.custom.Menu;
import com.Jakko.model.custom.Repair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepo extends CrudRepository<Menu, Integer> {

    List<Menu> findAll();
    int countByChatId(long chatId);
    Menu findByChatId(long id);
    Menu findById(int id);
}
