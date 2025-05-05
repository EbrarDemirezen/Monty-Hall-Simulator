package src.main.MontyHall;

import src.main.MontyHall.GameController; // Ensure this import is correct

public class HtmlGenerator {

    // Make sure these paths are correct relative to your static file handler context (/images)
    private static final String GOAT_IMAGE = "/images/image_goat.png";
    private static final String CAR_IMAGE = "/images/image_car.png";
    private static final String DOOR_1_IMAGE = "/images/door_1.png";
    private static final String DOOR_2_IMAGE = "/images/door_2.png";
    private static final String DOOR_3_IMAGE = "/images/door_3.png";

    public static String generateGamePage(GameController controller) {
        StringBuilder html = new StringBuilder();

        // --- HTML HEAD and CSS ---
        html.append("<!DOCTYPE html>\n");
        html.append("<html lang=\"en\">\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        html.append("    <title>Monty Hall Simulator</title>\n");
        html.append("    <link href=\"https://fonts.googleapis.com/css2?family=Bungee&family=Press+Start+2P&family=Open+Sans:wght@400;600&display=swap\" rel=\"stylesheet\">\n");
        html.append("    <style>\n");
        // --- General Styles ---
        html.append("        body {\n");
        html.append("            font-family: 'Open Sans', sans-serif;\n");
        html.append("            text-align: center;\n");
        html.append("            margin: 0;\n");
        html.append("            padding: 20px;\n");
        html.append("            background: url('/images/curtain_bg.jpg') no-repeat center center fixed;\n");
        html.append("            background-size: cover;\n");
        html.append("            min-height: 100vh;\n");
        html.append("            position: relative;\n");
        html.append("            color: #eee; /* Default text color for body */\n");
        html.append("        }\n");
        html.append("        body::before {\n");
        html.append("            content: '';\n");
        html.append("            position: fixed;\n");
        html.append("            top: 0;\n");
        html.append("            left: 0;\n");
        html.append("            width: 100%;\n");
        html.append("            height: 100%;\n");
        html.append("            background: rgba(0, 0, 0, 0.5);\n");
        html.append("            z-index: 1;\n");
        html.append("        }\n");
        html.append("        .game-container {\n");
        html.append("            position: relative;\n");
        html.append("            z-index: 2;\n");
        html.append("            margin: 30px auto;\n");
        html.append("            max-width: 1000px;\n");
        html.append("            background: rgba(0, 0, 0, 0.7);\n");
        html.append("            padding: 30px;\n");
        html.append("            border-radius: 20px;\n");
        html.append("            box-shadow: 0 0 50px rgba(255,215,0,0.2);\n");
        html.append("            border: 3px solid #FFD700;\n");
        html.append("            backdrop-filter: blur(5px);\n");
        html.append("        }\n");
        html.append("        h1 {\n");
        html.append("            position: relative;\n");
        html.append("            z-index: 2;\n");
        html.append("            font-family: 'Bungee', cursive;\n");
        html.append("            color: #FFD700;\n");
        html.append("            font-size: 3.5em;\n");
        html.append("            text-shadow: 0 0 10px rgba(0,0,0,0.8),\n");
        html.append("                         0 0 20px rgba(255,215,0,0.5),\n");
        html.append("                         0 0 30px rgba(255,215,0,0.3);\n");
        html.append("            margin-bottom: 10px;\n");
        html.append("            animation: glow 2s ease-in-out infinite alternate;\n");
        html.append("        }\n");
        html.append("        @keyframes glow {\n");
        html.append("            from { text-shadow: 0 0 10px rgba(255,215,0,0.5), 0 0 20px rgba(255,215,0,0.3); }\n");
        html.append("            to { text-shadow: 0 0 20px rgba(255,215,0,0.8), 0 0 30px rgba(255,215,0,0.6); }\n");
        html.append("        }\n");
        html.append("        .subtitle {\n");
        html.append("            font-family: 'Press Start 2P', cursive;\n");
        html.append("            color:rgb(42, 76, 230);\n");
        html.append("            font-size: 1em;\n");
        html.append("            margin-top: 0;\n");
        html.append("            text-shadow: 2px 2px 4px rgba(0,0,0,0.5);\n");
        html.append("        }\n");

        // --- Door Container and Wrapper Styles ---
        html.append("        .doors-container {\n");
        // Use inline style for width based on calculation below
        // Calculated width: (3 * doorWidth) + (2 * gap) = (3 * 180) + (2 * 20) = 580px
        html.append("            height: 280px; /* Increased from 220px to accommodate taller doors */\n");
        html.append("            margin: 40px auto; /* Increased top margin */\n");
        html.append("            position: relative; /* Crucial for absolute positioning of wrappers */\n");
        html.append("            perspective: 1200px; /* Apply 3D perspective to the container */\n");
        html.append("        }\n");
        html.append("        .door-wrapper {\n");
        html.append("            position: absolute;\n");
        html.append("            top: 0;\n");
        // 'left' is set via inline style in Java code
        html.append("            width: 180px;  /* Increased from 140px */\n");
        html.append("            height: 260px; /* Increased from 200px */\n");
        html.append("            transform-style: preserve-3d; /* Enable 3D positioning for children */\n");
        html.append("        }\n");

        // --- Door Content (Image) Styles ---
        html.append("        .door-content {\n");
        html.append("            position: absolute;\n");
        html.append("            top: 0;\n");
        html.append("            left: 0;\n");
        html.append("            width: 100%;\n");
        html.append("            height: 100%;\n");
        html.append("            z-index: 1; /* Behind the door */\n");
        html.append("            display: none; /* Hidden by default */\n");
        html.append("            align-items: center; /* Vertically center image */\n");
        html.append("            justify-content: center; /* Horizontally center image */\n");
        html.append("            background-color: rgba(30, 30, 30, 0.8); /* Optional dark background */\n");
        html.append("            border-radius: 5px; /* Match door shape if needed */\n");
        html.append("            overflow: hidden; /* Ensure image stays within bounds */\n");
        html.append("        }\n");
        html.append("        .door-content.visible {\n");
        html.append("            display: flex; /* Show the content */\n");
        html.append("            animation: contentAppear 0.4s 0.5s ease forwards; /* Fade in slightly after door opens */\n");
        html.append("            opacity: 0;\n");
        html.append("        }\n");
        html.append("        .door-content img {\n");
        html.append("            max-width: 90%;  /* Increased from 85% */\n");
        html.append("            max-height: 90%; /* Increased from 85% */\n");
        html.append("            object-fit: contain;\n");
        html.append("            display: block;\n");
        html.append("        }\n");

        // --- Door Styles ---
        html.append("        .door {\n");
        html.append("            position: absolute;\n");
        html.append("            top: 0;\n");
        html.append("            left: 0;\n");
        html.append("            width: 100%;\n");
        html.append("            height: 100%;\n");
        html.append("            background-size: contain;\n");
        html.append("            background-repeat: no-repeat;\n");
        html.append("            background-position: center;\n");
        html.append("            cursor: pointer;\n");
        html.append("            transform-origin: left center; /* Hinge on the left */\n");
        html.append("            transition: transform 0.8s ease-in-out, box-shadow 0.3s ease; /* Animate transform */\n");
        html.append("            z-index: 2; /* Door is on top of content */\n");
        html.append("            border: 3px solid transparent; /* For selection highlight */\n");
        html.append("            border-radius: 5px; /* Optional rounded corners */\n");
        html.append("            box-shadow: 0 4px 10px rgba(0,0,0,0.4); /* Add some depth */\n");
        html.append("        }\n");
        html.append("        .door.door-1 { background-image: url('" + DOOR_1_IMAGE + "'); }\n");
        html.append("        .door.door-2 { background-image: url('" + DOOR_2_IMAGE + "'); }\n");
        html.append("        .door.door-3 { background-image: url('" + DOOR_3_IMAGE + "'); }\n");
        html.append("        .door.selected {\n");
        html.append("             border-color: #f1c40f;\n");
        html.append("             box-shadow: 0 0 15px #f1c40f, 0 4px 10px rgba(0,0,0,0.4);\n");
        html.append("        }\n");
        // Only apply hover effect when the door is clickable (has onclick attribute)
        html.append("        .door[onclick]:hover {\n");
        html.append("             border-color: #f1c40f;\n");
        html.append("             box-shadow: 0 0 15px #f1c40f, 0 4px 10px rgba(0,0,0,0.4);\n");
        html.append("        }\n");

        // --- Door Animations ---
        html.append("        @keyframes openDoor {\n");
        html.append("            0% { transform: rotateY(0deg); }\n");
        html.append("            100% { transform: rotateY(-105deg); }\n"); // Rotate the door open
        html.append("        }\n");
        html.append("        @keyframes revealDoor {\n");
        html.append("            0% { transform: rotateY(0deg); }\n");
        html.append("            100% { transform: rotateY(-85deg); }\n");  // Rotate the door partly open
        html.append("        }\n");
        html.append("        @keyframes contentAppear {\n");
        html.append("             0% { opacity: 0; transform: scale(0.8); }\n");
        html.append("             100% { opacity: 1; transform: scale(1); }\n");
        html.append("         }\n");

        // Apply animations only to the .door element
        html.append("        .door.revealed {\n");
        html.append("            animation: revealDoor 0.8s ease-in-out forwards;\n");
        html.append("            cursor: default; /* Not clickable anymore */\n");
        html.append("        }\n");
        html.append("        .door.opened {\n");
        html.append("            animation: openDoor 1s ease-in-out forwards;\n");
        html.append("            cursor: default; /* Not clickable anymore */\n");
        html.append("        }\n");

        // --- Other UI Elements (Status, Controls, Result, Stats, Explanation, Tabs, Reset) ---
        html.append("        .status {\n");
        html.append("            font-family: 'Press Start 2P', cursive;\n");
        html.append("            font-size: 18px;\n");
        html.append("            line-height: 1.8;\n");
        html.append("            margin: 20px auto;\n");
        html.append("            padding: 20px;\n");
        html.append("            background: rgba(255,215,0,0.1);\n");
        html.append("            border: 2px solid #FFD700;\n");
        html.append("            border-radius: 10px;\n");
        html.append("            color: #FFD700;\n");
        html.append("            max-width: 800px;\n");
        html.append("            text-shadow: 2px 2px 4px rgba(0,0,0,0.5);\n");
        html.append("            box-shadow: 0 0 20px rgba(255,215,0,0.2);\n");
        html.append("        }\n");
        html.append("        .controls { margin: 40px 0 20px 0; }\n"); // Added margin-top
        html.append("        button { padding: 12px 25px; margin: 8px; font-size: 16px; font-family: 'Press Start 2P', cursive; cursor: pointer; background: linear-gradient(45deg, #FFD700, #FFA500); color: #000; border: none; border-radius: 8px; box-shadow: 0 4px 15px rgba(255,215,0,0.3); transition: all 0.3s ease; text-transform: uppercase; }\n");
        html.append("        button:hover:not(:disabled) { transform: translateY(-2px); box-shadow: 0 6px 20px rgba(255,215,0,0.4); background: linear-gradient(45deg, #FFA500, #FFD700); }\n");
        html.append("        button:disabled { background: #777; color: #bbb; cursor: not-allowed; transform: none; box-shadow: none; }\n");

        // --- Result Box ---
        html.append("        .result {\n");
        html.append("            background: linear-gradient(145deg, rgba(0,0,0,0.8), rgba(20,20,20,0.9));\n");
        html.append("            border: 2px solid #FFD700;\n");
        html.append("            border-radius: 15px;\n");
        html.append("            padding: 25px;\n");
        html.append("            margin: 20px auto;\n");
        html.append("            max-width: 800px;\n");
        html.append("            position: relative;\n");
        html.append("            overflow: hidden;\n");
        html.append("            box-shadow: 0 0 30px rgba(255,215,0,0.15);\n");
        html.append("            color: #eee;\n");
        html.append("        }\n");
        html.append("        .result h2 {\n");
        html.append("            font-family: 'Bungee', cursive;\n");
        html.append("            font-size: 32px;\n");
        html.append("            margin: 0 0 15px 0;\n");
        html.append("            text-shadow: 2px 2px 4px rgba(0,0,0,0.5);\n");
        // Animation removed from here, applied to win/lose specific
        html.append("        }\n");
        html.append("        .result p {\n");
        html.append("            font-family: 'Press Start 2P', cursive;\n");
        html.append("            font-size: 14px;\n");
        html.append("            margin: 10px 0;\n");
        html.append("            color: #FFD700;\n");
        html.append("            text-shadow: 1px 1px 2px rgba(0,0,0,0.5);\n");
        html.append("        }\n");
        html.append("        .result.win h2 { color: #4CAF50; animation: resultGlowWin 2s ease-in-out infinite alternate; }\n");
        html.append("        .result.lose h2 { color: #FF5252; animation: resultGlowLose 2s ease-in-out infinite alternate; }\n");
        html.append("        @keyframes resultGlowWin {\n");
        html.append("            from { text-shadow: 0 0 10px rgba(76,175,80,0.5), 0 0 20px rgba(76,175,80,0.3); }\n");
        html.append("            to { text-shadow: 0 0 20px rgba(76,175,80,0.8), 0 0 30px rgba(76,175,80,0.6); }\n");
        html.append("        }\n");
         html.append("        @keyframes resultGlowLose {\n");
        html.append("            from { text-shadow: 0 0 10px rgba(255,82,82,0.5), 0 0 20px rgba(255,82,82,0.3); }\n");
        html.append("            to { text-shadow: 0 0 20px rgba(255,82,82,0.8), 0 0 30px rgba(255,82,82,0.6); }\n");
        html.append("        }\n");

        // --- Tabs ---
        html.append("        .tab-container { margin-top: 40px; }\n");
        html.append("        .tab-buttons { display: flex; justify-content: center; margin-bottom: -2px; /* Overlap border */ }\n");
        html.append("        .tab-button {\n");
        html.append("           padding: 10px 25px;\n");
        html.append("           border: 2px solid transparent;\n");
        html.append("           border-bottom: none;\n");
        html.append("           background-color: rgba(0,0,0,0.3);\n");
        html.append("           color: #ccc;\n");
        html.append("           cursor: pointer;\n");
        html.append("           font-family: 'Press Start 2P', cursive;\n");
        html.append("           font-size: 14px;\n");
        html.append("           border-radius: 10px 10px 0 0;\n");
        html.append("           margin: 0 5px;\n");
        html.append("           transition: all 0.3s ease;\n");
        html.append("        }\n");
        html.append("        .tab-button:hover { background-color: rgba(255,215,0,0.2); color: #FFD700; }\n");
        html.append("        .tab-button.active {\n");
        html.append("           background: linear-gradient(145deg, rgba(44, 62, 80, 0.95), rgba(52, 73, 94, 0.95)); /* Match panel bg */\n");
        html.append("           color: #FFD700;\n");
        html.append("           border-color: #FFD700;\n");
        html.append("           box-shadow: 0 -5px 15px rgba(255,215,0,0.1);\n");
        html.append("        }\n");
        html.append("        .tab-content { display: none; }\n"); /* Hide inactive tabs */
        html.append("        .tab-content.active { display: block; }\n"); /* Show active tab */

        // --- Shared Panel Styles (Stats, Explanation) ---
        html.append("        .content-panel {\n"); // New common class for stats and explanation panels
        html.append("            background: linear-gradient(145deg,\n");
        html.append("                rgba(44, 62, 80, 0.95),\n"); // Dark blue/grey gradient
        html.append("                rgba(52, 73, 94, 0.95),\n");
        html.append("                rgba(44, 62, 80, 0.95)\n");
        html.append("            );\n");
        html.append("            border: 2px solid #FFD700;\n");
        html.append("            border-radius: 0 0 15px 15px; /* Rounded bottom corners */\n");
        html.append("            padding: 30px;\n"); // Increased padding
        html.append("            margin: 0 auto;\n"); // Margin handled by container
        html.append("            box-shadow: 0 10px 30px rgba(0,0,0,0.3),\n"); // Adjusted shadow
        html.append("                        inset 0 0 20px rgba(0,0,0,0.2);\n");
        html.append("            position: relative;\n");
        html.append("            overflow: hidden;\n");
        html.append("            color: #eee;\n"); // Default text color inside panels
        html.append("        }\n");

        // --- Statistics Panel Styles ---
        html.append("        .stats-header {\n");
        html.append("            font-family: 'Bungee', cursive;\n");
        html.append("            color: #FFD700;\n");
        html.append("            font-size: 28px;\n");
        html.append("            text-shadow: 0 0 10px rgba(255,215,0,0.5);\n");
        html.append("            margin-bottom: 5px;\n");
        html.append("            animation: glow 2s ease-in-out infinite alternate;\n");
        html.append("        }\n");
        html.append("        .stats-subtitle {\n");
        html.append("            font-family: 'Press Start 2P', cursive;\n");
        html.append("            color: #4FC3F7; /* Light blue */\n");
        html.append("            font-size: 12px;\n");
        html.append("            margin-bottom: 25px;\n");
        html.append("        }\n");
        html.append("        .games-played {\n");
        html.append("            font-family: 'Press Start 2P', cursive;\n");
        html.append("            color: #FFD700;\n");
        html.append("            font-size: 16px;\n");
        html.append("            padding: 15px;\n");
        html.append("            background: rgba(255,215,0,0.1);\n");
        html.append("            border-radius: 8px;\n");
        html.append("            margin-bottom: 25px;\n");
        html.append("            text-shadow: 1px 1px 2px rgba(0,0,0,0.5);\n");
        html.append("        }\n");
        html.append("        .strategy-cards {\n");
        html.append("            display: grid;\n");
        html.append("            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); /* Responsive */\n");
        html.append("            gap: 25px;\n");
        html.append("            margin-top: 20px;\n");
        html.append("        }\n");
        html.append("        .strategy-card {\n");
        html.append("            background: linear-gradient(145deg, #1e2a38, #2c3e50);\n"); // Darker card background
        html.append("            border: 1px solid rgba(255,215,0,0.3);\n");
        html.append("            border-radius: 12px;\n");
        html.append("            padding: 20px;\n");
        html.append("            transition: transform 0.3s ease, box-shadow 0.3s ease;\n");
        html.append("            box-shadow: 0 4px 10px rgba(0,0,0,0.3);\n");
        html.append("        }\n");
        html.append("        .strategy-card:hover { transform: translateY(-5px); box-shadow: 0 8px 20px rgba(0,0,0,0.4); }\n");
        html.append("        .strategy-title {\n");
        html.append("            font-family: 'Press Start 2P', cursive;\n");
        html.append("            font-size: 14px;\n");
        html.append("            color: #FFD700;\n");
        html.append("            margin-bottom: 15px;\n");
        html.append("            display: flex;\n");
        html.append("            align-items: center;\n");
        html.append("            gap: 10px;\n");
        html.append("        }\n");
        html.append("        .win-rate {\n");
        html.append("            font-size: 26px; /* Larger win rate */\n");
        html.append("            font-weight: bold;\n");
        html.append("            font-family: 'Bungee', cursive;\n"); // Use prominent font
        html.append("            color: #2ecc71; /* Green for win rate */\n");
        html.append("            margin: 10px 0;\n");
        html.append("            text-shadow: 1px 1px 2px rgba(0,0,0,0.5);\n");
        html.append("        }\n");
        html.append("        .stats-value {\n");
        html.append("            font-family: 'Open Sans', sans-serif;\n");
        html.append("            font-size: 15px;\n"); // Slightly smaller
        html.append("            color: #ccc;\n"); // Lighter text for details
        html.append("            margin: 5px 0;\n");
        html.append("        }\n");
        html.append("        .stats-divider {\n");
        html.append("            height: 2px;\n");
        html.append("            background: linear-gradient(90deg, transparent, rgba(255,215,0,0.3), transparent);\n");
        html.append("            margin: 40px 0;\n"); // Increased margin
        html.append("        }\n");

        // --- Bar Chart Styles ---
        html.append("        .performance-section .stats-header { font-size: 24px; margin-bottom: 25px; }\n");
        html.append("        .strategy-group { margin-bottom: 30px; position: relative; overflow: hidden; }\n");
        html.append("        .strategy-label {\n");
        html.append("            font-family: 'Press Start 2P', cursive;\n");
        html.append("            color: #FFD700;\n");
        html.append("            font-size: 14px;\n");
        html.append("            margin-bottom: 12px;\n");
        html.append("            text-shadow: 1px 1px 2px rgba(0,0,0,0.8);\n");
        html.append("            text-align: left;\n");
        html.append("        }\n");
        html.append("        .bar-container {\n");
        html.append("            height: 40px;\n");
        html.append("            display: flex;\n");
        html.append("            background: rgba(0,0,0,0.4);\n"); // Darker background for bars
        html.append("            border-radius: 8px;\n");
        html.append("            overflow: hidden;\n");
        html.append("            border: 2px solid #FFD700;\n");
        html.append("            box-shadow: inset 0 0 10px rgba(0,0,0,0.5);\n");
        html.append("        }\n");
        html.append("        .bar-wins {\n");
        html.append("            height: 100%;\n");
        html.append("            background: linear-gradient(90deg, #4CAF50, #81C784); /* Green gradient */\n");
        html.append("            transition: width 0.8s ease-in-out;\n");
        html.append("            display: flex;\n");
        html.append("            align-items: center;\n");
        html.append("            justify-content: center;\n");
        html.append("            position: relative;\n");
        html.append("            overflow: hidden;\n");
        html.append("        }\n");
        html.append("        .bar-losses {\n");
        html.append("            height: 100%;\n");
        html.append("            background: linear-gradient(90deg, #f44336, #e57373); /* Red gradient */\n");
        html.append("            transition: width 0.8s ease-in-out;\n");
        html.append("            display: flex;\n");
        html.append("            align-items: center;\n");
        html.append("            justify-content: center;\n");
        html.append("            position: relative;\n");
        html.append("        }\n");
        html.append("        .bar-text {\n");
        html.append("            color: white;\n");
        html.append("            font-family: 'Open Sans', sans-serif;\n");
        html.append("            font-weight: 600;\n");
        html.append("            font-size: 14px;\n");
        html.append("            padding: 0 15px;\n");
        html.append("            text-shadow: 1px 1px 2px rgba(0,0,0,0.8);\n");
        html.append("            white-space: nowrap; /* Prevent text wrapping */\n");
        html.append("        }\n");

        // --- Auto-Play Section ---
        html.append("        .auto-play-section .stats-header { font-size: 24px; margin-bottom: 15px; }\n");
        html.append("        .auto-play-section .stats-subtitle { margin-bottom: 20px; }\n");
        html.append("        .auto-play-buttons { display: flex; gap: 15px; justify-content: center; margin-top: 20px; flex-wrap: wrap; }\n");
        html.append("        .auto-play-buttons button {\n");
        html.append("            background: linear-gradient(145deg, #1a1a1a, #2c3e50);\n");
        html.append("            border: 2px solid #FFD700;\n");
        html.append("            padding: 12px 20px;\n");
        html.append("            color: #FFD700;\n");
        html.append("            font-family: 'Press Start 2P', cursive;\n");
        html.append("            font-size: 12px;\n");
        html.append("            transition: all 0.3s ease;\n");
        html.append("        }\n");
        html.append("        .auto-play-buttons button:hover {\n");
        html.append("            transform: translateY(-2px);\n");
        html.append("            box-shadow: 0 0 15px rgba(255,215,0,0.3);\n");
        html.append("            background: linear-gradient(145deg, #2c3e50, #1a1a1a);\n");
        html.append("        }\n");

        // --- Explanation Panel Styles ---
        html.append("        .explanation-title {\n");
        html.append("            font-family: 'Bungee', cursive;\n");
        html.append("            color: #FFD700;\n");
        html.append("            font-size: 28px;\n");
        html.append("            margin-bottom: 25px;\n"); // Increased margin
        html.append("            text-shadow: 0 0 10px rgba(255,215,0,0.5);\n");
        html.append("        }\n");
        html.append("        .explanation-text {\n");
        html.append("            font-family: 'Open Sans', sans-serif;\n");
        html.append("            color: #eee;\n");
        html.append("            font-size: 16px;\n");
        html.append("            line-height: 1.8;\n");
        html.append("            margin-bottom: 20px;\n"); // Increased margin
        html.append("            text-shadow: 1px 1px 1px rgba(0,0,0,0.2);\n");
        html.append("            text-align: left;\n"); // Left align text
        html.append("        }\n");
        html.append("        .explanation-text .highlight {\n");
        html.append("            color: #FFD700;\n");
        html.append("            font-weight: bold;\n");
        html.append("        }\n");
        html.append("        .explanation-section { margin-bottom: 30px; }\n");
        html.append("        .explanation-subtitle {\n");
        html.append("            font-family: 'Press Start 2P', cursive;\n");
        html.append("            color: #4FC3F7;\n");
        html.append("            font-size: 14px;\n");
        html.append("            margin: 25px 0 15px 0;\n"); // Adjusted margin
        html.append("            text-shadow: 1px 1px 2px rgba(0,0,0,0.5);\n");
        html.append("            text-align: left;\n");
        html.append("        }\n");
        html.append("        .probability-box {\n");
        html.append("            background: linear-gradient(145deg, #1a1a1a, #2c3e50);\n");
        html.append("            border: 2px solid rgba(255,215,0,0.5);\n");
        html.append("            border-radius: 12px;\n");
        html.append("            padding: 25px;\n"); // Increased padding
        html.append("            margin: 20px 0;\n");
        html.append("            font-family: 'Press Start 2P', cursive;\n");
        html.append("            font-size: 14px;\n");
        html.append("            color: #FFD700;\n");
        html.append("            line-height: 2.2;\n"); // Increased line height
        html.append("            text-shadow: 1px 1px 2px rgba(0,0,0,0.8);\n");
        html.append("            box-shadow: inset 0 0 15px rgba(0,0,0,0.4);\n"); // Inner shadow
        html.append("        }\n");
        html.append("        .probability-box .highlight {\n");
        html.append("            color: #4FC3F7;\n");
        html.append("            font-weight: bold;\n");
        html.append("            font-size: 16px;\n");
        html.append("            text-shadow: 0 0 10px rgba(79,195,247,0.5);\n");
        html.append("        }\n");

        // --- Reset Button Styles ---
        html.append("        .reset-container {\n");
        html.append("            position: fixed;\n");
        html.append("            top: 15px;\n"); // Adjusted position
        html.append("            right: 15px;\n");
        html.append("            z-index: 9999;\n");
        html.append("        }\n");
        html.append("        button.reset-button {\n");
        html.append("            background: linear-gradient(45deg, #ff6b6b, #fa8072);\n");
        html.append("            color: white;\n");
        html.append("            font-weight: bold;\n");
        html.append("            border: none;\n");
        html.append("            padding: 8px 16px;\n");
        html.append("            font-size: 12px;\n");
        html.append("            font-family: 'Open Sans', sans-serif; /* More readable font */\n");
        html.append("            border-radius: 4px;\n");
        html.append("            box-shadow: 0 2px 8px rgba(0,0,0,0.3);\n");
        html.append("            transition: all 0.3s ease;\n");
        html.append("            cursor: pointer;\n");
        html.append("        }\n");
        html.append("        button.reset-button:hover {\n");
        html.append("            background: linear-gradient(45deg, #fa8072, #ff6b6b);\n");
        html.append("            transform: translateY(-2px);\n");
        html.append("            box-shadow: 0 4px 15px rgba(0,0,0,0.3);\n");
        html.append("        }\n");

        // Add after the reset-button styles:
        html.append("        .sound-control {\n");
        html.append("            position: fixed;\n");
        html.append("            top: 15px;\n");
        html.append("            left: 15px;\n");
        html.append("            z-index: 9999;\n");
        html.append("            cursor: pointer;\n");
        html.append("            width: 40px;\n");
        html.append("            height: 40px;\n");
        html.append("            background: linear-gradient(145deg, #1a1a1a, #2c3e50);\n");
        html.append("            border: 2px solid #FFD700;\n");
        html.append("            border-radius: 50%;\n");
        html.append("            display: flex;\n");
        html.append("            align-items: center;\n");
        html.append("            justify-content: center;\n");
        html.append("            transition: all 0.3s ease;\n");
        html.append("            box-shadow: 0 2px 8px rgba(0,0,0,0.3);\n");
        html.append("        }\n");
        html.append("        .sound-control:hover {\n");
        html.append("            transform: translateY(-2px);\n");
        html.append("            box-shadow: 0 4px 15px rgba(255,215,0,0.3);\n");
        html.append("        }\n");
        html.append("        .sound-control svg {\n");
        html.append("            width: 24px;\n");
        html.append("            height: 24px;\n");
        html.append("            fill: #FFD700;\n");
        html.append("        }\n");
        html.append("        .sound-control.muted .unmuted-icon { display: none; }\n");
        html.append("        .sound-control:not(.muted) .muted-icon { display: none; }\n");

        html.append("    </style>\n");
        // --- JAVASCRIPT ---
        html.append("    <script>\n");
        // Basic Tab switching function
        html.append("        function toggleTab(tabId) {\n");
        html.append("            const contents = document.querySelectorAll('.tab-content');\n");
        html.append("            contents.forEach(c => c.classList.remove('active'));\n");
        html.append("            const buttons = document.querySelectorAll('.tab-button');\n");
        html.append("            buttons.forEach(b => b.classList.remove('active'));\n");
        html.append("            document.getElementById(tabId).classList.add('active');\n");
        html.append("            document.getElementById('btn-' + tabId).classList.add('active');\n");
        html.append("        }\n");
        // Function to trigger auto-play via page reload
        html.append("        function autoPlay(iterations) {\n");
        html.append("            window.location.href = '?action=auto-play&iterations=' + iterations;\n");
        html.append("        }\n");
        // Ensure the default tab is set on page load
        html.append("        document.addEventListener('DOMContentLoaded', () => {\n");
        html.append("            toggleTab('tab-stats'); // Default to statistics tab\n");
        html.append("        });\n");

        // Add JavaScript functions for playing sounds (add this in the <script> section):
        html.append("        function playSound(soundId) {\n");
        html.append("            if (localStorage.getItem('isMuted') !== 'true') {\n");
        html.append("                const sound = document.getElementById(soundId);\n");
        html.append("                if (sound) {\n");
        html.append("                    sound.currentTime = 0;\n");
        html.append("                    sound.play();\n");
        html.append("                }\n");
        html.append("            }\n");
        html.append("        }\n");

        // Add function to play multiple sounds with delay
        html.append("        function playGameOverSounds(hasWon) {\n");
        html.append("            playSound('doorSound');\n");
        html.append("            setTimeout(() => {\n");
        html.append("                if (hasWon) {\n");
        html.append("                    playSound('carSound');\n");
        html.append("                } else {\n");
        html.append("                    playSound('goatSound');\n");
        html.append("                }\n");
        html.append("            }, 1000); // Delay second sound by 1 second\n");
        html.append("        }\n");

        // Add autoplay attribute to ensure sounds can play
        html.append("        document.addEventListener('DOMContentLoaded', () => {\n");
        html.append("            toggleTab('tab-stats');\n");
        html.append("            // Enable sound autoplay\n");
        html.append("            const sounds = document.getElementsByTagName('audio');\n");
        html.append("            for (let sound of sounds) {\n");
        html.append("                sound.play().then(() => {\n");
        html.append("                    sound.pause();\n");
        html.append("                    sound.currentTime = 0;\n");
        html.append("                }).catch(e => console.log('Audio autoplay failed'));\n");
        html.append("            }\n");
        html.append("        });\n");

        // Add to the <script> section:
        html.append("        // Initialize mute state from localStorage\n");
        html.append("        let isMuted = localStorage.getItem('isMuted') === 'true';\n");
        html.append("        \n");
        html.append("        // Apply initial mute state on page load\n");
        html.append("        document.addEventListener('DOMContentLoaded', () => {\n");
        html.append("            const soundControl = document.getElementById('sound-control');\n");
        html.append("            if (isMuted) {\n");
        html.append("                soundControl.classList.add('muted');\n");
        html.append("                const sounds = document.getElementsByTagName('audio');\n");
        html.append("                for (let sound of sounds) {\n");
        html.append("                    sound.muted = true;\n");
        html.append("                }\n");
        html.append("            }\n");
        html.append("        });\n");
        html.append("        \n");
        html.append("        function toggleMute() {\n");
        html.append("            isMuted = !isMuted;\n");
        html.append("            localStorage.setItem('isMuted', isMuted);\n");
        html.append("            document.getElementById('sound-control').classList.toggle('muted');\n");
        html.append("            const sounds = document.getElementsByTagName('audio');\n");
        html.append("            for (let sound of sounds) {\n");
        html.append("                sound.muted = isMuted;\n");
        html.append("            }\n");
        html.append("        }\n");

        html.append("    </script>\n");
        // First, add the audio elements in the HTML head section after the existing scripts:
        html.append("    <audio id=\"goatSound\" src=\"/sounds/goat.mp3\"></audio>\n");
        html.append("    <audio id=\"carSound\" src=\"/sounds/car.mp3\"></audio>\n");
        html.append("    <audio id=\"doorSound\" src=\"/sounds/door.mp3\"></audio>\n");
        html.append("</head>\n");
        // --- HTML BODY ---
        html.append("<body>\n");
        html.append("    <h1>🐐 Monty Hall Simulator 🐐</h1>\n");
        html.append("    <p class=\"subtitle\">Should you Stay or Should you Switch?</p>\n");
        html.append("    <div class=\"game-container\">\n");

        // Reset Button (Placed early for fixed positioning)
        html.append("        <div class=\"reset-container\">\n");
        html.append("            <button class=\"reset-button\" onclick=\"location.href='?action=reset'\">Reset Stats</button>\n");
        html.append("        </div>\n");

        html.append("        <div id=\"sound-control\" class=\"sound-control\" onclick=\"toggleMute()\">\n");
        html.append("            <svg class=\"unmuted-icon\" viewBox=\"0 0 24 24\">\n");
        html.append("                <path d=\"M3 9v6h4l5 5V4L7 9H3zm13.5 3c0-1.77-1.02-3.29-2.5-4.03v8.05c1.48-.73 2.5-2.25 2.5-4.02zM14 3.23v2.06c2.89.86 5 3.54 5 6.71s-2.11 5.85-5 6.71v2.06c4.01-.91 7-4.49 7-8.77s-2.99-7.86-7-8.77z\"/>\n");
        html.append("            </svg>\n");
        html.append("            <svg class=\"muted-icon\" viewBox=\"0 0 24 24\">\n");
        html.append("                <path d=\"M16.5 12c0-1.77-1.02-3.29-2.5-4.03v2.21l2.45 2.45c.03-.2.05-.41.05-.63zm2.5 0c0 .94-.2 1.82-.54 2.64l1.51 1.51C20.63 14.91 21 13.5 21 12c0-4.28-2.99-7.86-7-8.77v2.06c2.89.86 5 3.54 5 6.71zM4.27 3L3 4.27 7.73 9H3v6h4l5 5v-6.73l4.25 4.25c-.67.52-1.42.93-2.25 1.18v2.06c1.38-.31 2.63-.95 3.69-1.81L19.73 21 21 19.73l-9-9L4.27 3zM12 4L9.91 6.09 12 8.18V4z\"/>\n");
        html.append("            </svg>\n");
        html.append("        </div>\n");

        // Game status message
        html.append("        <div class=\"status\" id=\"status\">\n");
        boolean gameStarted = controller.getGameState().getPlayerChoice() != -1;
        boolean doorRevealed = controller.getGameState().getRevealedDoor() != -1;
        boolean gameOver = controller.getGameState().isGameOver();
        int playerChoice = controller.getGameState().getPlayerChoice(); // Get player choice state
        int revealedDoor = controller.getGameState().getRevealedDoor(); // Get revealed door state
        int carPosition = controller.getGameState().getCarPosition();   // Get car position

        if (!gameStarted) {
            html.append("            Pick a Door to Begin! 👆\n");
        } else if (doorRevealed && !gameOver) {
            html.append("            Host reveals a goat behind Door ").append(revealedDoor + 1);
            html.append("! Do you want to <span class='highlight'>STAY</span> with Door ");
            html.append(playerChoice + 1).append(" or <span class='highlight'>SWITCH</span>?\n");
        } else if (gameOver) {
            html.append("            Game Over! The car was behind Door ")
                    .append(carPosition + 1).append(".\n");
        } else { // Player has chosen, but host hasn't revealed yet
             html.append("            You chose Door ").append(playerChoice + 1)
                     .append(". The host will now open another door...\n");
        }
        html.append("        </div>\n");

        // --- Doors ---
        int doorWidth = 180; // pixels
        int doorGap = 20;    // pixels
        int containerWidth = (3 * doorWidth) + (2 * doorGap);

        html.append("        <div class=\"doors-container\" style=\"width: ").append(containerWidth).append("px;\">\n");

        // NOTE: playerChoice, revealedDoor, carPosition, gameOver are already fetched above

        for (int i = 0; i < 3; i++) {
            int leftPosition = i * (doorWidth + doorGap);
            html.append("            <div class=\"door-wrapper\" style=\"left: ").append(leftPosition).append("px;\">\n");

            // --- 1. Door Content (Image - Placed first in DOM, behind door via z-index) ---
            // Content is shown if the door is revealed OR if the game is over
            String contentVisibilityClass = (revealedDoor == i || gameOver) ? " visible" : "";
            html.append("                <div class=\"door-content").append(contentVisibilityClass).append("\">\n");
            String imageSrc = (i == carPosition) ? CAR_IMAGE : GOAT_IMAGE;
            String imageAlt = (i == carPosition) ? "Car" : "Goat";
            html.append("                    <img src=\"").append(imageSrc).append("\" alt=\"").append(imageAlt).append("\">\n");
            html.append("                </div>\n");

            // --- 2. The Door itself ---
            String doorClasses = "door door-" + (i + 1);
            if (playerChoice == i && !gameOver) { // Show selection only before game over
                doorClasses += " selected";
            }
            // Apply reveal/open animation classes
            if (revealedDoor == i) {
                doorClasses += " revealed"; // Host revealing a goat
                // Only play reveal sounds if this is Monty's reveal (not game over)
                if (!gameOver) {
                    html.append("                <script>playSound('doorSound'); setTimeout(() => playSound('goatSound'), 800);</script>\n");
                }
            } else if (gameOver) { // Changed condition to open all doors at game over
                doorClasses += " opened";
            }

            // Enable/disable clicking
            // Clickable only if: game hasn't started OR player hasn't chosen yet
            String onClickAction = "";
            if (!gameOver && !doorRevealed && playerChoice == -1) {
                 onClickAction = " onclick=\"location.href='?action=choose&door=" + i + "'\"";
            }

            html.append("                <div class=\"").append(doorClasses).append("\"").append(onClickAction).append(">\n");
            // Background image is applied via CSS (.door-1, .door-2, .door-3)
            html.append("                </div>\n"); // End .door

            html.append("            </div>\n"); // End .door-wrapper
        }
        html.append("        </div>\n"); // End .doors-container

        // --- Game Controls ---
        html.append("        <div class=\"controls\">\n");
        if (!gameStarted) {
            // No buttons initially, rely on door clicks
            html.append("           <!-- Click a door above -->\n");
        } else if (doorRevealed && !gameOver) {
            // Offer Stay/Switch
            html.append("            <button onclick=\"location.href='?action=stay'\">Stay with Door ")
                    .append(playerChoice + 1).append("</button>\n");
            html.append("            <button onclick=\"location.href='?action=switch'\">Switch Door</button>\n");
        } else if (gameOver) {
            // Game over, offer Play Again
            html.append("            <button onclick=\"location.href='?action=play-again'\">Play Again?</button>\n");
        } else {
             // Player chose, waiting for reveal - show disabled buttons or no buttons
              html.append("           <button disabled>Stay</button>\n");
              html.append("           <button disabled>Switch</button>\n");
        }
        html.append("        </div>\n");

        // --- Game Result ---
        if (gameOver) {
            html.append("        <div class=\"result ").append(controller.getGameState().hasWon() ? "win" : "lose").append("\">\n");
            // Only play game over sounds for the player's final choice
            if (playerChoice == carPosition || playerChoice == (3 - revealedDoor - carPosition)) {
                html.append("        <script>playGameOverSounds(" + controller.getGameState().hasWon() + ");</script>\n");
            }
            if (controller.getGameState().hasWon()) {
                html.append("            <h2>🏆 Congratulations! You WON! 🎉</h2>\n");
            } else {
                html.append("            <h2>💔 Sorry! You LOST! 🐐</h2>\n");
            }
            // *** Use playerChoice here as it holds the final choice when gameOver is true ***
            html.append("            <p>You chose Door ").append(playerChoice + 1);
            html.append(" after ").append(controller.getGameState().hasSwitched() ? "switching" : "staying")
                  .append(". The car was behind Door ").append(carPosition + 1).append(".</p>\n");
            html.append("        </div>\n");
        }

        // --- Tab Navigation ---
        html.append("        <div class=\"tab-container\">\n");
        html.append("            <div class=\"tab-buttons\">\n");
        html.append("                <button id=\"btn-tab-stats\" class=\"tab-button\" onclick=\"toggleTab('tab-stats')\">Statistics</button>\n"); // No 'active' initially, set by JS
        html.append("                <button id=\"btn-tab-explanation\" class=\"tab-button\" onclick=\"toggleTab('tab-explanation')\">Explanation</button>\n");
        html.append("            </div>\n");

        // --- Statistics Tab Content ---
        html.append("            <div id=\"tab-stats\" class=\"tab-content\">\n"); // No 'active' initially
        html.append("                <div class=\"content-panel\">\n"); // Use common panel class
        // Game Statistics Section
        html.append("                    <div class=\"stats-header\">Game Statistics</div>\n");
        html.append("                    <div class=\"stats-subtitle\">Track Your Success Rate Over Time</div>\n");
        html.append("                    <div class=\"games-played\">🎮 Games Played: " + controller.getGamesPlayed() + "</div>\n");
        html.append("                    <div class=\"strategy-cards\">\n");
        html.append("                        <div class=\"strategy-card\">\n");
        html.append("                            <div class=\"strategy-title\">🚪 Stay Strategy</div>\n");
        html.append("                            <div class=\"win-rate\">" + String.format("%.1f%%", controller.getStayWinRate()) + "</div>\n"); // Added % sign
        html.append("                            <div class=\"stats-value\">Wins: " + controller.getStayWins() + " / " + controller.getStayGames() + "</div>\n");
        html.append("                        </div>\n");
        html.append("                        <div class=\"strategy-card\">\n");
        html.append("                            <div class=\"strategy-title\">🔄 Switch Strategy</div>\n");
        html.append("                            <div class=\"win-rate\">" + String.format("%.1f%%", controller.getSwitchWinRate()) + "</div>\n"); // Added % sign
        html.append("                            <div class=\"stats-value\">Wins: " + controller.getSwitchWins() + " / " + controller.getSwitchGames() + "</div>\n");
        html.append("                        </div>\n");
        html.append("                    </div>\n");

        html.append("                    <div class=\"stats-divider\"></div>\n");

        // Performance per Strategy Section (Bar Chart)
        html.append("                    <div class=\"performance-section\">\n");
        html.append("                        <div class=\"stats-header\">Performance per Strategy</div>\n");
        if (controller.getStayGames() > 0 || controller.getSwitchGames() > 0) { // Only show bars if games played
            html.append("                        <div class=\"strategy-group\">\n");
            html.append("                            <div class=\"strategy-label\">Stay Strategy 🔒</div>\n");
            html.append("                            <div class=\"bar-container\">\n");
            double stayWinWidth = controller.getStayWinRate();
            double stayLossWidth = (controller.getStayGames() > 0) ? (100.0 - stayWinWidth) : 0;
            html.append("                                <div class=\"bar-wins\" style=\"width: " + stayWinWidth + "%;\">\n");
            if(stayWinWidth > 15) html.append("                                    <span class=\"bar-text\">" + controller.getStayWins() + " Wins (" + String.format("%.1f%%", stayWinWidth) + ")</span>\n");
            else if(stayWinWidth > 0) html.append("<span class=\"bar-text\">" + controller.getStayWins() + " W</span>"); // Shorter text for small bars
            html.append("                                </div>\n");
            html.append("                                <div class=\"bar-losses\" style=\"width: " + stayLossWidth + "%;\">\n");
             if(stayLossWidth > 15) html.append("                                   <span class=\"bar-text\">" + (controller.getStayGames() - controller.getStayWins()) + " Losses (" + String.format("%.1f%%", stayLossWidth) + ")</span>\n");
             else if(stayLossWidth > 0) html.append("<span class=\"bar-text\">" + (controller.getStayGames() - controller.getStayWins()) + " L</span>"); // Shorter text
            html.append("                                </div>\n");
            html.append("                            </div>\n");
            html.append("                        </div>\n");
            html.append("                        <div class=\"strategy-group\">\n");
            html.append("                            <div class=\"strategy-label\">Switch Strategy 🔄</div>\n");
            html.append("                            <div class=\"bar-container\">\n");
            double switchWinWidth = controller.getSwitchWinRate();
            double switchLossWidth = (controller.getSwitchGames() > 0) ? (100.0 - switchWinWidth) : 0;
            html.append("                                <div class=\"bar-wins\" style=\"width: " + switchWinWidth + "%;\">\n");
             if(switchWinWidth > 15) html.append("                                   <span class=\"bar-text\">" + controller.getSwitchWins() + " Wins (" + String.format("%.1f%%", switchWinWidth) + ")</span>\n");
             else if(switchWinWidth > 0) html.append("<span class=\"bar-text\">" + controller.getSwitchWins() + " W</span>");
            html.append("                                </div>\n");
            html.append("                                <div class=\"bar-losses\" style=\"width: " + switchLossWidth + "%;\">\n");
             if(switchLossWidth > 15) html.append("                                   <span class=\"bar-text\">" + (controller.getSwitchGames() - controller.getSwitchWins()) + " Losses (" + String.format("%.1f%%", switchLossWidth) + ")</span>\n");
             else if(switchLossWidth > 0) html.append("<span class=\"bar-text\">" + (controller.getSwitchGames() - controller.getSwitchWins()) + " L</span>");
            html.append("                                </div>\n");
            html.append("                            </div>\n");
            html.append("                        </div>\n");
        } else {
             html.append("                      <p style='font-family: \"Press Start 2P\", cursive; font-size: 12px; color: #aaa;'>Play some games to see the performance breakdown!</p>\n");
        }
        html.append("                    </div>\n");

        html.append("                    <div class=\"stats-divider\"></div>\n");

        // Auto-Play Section
        html.append("                    <div class=\"auto-play-section\">\n");
        html.append("                        <div class=\"stats-header\">Auto-Play Simulation</div>\n");
        html.append("                        <div class=\"stats-subtitle\">Run Multiple Games Automatically (Resets Current Game)</div>\n");
        html.append("                        <div class=\"auto-play-buttons\">\n");
        html.append("                            <button onclick=\"autoPlay(10)\">Run 10 Games</button>\n");
        html.append("                            <button onclick=\"autoPlay(100)\">Run 100 Games</button>\n");
        html.append("                            <button onclick=\"autoPlay(1000)\">Run 1000 Games</button>\n");
        html.append("                        </div>\n");
        html.append("                    </div>\n");

        html.append("                </div>\n"); // End .content-panel (for stats)
        html.append("            </div>\n"); // End #tab-stats

        // --- Explanation Tab Content ---
        html.append("            <div id=\"tab-explanation\" class=\"tab-content\">\n"); // No 'active' initially
        html.append("                <div class=\"content-panel\">\n"); // Use common panel class
        html.append("                    <div class=\"explanation-title\">The Monty Hall Problem</div>\n");
        html.append("                    <div class=\"explanation-text\">\n");
        html.append("                        This is a famous probability puzzle based on the American TV game show \"Let's Make a Deal\", named after its original host, Monty Hall.\n");
        html.append("                    </div>\n");

        html.append("                    <div class=\"explanation-section\">\n");
        html.append("                        <div class=\"explanation-subtitle\">The Setup:</div>\n");
        html.append("                        <div class=\"explanation-text\">\n");
        html.append("                           ▸ You are presented with <span class=\"highlight\">three closed doors</span>.<br>\n");
        html.append("                           ▸ Behind one door is a <span class=\"highlight\">car</span> 🚗 (the prize you want!).<br>\n");
        html.append("                           ▸ Behind the other two doors are <span class=\"highlight\">goats</span> 🐐.<br>\n");
        html.append("                           ▸ You pick one door (e.g., Door 1), but it remains closed.\n");
        html.append("                        </div>\n");
        html.append("                    </div>\n");

         html.append("                    <div class=\"explanation-section\">\n");
        html.append("                        <div class=\"explanation-subtitle\">The Host's Turn:</div>\n");
        html.append("                        <div class=\"explanation-text\">\n");
        html.append("                           ▸ The host, who <span class=\"highlight\">knows</span> where the car is, opens one of the *other* doors (e.g., Door 3) that you *didn't* pick.<br>\n");
        html.append("                           ▸ Crucially, the host <span class=\"highlight\">always opens a door with a goat</span>.\n");
        html.append("                        </div>\n");
        html.append("                    </div>\n");

        html.append("                    <div class=\"explanation-section\">\n");
        html.append("                        <div class=\"explanation-subtitle\">The Choice:</div>\n");
        html.append("                        <div class=\"explanation-text\">\n");
        html.append("                           ▸ The host then asks you: \"Do you want to <span class=\"highlight\">SWITCH</span> your choice to the other remaining closed door (e.g., Door 2), or <span class=\"highlight\">STAY</span> with your original choice (Door 1)?\"\n");
        html.append("                        </div>\n");
        html.append("                    </div>\n");

        html.append("                    <div class=\"explanation-section\">\n");
        html.append("                        <div class=\"explanation-subtitle\">The Counter-Intuitive Answer:</div>\n");
        html.append("                        <div class=\"explanation-text\">\n");
        html.append("                           It feels like a 50/50 chance now, but mathematically, you are <span class=\"highlight\">better off switching</span>!\n");
        html.append("                        </div>\n");
        html.append("                        <div class=\"probability-box\">\n");
        html.append("                           🔹 If you <span class=\"highlight\">STAY</span> with your first choice:<br>\n");
        html.append("                              Win Probability: <span class=\"highlight\">1/3 (≈33.3%)</span><br>\n");
        html.append("                           🔹 If you <span class=\"highlight\">SWITCH</span> to the other closed door:<br>\n");
        html.append("                              Win Probability: <span class=\"highlight\">2/3 (≈66.7%)</span>\n");
        html.append("                        </div>\n");
        html.append("                    </div>\n");

        html.append("                    <div class=\"explanation-section\">\n");
        html.append("                        <div class=\"explanation-subtitle\">Why Switching Works:</div>\n");
        html.append("                        <div class=\"explanation-text\">\n");
        html.append("                           ▸ Your initial choice has a 1/3 chance of being the car.<br>\n");
        html.append("                           ▸ This means there's a 2/3 chance the car is behind one of the *other* two doors.<br>\n");
        html.append("                           ▸ The host then <span class=\"highlight\">eliminates one</span> of those other doors (by showing you a goat). This doesn't change the initial 2/3 probability that the car was in that group of two.<br>\n");
        html.append("                           ▸ Since the host removed a goat door, that entire 2/3 probability is now concentrated on the <span class=\"highlight\">single remaining unopened door</span> that you didn't initially pick.<br>\n");
        html.append("                           ▸ Therefore, switching gives you that 2/3 chance of winning.\n");
        html.append("                        </div>\n");
        html.append("                    </div>\n");

         html.append("                    <div class=\"explanation-section\">\n");
        html.append("                        <div class=\"explanation-subtitle\">Think About 100 Doors:</div>\n");
        html.append("                        <div class=\"explanation-text\">\n");
        html.append("                           Imagine 100 doors. You pick Door 1. The host opens 98 other doors, all showing goats, leaving only your Door 1 and Door 74. The car is behind one of them. Your initial pick (Door 1) only ever had a 1/100 chance. The host's action concentrated the remaining 99/100 probability onto Door 74. Would you switch now? <span class=\"highlight\">Absolutely!</span> The 3-door case works exactly the same way.\n");
        html.append("                        </div>\n");
        html.append("                    </div>\n");

        html.append("                    <div class=\"stats-divider\"></div>\n");
        html.append("                    <div class=\"explanation-text\" style='text-align: center;'>\n");
        html.append("                        Use the <span class=\"highlight\">Statistics</span> tab and the <span class=\"highlight\">Auto-Play</span> feature to see this probability in action!\n");
        html.append("                    </div>\n");
        html.append("                </div>\n"); // End .content-panel (for explanation)
        html.append("            </div>\n"); // End #tab-explanation

        html.append("        </div>\n"); // End .tab-container

        html.append("    </div>\n"); // End .game-container
        html.append("</body>\n");
        html.append("</html>\n");

        return html.toString();
    }
}