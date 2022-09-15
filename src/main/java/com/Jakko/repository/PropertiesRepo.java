package com.Jakko.repository;

import com.Jakko.model.standart.Properties;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepo extends CrudRepository<Properties, Integer> {
Properties findFirstById(int id);
}
