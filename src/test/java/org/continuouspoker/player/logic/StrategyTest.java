package org.continuouspoker.player.logic;

import org.continuouspoker.player.model.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

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
    void decide2() {
        // ARRANGE
        Table table = new Table();


        // ACT
        Bet bet = strategy.decide(table);

        // ASSERT7
        assertEquals(bet.getBet(), 0);
    }

    @Test
    void testCountPairs() {
        List<Card> cards = Arrays.asList(
                cardFrom(Rank._2, Suit.CLUBS),
                cardFrom(Rank._2, Suit.CLUBS)
        );

        int pairs = strategy.countPairs(cards);

        assertEquals(pairs, 1);
    }


    private Card cardFrom(Rank rank, Suit suit){
        Card card = new Card();
        card.rank(rank);
        card.suit(suit);
        return card;
    }
}