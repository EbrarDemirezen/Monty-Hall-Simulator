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
        html.append("    <link href=\"https://fonts.googleapis.com/css2?family=Bungee&family=Press+Start+2P&family=Open+Sans:wght@400;600&display=swap\" rel=\"stylesheet\">\n");
        html.append("    <style>\n");
        html.append("        @keyframes spotlight {\n");
        html.append("            0%, 100% { background-position: 0% 0%; }\n");
        html.append("            50% { background-position: 100% 100%; }\n");
        html.append("        }\n");
        html.append("        body {\n");
        html.append("            font-family: 'Open Sans', sans-serif;\n");
        html.append("            text-align: center;\n");
        html.append("            margin: 0;\n");
        html.append("            padding: 20px;\n");
        html.append("            background: url('/images/curtain_bg.jpg') no-repeat center center fixed;\n");
        html.append("            background-size: cover;\n");
        html.append("            min-height: 100vh;\n");
        html.append("            position: relative;\n");
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

        html.append("        .doors-container { display: flex; justify-content: center; gap: 20px; flex-wrap: wrap; margin: 30px 0; }\n");
        //!       
        html.append("        .door { width: 170px; height: 260px; background-size: contain; background-repeat: no-repeat; background-position: center; cursor: pointer; position: relative; transition: all 0.3s ease; margin: 10px; }\n");
        //!
        // Only apply hover effect when no door is selected (before first choice)
        html.append("        .doors-container:not(:has(.selected)) .door:hover { border: 4px solid #f1c40f; box-shadow: 0 0 15px #f1c40f; }\n");

        //! Red marked lines indicate the changed codes in this current branch.
        
        //!
        // html.append("        .door:hover { border: 4px solid #f1c40f; box-shadow: 0 0 15px #f1c40f; }\n");
                            
        html.append("        .door.door-1 { background-image: url('" + DOOR_1_IMAGE + "'); }\n");
        html.append("        .door.door-2 { background-image: url('" + DOOR_2_IMAGE + "'); }\n");
        html.append("        .door.door-3 { background-image: url('" + DOOR_3_IMAGE + "'); }\n");
        html.append("        .door.selected { border-color: #f1c40f; box-shadow: 0 0 15px #f1c40f; }\n");
        html.append("        .door.revealed { transform: perspective(700px) rotateY(55deg); }\n");
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
        html.append("        button { padding: 12px 25px; margin: 8px; font-size: 16px; font-family: 'Press Start 2P', cursive; cursor: pointer; background: linear-gradient(45deg, #FFD700, #FFA500); color: #000; border: none; border-radius: 8px; box-shadow: 0 4px 15px rgba(255,215,0,0.3); transition: all 0.3s ease; text-transform: uppercase; }\n");
        html.append("        button:hover { transform: translateY(-2px); box-shadow: 0 6px 20px rgba(255,215,0,0.4); background: linear-gradient(45deg, #FFA500, #FFD700); }\n");
        html.append("        button:disabled { background: #95a5a6; cursor: not-allowed; transform: none; box-shadow: none; }\n");
        
        //!
        html.append(
                "        .status { \n");
        html.append("            font-family: 'Press Start 2P', cursive;\n"); 
        html.append("            font-size: 18px;\n");
        html.append("            line-height: 1.8;\n");  // Added this line for increased spacing
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
        html.append(
                "        .status:hover { transform: translateY(-2px); box-shadow: 0 4px 8px rgba(0,0,0,0.15); }\n");
        
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
        html.append("        }\n");

        html.append("        .result h2 {\n");
        html.append("            font-family: 'Bungee', cursive;\n");
        html.append("            font-size: 32px;\n");
        html.append("            margin: 0 0 15px 0;\n");
        html.append("            text-shadow: 2px 2px 4px rgba(0,0,0,0.5);\n");
        html.append("            animation: resultGlow 2s ease-in-out infinite alternate;\n");
        html.append("        }\n");

        html.append("        .result p {\n");
        html.append("            font-family: 'Press Start 2P', cursive;\n");
        html.append("            font-size: 14px;\n");
        html.append("            margin: 10px 0;\n");
        html.append("            color: #FFD700;\n");
        html.append("            text-shadow: 1px 1px 2px rgba(0,0,0,0.5);\n");
        html.append("        }\n");

        html.append("        .win h2 {\n");
        html.append("            color: #4CAF50;\n");
        html.append("        }\n");

        html.append("        .lose h2 {\n");
        html.append("            color: #FF5252;\n");
        html.append("        }\n");

        html.append("        @keyframes resultGlow {\n");
        html.append("            from { text-shadow: 0 0 10px rgba(255,215,0,0.5), 0 0 20px rgba(255,215,0,0.3); }\n");
        html.append("            to { text-shadow: 0 0 20px rgba(255,215,0,0.8), 0 0 30px rgba(255,215,0,0.6); }\n");
        html.append("        }\n");

        html.append("        .statistics { margin-top: 30px; padding: 15px; background-color: #f8f9fa; border-radius: 5px; }\n");
        html.append("        .stat-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; margin-top: 15px; }\n");
        html.append("        .stat-box { padding: 15px; border-radius: 5px; }\n");
        html.append("        .stay-stats { background-color: #e8f4f8; }\n");
        html.append("        .switch-stats { background-color: #e8f8ef; }\n");
        html.append("        .explanation { margin-top: 30px; text-align: left; padding: 20px; background-color: #f9f9f9; border-radius: 5px; }\n");
        html.append("        .explanation h3 { margin-top: 0; }\n");
        html.append("        .tab-container { margin-top: 20px; }\n");
        html.append("        .tab-buttons { display: flex; justify-content: center; }\n");
        html.append("        .tab-button { padding: 10px 20px; border: none; background-color: #ddd; cursor: pointer; }\n");
        html.append("        .tab-button.active { background-color: #3498db; color: white; }\n");
        html.append("        .tab-content { \n");
        html.append("            display: none; \n");
        html.append("            padding: 20px; \n");
        html.append("            background: transparent; \n"); // Changed from background-color: #f1f1f1
        html.append("        }\n");
        html.append("        .tab-content.active { \n");
        html.append("            display: block; \n");
        html.append("        }\n");
        html.append("        .auto-play { margin-top: 20px; padding: 15px; background-color: #e8f8f8; border-radius: 5px; }\n");
        
        //!----------------------------------------------------------------------------------------------------------------------------------
        html.append("        .bar-chart { margin: 30px auto; max-width: 800px; }\n");
        html.append("        .strategy-group { margin-bottom: 25px; }\n");
        html.append("        .strategy-label { text-align: left; font-weight: bold; margin-bottom: 5px; }\n");
        html.append("        .bar-container { height: 35px; display: flex; background-color: #f0f0f0; border-radius: 4px; overflow: hidden; }\n");
        html.append("        .bar-wins { height: 100%; background-color: #2ecc71; transition: width 0.8s ease-in-out; display: flex; align-items: center; justify-content: center; }\n");
        html.append("        .bar-losses { height: 100%; background-color: #e74c3c; transition: width 0.8s ease-in-out; display: flex; align-items: center; justify-content: center; }\n");
        html.append("        .bar-text { color: white; font-weight: bold; padding: 0 10px; text-shadow: 1px 1px 1px rgba(0,0,0,0.3); }\n");
        //!----------------------------------------------------------------------------------------------------------------------------------
        html.append("        .bar-chart { \n");
        html.append("            margin: 40px auto;\n");
        html.append("            max-width: 800px;\n");
        html.append("            background: linear-gradient(145deg, #2c3e50, #1a1a1a);\n");
        html.append("            padding: 25px;\n");
        html.append("            border-radius: 15px;\n");
        html.append("            box-shadow: 0 0 30px rgba(255, 215, 0, 0.2);\n");
        html.append("            border: 3px solid #FFD700;\n");
        html.append("        }\n");

        html.append("        .bar-chart h4 {\n");
        html.append("            font-family: 'Bungee', cursive;\n");
        html.append("            color: #FFD700;\n");
        html.append("            text-shadow: 2px 2px 4px rgba(0,0,0,0.5);\n");
        html.append("            margin-bottom: 25px;\n");
        html.append("            font-size: 24px;\n");
        html.append("        }\n");

        html.append("        .strategy-group {\n");
        html.append("            margin-bottom: 30px;\n");
        html.append("            position: relative;\n");
        html.append("            overflow: hidden;\n");
        html.append("        }\n");

        html.append("        .strategy-label {\n");
        html.append("            font-family: 'Press Start 2P', cursive;\n");
        html.append("            color: #FFD700;\n");
        html.append("            font-size: 14px;\n");
        html.append("            margin-bottom: 10px;\n");
        html.append("            text-shadow: 1px 1px 2px rgba(0,0,0,0.8);\n");
        html.append("        }\n");

        html.append("        .bar-container {\n");
        html.append("            height: 40px;\n");
        html.append("            display: flex;\n");
        html.append("            background: rgba(0,0,0,0.3);\n");
        html.append("            border-radius: 8px;\n");
        html.append("            overflow: hidden;\n");
        html.append("            border: 2px solid #FFD700;\n");
        html.append("            box-shadow: 0 0 15px rgba(255,215,0,0.3);\n");
        html.append("        }\n");

        html.append("        .bar-wins {\n");
        html.append("            height: 100%;\n");
        html.append("            background: linear-gradient(90deg, #4169E1, #1e90ff);\n");
        html.append("            transition: width 0.8s ease-in-out;\n");
        html.append("            display: flex;\n");
        html.append("            align-items: center;\n");
        html.append("            justify-content: center;\n");
        html.append("            position: relative;\n");
        html.append("            overflow: hidden;\n");
        html.append("        }\n");

        html.append("        .bar-losses {\n");
        html.append("            height: 100%;\n");
        html.append("            background: linear-gradient(90deg, #B22222, #8B0000);\n");
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
        html.append("            z-index: 2;\n");
        html.append("        }\n");

        // Add shimmer effect
        html.append("        @keyframes shimmer {\n");
        html.append("            0% { background-position: -100% 0; }\n");
        html.append("            100% { background-position: 100% 0; }\n");
        html.append("        }\n");

        html.append("        .bar-container::after {\n");
        html.append("            content: '';\n");
        html.append("            position: absolute;\n");
        html.append("            top: 0;\n");
        html.append("            left: 0;\n");
        html.append("            width: 100%;\n");
        html.append("            height: 100%;\n");
        html.append("            background: linear-gradient(90deg, transparent, rgba(255,215,0,0.2), transparent);\n");
        html.append("            animation: shimmer 4s infinite;\n");  // Changed from 2s to 4s
        html.append("        }\n");

        // Add these CSS rules after the existing styles
        html.append("        .statistics-panel {\n");
        html.append("            background: linear-gradient(145deg, \n");
        html.append("                rgba(44, 62, 80, 0.95), \n");
        html.append("                rgba(52, 73, 94, 0.95),\n");
        html.append("                rgba(44, 62, 80, 0.95)\n");
        html.append("            );\n");
        html.append("            border: 2px solid #FFD700;\n");
        html.append("            border-radius: 15px;\n");
        html.append("            padding: 25px;\n");
        html.append("            margin: 20px auto;\n");
        html.append("            box-shadow: 0 0 30px rgba(255,215,0,0.15),\n");
        html.append("                        inset 0 0 50px rgba(255,215,0,0.05);\n");
        html.append("            backdrop-filter: blur(5px);\n");
        html.append("            position: relative;\n");
        html.append("            overflow: hidden;\n");
        html.append("        }\n");

        // Add a subtle animated gradient overlay
        

        // Add the animation keyframes
        html.append("        @keyframes panelShimmer {\n");
        html.append("            0% { transform: translateX(-100%); }\n");
        html.append("            100% { transform: translateX(100%); }\n");
        html.append("        }\n");

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
        html.append("            color: #4FC3F7;\n");
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
        html.append("            margin-bottom: 20px;\n");
        html.append("            text-shadow: 1px 1px 2px rgba(0,0,0,0.5);\n");
        html.append("        }\n");

        html.append("        .strategy-cards {\n");
        html.append("            display: grid;\n");
        html.append("            grid-template-columns: 1fr 1fr;\n");
        html.append("            gap: 20px;\n");
        html.append("            margin-top: 20px;\n");
        html.append("        }\n");

        html.append("        .strategy-card {\n");
        html.append("            background: linear-gradient(145deg, #1a1a1a, #2c3e50);\n");
        html.append("            border: 1px solid rgba(255,215,0,0.3);\n");
        html.append("            border-radius: 12px;\n");
        html.append("            padding: 20px;\n");
        html.append("            transition: transform 0.3s ease;\n");
        html.append("        }\n");

        html.append("        .strategy-card:hover {\n");
        html.append("            transform: translateY(-5px);\n");
        html.append("        }\n");

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
        html.append("            font-size: 24px;\n");
        html.append("            font-weight: bold;\n");
        html.append("            color: #2ecc71;\n");
        html.append("            margin: 10px 0;\n");
        html.append("            text-shadow: 1px 1px 2px rgba(0,0,0,0.5);\n");
        html.append("        }\n");

        html.append("        .stats-value {\n");
        html.append("            font-family: 'Open Sans', sans-serif;\n");
        html.append("            font-size: 16px;\n");
        html.append("            color: #fff;\n");
        html.append("            margin: 5px 0;\n");
        html.append("        }\n");

        // Add these CSS rules to the existing styles section
        html.append("        .stats-divider {\n");
        html.append("            height: 2px;\n");
        html.append("            background: linear-gradient(90deg, transparent, rgba(255,215,0,0.3), transparent);\n");
        html.append("            margin: 30px 0;\n");
        html.append("        }\n");

        html.append("        .performance-section {\n");
        html.append("            padding-top: 10px;\n");
        html.append("        }\n");

        html.append("        .performance-section .stats-header {\n");
        html.append("            font-size: 24px;\n");
        html.append("            margin-bottom: 25px;\n");
        html.append("        }\n");

        html.append("        .auto-play-section {\n");
        html.append("            padding-top: 10px;\n");
        html.append("        }\n");

        html.append("        .auto-play-buttons {\n");
        html.append("            display: flex;\n");
        html.append("            gap: 15px;\n");
        html.append("            justify-content: center;\n");
        html.append("            margin-top: 20px;\n");
        html.append("        }\n");

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

        html.append("        .explanation-panel {\n");
        html.append("            background: linear-gradient(145deg, rgba(0,0,0,0.8), rgba(20,20,20,0.9));\n");
        html.append("            border: 2px solid #FFD700;\n");
        html.append("            border-radius: 15px;\n");
        html.append("            padding: 25px;\n");
        html.append("            margin: 20px auto;\n");
        html.append("            max-width: 800px;\n");
        html.append("            position: relative;\n");
        html.append("            box-shadow: 0 0 30px rgba(255,215,0,0.15);\n");
        html.append("        }\n");

        html.append("        .explanation-title {\n");
        html.append("            font-family: 'Bungee', cursive;\n");
        html.append("            color: #FFD700;\n");
        html.append("            font-size: 28px;\n");
        html.append("            margin-bottom: 20px;\n");
        html.append("            text-shadow: 0 0 10px rgba(255,215,0,0.5);\n");
        html.append("        }\n");

        html.append("        .explanation-text {\n");
        html.append("            font-family: 'Open Sans', sans-serif;\n");
        html.append("            color: #fff;\n");
        html.append("            font-size: 16px;\n");
        html.append("            line-height: 1.8;\n");
        html.append("            margin-bottom: 15px;\n");
        html.append("            text-shadow: 1px 1px 2px rgba(0,0,0,0.5);\n");
        html.append("        }\n");

        html.append("        .highlight {\n");
        html.append("            color: #FFD700;\n");
        html.append("            font-weight: bold;\n");
        html.append("        }\n");

        html.append("        .explanation-section {\n");
        html.append("            margin-bottom: 30px;\n");
        html.append("        }\n");

        html.append("        .explanation-subtitle {\n");
        html.append("            font-family: 'Press Start 2P', cursive;\n");
        html.append("            color: #4FC3F7;\n");
        html.append("            font-size: 14px;\n");
        html.append("            margin: 20px 0;\n");
        html.append("            text-shadow: 1px 1px 2px rgba(0,0,0,0.5);\n");
        html.append("        }\n");

        html.append("        .probability-box {\n");
        html.append("            background: linear-gradient(145deg, #1a1a1a, #2c3e50);\n");
        html.append("            border: 2px solid rgba(255,215,0,0.5);\n");
        html.append("            border-radius: 12px;\n");
        html.append("            padding: 20px;\n");
        html.append("            margin: 20px 0;\n");
        html.append("            font-family: 'Press Start 2P', cursive;\n");
        html.append("            font-size: 14px;\n");
        html.append("            color: #FFD700;\n");
        html.append("            line-height: 2;\n");
        html.append("            text-shadow: 1px 1px 2px rgba(0,0,0,0.8);\n");
        html.append("            box-shadow: 0 0 20px rgba(255,215,0,0.1);\n");
        html.append("        }\n");

        html.append("        .probability-box .highlight {\n");
        html.append("            color: #4FC3F7;\n");
        html.append("            font-weight: bold;\n");
        html.append("            font-size: 16px;\n");
        html.append("            text-shadow: 0 0 10px rgba(79,195,247,0.5);\n");
        html.append("        }\n");

        // Add this to the style section:
        html.append("        button.reset-button {\n");
        html.append("            background: linear-gradient(45deg, #FF6B6B, #FF8E8E);\n");
        html.append("            color: white;\n");
        html.append("            border: 2px solid #FF6B6B;\n");
        html.append("        }\n");
        html.append("        button.reset-button:hover {\n");
        html.append("            background: linear-gradient(45deg, #FF8E8E, #FF6B6B);\n");
        html.append("            transform: translateY(-2px);\n");
        html.append("        }\n");

        // Add this CSS after the game-container styles:
        html.append("        .reset-container {\n");
        html.append("            position: fixed;\n");  // Change from absolute to fixed
        html.append("            top: 10px;\n");           // Reduced from 20px to 10px to move it higher
        html.append("            right: 10px;\n");         // Reduced from 20px to 10px to move it more to the right
        html.append("            z-index: 9999;\n");    // Increase z-index to ensure it's always on top
        html.append("        }\n");

        // Update the reset button styles for better visibility:
        html.append("        button.reset-button {\n");
        html.append("            background: linear-gradient(45deg, #ff6b6b, #fa8072);\n");
        html.append("            color: white;\n");
        html.append("            font-weight: bold;\n");
        html.append("            border: none;\n");
        html.append("            padding: 8px 16px;\n");   // Reduced padding
        html.append("            font-size: 12px;\n");     // Reduced font size
        html.append("            border-radius: 4px;\n");   // Smaller border radius
        html.append("            box-shadow: 0 2px 8px rgba(0,0,0,0.3);\n");
        html.append("            transition: all 0.3s ease;\n");
        html.append("            cursor: pointer;\n");
        html.append("        }\n");

        html.append("        button.reset-button:hover {\n");
        html.append("            background: linear-gradient(45deg, #fa8072, #ff6b6b);\n");
        html.append("            transform: translateY(-2px);\n");
        html.append("            box-shadow: 0 4px 15px rgba(0,0,0,0.3);\n");
        html.append("        }\n");

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
        html.append("    </script>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("    <h1>🐐 Monty Hall Simulator 🐐</h1>\n");
        html.append("    <p class=\"subtitle\">Put your luck and logic to the test!</p>\n");
        html.append("    <div class=\"game-container\">\n");

        // Add this right after the <div class="game-container"> opening tag:
        html.append("        <div class=\"reset-container\">\n");
        html.append("            <button class=\"reset-button\" onclick=\"location.href='?action=reset'\">Reset Game</button>\n");
        html.append("        </div>\n");

        // Game status message
        html.append("        <div class=\"status\" id=\"status\">\n");
        boolean gameStarted = controller.getGameState().getPlayerChoice() != -1;
        boolean doorRevealed = controller.getGameState().getRevealedDoor() != -1;
        boolean gameOver = controller.getGameState().isGameOver();

        //!
        if (!gameStarted) {
            html.append("            👆 Click on a door to start the game! 👆\n");
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

        for (int i = 0; i < 3; i++) {
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
            // Game over - only play again button
            html.append("            <button onclick=\"location.href='?action=reset'\">Play Again</button>\n");
        }
        html.append("        </div>\n");

        // Game result (if game is over)
        if (gameOver) {
            html.append("        <div class=\"result ").append(controller.getGameState().hasWon() ? "win" : "lose").append("\">\n");
            if (controller.getGameState().hasWon()) {
                html.append("            <h2>🏆 Congratulations! You WON! 🎉</h2>\n");
            } else {
                html.append("            <h2>💔 Sorry! You LOST! 😢</h2>\n");
            }
            html.append("            <p>You ").append(
                    controller.getGameState().hasSwitched() ? "switched doors" : "stayed with your initial choice")
                    .append("</p>\n");
            html.append("        </div>\n");
        }

        // Tab navigation for additional content
        html.append("        <div class=\"tab-container\">\n");
        html.append("            <div class=\"tab-buttons\">\n");
        html.append(
                "                <button id=\"btn-tab-stats\" class=\"tab-button active\" onclick=\"toggleTab('tab-stats')\">Statistics</button>\n");
        html.append(
                "                <button id=\"btn-tab-explanation\" class=\"tab-button\" onclick=\"toggleTab('tab-explanation')\">Explanation</button>\n");
        html.append("            </div>\n");

        //? Statistics tab
        html.append("            <div id=\"tab-stats\" class=\"tab-content active\">\n");
        html.append("                <div class=\"statistics-panel\">\n");
        // Game Statistics Section
        html.append("                    <div class=\"stats-header\">Game Statistics</div>\n");
        html.append("                    <div class=\"stats-subtitle\">Track Your Success Rate Over Time</div>\n");
        html.append("                    <div class=\"games-played\">🎮 Games Played: " + controller.getGamesPlayed() + "</div>\n");
        html.append("                    <div class=\"strategy-cards\">\n");
        html.append("                        <div class=\"strategy-card\">\n");
        html.append("                            <div class=\"strategy-title\">🚪 Stay Strategy</div>\n");
        html.append("                            <div class=\"win-rate\">" + String.format("%.1f", controller.getStayWinRate()) + "% ✨</div>\n");
        html.append("                            <div class=\"stats-value\">Wins: " + controller.getStayWins() + "</div>\n");
        html.append("                            <div class=\"stats-value\">Total Games: " + controller.getStayGames() + "</div>\n");
        html.append("                        </div>\n");
        html.append("                        <div class=\"strategy-card\">\n");
        html.append("                            <div class=\"strategy-title\">🔄 Switch Strategy</div>\n");
        html.append("                            <div class=\"win-rate\">" + String.format("%.1f", controller.getSwitchWinRate()) + "% ✨</div>\n");
        html.append("                            <div class=\"stats-value\">Wins: " + controller.getSwitchWins() + "</div>\n");
        html.append("                            <div class=\"stats-value\">Total Games: " + controller.getSwitchGames() + "</div>\n");
        html.append("                        </div>\n");
        html.append("                    </div>\n");

        // Divider
        html.append("                    <div class=\"stats-divider\"></div>\n");

        // Performance per Strategy Section
        html.append("                    <div class=\"performance-section\">\n");
        html.append("                        <div class=\"stats-header\">Performance per Strategy</div>\n");
        html.append("                        <div class=\"strategy-group\">\n");
        html.append("                            <div class=\"strategy-label\">Stay Strategy 🔒</div>\n");
        html.append("                            <div class=\"bar-container\">\n");
        double stayWinWidth = controller.getStayWinRate();
        double stayLossWidth = 100 - stayWinWidth;
        html.append("                                <div class=\"bar-wins\" style=\"width: " + stayWinWidth + "%;\">\n");
        html.append("                                    <span class=\"bar-text\">" + controller.getStayWins() + " Wins (" + String.format("%.1f", stayWinWidth) + "%)</span>\n");
        html.append("                                </div>\n");
        html.append("                                <div class=\"bar-losses\" style=\"width: " + stayLossWidth + "%;\">\n");
        html.append("                                    <span class=\"bar-text\">" + (controller.getStayGames() - controller.getStayWins()) + " Losses (" + String.format("%.1f", stayLossWidth) + "%)</span>\n");
        html.append("                                </div>\n");
        html.append("                            </div>\n");
        html.append("                        </div>\n");
        html.append("                        <div class=\"strategy-group\">\n");
        html.append("                            <div class=\"strategy-label\">Switch Strategy 🔄</div>\n");
        html.append("                            <div class=\"bar-container\">\n");
        double switchWinWidth = controller.getSwitchWinRate();
        double switchLossWidth = 100 - switchWinWidth;
        html.append("                                <div class=\"bar-wins\" style=\"width: " + switchWinWidth + "%;\">\n");
        html.append("                                    <span class=\"bar-text\">" + controller.getSwitchWins() + " Wins (" + String.format("%.1f", switchWinWidth) + "%)</span>\n");
        html.append("                                </div>\n");
        html.append("                                <div class=\"bar-losses\" style=\"width: " + switchLossWidth + "%;\">\n");
        html.append("                                    <span class=\"bar-text\">" + (controller.getSwitchGames() - controller.getSwitchWins()) + " Losses (" + String.format("%.1f", switchLossWidth) + "%)</span>\n");
        html.append("                                </div>\n");
        html.append("                            </div>\n");
        html.append("                        </div>\n");
        html.append("                    </div>\n");

        // Divider
        html.append("                    <div class=\"stats-divider\"></div>\n");

        // Add Auto-Play section
        html.append("                    <div class=\"auto-play-section\">\n");
        html.append("                        <div class=\"stats-header\">Auto-Play Simulation</div>\n");
        html.append("                        <div class=\"stats-subtitle\">Run Multiple Games Automatically</div>\n");
        html.append("                        <div class=\"auto-play-buttons\">\n");
        html.append("                            <button onclick=\"autoPlay(10)\">Run 10 Games</button>\n");
        html.append("                            <button onclick=\"autoPlay(100)\">Run 100 Games</button>\n");
        html.append("                            <button onclick=\"autoPlay(1000)\">Run 1000 Games</button>\n");
        html.append("                        </div>\n");
        html.append("                    </div>\n");
        html.append("                </div>\n"); // End of statistics-panel

        html.append("            </div>\n");

        // Explanation tab
        html.append("            <div id=\"tab-explanation\" class=\"tab-content\">\n");
        html.append("                <div class=\"explanation-panel\">\n");
        html.append("                    <div class=\"explanation-title\">🎮 The Monty Hall Problem Explained</div>\n");
        html.append("                    <div class=\"explanation-text\">\n");
        html.append("                        The Monty Hall problem is a famous probability puzzle named after the host of the game show \"Let's Make a Deal.\"\n");
        html.append("                    </div>\n");

        html.append("                    <div class=\"explanation-section\">\n");
        html.append("                        <div class=\"explanation-subtitle\">The Scenario:</div>\n");
        html.append("                        <div class=\"explanation-text\">\n");
        html.append("                            • There are <span class=\"highlight\">three doors</span>. Behind one door is a <span class=\"highlight\">car</span> (the prize), and behind the other two are <span class=\"highlight\">goats</span>.<br>\n");
        html.append("                            • You pick a door, say door 1.<br>\n");
        html.append("                            • The host, who knows what's behind each door, opens one of the other doors, say door 3, to reveal a goat.<br>\n");
        html.append("                            • The host then asks if you want to switch your choice to the remaining door (door 2) or stick with your original choice (door 1).\n");
        html.append("                        </div>\n");
        html.append("                    </div>\n");

        html.append("                    <div class=\"explanation-section\">\n");
        html.append("                        <div class=\"explanation-subtitle\">The Paradox:</div>\n");
        html.append("                        <div class=\"explanation-text\">\n");
        html.append("                            Intuitively, many people think it doesn't matter whether you switch or not, assuming the probability is 50/50. However, mathematical analysis shows that:\n");
        html.append("                        </div>\n");
        html.append("                        <div class=\"probability-box\">\n");
        html.append("                            • If you <span class=\"highlight\">STAY</span> with your initial choice:<br>\n");
        html.append("                              Win Rate: <span class=\"highlight\">1/3 (33.3%)</span><br>\n");
        html.append("                            • If you <span class=\"highlight\">SWITCH</span> to the other door:<br>\n");
        html.append("                              Win Rate: <span class=\"highlight\">2/3 (66.6%)</span>\n");
        html.append("                        </div>\n");
        html.append("                    </div>\n");

        html.append("                    <div class=\"explanation-section\">\n");
        html.append("                        <div class=\"explanation-subtitle\">Why Switching is Better:</div>\n");
        html.append("                        <div class=\"explanation-text\">\n");
        html.append("                            Think of it this way:<br><br>\n");
        html.append("                            • When you make your initial choice, there's a 1/3 chance you picked the car and a 2/3 chance you picked a goat.<br>\n");
        html.append("                            • If you initially picked a goat (2/3 chance), then switching will ALWAYS lead you to the car.<br>\n");
        html.append("                            • If you initially picked the car (1/3 chance), then switching will lead you to a goat.<br><br>\n");
        html.append("                            So by switching, you win whenever your initial choice was a goat, which happens 2/3 of the time!\n");
        html.append("                        </div>\n");
        html.append("                    </div>\n");

        html.append("                    <div class=\"explanation-section\">\n");
        html.append("                        <div class=\"explanation-subtitle\">Still not convinced?</div>\n");
        html.append("                        <div class=\"explanation-text\">\n");
        html.append("                            Imagine that there are <span class=\"highlight\">100 doors</span> with the same conditions before. The car is behind one of the doors. You pick one.<br><br>\n");
        html.append("                            Then, the host opens <span class=\"highlight\">98</span> of the remaining 99 doors, all revealing goats.<br><br>\n");
        html.append("                            Now only two doors remain: the one you picked and one other unopened door.<br><br>\n");
        html.append("                            Would you still stick with your original choice — a <span class=\"highlight\">1 in 100</span> chance — or switch to the other door, which now has a <span class=\"highlight\">99 in 100</span> chance of hiding the prize?\n");
        html.append("                        </div>\n");
        html.append("                    </div>\n");

        html.append("                    <div class=\"stats-divider\"></div>... (1 KB kvar)
