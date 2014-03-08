package core;

import java.util.Map;

import com.google.common.collect.ImmutableMap;


public class ChampReference {

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

  public static String getChampById(final int id) {
    String champName = null;
    if (CHAMP_MAP.get(id) != null) {
      champName = CHAMP_MAP.get(id);
    }
    return champName;
  }

}
