package com.lyw.LeadYourWay.repository;

import com.lyw.LeadYourWay.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    boolean existsByUserId(Long user_id);
    boolean existsByCardNumber(String card_number);
    List<Card> findByUserId(Long user_id);
}
