package org.continuouspoker.player.logic;

import org.continuouspoker.player.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class Strategy {

    Map<Rank, Integer> rankMap = new HashMap<>();
    Map<Suit, Integer> rankSuit = new HashMap<>();

    public Bet decide(final Table table) {
        System.out.println(table);

        List<Card> communityCards = table.getCommunityCards();
        Player me = table.getPlayers().get(table.getActivePlayer());

        List<Card> allCards = communityCards;
        allCards.addAll(me.getCards());

        HashMap<Suit, Integer> suitIntegerHashMap = countSuits(allCards);
        HashMap<Rank, Integer> rankIntegerHashMap = countRanks(allCards);

        List<Integer> top2List = rankIntegerHashMap.values().stream().sorted(Comparator.reverseOrder()).limit(2).collect(Collectors.toList());

        int bet = 0;
        Integer top1 = top2List.get(0);
        Integer top2 = top2List.get(1);

        if (top1 >= 2) { // pair/tripple/quad
            bet = top1 * 10;
        }
        if (isFullHouse(top1, top2)) { // FUll house
            bet = me.getStack();
        }
        if (isStraight(allCards.stream().map(card -> card.getRank()).collect(Collectors.toSet()))) {
            bet = me.getStack() / 2;
        }

        return new Bet().bet(bet);
    }


    static Map<Rank, Integer> cardOrderMap = new HashMap<>();

    static {
        cardOrderMap.put(Rank._2, 2);
        cardOrderMap.put(Rank._3, 3);
        cardOrderMap.put(Rank._4, 4);
        cardOrderMap.put(Rank._5, 5);
        cardOrderMap.put(Rank._6, 6);
        cardOrderMap.put(Rank._7, 7);
        cardOrderMap.put(Rank._8, 8);
        cardOrderMap.put(Rank._9, 9);
        cardOrderMap.put(Rank._10, 10);
        cardOrderMap.put(Rank.J, 11);
        cardOrderMap.put(Rank.Q, 12);
        cardOrderMap.put(Rank.K, 13);
        cardOrderMap.put(Rank.A, 14);
    }

    public boolean isStraight(Set<Rank> collect) {

        List<Integer> collect1 = collect.stream().map(rank -> cardOrderMap.get(rank)).sorted().collect(Collectors.toList());

        int followingCount = 0;
        int lastRank = -1;
        for (int i : collect1) {
            if (followingCount == 5) return true;


            if (i != lastRank + 1) {
                followingCount = 1;
            } else {
                followingCount++;
            }
            lastRank = i;
        }

        return followingCount == 5;
    }

    private static boolean isFullHouse(Integer top1, Integer top2) {
        return top1 == 3 && top2 == 2;
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
