package core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import jriot.main.JRiot;
import jriot.objects.Game;
import jriot.objects.Player;

public class Blitz {

  private static final String APIKEY = "c705cff4-de8c-4454-848f-99c2888eac1e";

  public static void main(final String[] args) {

    final JRiot lol = new JRiot();
    lol.setApiKey(APIKEY);
    lol.setRegion("na");

    try {
      final PrintWriter out =
          new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\justin.chan\\Documents\\Testing.txt", true)));
//      final Iterator<Champion> itr = lol.getChampions().getChampions().iterator();
//      while (itr.hasNext()) {
//        final Champion champ = itr.next();
//        out.println(".put("+champ.getId()+", \""+champ.getName()+"\")");
//
//
//      }

      final long id = lol.getSummoner("Bananagram").getId();
      final ArrayList<Game> games = lol.getRecentGames(id).getGames();
      final long champId = 0;
      final Iterator gameItr = games.iterator();
      ArrayList<Player> players = null;
      final Player curPlayer = null;
      while (gameItr.hasNext()) {
        final Game curGame = (Game) gameItr.next();
        players = curGame.getFellowPlayers();
        final Iterator<Player> playerItr = players.iterator();
        out.println("played: "+ChampReference.getChampById(curGame.getChampionId()));
        out.println("Other champs were: ");
        while (playerItr.hasNext()) {
          out.println(ChampReference.getChampById(playerItr.next().getChampionId()));
        }
      }
      out.close();
    }
    catch (final IOException e) {
      e.printStackTrace();
    }

  }
}
