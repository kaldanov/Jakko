package com.Jakko.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.Jakko.model.standart.Admin;

import java.util.List;

@Repository
public interface AdminRepo extends CrudRepository<Admin, Integer> {
    int countByChatId(long chatId);
    List<Admin> findAll();
    Admin findById(int id);

    Admin findByChatId(long id);
}
