package pl.dopierala.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.dopierala.domain.Author;
import pl.dopierala.domain.BookDefinition;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


public class CustomBookDefinitionDeserializer extends StdDeserializer<BookDefinition> {

    public CustomBookDefinitionDeserializer() {
        super(BookDefinition.class);
    }

    @Override
    public BookDefinition deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        JsonNode node = jp.getCodec().readTree(jp);

        BookDefinition bookDef = new BookDefinition();

        String title = node.get("title").textValue();
        bookDef.setTitle(title);
        if (node.has("isbn")) {
            String isbn = node.get("isbn").textValue();
            bookDef.setIsbn(isbn);
        }
        if(node.has("pageCount")) {
            Integer pageCount = node.get("pageCount").bigIntegerValue().intValue();
            bookDef.setPageCount(pageCount);
        }
        //bookDef.setPublishedDate(LocalDate.parse(node.get("publishedDate").textValue(),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        if (node.has("thumbnailUrl")) {
            String thumbnailUrl = node.get("thumbnailUrl").textValue();
            bookDef.setThumbnailUrl(thumbnailUrl);
        }
        if (node.has("shortDescription")) {
            String shortDescription = node.get("shortDescription").textValue();
            bookDef.setShortDescription(shortDescription);
        }
        if(node.has("authors")) {
            List<Author> authors = new ArrayList<>();
            node.findValues("authors").get(0).forEach(a -> {
                        String[] split = a.asText().split(" ");
                        if (split.length == 1)
                            authors.add(new Author("", split[0]));
                        if (split.length > 1) {
                            String nameString = a.asText();
                            int idxLastSpace = nameString.lastIndexOf(" ");
                            authors.add(new Author(nameString.substring(0,idxLastSpace),nameString.substring(idxLastSpace+1)));
                        }
                    }
            );
            bookDef.setAuthors(authors);
        }

        Collection<String> categories = new HashSet<>();
        node.findValue("categories").forEach(c->categories.add(c.textValue()));
        bookDef.setCategories(categories);

        return bookDef;
    }
}
