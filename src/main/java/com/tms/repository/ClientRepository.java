package com.tms.repository;

import com.tms.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByLogin(String login);

    @Query(value = "select * from clients where role = :role", nativeQuery = true)
    List<Client> findOnlyUsers(String role);
}
