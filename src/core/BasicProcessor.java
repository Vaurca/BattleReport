package core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;

import jriot.main.JRiot;
import jriot.objects.Game;
import jriot.objects.Player;
import jriot.objects.RawStats;


public class BasicProcessor extends Processor {

  private String playerChampion;
  private static final String RANKED = "RANKED_GAME";
  private final JRiot lol;
  private final String fileName;
  private final Game game;

  public BasicProcessor (final JRiot lol, final String fileName, final Game game) {
    this.lol = lol;
    this.fileName = fileName;
    this.game = game;
  }

  /**
   * This handles the basic stats of a game.
   * @param game
   * @return the total score from this section
   */
  @Override
  public BigDecimal process() {

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
    printOut("Game Stat Score =                      "+totalScore);

    return totalScore;
  }

}
