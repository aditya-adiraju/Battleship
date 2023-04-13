# CPSC 210 Personal Project: Battleship

### What will this application do?

This application enables users to play a multiplayer version of the classic game of Battleship. Battleship is a fun and
strategic guessing game where players place their ships on their 10x10 playing board and exchange turns guessing where their opponents placed their ships. The game ends when all the ships of an opponent are sunk. (The player with the sunk battleships loses)

### Who will use it?
The intended audience for this application includes everyone. Specifically, groups of 2 players who wish to play a
nostalgic guessing game or simply wish to use this as a means to resolve an argument.

### Why am I making this project?
Battleship has always been a staple boardgame in my household. When I came to university, I discovered that many of my
friends have never heard of it before. This was quite shocking to me since to me the game is a symbol of family game
nights. Hence, I want to make this project to make this beloved game more accessible to everyone.


## User-Stories


As a user, I want to be able to: 
- name my player and opponent
- add ships to my board
- choose coordinates to strike on my opponent's board for my missiles
- play a game of Battleship with different board sizes
- save and load a tally of my wins against others
- save the game against an opponent (Battleship is a long game that can take over 30 minutes)
- load a pre-existing game of Battleship

# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by selecting new Game on the title screen, 
  pressing the button labelled "submit", pressing on the button map above to place a ship displayed below onto the map. 
- You can generate the second required action related to adding Xs to a Y by going to a Battle state by loading a game, 
  or initializing a game with "start new Game", pressing "submit", assigning Battleships for each player.
  Now,you can select any coordinate to launch a missile and add it to your radar and a missile on your opponent's ocean 
  map. Once all X's on the opponents board is eliminated, the ship is removed, and the game ends.
- You can locate my visual component by starting the game on the title screen or after starting a new game once you've 
  entered the necessary info to start the game
- You can save the state of my application by selecting the save file option when you're in the Battle turn in the 
  game. To reach this state, select load game in the title screen OR start new game, initialize game parameters, 
  and place ships for each player. 
- You can reload the state of my application by selecting the load option from the file menu during a Battle Turn. 
  Follow the instructions above to reach a battle Turn.

# Phase 4: Task 2

Examples of events in project including those that trigger on:
- placing ships on specific coordinates on a player's board
- successfully striking a missile on opponent map.
- sinking an opponent's battleship and removing it from their board.
- a game over.
- saving score data
- saving player data

# Phase 4: Task 3

Upon examining the diagram, it is apparent that substantial refactoring is required to make the design more effective.
Especially, the number of associations with the Game class should be reduced to a single point from the rest of the GUI.
Additionally, the Game class should probably be refactored to use the Singleton design pattern, as well as the Observer 
Pattern to reduce the extreme coupling with UI classes. 

In the GUI classes, there are many bi-drectional relationships between panels and frames that increases coupling. This 
should be addressed and reduced in future designs through the use of static variables or singleton patterns on the Game 
object. Furthermore, there is a large amount of code duplication between the classes that could be avoided with 
appropriate supertyping with interfaces and superclasses. Finally, the model class has made good use of the 
design principles and has relatively low coupling, with high cohesion. 