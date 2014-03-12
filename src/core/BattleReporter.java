package core;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import jriot.main.JRiot;
import jriot.objects.Game;
import jriot.objects.Player;
import jriot.objects.RawStats;


public class BattleReporter {

  private static final String DIVIDER = "---------------------------------------------------";

  private JRiot lol;
  private String summonerName;
  private String fileName;

  public BattleReporter() {
    super();
  }

  /**
   * Constructor for the BattleReport
   * @param summonerName - name of the summoner
   * @param apiKey - API key to be used
   * @param region - region summoner is in
   */
  public BattleReporter(final String summonerName, final String apiKey, final String region) {
    this.summonerName = summonerName;
    lol = new JRiot();
    configJRiot(apiKey, region);
    prepareFile(summonerName);
  }

  /**
   * Creates the report itself
   * @param summonerName - name of the summoner
   */
  protected void createReport() {
    final long id = lol.getSummoner(summonerName).getId();

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
    processGenericStats(game);
    // Process bonus things - like ton of damage, etc.
    // Process specific champion bonuses
    printOut(DIVIDER);
  }

  private void processGenericStats(final Game game) {
    final RawStats stats = game.getStats();
    final double KDAMultiplier = (stats.getChampionsKilled() + stats.getAssists())/stats.getNumDeaths();
    final double mapDamage = (stats.getTotalDamageDealt() - stats.getTotalDamageTaken()) / 100;
    final double GPM = (stats.getGoldEarned() / stats.getTimePlayed()) * 60;
    final double CCTimeMultiplier = ((stats.getTotalTimeCrowdControlDealt() / stats.getTimePlayed()) * 60) + 1;
    final double baseKills = (stats.getBarracksKilled() + stats.getTurretsKilled()) * 100;
    final double warding = (stats.getWardPlaced() + stats.getWardKilled()) * 40;
    final double medic = (stats.getTotalUnitsHealed() * stats.getTotalHeal()) / 100;
    final String playerChampion = ChampReference.getChampById(game.getChampionId());
    final int side = stats.getTeam();

    final ArrayList<Player> players = game.getFellowPlayers();
    final ArrayList<String> allyChamps = new ArrayList<String>();
    final ArrayList<String> enemyChamps = new ArrayList<String>();

    final Iterator<Player> playerItr = players.iterator();
    while (playerItr.hasNext()) {
      final Player friendOrFoe = playerItr.next();
      if (friendOrFoe.getTeamId() == side) {
        allyChamps.add(ChampReference.getChampById(friendOrFoe.getChampionId()));
      }
      else {
        enemyChamps.add(ChampReference.getChampById(friendOrFoe.getChampionId()));
      }
    }

    final Iterator<String> allyItr = allyChamps.iterator();
    final Iterator<String> enemyItr = enemyChamps.iterator();

    printOut("You played " + playerChampion + ", fighting on the side of "+ChampReference.getFaction(playerChampion));
    printOut(("Your comrades were: "+allyChamps.toString()).replaceAll("\\[", "").replaceAll("\\]", "."));
    printOut("Your enemies were: "+enemyChamps.toString().replaceAll("\\[", "").replaceAll("\\]", "."));

    /**
     * Other things to process-
     * Game type
     * Nexus killing blow
     * Largest critical (>1000 = 1 ton of damage)
     * Node caps/assists/neutralize/neutralize-assist
     * Quadra
     * Penta
     * Killing Spree
     * Double
     * Largest spree
     * triple kills
     * victory point total
     * win
     * firstblood
     */
  }

  /**
   * Creates the file to be used for the BattleReport
   * @param summonerName - the summonerName being queried
   */
  private void prepareFile(final String summonerName) {
    final Calendar now = Calendar.getInstance();
    fileName = "Battle Report - " + summonerName + " - " + now.get(Calendar.DAY_OF_MONTH)+" "+now.get(Calendar.MONTH)+" "+now.get(Calendar.YEAR)+".txt";
    Writer writer = null;

    try {
      writer = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream(fileName), "utf-8"));
    }
    catch (final IOException e) {
      System.err.println("Error creating file.");
      e.printStackTrace();
    }
    finally {
      try {
        writer.close();
        }
      catch (final Exception e)
      {
        System.err.println("Error closing writer.");
        e.printStackTrace();
      }
    }

    printOut("Battle Report - "+summonerName + " (Summoner ID - " + lol.getSummoner(summonerName).getId() + ")");
    printOut("Generated at: "+Calendar.getInstance().getTime());
    printOut(DIVIDER);
  }

  /**
   * Configures the JRiot object
   * @param apiKey - API key to be used
   * @param region - region the summoner's in
   */
  private void configJRiot(final String apiKey, final String region) {
    System.out.println("apikey: "+apiKey+", reg: "+region);
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
          new PrintWriter(new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"\\"+fileName, true)));
      out.println(msg);
      out.close();
    }
    catch (final IOException e) {
      e.printStackTrace();
    }
  }

  private void printOut(final int msg) {
    try {
      final PrintWriter out =
          new PrintWriter(new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"\\"+fileName, true)));
      out.println(""+msg);
      out.close();
    }
    catch (final IOException e) {
      e.printStackTrace();
    }
  }

}
