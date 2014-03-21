===================
   Battle Report 
===================
A fun way to display and play with stats, including introducing Achievements.


i. 		What is it?
ii.		The Challenge
iii.	Mapping a solution
iv.		Weaknesses
v.		How this could move quick to Awesome

i.		What is it?

Battle Report is a simple program using the JRiot library (https://github.com/t-sauer/jriot) to pull recent 
game results using the Riot API. It performs a few calculations to have some fun with numbers, and displays 
them in a more entertaining fashion.

ii.		The Challenge

It seems to me that most design challenges would revolve around creating new champions; while I've got a few 
cool ideas I could flesh out, I thought I would focus on something a little less explored. Statistics are 
useful pieces of information from a mechanical standpoint, as they give insight onto how skilled a player is, 
and how the game may have gone. That being said, they're not particularly interesting to look at unless you're
specifically interested in someone's average stats. I would wager that most people can't even remember their most recent KDA a few hours after they've played (unless it's something like 1/33/7).

Additionally, I noticed that most of the uses of the Riot API so far have been wrappers and libraries meant to 
be used to just query, and that there was an interest in doing something with just this API alone. I figured
as a bonus, this project only requires the base Riot API to do fun things.

iii.	Mapping a solution 

I had a few basic thoughts that I put together - 

1 - At the core of almost any game is a comparison between two numbers. Comparing numbers competitively with 
others is fun. (Competition!)

2 - Outside of direct competition, numbers are boring unless there's a system to compete against themselves.
(Achievements!)

3 - Humans love to be part of a group, and like supporting their group against other groups. (Teamwork + 
Competition!)

4 - League has a lot of fluff that's pretty sweet, but we never really touch on it other than little hidden
VOs or buffs.

These suggest something that will allow people to join together in groups, compete against other groups,
and compete against themselves. Fluff naturally ties this all together - making the 'faction' of a champion
more clear provides an added non-mechanical benefit for people who like a certain faction, and encourages them
to play against other factions. Similar to how there's a preference to dive Jinx if you're Vi, if you know you're
Noxus, then you want to execute Demacians. Having a numerical, comparable value of stats rather than just straight
KDA or GPM lets people compete against each other on another axis.

Examining stats also lets us play with the idea of achievements - achievements can mean quite a lot to people in
other games. They've been widely implement and while not everyone spends a huge deal of time trying to grind them out, certain subsets of the population spend a great deal of time amassing them and it provides more stuff to do when you're bored of just the standard slog. They're a nice thing to pick up on the side, and boast to your friends. 

The league system allowed this to an extent with things like borders, icons, wards, and skins. Event icons for completing certain tasks follow that path too. Expanding this to normal play makes pulling off something awesome that much more sweet - like scoring a certain number of pentakills, or being a master Master Yi - without having to wait 'till the end of a season. It also lets more casual players have more goals to work for, without forcing anyone to have to participate.

iv. 	Weaknesses

While achievements and fun stats can be enjoyable, there's the potential for people to abuse the systems just
to make themselves look better, or to grab a difficult achievement. For example, if the ward score is something
that they want to really inflate, they could spam wards in fountain uselessly, or if they want pentakills they
could rig games and farm them up. While the creativity of a large group of players will almost always allow them
to find a way around any measure meant to prohibit abuse, it's generally possible to curb those methods. As long as achievements and stats are generated in line with the core tenets of the game (killing champions, gaining control, breaking bases and winning the game), there's no added incentive to troll either.

In terms of coding I've not implemented a class that would handle champion-specific achievements, but I think that'd be a great way of adding more depth. Things like a bonus for TF if he beats Graves, or Syndra if she's got an Ori on her team (ball comp), or any time you kill Teemo, are fun little flavour things and a way to bring more of the lore into the game. One of the coolest things to me were the big faction-based show downs earlier in the game's history. Flavour based rewards are a way to bring that feel back, offer them in impromptu fashion, and promote healthy in-game rivalries similar to events like The Hunt is On! or the Frozen Crown (that's what it's called, right? Between Ashe, Sej, Liss?)

v. 	How this could move quick to Awesome

The biggest way to take advantage would be saving scores per champion/faction over a player's history, and 
offering recognition/reward for those that top the individual 'ladders'. A leaderboard for each champion would allow players to shine who may not necessarily be the strongest competitive player but who love that champ, and doesn't hinder players who are good at the champion too. The same goes for factions; I would expect quite a fierce competition if there was a simple 'Noxian Grand General' icon for the top Noxian players. Even a rank system according to your liftime faction points, maybe adding a neat flare to your loading portrait, like silver/gold/plat borders etc., but with faction-inspired flares would add a new way to compete.

Other cool directions could be bi-weekly/monthly 'wars' over a fluff-inspired location, where games played on a given map could contribute points to an overall faction score. Top contributors in the war get something special, and a real-time map with different highlights on the map according to % overall faction score to show 'battle lines' would promote play on those specific maps.

In addition to adding flavour, it can serve as an additional way to use RP/IP - certain skins vs. certain other skins or situations could provide a bonus (just ensure it wouldn't be just pay to win), or influence points could literally be used to increase influence with a specific faction to unlock rewards. Faction specific skins/icons/wards could unlock for purchase at certain scores, or an in game quest could pop when you're against a particularly high-ranked opposing faction player for larger IP rewards. 
