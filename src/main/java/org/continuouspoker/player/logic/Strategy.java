package org.continuouspoker.player.logic;

import org.continuouspoker.player.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Strategy {

    Map<Rank, Integer> rankMap = new HashMap<>();
    Map<Suit, Integer> rankSuit = new HashMap<>();

    public Bet decide(final Table table) {
        System.out.println(table);

        List<Card> communityCards = table.getCommunityCards();
        Player me = table.getPlayers().get(table.getActivePlayer());

        List<Card> cards = communityCards;
        cards.addAll(me.getCards());

        HashMap<Rank, Integer> rankIntegerHashMap = countRanks(cards);

        HashMap<Suit, Integer> suitIntegerHashMap = countSuits(cards);

        int highestRankCount = 0;
        for (Rank rank : rankIntegerHashMap.keySet()) {
            Integer rankCount = rankIntegerHashMap.get(rank);
            if (rankCount > highestRankCount) highestRankCount = rankCount;
        }
        
        int bet = 0;
        if (highestRankCount >= 2) {
            bet = highestRankCount * 10;
        }

        return new Bet().bet(bet);
    }

    public HashMap<Rank, Integer> countRanks(List<Card> cards) {
        HashMap<Rank, Integer> rankIntegerHashMap = new HashMap<>();

        for (Card card : cards) {
            Integer rankCount = rankIntegerHashMap.get(card.getRank());
            if (rankCount == null) {
                rankIntegerHashMap.put(card.getRank(), 1);
            } else {
                rankIntegerHashMap.put(card.getRank(), rankCount + 1);
            }
        }

        return rankIntegerHashMap;
    }

    public HashMap<Suit, Integer> countSuits(List<Card> cards) {
        HashMap<Suit, Integer> rankIntegerHashMap = new HashMap<>();

        for (Card card : cards) {
            Integer rankCount = rankIntegerHashMap.get(card.getSuit());
            if (rankCount == null) {
                rankIntegerHashMap.put(card.getSuit(), 1);
            } else {
                rankIntegerHashMap.put(card.getSuit(), rankCount + 1);
            }
        }

        return rankIntegerHashMap;
    }
}
