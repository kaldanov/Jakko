package com.Jakko.repository;

import com.Jakko.model.standart.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<User, Integer> {
    User        getByChatId(long chatId);
    Integer     countUserByChatId(long chatId);
    List<User> findAll();
    User findFirstByChatId(long chatId);
    User findByPhone(String phone);
    User findByChatId(long chatId);

}
