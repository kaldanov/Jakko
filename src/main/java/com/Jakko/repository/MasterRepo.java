package com.Jakko.repository;

import com.Jakko.model.custom.Master;
import com.Jakko.model.custom.Menu;
import com.Jakko.model.standart.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterRepo extends CrudRepository<Master, Integer> {

    Master findByPhone(String phone);

    Master findByUser(User user);

    List<Master> findAll();
}
