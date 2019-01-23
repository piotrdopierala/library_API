package pl.dopierala.domain;

import org.junit.Test;
import pl.dopierala.utils.CustomUserDeserializer;

import static pl.dopierala.utils.Utils.readUsersFromJson;

public class UtilsTest {

    @Test
    public void Should_return_values_from_JSON(){
        //given
        //when
        readUsersFromJson("/sample_user_data.json",new CustomUserDeserializer());
        //then
    }

}