package com.ironhack.vbnk_antifraudservice.repositories;

import com.ironhack.vbnk_antifraudservice.model.AFTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntiFraudRepository extends JpaRepository<AFTransaction,String> {




}
