package org.continuouspoker.player.logic;

import org.continuouspoker.player.model.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Disabled
class StrategyTest {

    Strategy strategy = new Strategy();

    @Test
    void decide() {
        // ARRANGE
        Table table = new Table();
        Player player = new Player();
        player.setCards(Arrays.asList(cardFrom(Rank.A), cardFrom(Rank._5)));

        table.setPlayers(Arrays.asList(player));
        table.setActivePlayer(0);
        table.setRound(0);
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
        List<Integer> a = Arrays.asList(4, 2, 1, 99);

        List<Integer> asd = a.stream().sorted(Comparator.reverseOrder()).limit(2).collect(Collectors.toList());
        System.out.println(asd);
    }

    @Test
    void straight() {
        Set<Rank> cards = new HashSet<>();
        cards.add(Rank._2);
        cards.add(Rank._3);
        cards.add(Rank._4);
        cards.add(Rank._5);
        cards.add(Rank._6);

        boolean isStraight = strategy.isStraight(cards);

        assertEquals(true, isStraight);
    }


    private Card cardFrom(Rank rank) {
        Card card = new Card();
        card.rank(rank);
        card.suit(Suit.CLUBS);
        return card;
    }

    private Card cardFrom(Rank rank, Suit suit) {
        Card card = new Card();
        card.rank(rank);
        card.suit(suit);
        return card;
    }
}