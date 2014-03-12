package core;

import jriot.main.JRiot;

public class Blitz {

  private static final String APIKEY = "c705cff4-de8c-4454-848f-99c2888eac1e";

  public static void main(final String[] args) {

    final JRiot lol = new JRiot();
    lol.setApiKey(APIKEY);
    lol.setRegion("na");

    final BattleReporter reporter = new BattleReporter("Bananagram",APIKEY,"na");
    reporter.createReport();

  }
}
