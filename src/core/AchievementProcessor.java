package core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

import jriot.main.JRiot;
import jriot.objects.Game;
import jriot.objects.RawStats;


public class AchievementProcessor {

  private static final String DIVIDER = "---------------------------------------------------";
  private final JRiot lol;
  private final String fileName;
  private final Game game;

  public AchievementProcessor (final JRiot lol, final String fileName, final Game game) {
    this.lol = lol;
    this.fileName = fileName;
    this.game = game;
  }

  public double processFlavourStats() {
    // Grab the stats
    final RawStats stats = game.getStats();

    // Set up all the achievements first
    final double shoppingSpree = val(stats.getGoldEarned() * 0.0099).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    final double doubleKills = stats.getDoubleKills() * 50;
    final double tripleKills = stats.getTripleKills() * 100;
    final double quadraKills = stats.getQuadraKills() * 300;
    final double pentaKills = stats.getPentaKills() * 1000;
    final double unrealKills = stats.getUnrealKills() * 10000;
    final double firstBlood = stats.getFirstBlood() * 100;
    final double killingSprees = stats.getKillingSprees() * 50;
    final double tonOfDamage = stats.getLargestCriticalStrike() > 1000 ? 1000 : 0;
    final double unluckyThirteen = stats.getNumDeaths() == 13 ? 130 : 0;
    final double fourTwenty = stats.getChampionsKilled() == 4 && stats.getAssists() == 20 ? 420 : 0;
    final double cool = stats.getTrueDamageDealtToChampions() == 1337 ? 1337 : 0;

    // Messy...but method for combining if statements + grabbing the value and then inserting into a variable maybe later
    final double totalScore = shoppingSpree + doubleKills + tripleKills + quadraKills + pentaKills + unrealKills + firstBlood +
        killingSprees + tonOfDamage + unluckyThirteen + fourTwenty + cool;

    // Print them out - since we have their values previously we can just check if a value exists and use it if it does
    printOut("****      Achievement Score      ****");

    if (shoppingSpree < stats.getGoldSpent()) {
      printOut("LET'S GO SHOPPPINGGGGGG! :              "+shoppingSpree);
    }
    if (firstBlood > 0) {
      printOut("First Blood! :                          "+firstBlood);
    }
    if (doubleKills > 0) {
      printOut("Double Trouble:                         "+doubleKills);
    }
    if (tripleKills > 0) {
      printOut("3-pointer! :                            "+tripleKills);
    }
    if (quadraKills > 0) {
      printOut("Four-rocious:                           "+quadraKills);
    }
    if (pentaKills > 0 ) {
      printOut("Five Finger Death Punch:                "+pentaKills);
    }
    if (unrealKills > 0) {
      printOut("I'm Kind Of A Big Deal:                 "+unrealKills);
    }
    if (killingSprees > 0) {
      printOut("Killing Sprees:                         "+killingSprees);
    }
    if (tonOfDamage > 0) {
      printOut("Ton Of Damage:                          "+tonOfDamage);
    }
    if (unluckyThirteen > 0) {
      printOut("Unlucky Thirteen:                       "+unluckyThirteen);
    }
    if (fourTwenty > 0) {
      printOut("Chilled Out:                            "+fourTwenty);
    }
    if (cool > 0) {
      printOut("Truly Cool:                             "+cool);
    }

    printOut(DIVIDER);
    return totalScore;
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
