package src.main.MontyHall;

public class HtmlGenerator {

    private static final String GOAT_IMAGE = "/images/image_goat.png"; // Add your image URL here
    private static final String CAR_IMAGE = "/images/image_car.png"; // Add your image URL here
    private static final String DOOR_1_IMAGE = "/images/door_1.png";
    private static final String DOOR_2_IMAGE = "/images/door_2.png";
    private static final String DOOR_3_IMAGE = "/images/door_3.png";

    public static String generateGamePage(GameController controller) {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>\n");
        html.append("<html lang=\"en\">\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        html.append("    <title>Monty Hall Simulator</title>\n");
        html.append("    <style>\n");
        html.append(
                "        body { font-family: Arial, sans-serif; text-align: center; margin: 0; padding: 20px; background-color: #f5f5f5; }\n");
        html.append("        h1 { color: #2c3e50; margin-bottom: 10px; }\n");
        html.append("        .subtitle { color: #7f8c8d; margin-top: 0; }\n");
        html.append(
                "        .game-container { margin: 30px auto; max-width: 1000px; background-color: white; padding: 20px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }\n");
        html.append(
                "        .doors-container { display: flex; justify-content: center; gap: 20px; flex-wrap: wrap; margin: 30px 0; }\n");
                
                html.append(
                    "        .door { width: 170px; height: 260px; background-size: contain; background-repeat: no-repeat; background-position: center; cursor: pointer; position: relative; transition: all 0.3s ease; margin: 10px; }\n");
                            
        html.append("        .door.door-1 { background-image: url('" + DOOR_1_IMAGE + "'); }\n");
        html.append("        .door.door-2 { background-image: url('" + DOOR_2_IMAGE + "'); }\n");
        html.append("        .door.door-3 { background-image: url('" + DOOR_3_IMAGE + "'); }\n");
        html.append("        .door.selected { border-color: #f1c40f; box-shadow: 0 0 15px #f1c40f; }\n");
        html.append("        .door.revealed { transform: perspective(1200px) rotateY(30deg); }\n");
        html.append("        .door.revealed .door-content { display: block; animation: popIn 0.5s ease; }\n");
        html.append("        .door.opened { transform: perspective(1200px) rotateY(30deg); }\n");
        // html.append(" .door-number { position: absolute; top: 50%; left: 50%;
        // transform: translate(-50%, -50%); font-size: 24px; color: white; }\n");
        html.append(
                "        .door-content { display: none; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); font-size: 40px; background-color: transparent; }\n");
        html.append(
                "        .door-content img { max-width: 100px; max-height: 100px; object-fit: contain; background-color: transparent; }\n");
        html.append("        .door.opened .door-content { display: block; animation: popIn 0.5s ease; }\n");
        html.append(
                "        @keyframes popIn { 0% { transform: translate(-50%, -50%) scale(0); } 100% { transform: translate(-50%, -50%) scale(1); } }\n");
        html.append("        .controls { margin: 20px 0; }\n");
        html.append(
                "        button { padding: 10px 20px; margin: 5px; font-size: 16px; cursor: pointer; background-color: #3498db; color: white; border: none; border-radius: 5px; transition: background-color 0.2s; }\n");
        html.append("        button:hover { background-color:rgb(185, 41, 87); }\n");
        html.append("        button:disabled { background-color: #95a5a6; cursor: not-allowed; }\n");
        html.append(
                "        .status { font-size: 18px; margin: 20px 0; min-height: 50px; padding: 10px; background-color: #ecf0f1; border-radius: 5px; }\n");
        html.append("        .result { margin: 20px 0; padding: 15px; border-radius: 5px; }\n");
        html.append("        .win { background-color: #d5f5e3; color: #27ae60; }\n");
        html.append("        .lose { background-color: #fadbd8; color: #c0392b; }\n");
        html.append(
                "        .statistics { margin-top: 30px; padding: 15px; background-color: #f8f9fa; border-radius: 5px; }\n");
        html.append(
                "        .stat-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; margin-top: 15px; }\n");
        html.append("        .stat-box { padding: 15px; border-radius: 5px; }\n");
        html.append("        .stay-stats { background-color: #e8f4f8; }\n");
        html.append("        .switch-stats { background-color: #e8f8ef; }\n");
        html.append(
                "        .explanation { margin-top: 30px; text-align: left; padding: 20px; background-color: #f9f9f9; border-radius: 5px; }\n");
        html.append("        .explanation h3 { margin-top: 0; }\n");
        html.append("        .tab-container { margin-top: 20px; }\n");
        html.append("        .tab-buttons { display: flex; justify-content: center; }\n");
        html.append(
                "        .tab-button { padding: 10px 20px; border: none; background-color: #ddd; cursor: pointer; }\n");
        html.append("        .tab-button.active { background-color: #3498db; color: white; }\n");
        html.append("        .tab-content { display: none; padding: 20px; background-color: #f1f1f1; }\n");
        html.append("        .tab-content.active { display: block; }\n");
        html.append(
                "        .options { margin: 20px 0; padding: 15px; background-color: #f8f9fa; border-radius: 5px; text-align: left; }\n");
        html.append("        .options label { margin-right: 10px; }\n");
        html.append(
                "        .auto-play { margin-top: 20px; padding: 15px; background-color: #e8f8f8; border-radius: 5px; }\n");
        html.append("    </style>\n");
        html.append("    <script>\n");
        html.append("        function toggleTab(tabId) {\n");
        html.append("            const tabs = document.getElementsByClassName('tab-content');\n");
        html.append("            for (let i = 0; i < tabs.length; i++) {\n");
        html.append("                tabs[i].classList.remove('active');\n");
        html.append("            }\n");
        html.append("            const buttons = document.getElementsByClassName('tab-button');\n");
        html.append("            for (let i = 0; i < buttons.length; i++) {\n");
        html.append("                buttons[i].classList.remove('active');\n");
        html.append("            }\n");
        html.append("            document.getElementById(tabId).classList.add('active');\n");
        html.append("            document.getElementById('btn-'+tabId).classList.add('active');\n");
        html.append("        }\n");
        html.append("        function autoPlay(iterations) {\n");
        html.append("            // Start with a reset\n");
        html.append("            window.location.href = '?action=auto-play&iterations=' + iterations;\n");
        html.append("        }\n");
        html.append("        function setDoorCount(count) {\n");
        html.append("            window.location.href = '?action=set-doors&count=' + count;\n");
        html.append("        }\n");
        html.append("    </script>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("    <h1>🐐 Monty Hall Simulator 🐐</h1>\n");
        html.append("    <p class=\"subtitle\">Test your probability intuition with the famous paradox</p>\n");
        html.append("    <div class=\"game-container\">\n");

        // Game status message
        html.append("        <div class=\"status\" id=\"status\">\n");
        boolean gameStarted = controller.getGameState().getPlayerChoice() != -1;
        boolean doorRevealed = controller.getGameState().getRevealedDoor() != -1;
        boolean gameOver = controller.getGameState().isGameOver();

        if (!gameStarted) {
            html.append("            Click on a door to start the game!\n");
        } else if (doorRevealed && !gameOver) {
            html.append("            Door ").append(controller.getGameState().getRevealedDoor() + 1);
            html.append(" has been revealed to contain a goat! Will you stay with Door ");
            html.append(controller.getGameState().getPlayerChoice() + 1).append(" or switch?\n");
        } else if (gameOver) {
            html.append("            Game over! The car was behind Door ")
                    .append(controller.getGameState().getCarPosition() + 1).append(".\n");
        }
        html.append("        </div>\n");

        // Doors
        html.append("        <div class=\"doors-container\">\n");

        int playerChoice = controller.getGameState().getPlayerChoice();
        int revealedDoor = controller.getGameState().getRevealedDoor();
        int carPosition = controller.getGameState().getCarPosition();

        for (int i = 0; i < controller.getDoorCount(); i++) {
            html.append("            <div class=\"door door-").append(i + 1);
            if (playerChoice == i)
                html.append(" selected");
            if (revealedDoor == i)
                html.append(" revealed");
            if (gameOver)
                html.append(" opened");
            html.append("\" onclick=\"location.href='?action=choose&door=").append(i).append("'\">\n");
            // html.append(" <div class=\"door-number\">").append(i + 1).append("</div>\n");

            // Door content (car or goat)
            html.append("                <div class=\"door-content\">\n");
            if (gameOver || revealedDoor == i) {
                html.append("                    <img src=\"").append(i == carPosition ? CAR_IMAGE : GOAT_IMAGE)
                        .append("\" alt=\"").append(i == carPosition ? "Car" : "Goat").append("\">\n");
            }
            html.append("                </div>\n");

            html.append("            </div>\n");
        }

        html.append("        </div>\n");

        // Game controls
        html.append("        <div class=\"controls\">\n");

        if (!gameStarted) {
            // Initial state - no buttons needed
        } else if (doorRevealed && !gameOver) {
            // After door reveal, before final choice
            html.append("            <button onclick=\"location.href='?action=stay'\">Stay with Door ")
                    .append(playerChoice + 1).append("</button>\n");
            html.append("            <button onclick=\"location.href='?action=switch'\">Switch Door</button>\n");
        } else if (gameOver) {
            // Game over - reset button
            html.append("            <button onclick=\"location.href='?action=reset'\">Play Again</button>\n");
        }

        html.append("        </div>\n");

        // Game result (if game is over)
        if (gameOver) {
            html.append("        <div class=\"result ").append(controller.getGameState().hasWon() ? "win" : "lose")
                    .append("\">\n");
            if (controller.getGameState().hasWon()) {
                html.append("            <h2>Congratulations! You WON! 🎉</h2>\n");
            } else {
                html.append("            <h2>Sorry! You LOST! 😢</h2>\n");
            }
            html.append("            <p>You ").append(
                    controller.getGameState().hasSwitched() ? "switched doors" : "stayed with your initial choice")
                    .append(".</p>\n");
            html.append("        </div>\n");
        }

        // Tab navigation for additional content
        html.append("        <div class=\"tab-container\">\n");
        html.append("            <div class=\"tab-buttons\">\n");
        html.append(
                "                <button id=\"btn-tab-stats\" class=\"tab-button active\" onclick=\"toggleTab('tab-stats')\">Statistics</button>\n");
        html.append(
                "                <button id=\"btn-tab-options\" class=\"tab-button\" onclick=\"toggleTab('tab-options')\">Options</button>\n");
        html.append(
                "                <button id=\"btn-tab-explanation\" class=\"tab-button\" onclick=\"toggleTab('tab-explanation')\">Explanation</button>\n");
        html.append("            </div>\n");

        // Statistics tab
        html.append("            <div id=\"tab-stats\" class=\"tab-content active\">\n");
        html.append("                <h3>Game Statistics</h3>\n");
        html.append("                <p>Games played: ").append(controller.getGamesPlayed()).append("</p>\n");
        html.append("                <div class=\"stat-grid\">\n");
        html.append("                    <div class=\"stat-box stay-stats\">\n");
        html.append("                        <h4>Stay Strategy</h4>\n");
        html.append("                        <p>Wins: ").append(controller.getStayWins()).append("</p>\n");
        html.append("                        <p>Win rate: ").append(String.format("%.1f", controller.getStayWinRate()))
                .append("%</p>\n");
        html.append("                    </div>\n");
        html.append("                    <div class=\"stat-box switch-stats\">\n");
        html.append("                        <h4>Switch Strategy</h4>\n");
        html.append("                        <p>Wins: ").append(controller.getSwitchWins()).append("</p>\n");
        html.append("                        <p>Win rate: ")
                .append(String.format("%.1f", controller.getSwitchWinRate())).append("%</p>\n");
        html.append("                    </div>\n");
        html.append("                </div>\n");

        // Auto-play section
        html.append("                <div class=\"auto-play\">\n");
        html.append("                    <h4>Auto-Play Simulation</h4>\n");
        html.append(
                "                    <p>Run multiple games automatically to see the probabilities in action:</p>\n");
        html.append("                    <button onclick=\"autoPlay(10)\">Run 10 Games</button>\n");
        html.append("                    <button onclick=\"autoPlay(100)\">Run 100 Games</button>\n");
        html.append("                    <button onclick=\"autoPlay(1000)\">Run 1000 Games</button>\n");
        html.append("                </div>\n");
        html.append("            </div>\n");

        // Options tab
        html.append("            <div id=\"tab-options\" class=\"tab-content\">\n");
        html.append("                <h3>Game Options</h3>\n");
        html.append("                <div class=\"options\">\n");
        html.append("                    <h4>Number of Doors:</h4>\n");
        html.append("                    <p>Change the number of doors to experiment with different scenarios:</p>\n");
        html.append("                    <button onclick=\"setDoorCount(3)\">3 Doors (Classic)</button>\n");
        html.append("                    <button onclick=\"setDoorCount(4)\">4 Doors</button>\n");
        html.append("                    <button onclick=\"setDoorCount(5)\">5 Doors</button>\n");
        html.append("                </div>\n");
        html.append("            </div>\n");

        // Explanation tab
        html.append("            <div id=\"tab-explanation\" class=\"tab-content\">\n");
        html.append("                <div class=\"explanation\">\n");
        html.append("                    <h3>The Monty Hall Problem Explained</h3>\n");
        html.append(
                "                    <p>The Monty Hall problem is a famous probability puzzle named after the host of the game show \"Let's Make a Deal.\"</p>\n");
        html.append("                    <h4>The Scenario:</h4>\n");
        html.append("                    <ol>\n");
        html.append(
                "                        <li>There are three doors. Behind one door is a car (the prize), and behind the other two are goats.</li>\n");
        html.append("                        <li>You pick a door, say door 1.</li>\n");
        html.append(
                "                        <li>The host, who knows what's behind each door, opens one of the other doors, say door 3, to reveal a goat.</li>\n");
        html.append(
                "                        <li>The host then asks if you want to switch your choice to the remaining door (door 2) or stick with your original choice (door 1).</li>\n");
        html.append("                    </ol>\n");
        html.append("                    <h4>The Paradox:</h4>\n");
        html.append(
                "                    <p>Intuitively, many people think it doesn't matter whether you switch or not, assuming the probability is 50/50. However, mathematical analysis shows that:</p>\n");
        html.append("                    <ul>\n");
        html.append(
                "                        <li>If you <strong>stay</strong> with your initial choice, your probability of winning is 1/3 (about 33%).</li>\n");
        html.append(
                "                        <li>If you <strong>switch</strong> to the other door, your probability of winning is 2/3 (about 67%).</li>\n");
        html.append("                    </ul>\n");
        html.append("                    <h4>Why Switching is Better:</h4>\n");
        html.append("                    <p>Think of it this way:</p>\n");
        html.append("                    <ul>\n");
        html.append(
                "                        <li>When you make your initial choice, there's a 1/3 chance you picked the car and a 2/3 chance you picked a goat.</li>\n");
        html.append(
                "                        <li>If you initially picked a goat (2/3 chance), then switching will ALWAYS lead you to the car.</li>\n");
        html.append(
                "                        <li>If you initially picked the car (1/3 chance), then switching will lead you to a goat.</li>\n");
        html.append("                    </ul>\n");
        html.append(
                "                    <p>So by switching, you win whenever your initial choice was a goat, which happens 2/3 of the time!</p>\n");
        html.append(
                "                    <p>Play the game multiple times to see the probabilities in action, or use the auto-play feature to run many simulations.</p>\n");
        html.append("                </div>\n");
        html.append("            </div>\n");
        html.append("        </div>\n");

        html.append("    </div>\n");
        html.append("</body>\n");
        html.append("</html>\n");

        return html.toString();
    }
}