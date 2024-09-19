package com.example.tourstothefuture.repositories;

import com.example.tourstothefuture.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    @Modifying
    @Query("UPDATE Card c SET c.name = :name, c.descriptions = :descriptions, c.price = :price WHERE c.id = :id")
    void updateCardById(@Param("id") Integer id,
                        @Param("name") String name,
                        @Param("descriptions") List<String> descriptions,
                        @Param("price") Integer price);
}
