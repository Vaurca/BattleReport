package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jriot.main.JRiot;

public class Blitz {

  public static void main(final String[] args) {

    // Initialized variables
    final JRiot lol = new JRiot();
    String apiKey = null;
    String region = null;
    String summoner = null;

    // Pull in info from the user (this has noooooo error checking currently)
    final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    try {
      System.out.println("Enter your api key (You can get one free at - https://developer.riotgames.com/sign-in ): ");
      apiKey = in.readLine();
      System.out.println("Enter your region (BR, EUNE, EUW, KR, LAN, LAS, NA, OCE, RU, TR): ");
      region = in.readLine();
      System.out.println("Enter your summoner name: ");
      summoner = in.readLine();
    }
    catch (final IOException e) {
      System.err.println("Error reading in the value.");
      e.printStackTrace();
    }

    // Set the values
    lol.setApiKey(apiKey);
    lol.setRegion(region);

    // Create the report
    System.out.println("Preparing your Battle Report..");
    final BattleReporter reporter = new BattleReporter(summoner, apiKey, region);
    final String location = reporter.createReport();
    System.out.println("Report complete! You can find it at " + location);

  }
}
