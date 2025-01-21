package poo.booksync.model.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class BaseDto {

    public String toJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this); // Sérialiser l'objet actuel en JSON
        } catch (IOException e) {
            //TODO: return custom error
            return null;
        }
    }

}