package core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import jriot.main.JRiot;
import jriot.objects.Game;
import jriot.objects.Player;

import com.google.common.collect.ImmutableMap;

public class Blitz {

  private static final String APIKEY = "c705cff4-de8c-4454-848f-99c2888eac1e";

  private static final String NOXUS = "Noxus";

  private static final String DEMACIA = "Demacia";

  private static final String BALANCE = "Balance";

  private static final String CHAOS = "Chaos";

  private static final Map<String, String> FACTION_MAP = new ImmutableMap.Builder<String, String>()
      .put("Aatrox", CHAOS)
      .put("Ahri", CHAOS)
      .put("Akali", BALANCE)
      .put("Alistar", DEMACIA)
      .put("Amumu", BALANCE)
      .put("Anivia", BALANCE)
      .put("Annie", CHAOS)
      .put("Ashe", BALANCE)
      .put("Blitzcrank", DEMACIA)
      .put("Brand", CHAOS)
      .put("Caitlyn", DEMACIA)
      .put("Cassiopeia", NOXUS)
      .put("Chogath", CHAOS)
      .put("Corki", DEMACIA)
      .put("Darius", NOXUS)
      .put("Diana", CHAOS)
      .put("Draven", NOXUS)
      .put("DrMundo", NOXUS)
      .put("Elise", CHAOS)
      .put("Evelynn", CHAOS)
      .put("Ezreal", CHAOS)
      .put("FiddleSticks", CHAOS)
      .put("Fiora", DEMACIA)
      .put("Fizz", CHAOS)
      .put("Galio", DEMACIA)
      .put("Gangplank", CHAOS)
      .put("Garen", DEMACIA)
      .put("Gragas", CHAOS)
      .put("Graves", CHAOS)
      .put("Hecarim", CHAOS)
      .put("Heimerdinger", BALANCE)
      .put("Irelia", BALANCE)
      .put("Janna", DEMACIA)
      .put("JarvanIV", DEMACIA)
      .put("Jax", BALANCE)
      .put("Jayce", DEMACIA)
      .put("Jinx", CHAOS)
      .put("Karma", BALANCE)
      .put("Karthus", CHAOS)
      .put("Kassadin", CHAOS)
      .put("Katarina", NOXUS)
      .put("Kayle", BALANCE)
      .put("Kennen", BALANCE)
      .put("Khazix", CHAOS)
      .put("KogMaw", CHAOS)
      .put("Leblanc", NOXUS)
      .put("LeeSin", BALANCE)
      .put("Leona", BALANCE)
      .put("Lissandra", CHAOS)
      .put("Lucian", BALANCE)
      .put("Lulu", CHAOS)
      .put("Lux", DEMACIA)
      .put("Malphite", BALANCE)
      .put("Malzahar", CHAOS)
      .put("Maokai", CHAOS)
      .put("MasterYi", BALANCE)
      .put("MissFortune", BALANCE)
      .put("MonkeyKing", BALANCE)
      .put("Mordekaiser", CHAOS)
      .put("Morgana", CHAOS)
      .put("Nami", BALANCE)
      .put("Nasus", BALANCE)
      .put("Nautilus", CHAOS)
      .put("Nidalee", BALANCE)
      .put("Nocturne", CHAOS)
      .put("Nunu", BALANCE)
      .put("Olaf", BALANCE)
      .put("Orianna", DEMACIA)
      .put("Pantheon", CHAOS)
      .put("Poppy", DEMACIA)
      .put("Quinn", DEMACIA)
      .put("Rammus", BALANCE)
      .put("Renekton", CHAOS)
      .put("Rengar", CHAOS)
      .put("Riven", CHAOS)
      .put("Rumble", CHAOS)
      .put("Ryze", BALANCE)
      .put("Sejuani", BALANCE)
      .put("Shaco", CHAOS)
      .put("Shen", BALANCE)
      .put("Shyvana", DEMACIA)
      .put("Singed", NOXUS)
      .put("Sivir", CHAOS)
      .put("Sion", NOXUS)
      .put("Skarner", BALANCE)
      .put("Sona", BALANCE)
      .put("Soraka", BALANCE)
      .put("Swain", NOXUS)
      .put("Syndra", CHAOS)
      .put("Talon", NOXUS)
      .put("Taric", BALANCE)
      .put("Teemo", BALANCE)
      .put("Thresh", CHAOS)
      .put("Tristana", BALANCE)
      .put("Trundle", CHAOS)
      .put("Tryndamere", CHAOS)
      .put("TwistedFate", CHAOS)
      .put("Twitch", NOXUS)
      .put("Udyr", BALANCE)
      .put("Urgot", NOXUS)
      .put("Varus", DEMACIA)
      .put("Vayne", DEMACIA)
      .put("Veigar", CHAOS)
      .put("VelKoz", CHAOS)
      .put("Vi", DEMACIA)
      .put("Viktor", NOXUS)
      .put("Vladimir", NOXUS)
      .put("Volibear", BALANCE)
      .put("Warwick", NOXUS)
      .put("Xerath", CHAOS)
      .put("XinZhao", DEMACIA)
      .put("Yasuo", BALANCE)
      .put("Yorick", BALANCE)
      .put("Zac", DEMACIA)
      .put("Zed", CHAOS)
      .put("Ziggs", DEMACIA)
      .put("Zilean", BALANCE)
      .put("Zyra", CHAOS)
      .build();

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
