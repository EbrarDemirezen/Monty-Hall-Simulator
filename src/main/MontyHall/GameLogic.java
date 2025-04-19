package src.main.MontyHall;
import java.util.Random;

public class GameLogic {
    private int doorCount;
    private int carPosition;
    private int playerChoice;
    private int revealedDoor;
    private boolean gameOver;
    private boolean switched;
    private boolean won;
    
    private Random random;
    
    public GameLogic() {
        this(3); // Default to 3 doors
    }
    
    public GameLogic(int doorCount) {
        if (doorCount < 3) {
            throw new IllegalArgumentException("Game requires at least 3 doors");
        }
        this.doorCount = doorCount;
        this.random = new Random();
        this.gameOver = false;
        resetGame();
    }
    
    public void resetGame() {
        // Place the car behind a random door (0 to doorCount-1)
        this.carPosition = random.nextInt(doorCount);
        this.playerChoice = -1;
        this.revealedDoor = -1;
        this.gameOver = false;
        this.switched = false;
        this.won = false;
    }
    
    public void makeInitialChoice(int doorNumber) {
        if (doorNumber < 0 || doorNumber >= doorCount) {
            throw new IllegalArgumentException("Door choice must be between 0 and " + (doorCount - 1));
        }
        this.playerChoice = doorNumber;
        
        // Host reveals a goat door (not the player's choice and not containing the car)
        revealGoatDoor();
    }
    
    private void revealGoatDoor() {
        // Host needs to reveal a door that:
        // 1. Is not the player's choice
        // 2. Does not contain the car
        // 3. Is a valid door number
        
        // Find all valid doors that can be revealed
        int[] revealableDoors = new int[doorCount - 1]; // At most doorCount-1 doors can be revealed
        int count = 0;
        
        for (int i = 0; i < doorCount; i++) {
            if (i != playerChoice && i != carPosition) {
                revealableDoors[count++] = i;
            }
        }
        
        // Choose one door to reveal randomly from valid options
        if (count > 0) {
            this.revealedDoor = revealableDoors[random.nextInt(count)];
        } else {
            // Special case: if player chose the car door, host can reveal any other door
            int[] otherDoors = new int[doorCount - 1];
            count = 0;
            for (int i = 0; i < doorCount; i++) {
                if (i != playerChoice) {
                    otherDoors[count++] = i;
                }
            }
            this.revealedDoor = otherDoors[random.nextInt(count)];
        }
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
        
        // Find available doors (not player's current choice and not revealed)
        int[] availableDoors = new int[doorCount - 2]; // Current choice and revealed door are not available
        int count = 0;
        
        for (int i = 0; i < doorCount; i++) {
            if (i != playerChoice && i != revealedDoor) {
                availableDoors[count++] = i;
            }
        }
        
        // In the classic 3-door case, there's only one door to switch to
        if (count == 1) {
            playerChoice = availableDoors[0];
        } else if (count > 1) {
            // In case of more than 3 doors, player might need to choose which door to switch to
            // For simplicity, we'll just pick randomly here
            playerChoice = availableDoors[random.nextInt(count)];
        }
        
        this.switched = true;
        finishGame();
    }
    
    private void finishGame() {
        this.gameOver = true;
        this.won = (playerChoice == carPosition);
    }
    
    // Getters
    public int getDoorCount() {
        return doorCount;
    }
    
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
        // Only return car position if game is over
        if (!gameOver) {
            throw new IllegalStateException("Game is not over yet");
        }
        return carPosition;
    }
}