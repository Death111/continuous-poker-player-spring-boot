package org.continuouspoker.player.logic;

import org.continuouspoker.player.model.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;

@Disabled
class StrategyTest {

    Strategy strategy = new Strategy();

    @Test
    void decide() {
        // ARRANGE
        Table table = new Table();

        // ACT
        Bet bet = strategy.decide(table);

        // ASSERT7
        assertEquals(bet.getBet(), 0);
    }

    @Test
    void testCountRanks() {
        List<Card> cards = Arrays.asList(
                cardFrom(Rank._2, Suit.CLUBS),
                cardFrom(Rank._2, Suit.CLUBS)
        );

        HashMap<Rank, Integer> countedRanks = strategy.countRanks(cards);

        assertEquals(2, countedRanks.get(Rank._2));
    }

    @Test
    void testCountSuites() {
        List<Card> cards = Arrays.asList(
                cardFrom(Rank._2, Suit.CLUBS),
                cardFrom(Rank._2, Suit.CLUBS)
        );

        HashMap<Suit, Integer> countedRanks = strategy.countSuits(cards);

        assertEquals(2, countedRanks.get(Suit.CLUBS));
    }

    @Test
    void asd() {
        List<Integer> a = Arrays.asList(4,2,1,99);

        List<Integer> asd = a.stream().sorted(Comparator.reverseOrder()).limit(2).collect(Collectors.toList());
        System.out.println(asd);
    }

    private Card cardFrom(Rank rank, Suit suit){
        Card card = new Card();
        card.rank(rank);
        card.suit(suit);
        return card;
    }
}