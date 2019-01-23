package pl.dopierala.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import pl.dopierala.domain.LibraryUser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class Utils {

    public static void readUsersFromJson(String resourceFilePath, StdDeserializer<LibraryUser> deserializer) {
        ObjectMapper mapper = new ObjectMapper();
        if (Objects.nonNull(deserializer)){
            SimpleModule module = new SimpleModule();
            module.addDeserializer(LibraryUser.class, deserializer);
            mapper.registerModule(module);
        }

        InputStream userStream = TypeReference.class.getResourceAsStream(resourceFilePath);
        try {
            LibraryUser libraryUser = mapper.readValue(userStream, new TypeReference<LibraryUser>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Utils() {
    }
}
