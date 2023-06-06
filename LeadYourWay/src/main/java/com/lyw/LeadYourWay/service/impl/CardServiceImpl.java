package com.lyw.LeadYourWay.service.impl;

import com.lyw.LeadYourWay.model.Card;
import com.lyw.LeadYourWay.repository.CardRepository;
import com.lyw.LeadYourWay.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card getCardById(Long card_id) {
        return cardRepository.findById(card_id).orElse(null);
    }

    @Override
    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public void deleteCard(Long card_id) {
        cardRepository.deleteById(card_id);
    }
}
