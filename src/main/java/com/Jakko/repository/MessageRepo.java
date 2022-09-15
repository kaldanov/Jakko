package com.Jakko.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Jakko.model.standart.Message;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepo extends CrudRepository<Message, Integer> {

    Message findFirstById(int id);

    List<Message> findAllByNameContainingOrderById(String text);

    Message findById(int id);

    @Query("select m.name from Message m where m.id = :id")
    String getMessageText(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("update Message set name = ?1 where id = ?2")
    void update(String name, int id);

    @Query("select m.name from Message m WHERE m.id =?1")
    Optional<String> getName(int id);
}
