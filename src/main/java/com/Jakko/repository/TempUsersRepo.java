package com.Jakko.repository;

import com.Jakko.model.standart.TempUser;
import com.Jakko.model.standart.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempUsersRepo extends JpaRepository<TempUser, Integer> {
    TempUser        getByChatId(long chatId);
    Integer     countUserByChatId(long chatId);
    List<TempUser> findAll();
    TempUser        findById(int chatId);
    TempUser findFirstByChatId(long chatId);
    TempUser findByPhone(String phone);
    TempUser findByChatId(long chatId);

}
