package pl.dopierala.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.dopierala.domain.Gender;
import pl.dopierala.domain.LibraryUser;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CustomUserDeserializer extends StdDeserializer<LibraryUser> {

    public CustomUserDeserializer() {
        super(LibraryUser.class);
    }

    @Override
    public LibraryUser deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        JsonNode node = jp.getCodec().readTree(jp);


        LibraryUser usr = new LibraryUser();


        String firstName = node.get("firstName").textValue();
        usr.setFirstName(firstName);
        String lastName = node.get("lastName").textValue();
        usr.setLastName(lastName);
        String email = node.get("email").textValue();
        usr.setEmail(email);
        String gender = node.get("gender").textValue();
        switch (gender.toUpperCase()) {
            case "MALE":
                usr.setGender(Gender.MALE);
                break;
            case "FEMALE":
                usr.setGender(Gender.FEMALE);
                break;
            default:
                usr.setGender(Gender.UNKNOWN);
        }
        usr.setBirthDate(LocalDate.parse(node.get("birthDate").textValue(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        usr.setCountryCode(node.get("country").textValue());


        return usr;
    }
}
