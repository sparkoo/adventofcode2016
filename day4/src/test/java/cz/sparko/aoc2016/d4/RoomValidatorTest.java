package cz.sparko.aoc2016.d4;

import org.testng.annotations.DataProvider;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RoomValidatorTest {


    @DataProvider
    public Object[][] parseRooms() {
        return new Object[][] {
                {"aaaaa-bbb-z-y-x-123[abxyz]", new Room("aaaaabbbzyx", 123, "abxyz")},
                {"a-b-c-d-e-f-g-h-987[abcde]", new Room("abcdefgh", 987, "abcde")},
                {"not-a-real-room-404[oarel]", new Room("notarealroom", 404, "oarel")},
                {"totally-real-room-200[decoy]", new Room("totallyrealroom", 200, "decoy")}
        };
    }

    @DataProvider
    public Object[][] validateRooms() {
        return new Object[][] {
                {new Room("aaaaabbbzyx", 123, "abxyz"), true},
                {new Room("abcdefgh", 987, "abcde"), true},
                {new Room("notarealroom", 404, "oarel"), true},
                {new Room("totallyrealroom", 200, "decoy"), false}
        };
    }

    @Test(dataProvider = "parseRooms")
    public void givenInput_whenParse_thenCreateCorrectRoom(String input, Room expectedRoom) {
        Room parsedRoom = Room.parseFromLine(input);
        assertEquals(parsedRoom, expectedRoom);
    }

    @Test(dataProvider = "validateRooms")
    public void givenRoom_whenValidate_thenRoomCorrectlyValidated(Room room, boolean expectedValidity) {
        assertEquals(new RoomValidator().isValid(room), expectedValidity);
    }
}