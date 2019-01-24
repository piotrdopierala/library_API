package pl.dopierala.domain;

import org.junit.Assert;
import org.junit.Test;
import pl.dopierala.utils.CustomBookDefinitionDeserializer;
import pl.dopierala.utils.CustomUserDeserializer;

import java.util.List;

import static pl.dopierala.utils.Utils.readBookDefFromJson;
import static pl.dopierala.utils.Utils.readUsersFromJson;

public class UtilsTest {

    @Test
    public void Should_return_users_from_JSON(){
        //given
        //when
        List<LibraryUser> libraryUsers = readUsersFromJson("/sample_user_data.json", new CustomUserDeserializer());
        //then
        Assert.assertTrue(libraryUsers.size()>10);
    }

    @Test
    public void Should_return_books_from_JSON() {
        //given
        //when
        List<BookDefinition> bookDefinitions = readBookDefFromJson("/sample_book_data.json", new CustomBookDefinitionDeserializer());
        //then
        Assert.assertTrue(bookDefinitions.size()>10);
    }
}