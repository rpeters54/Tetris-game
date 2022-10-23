package components;

public interface Playable {
    void openGame(); // which opens up the game interface / screen and has buttons like Start Game, and About this Game, Settings, and things like that
    boolean playGame(); // starts the actual game, initiates the player to start playing the game, returns true if the game has been started

    void endGame(); //which closes up the game interface / screen and has buttons like End Game, and things like that
    String getScore(); //Which returns the score for the current game
    String returnUsername(); // Which returns the player's username in a string



}