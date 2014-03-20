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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jriot.main.JRiot;
import jriot.objects.Game;

/**
 * This is the class that ties it all together. Makes calls to the other classes that do the work, and does a little itself.
 * @author Justin Chan
 */
public class BattleReporter {

  private static final String DIVIDER = "---------------------------------------------------";

  private JRiot lol;
  private String summonerName;
  private String fileName;
  private static final String CHAOS = "Chaos";
  private static final String BALANCE = "Balance";
  private static final String DEMACIA = "Demacia";
  private static final String NOXUS = "Noxus";

  private final Map<String, Integer> SCORE_MAP = new HashMap<String, Integer>();

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
   * @return String location of where the file is
   */
  protected String createReport() {
    final long id = lol.getSummoner(summonerName).getId();

    // Get the games first
    final ArrayList<Game> games = getGames(id);

    // Process the games one by one
    final Iterator<Game> itr = games.iterator();
    Game game = new Game();

    while (itr.hasNext()) {
      game = itr.next();

      // Get the faction of the champion being played so we know where to put those points
      final String faction = Reference.getFaction(Reference.getChampById(game.getChampionId()));

      // Process the actual game
      final double gameScore = processGame(game);
      printOut("Game Score:                            " +            gameScore);

      // Add the score from that game to the faction
      SCORE_MAP.put(faction, (int) (SCORE_MAP.get(faction) + gameScore));

      printOut(DIVIDER);
    }

    printOut("");

    // Show the final scores for each faction
    final int totalScore = getTotalScore(SCORE_MAP);
    printOut("Your total scores are - ");
    for (final String faction : SCORE_MAP.keySet()) {
      printOut(faction + ": " + SCORE_MAP.get(faction) + " (" + getPct(SCORE_MAP.get(faction), totalScore) + "%)");
    }
    printOut("Overall score: " + totalScore);

    return System.getProperty("user.dir")+"\\"+fileName;

  }

  /**
   * Processes the stats from a specific game
   * @param game - the game to look at
   * @return - double of the score of the game
   */
  private double processGame(final Game game) {
    // Process the basic game stats first
    // Then, process bonus things - like ton of damage, etc.
    // Process specific champion bonuses with something like processChampStats, will implement later
    return (processGenericStats(game).add(processFlavourStats(game))).doubleValue();
  }

  /**
   * This handles the basic stats of a game.
   * @param game - The game to process
   * @return the total score from this section
   */
  private BigDecimal processGenericStats(final Game game) {
    final BasicProcessor processor = new BasicProcessor(lol, fileName, game);
    processor.setFilename(fileName);
    return processor.process();
  }

  /**
   * Offloading the achievement processor so this doesn't get too messy
   * @param game - current game that's being processed
   * @return - double value from processFlavourStats
   */
  private BigDecimal processFlavourStats(final Game game) {
    final AchievementProcessor processor = new AchievementProcessor(lol, fileName, game);
    processor.setFilename(fileName);
    return processor.process();
  }

  /**
   * Creates the file to be used for the BattleReport, also going to populate the score map
   * @param summonerName - the summonerName being queried
   */
  private void prepareFile(final String summonerName) {

    SCORE_MAP.put(CHAOS, 0);
    SCORE_MAP.put(BALANCE, 0);
    SCORE_MAP.put(NOXUS, 0);
    SCORE_MAP.put(DEMACIA, 0);

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
    lol.setApiKey(apiKey);
    lol.setRegion(region);
  }

  /**
   * Gets recently played games from summoner based on the id
   * @param id - id of summoner
   * @return ArrayList of the games
   */
  private ArrayList<Game> getGames(final long id) {
    return lol.getRecentGames(id).getGames();
  }

  /**
   * Just a method to print stuff to the file
   * @param msg - Message to print
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
   * @param value - value of double
   * @return BigDecimal representation of the double
   */
  private BigDecimal val (final double value) {
    return BigDecimal.valueOf(value);
  }

  /**
   * Grabs the total value of all scores so we can use it for % calculation
   * @param map - The score Map
   * @return The total value of all scores
   */
  private int getTotalScore(final Map<String, Integer> map) {
    int total = 0;
    for (final String faction : map.keySet()) {
      total += map.get(faction);
    }
    return total;
  }

  /**
   * Gets the percentage to 1 decimal place of the scores
   * @param score - score of the specific faction
   * @param total - total score of all factions
   * @return the % score of the specified faction
   */
  private BigDecimal getPct(final double score, final int total) {
    return val((score/total)*100).setScale(1, RoundingMode.HALF_EVEN);
  }

}
