package poo.booksync.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TokenExtractor {

    public static void extractToken(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode tokenNode = rootNode.get("token");
            Path path = Paths.get("auth_token.txt");
            try {
                Files.write(path, new byte[0]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Files.writeString(path, tokenNode.textValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentToken() {
        String token = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("auth_token.txt"))) {
            token = reader.readLine();  // Lire la première ligne du fichier
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }
}
