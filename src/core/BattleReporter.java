package core;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import jriot.main.JRiot;
import jriot.objects.Game;
import jriot.objects.Player;
import jriot.objects.RawStats;


public class BattleReporter {

  private static final String DIVIDER = "---------------------------------------------------";
  private static final String RANKED = "RANKED_GAME";

  private JRiot lol;
  private String summonerName;
  private String fileName;
  private String playerChampion;

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
    double finalScore = 0;
    final long id = lol.getSummoner(summonerName).getId();

    // Get the games first
    final ArrayList<Game> games = getGames(id);

    // Process the games one by one
    final Iterator<Game> itr = games.iterator();
    while (itr.hasNext()) {
      finalScore += processGame(itr.next());
      printOut(DIVIDER);
    }

    printOut("");
    printOut("Your total score is: " + val(finalScore).setScale(0, RoundingMode.HALF_EVEN) + " for " + Reference.getFaction(playerChampion));

  }

  /**
   * Processes the stats from a specific game
   * @param game - the game to look at
   */
  private double processGame(final Game game) {
    // Process the basic game stats first
    // Then, process bonus things - like ton of damage, etc.
    // Process specific champion bonuses with something like processChampStats, will implement later
    return (processGenericStats(game).add(val(processFlavourStats(game)))).doubleValue();
  }

  /**
   * This handles the basic stats of a game.
   * @param game
   * @return the total score from this section
   */
  private BigDecimal processGenericStats(final Game game) {

    // Grab the stats first
    final RawStats stats = game.getStats();

    // Play with numbers
    final double killsAssists = stats.getChampionsKilled() + stats.getAssists();
    final BigDecimal KDAMultiplier = (val(killsAssists).divide(val(stats.getNumDeaths()), 3, RoundingMode.HALF_EVEN)).divide(val(10)).setScale(2,RoundingMode.HALF_EVEN);
    final BigDecimal mapDamage = val(stats.getTotalDamageDealt() - stats.getTotalDamageTaken()).divide(val(100)).setScale(2, RoundingMode.HALF_EVEN);
    final BigDecimal GPM = val(stats.getGoldEarned()).divide(val(stats.getTimePlayed()), 3, RoundingMode.HALF_EVEN).multiply(val(60)).setScale(2, RoundingMode.HALF_EVEN);
    final BigDecimal CCTimeMultiplier = val(stats.getTotalTimeCrowdControlDealt()).divide(val(stats.getTimePlayed()), 2, RoundingMode.HALF_EVEN).multiply(val(6)).setScale(2, RoundingMode.HALF_EVEN);
    final BigDecimal baseKills = val(((stats.getBarracksKilled() + stats.getTurretsKilled()) * 100)).setScale(2, RoundingMode.HALF_EVEN);
    final BigDecimal warding = val((stats.getWardPlaced() + stats.getWardKilled()) * 40).setScale(2, RoundingMode.HALF_EVEN);
    final BigDecimal medic = val(stats.getTotalUnitsHealed()).multiply(val(stats.getTotalHeal())).divide(val(100)).setScale(2, RoundingMode.HALF_EVEN);
    final BigDecimal multipliers = (KDAMultiplier.add(CCTimeMultiplier)).add(val(1));

    // Grabbing map info + multipliers
    final int mapId = game.getMapId();
    final BigDecimal mapValue = val(Double.valueOf(Reference.getMapInfo(mapId).get(1)));
    final boolean ranked = (game.getGameType().equals(RANKED));
    final boolean won = stats.getWin();

    // Base total score value
    BigDecimal totalScore = (mapDamage.add(GPM).add(baseKills).add(warding).add(medic).add(mapValue)).multiply(multipliers).setScale(0, RoundingMode.HALF_EVEN);

    // Apply multipliers
    if (won) {
      totalScore = totalScore.multiply(val(1.1)).setScale(0, RoundingMode.HALF_EVEN);
    }
    if (ranked) {
      totalScore = totalScore.multiply(val(1.1)).setScale(0, RoundingMode.HALF_EVEN);
    }

    // Check who's on first
    playerChampion = Reference.getChampById(game.getChampionId());
    final int side = stats.getTeam();

    final ArrayList<Player> players = game.getFellowPlayers();
    final ArrayList<String> allyChamps = new ArrayList<String>();
    final ArrayList<String> enemyChamps = new ArrayList<String>();

    // Figure out the actual players on the sides
    final Iterator<Player> playerItr = players.iterator();
    while (playerItr.hasNext()) {
      final Player friendOrFoe = playerItr.next();
      if (friendOrFoe.getTeamId() == side) {
        allyChamps.add(Reference.getChampById(friendOrFoe.getChampionId()));
      }
      else {
        enemyChamps.add(Reference.getChampById(friendOrFoe.getChampionId()));
      }
    }

    final Iterator<String> allyItr = allyChamps.iterator();
    final Iterator<String> enemyItr = enemyChamps.iterator();

    // Get it all onto the report
    printOut("You played " + playerChampion + " on " + Reference.getMapInfo(mapId).get(0) + ", fighting for "+Reference.getFaction(playerChampion));
    printOut(("Your comrades were: "+allyChamps.toString()).replaceAll("\\[", "").replaceAll("\\]", "."));
    printOut("Your enemies were: "+enemyChamps.toString().replaceAll("\\[", "").replaceAll("\\]", "."));
    printOut("");
    printOut("****      Game Stat Score      ****");
    printOut("Kill/Death/Assist Ratio Multiplier:    "+KDAMultiplier);
    printOut("Damage Bonus:                          "+mapDamage);
    printOut("Gold Per Minute:                       "+GPM);
    printOut("Crowd Control Time Multiplier:         "+CCTimeMultiplier);
    printOut("Structure Kills:                       "+baseKills);
    printOut("Warding/Counterwarding:                "+warding);
    printOut("Medic Bonus:                           "+medic);
    printOut("Map Bonus:                             "+mapValue);
    if (ranked) {
    printOut("Ranked game:                           10% bonus!");
    }
    if (won) {
    printOut("Victory! :                             10% bonus!");
    }
    printOut("Game Stat Score:                       "+totalScore);

    return totalScore;
  }

  /**
   * Offloading the achievement processor so this doesn't get too messy
   * @param game - current game that's being processed
   * @return - double value from processFlavourStats
   */
  private double processFlavourStats(final Game game) {
    final AchievementProcessor processor = new AchievementProcessor(lol, fileName, game);
    return processor.processFlavourStats();
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

  /**
   * Just a method to print stuff to the file
   * @param msg Message to print
   */
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

  /**
   * Simple helper method just to easy readability/coding
   * @param value of double
   * @return BigDecimal representation of the double
   */
  private BigDecimal val (final double value) {
    return BigDecimal.valueOf(value);
  }

}
