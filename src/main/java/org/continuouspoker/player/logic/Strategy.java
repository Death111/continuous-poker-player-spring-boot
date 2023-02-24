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


      return new Bet().bet(table.getMinimumBet());
   }

   public int countPairs(List<Card> cards){
     return 0;
   }

}
