package core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import jriot.main.JRiot;
import jriot.objects.Game;
import jriot.objects.RawStats;


public class BattleReporter {

  JRiot lol;
  File file;

  /**
   * Creates the report itself
   * @param summonerName - name of the summoner
   * @param apiKey - API key to be used
   * @param region - region summoner is in
   */
  protected void createReport(final String summonerName, final String apiKey, final String region) {
    final long id = lol.getSummoner(summonerName).getId();
    configJRiot(apiKey, region);
    prepareFile(summonerName);

    // Get the games first
    final ArrayList<Game> games = getGames(id);

    // Process the games one by one
    final Iterator<Game> itr = games.iterator();
    while (itr.hasNext()) {
      processGame(itr.next());
    }

  }

  /**
   * Processes the stats from a specific game
   * @param game - the game to look at
   */
  private void processGame(final Game game) {
    // Process the non-champ specific stats first
    processGenericStats(game.getStats());

  }

  private void processGenericStats(final RawStats stats) {
    final double KDA = (stats.getChampionsKilled() + stats.getAssists())/stats.getNumDeaths();
    final double mapDamage = (stats.getTotalDamageDealt() - stats.getTotalDamageTaken()) / 100;
    final double GPM = (stats.getGoldEarned() / stats.getTimePlayed()) * 60;
    final double CCTime = ((stats.getTotalTimeCrowdControlDealt() / stats.getTimePlayed()) * 60) + 1;

  }

  /**
   * Creates the file to be used for the BattleReport
   * @param summonerName - the summonerName being queried
   */
  private void prepareFile(final String summonerName) {
    file = new File("Battle Report - " + summonerName + ", " + Calendar.getInstance().getTime());
    try {
      file.createNewFile();
    }
    catch (final IOException e) {
      System.err.println("Error creating file.");
      e.printStackTrace();
    }
  }

  /**
   * Configures the JRiot object
   * @param apiKey - API key to be used
   * @param region - region the summoner's in
   */
  private void configJRiot(final String apiKey, final String region) {
    lol.setApiKey(apiKey);
    lol.setRegion(region);
  }

  /**
   * Gets recently played games from summoner based on the id
   * @param id of summoner
   * @return ArrayList of the games
   */
  private ArrayList<Game> getGames(final long id) {
    return lol.getRecentGames(id).getGames();
  }

  private void printOut(final String msg) {
    try {
      final PrintWriter out =
          new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
      System.out.println(msg);
    }
    catch (final IOException e) {
      e.printStackTrace();
    }
  }

}
