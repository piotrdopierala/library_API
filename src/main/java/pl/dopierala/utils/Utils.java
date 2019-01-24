package pl.dopierala.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import pl.dopierala.domain.BookCopy;
import pl.dopierala.domain.BookDefinition;
import pl.dopierala.domain.LibraryUser;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static List<BookDefinition> readBookDefFromJson(String resourceFilePath, StdDeserializer<BookDefinition> deserializer) {
        List<BookDefinition> bookDefs = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        if (Objects.nonNull(deserializer)) {
            SimpleModule module = new SimpleModule();
            module.addDeserializer(BookDefinition.class, deserializer);
            mapper.registerModule(module);
        }

        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, BookDefinition.class);
        InputStream userStream = TypeReference.class.getResourceAsStream(resourceFilePath);
        try {
            bookDefs = mapper.readValue(userStream, collectionType);
            return bookDefs;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static List<BookCopy> generateRandomBookCopies(List<BookDefinition> bookDefinitions) {
        Random rnd = new Random();

        List<String> bookStateSampleDesc = new ArrayList<>();
        bookStateSampleDesc.add("Front page liquid mark");
        bookStateSampleDesc.add("Missing pages");
        bookStateSampleDesc.add("Hand writing on pages");
        bookStateSampleDesc.add("Dirty");
        bookStateSampleDesc.add("Liquid marks on pages");
        bookStateSampleDesc.add("Cup circle mark on front page");

        List<BookCopy> generatedBookCopies = bookDefinitions
                .stream()
                .parallel()
                .flatMap(
                        bookDef -> {
                            List<BookCopy> collect = IntStream
                                    .range(0, rnd.nextInt(101) + 10)
                                    .mapToObj(i -> generateBookCopy(bookDef, bookStateSampleDesc, rnd)
                                    ).collect(Collectors.toList());
                            return collect.stream();
                        }
                )
                .collect(Collectors.toList());

        return generatedBookCopies;
    }

    private static BookCopy generateBookCopy(BookDefinition bookDef, List<String> bookStateDescriptions, Random rnd) {
        BookCopy book = new BookCopy(bookDef);

        if (rnd.nextBoolean()) {
            book.setPhysicalStateDescription(bookStateDescriptions.get(rnd.nextInt(bookStateDescriptions.size())));
        }

        if (Objects.nonNull(bookDef.getPublishedDate())) {
            book.setDateAddedToLibrary(LocalDateTime.of(bookDef.getPublishedDate().plusDays((long) rnd.nextInt(200)), LocalTime.now()));
        } else {
            book.setDateAddedToLibrary(LocalDateTime.now());
        }

        return book;
    }


    private Utils() {
    }
}
