package src.main.MontyHall;

public class Main {
    public static void main(String[] args) {
        System.out.println("Monty Hall Simulator");
        System.out.println("===================");
        
        // This will eventually initialize our web application
        // For now, we'll run a simple simulation to test the game logic
        
        int stayWins = 0;
        int switchWins = 0;
        int totalGames = 10000;
        
        for (int i = 0; i < totalGames; i++) {
            GameLogic stayGame = new GameLogic();
            stayGame.makeInitialChoice(0); // Always choose door 0
            stayGame.stayWithCurrentChoice();
            if (stayGame.hasWon()) {
                stayWins++;
            }
            
            GameLogic switchGame = new GameLogic();
            switchGame.makeInitialChoice(0); // Always choose door 0
            switchGame.switchDoor();
            if (switchGame.hasWon()) {
                switchWins++;
            }
        }
        
        System.out.println("Simulation Results:");
        System.out.println("Stay strategy wins: " + stayWins + " (" + (stayWins * 100.0 / totalGames) + "%)");
        System.out.println("Switch strategy wins: " + switchWins + " (" + (switchWins * 100.0 / totalGames) + "%)");
    }
}