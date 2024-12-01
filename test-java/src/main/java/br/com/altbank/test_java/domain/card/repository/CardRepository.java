package br.com.altbank.test_java.domain.card.repository;

import br.com.altbank.test_java.domain.card.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

}
