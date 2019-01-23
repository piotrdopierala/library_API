package pl.dopierala.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.dopierala.domain.Gender;
import pl.dopierala.domain.LibraryUser;

import java.io.IOException;

public class CustomUserDeserializer extends StdDeserializer<LibraryUser> {

    public CustomUserDeserializer() {
        super(LibraryUser.class);
    }

    @Override
    public LibraryUser deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        LibraryUser ret = new LibraryUser();

        JsonNode node = jp.getCodec().readTree(jp);
        String firstName = node.get(0).get("firstName").textValue();
        ret.setFirstName(firstName);
        String lastName = node.get(0).get("lastName").textValue();
        ret.setLastName(lastName);
        String email = node.get(0).get("email").textValue();
        ret.setEmail(email);
        String gender = node.get(0).get("gender").textValue();
        switch (gender.toUpperCase()) {
            case "MALE":
                ret.setGender(Gender.MALE);
                break;
            case "FEMALE":
                ret.setGender(Gender.FEMALE);
                break;
            default:
                ret.setGender(Gender.UNKNOWN);
        }

        return ret;
    }
}
