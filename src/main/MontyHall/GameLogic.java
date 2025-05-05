package src.main.MontyHall;
import java.util.Random;

public class GameLogic {
    private static final int DOOR_COUNT = 3; // Make this a constant
    private int carPosition;
    private int playerChoice;
    private int revealedDoor;
    private boolean gameOver;
    private boolean switched;
    private boolean won;
    
    private Random random;
    
    public GameLogic() {
        this.random = new Random();
        this.gameOver = false;
        resetGame();
    }
    
    public void resetGame() {
        // Place the car behind a random door (0 to 2)
        this.carPosition = random.nextInt(DOOR_COUNT);
        this.playerChoice = -1;
        this.revealedDoor = -1;
        this.gameOver = false;
        this.switched = false;
        this.won = false;
    }
    
    public void makeInitialChoice(int doorNumber) {
        if (doorNumber < 0 || doorNumber >= DOOR_COUNT) {
            throw new IllegalArgumentException("Door choice must be between 0 and 2");
        }
        this.playerChoice = doorNumber;
        revealGoatDoor();
    }
    
    private void revealGoatDoor() {
        // Simplified logic for 3 doors
        for (int i = 0; i < DOOR_COUNT; i++) {
            if (i != playerChoice && i != carPosition) {
                this.revealedDoor = i;
                return;
            }
        }
        // If player chose the car, reveal any other door
        this.revealedDoor = (playerChoice + 1) % DOOR_COUNT;
    }
    
    public void stayWithCurrentChoice() {
        if (playerChoice == -1) {
            throw new IllegalStateException("Player must make an initial choice first");
        }
        
        this.switched = false;
        finishGame();
    }
    
    public void switchDoor() {
        if (playerChoice == -1) {
            throw new IllegalStateException("Player must make an initial choice first");
        }
        
        // In 3-door case, switch to the only remaining door
        for (int i = 0; i < DOOR_COUNT; i++) {
            if (i != playerChoice && i != revealedDoor) {
                playerChoice = i;
                break;
            }
        }
        
        this.switched = true;
        finishGame();
    }
    
    private void finishGame() {
        this.gameOver = true;
        this.won = (playerChoice == carPosition);
    }
    
    // Getters
    public int getPlayerChoice() {
        return playerChoice;
    }
    
    public int getRevealedDoor() {
        return revealedDoor;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public boolean hasWon() {
        return won;
    }
    
    public boolean hasSwitched() {
        return switched;
    }
    
    public int getCarPosition() {
        return carPosition;
    }
}