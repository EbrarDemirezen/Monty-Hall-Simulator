package src.main.MontyHall;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import src.main.MontyHall.GameController;
import src.main.MontyHall.HtmlGenerator;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.InputStream;

public class Main {
    private static GameController gameController = new GameController();

    public static void main(String[] args) {
        try {
            // Create a simple HTTP server on port 8080
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            // Add handler for static files
            server.createContext("/images", new StaticFileHandler("src/main/resources/static/images"));
            // Add another context for sounds
            server.createContext("/sounds", new StaticFileHandler("src/main/resources/static/sounds"));

            // Create a context for handling requests
            server.createContext("/", new GameHandler());

            // Start the server
            server.start();

            System.out.println("Monty Hall Simulator running at http://localhost:8080/");
            System.out.println("Press Ctrl+C to stop the server");

        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }

    static class GameHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Parse query parameters
            String query = exchange.getRequestURI().getQuery();
            Map<String, String> params = parseQueryParams(query);

            // Handle different actions
            if (params.containsKey("action")) {
                String action = params.get("action");

                switch (action) {
                    case "reset":
                        gameController.resetAllStats(); // This should only be called for the "Reset Stats" button
                        break;
                    case "play-again": // New action for the "Play Again" button
                        gameController.resetGame(); // Only reset the current game, not the stats
                        break;
                    case "choose":
                        if (params.containsKey("door")) {
                            try {
                                int doorNumber = Integer.parseInt(params.get("door"));
                                gameController.makeInitialChoice(doorNumber);
                            } catch (NumberFormatException e) {
                                // Invalid door number, ignore
                            }
                        }
                        break;
                    case "stay":
                        gameController.stayWithCurrentChoice();
                        break;
                    case "switch":
                        gameController.switchDoor();
                        break;
                    case "auto-play":
                        if (params.containsKey("iterations")) {
                            try {
                                int iterations = Integer.parseInt(params.get("iterations"));
                                gameController.runAutoPlay(iterations);
                            } catch (NumberFormatException e) {
                                // Invalid iterations, ignore
                            }
                        }
                        break;
                }
            }

            // Generate HTML for the current game state
            String responseHTML = HtmlGenerator.generateGamePage(gameController);

            // Send the response
            exchange.sendResponseHeaders(200, responseHTML.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(responseHTML.getBytes());
            os.close();
        }

        private Map<String, String> parseQueryParams(String query) {
            Map<String, String> params = new HashMap<>();
            if (query == null || query.isEmpty()) {
                return params;
            }

            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    params.put(keyValue[0], keyValue[1]);
                }
            }

            return params;
        }
    }

    static class StaticFileHandler implements HttpHandler {
        private final String baseDir;
        
        public StaticFileHandler(String baseDir) {
            this.baseDir = baseDir;
        }
        
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            Path filePath = Paths.get(baseDir, path.substring(path.lastIndexOf("/") + 1));
            
            if (Files.exists(filePath)) {
                exchange.sendResponseHeaders(200, Files.size(filePath));
                try (OutputStream os = exchange.getResponseBody();
                     InputStream is = Files.newInputStream(filePath)) {
                    is.transferTo(os);
                }
            } else {
                String response = "404 (Not Found)\n";
                exchange.sendResponseHeaders(404, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }
        }
    }
}