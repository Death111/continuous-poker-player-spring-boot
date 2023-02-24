package org.continuouspoker.player.logic;

import org.continuouspoker.player.model.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Strategy {

    Map<Rank, Integer> rankMap = new HashMap<>();
    Map<Suit, Integer> rankSuit = new HashMap<>();

    public Bet decide(final Table table) {
        System.out.println(table);

        List<Card> communityCards = table.getCommunityCards();
        Player me = table.getPlayers().get(table.getActivePlayer());

        List<Card> cards = communityCards;
        cards.addAll(me.getCards());

        HashMap<Suit, Integer> suitIntegerHashMap = countSuits(cards);
        HashMap<Rank, Integer> rankIntegerHashMap = countRanks(cards);

        List<Integer> top2List = rankIntegerHashMap.values().stream().sorted(Comparator.reverseOrder()).limit(2).collect(Collectors.toList());

        int bet = 0;
        Integer top1 = top2List.get(0);
        Integer top2 = top2List.get(1);

        if (top1 >= 2) {
            bet = top1 * 10;
        }
        if (top1 == 3 && top2 == 2) { // FUll house
            bet = me.getStack();
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
