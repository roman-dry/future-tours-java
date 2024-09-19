package com.example.tourstothefuture.controllers;

import com.example.tourstothefuture.models.Card;
import com.example.tourstothefuture.requests.CardRequest;
import com.example.tourstothefuture.servises.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    /*@PostMapping
    public ResponseEntity<String> save(@RequestBody CardRequest cardRequest) {
        Card card = new Card(cardRequest.getName(), cardRequest.getDescription(), cardRequest.getPrice());
        cardService.save(card);
        return ResponseEntity.ok("Card is added successfully");
    }*/

    @PostMapping
    public ResponseEntity<String> save(@RequestBody CardRequest cardRequest) {
        try {
            Card card = new Card(cardRequest.getName(), cardRequest.getDescriptions(), cardRequest.getPrice());
            cardService.save(card);
            return ResponseEntity.ok("Card is added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving the card");
        }
    }

    /*@GetMapping
    public List<Card> findAll() {
        return cardService.findAll();
    }*/

    @GetMapping
    public ResponseEntity<?>findAll() {
        try {
            List<Card> cards = cardService.findAll();
            if (cards.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No cards found");
            } else {
                return ResponseEntity.ok(cards);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }

    }



    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Optional<Card> card = cardService.findById(id);
            if (card.isPresent()) {
                return ResponseEntity.ok(card.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    /*@GetMapping("/id")
    public Optional<Card> findById(@PathVariable Integer id) {
        return cardService.findById(id);
    }*/

    /*@PatchMapping
    public void updateCard(@RequestParam Integer id,
                           @RequestParam String name,
                           @RequestParam List<String> descriptions,
                           @RequestParam Integer price) {
        cardService.updateCardById(id, name, descriptions, price);
    }*/

    @PatchMapping
    public ResponseEntity<String> updateCard(@RequestParam Integer id,
                                             @RequestParam String name,
                                             @RequestParam List<String> descriptions,
                                             @RequestParam Integer price) {
        try {
            cardService.updateCardById(id, name, descriptions, price);
            return ResponseEntity.ok("Card updated successfully");
        } catch (Exception e) {
            // Логування помилки (необов'язково)
            e.printStackTrace();
            // Повертаємо відповідь з кодом 500 та повідомленням про помилку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the card");
        }
    }


    /*@DeleteMapping("/{id}")
    public void deleteCardById(@PathVariable Integer id) {
        cardService.deleteCardById(id);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCardById(@PathVariable Integer id) {
        try {
            cardService.deleteCardById(id);
            return ResponseEntity.ok("Card deleted successfully");
        } catch (Exception e) {
            // Логування помилки (необов'язково)
            e.printStackTrace();
            // Повертаємо відповідь з кодом 500 та повідомленням про помилку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the card");
        }
    }


}
