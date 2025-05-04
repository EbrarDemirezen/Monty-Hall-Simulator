// src/main/MontyHall/GameController.java
package src.main.MontyHall;

import src.main.MontyHall.GameLogic;
import java.util.Random;

/**
 * Serves as a bridge between the web UI and the game logic.
 * Will eventually handle HTTP requests from the frontend.
 */
public class GameController {
    private GameLogic game;
    private int gamesPlayed = 0;
    private int stayWins = 0;
    private int switchWins = 0;
    private int stayGames = 0;
    private int switchGames = 0;
    
    public GameController() {
        game = new GameLogic(); // GameLogic constructor should default to 3 doors
    }
    
    public void resetGame() {
        game.resetGame();
    }
    
    public void makeInitialChoice(int doorNumber) {
        if (game.getPlayerChoice() == -1) {
            game.makeInitialChoice(doorNumber);
        }
    }
    
    public void stayWithCurrentChoice() {
        if (game.getRevealedDoor() != -1 && !game.isGameOver()) {
            game.stayWithCurrentChoice();
            stayGames++;
            updateStatistics();
        }
    }
    
    public void switchDoor() {
        if (game.getRevealedDoor() != -1 && !game.isGameOver()) {
            game.switchDoor();
            switchGames++;
            updateStatistics();
        }
    }
    
    public void runAutoPlay(int iterations) {
        Random random = new Random();
        
        for (int i = 0; i < iterations; i++) {
            // Create a single game instance for each iteration
            GameLogic game = new GameLogic(); // Remove door count parameter
            
            // Make random initial choice
            int initialChoice = random.nextInt(3); // Hardcode to 3 doors
            game.makeInitialChoice(initialChoice);
            
            // Randomly decide whether to stay or switch
            boolean shouldSwitch = random.nextBoolean();
            
            if (shouldSwitch) {
                game.switchDoor();
                switchGames++;
                if (game.hasWon()) {
                    switchWins++;
                }
            } else {
                game.stayWithCurrentChoice();
                stayGames++;
                if (game.hasWon()) {
                    stayWins++;
                }
            }
            
            gamesPlayed++;
        }
        
        // Reset the current game so player can start fresh
        resetGame();
    }
    
    private void updateStatistics() {
        gamesPlayed++;
        if (game.hasWon()) {
            if (game.hasSwitched()) {
                switchWins++;
            } else {
                stayWins++;
            }
        }
    }
    
    public boolean isGameOver() {
        return game.isGameOver();
    }
    
    public boolean hasWon() {
        return game.hasWon();
    }
    
    public boolean hasSwitched() {
        return game.hasSwitched();
    }
    
    public GameLogic getGameState() {
        return game;
    }
    
    public int getGamesPlayed() {
        return gamesPlayed;
    }
    
    public int getStayWins() {
        return stayWins;
    }
    
    public int getSwitchWins() {
        return switchWins;
    }
    
    public double getStayWinRate() {
        return stayGames > 0 ? (double) stayWins / stayGames * 100 : 0;
    }
    
    public double getSwitchWinRate() {
        return switchGames > 0 ? (double) switchWins / switchGames * 100 : 0;
    }

    // Add these two new getter methods
    public int getStayGames() {
        return stayGames;
    }

    public int getSwitchGames() {
        return switchGames;
    }

    public void resetAllStats() {
        this.gamesPlayed = 0;
        this.stayWins = 0;
        this.switchWins = 0;
        this.stayGames = 0;
        this.switchGames = 0;
        resetGame(); // Reset the current game state as well
    }
}
        return stayGames;
    }

    public int getSwitchGames() {
        return switchGames;
    }
}
