package org.continuouspoker.player.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.continuouspoker.player.model.Bet;
import org.continuouspoker.player.model.Table;

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
}