package pl.dopierala.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import pl.dopierala.domain.LibraryUser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Utils {

    public static List<LibraryUser> readUsersFromJson(String resourceFilePath, StdDeserializer<LibraryUser> deserializer) {
        List<LibraryUser> libUsers;


        ObjectMapper mapper = new ObjectMapper();
        if (Objects.nonNull(deserializer)) {
            SimpleModule module = new SimpleModule();
            module.addDeserializer(LibraryUser.class, deserializer);
            mapper.registerModule(module);
        }

        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, LibraryUser.class);
        InputStream userStream = TypeReference.class.getResourceAsStream(resourceFilePath);
        try {
            libUsers = mapper.readValue(userStream, collectionType);
            return libUsers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private Utils() {
    }
}
