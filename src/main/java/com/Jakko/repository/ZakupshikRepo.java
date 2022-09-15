package com.Jakko.repository;

import com.Jakko.model.custom.Menu;
import com.Jakko.model.custom.Zakupshik;
import com.Jakko.model.standart.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZakupshikRepo extends CrudRepository<Zakupshik, Integer> {

    List<Zakupshik> findAll();
    boolean existsByUser(User user);
    Zakupshik findById(int id);
    Zakupshik findByUser(User user);
}
