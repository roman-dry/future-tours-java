package com.example.tourstothefuture.servises;

import com.example.tourstothefuture.models.Card;
import com.example.tourstothefuture.repositories.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    @Transactional(readOnly = true)
    public void save(Card card) {
        cardRepository.save(card);
    }

    @Transactional(readOnly = true)
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Card> findById(Integer id) {
        return cardRepository.findById(id);
    }
    @Transactional
    public void updateCardById(Integer id, String name, List<String> descriptions, Integer price) {
        cardRepository.updateCardById(id, name, descriptions, price);
    }
    @Transactional
    public void deleteCardById(Integer id) {
        cardRepository.deleteById(id);
    }
}
