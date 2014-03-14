package core;

import java.util.ArrayList;
import java.util.Map;

import com.google.common.collect.ImmutableMap;


public class Reference {

  private static final String NOXUS = "Noxus";

  private static final String DEMACIA = "Demacia";

  private static final String BALANCE = "Balance";

  private static final String CHAOS = "Chaos";

  private static final Map<Integer, String> CHAMP_MAP = new ImmutableMap.Builder<Integer, String>()
      .put(266, "Aatrox")
      .put(103, "Ahri")
      .put(84, "Akali")
      .put(12, "Alistar")
      .put(32, "Amumu")
      .put(34, "Anivia")
      .put(1, "Annie")
      .put(22, "Ashe")
      .put(53, "Blitzcrank")
      .put(63, "Brand")
      .put(51, "Caitlyn")
      .put(69, "Cassiopeia")
      .put(31, "Chogath")
      .put(42, "Corki")
      .put(122, "Darius")
      .put(131, "Diana")
      .put(119, "Draven")
      .put(36, "DrMundo")
      .put(60, "Elise")
      .put(28, "Evelynn")
      .put(81, "Ezreal")
      .put(9, "FiddleSticks")
      .put(114, "Fiora")
      .put(105, "Fizz")
      .put(3, "Galio")
      .put(41, "Gangplank")
      .put(86, "Garen")
      .put(79, "Gragas")
      .put(104, "Graves")
      .put(120, "Hecarim")
      .put(74, "Heimerdinger")
      .put(39, "Irelia")
      .put(40, "Janna")
      .put(59, "JarvanIV")
      .put(24, "Jax")
      .put(126, "Jayce")
      .put(222, "Jinx")
      .put(43, "Karma")
      .put(30, "Karthus")
      .put(38, "Kassadin")
      .put(55, "Katarina")
      .put(10, "Kayle")
      .put(85, "Kennen")
      .put(121, "Khazix")
      .put(96, "KogMaw")
      .put(7, "Leblanc")
      .put(64, "LeeSin")
      .put(89, "Leona")
      .put(127, "Lissandra")
      .put(236, "Lucian")
      .put(117, "Lulu")
      .put(99, "Lux")
      .put(54, "Malphite")
      .put(90, "Malzahar")
      .put(57, "Maokai")
      .put(11, "MasterYi")
      .put(21, "MissFortune")
      .put(62, "MonkeyKing")
      .put(82, "Mordekaiser")
      .put(25, "Morgana")
      .put(267, "Nami")
      .put(75, "Nasus")
      .put(111, "Nautilus")
      .put(76, "Nidalee")
      .put(56, "Nocturne")
      .put(20, "Nunu")
      .put(2, "Olaf")
      .put(61, "Orianna")
      .put(80, "Pantheon")
      .put(78, "Poppy")
      .put(133, "Quinn")
      .put(33, "Rammus")
      .put(58, "Renekton")
      .put(107, "Rengar")
      .put(92, "Riven")
      .put(68, "Rumble")
      .put(13, "Ryze")
      .put(113, "Sejuani")
      .put(35, "Shaco")
      .put(98, "Shen")
      .put(102, "Shyvana")
      .put(27, "Singed")
      .put(14, "Sion")
      .put(15, "Sivir")
      .put(72, "Skarner")
      .put(37, "Sona")
      .put(16, "Soraka")
      .put(50, "Swain")
      .put(134, "Syndra")
      .put(91, "Talon")
      .put(44, "Taric")
      .put(17, "Teemo")
      .put(412, "Thresh")
      .put(18, "Tristana")
      .put(48, "Trundle")
      .put(23, "Tryndamere")
      .put(4, "TwistedFate")
      .put(29, "Twitch")
      .put(77, "Udyr")
      .put(6, "Urgot")
      .put(110, "Varus")
      .put(67, "Vayne")
      .put(45, "Veigar")
      .put(161, "Velkoz")
      .put(254, "Vi")
      .put(112, "Viktor")
      .put(8, "Vladimir")
      .put(106, "Volibear")
      .put(19, "Warwick")
      .put(101, "Xerath")
      .put(5, "XinZhao")
      .put(157, "Yasuo")
      .put(83, "Yorick")
      .put(154, "Zac")
      .put(238, "Zed")
      .put(115, "Ziggs")
      .put(26, "Zilean")
      .put(143, "Zyra")
      .build();

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

  /**
   * yo dawg i herd u liek mapz
   */
  private static final Map<Integer, ArrayList<String>> MAP_MAP = new ImmutableMap.Builder<Integer, ArrayList<String>>()
      .put(1, createList("Summoner's Rift", "1000"))
      .put(2, createList("Summoner's Rift", "1000"))
      .put(3, createList("The Proving Grounds", "100"))
      .put(8, createList("The Crystal Scar", "500"))
      .put(10, createList("Twisted Treeline", "500"))
      .put(12, createList("The Howling Abyss", "200"))
      .build();

  /**
   * Helper method to make arrayLists for the map-map.
   * @param map Name of the map
   * @param value Value of the map
   * @return ArrayList with two objects in it.
   */
  private static ArrayList<String> createList(final String map, final String value) {
    final ArrayList<String> list = new ArrayList<String>();
    list.add(map);
    list.add(value);
    return list;
  }

  /**
   * Returns the name of a champion corresponding to a given id
   * @param id - id of the champion
   * @return the name of the champion based on id
   */
  protected static String getChampById(final int id) {
    return CHAMP_MAP.containsKey(id) ? CHAMP_MAP.get(id) : "error!";
  }

  /**
   * Returns the corresponding faction for a champion
   * @param champion - the champion for whom to check faction
   * @return faction of the champion
   */
  protected static String getFaction(final String champion) {
    return FACTION_MAP.containsKey(champion) ? FACTION_MAP.get(champion) : "error!";
  }

  /**
   * Returns the map info given a mapId
   * @param mapId - id of the map
   * @return ArrayList with the map info
   */
  protected static ArrayList<String> getMapInfo(final int mapId) {
    return MAP_MAP.containsKey(mapId) ? MAP_MAP.get(mapId) : null;
  }

}
