package persistence;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    // testJsonWriterNotValid idea was gotten from JsonWriterTest in JsonSerializationDemo
    // link below:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonWriterTest.java

    @Test
    void testJsonWriterNotValidFile() {
        try {
            JsonWriter jsonWriter = new JsonWriter("./data/quiz\0invalid:filename.json");
            jsonWriter.open();
            fail("Test failed: An IOException was supposed to be thrown");
        } catch (IOException e) {
            // expected behaviour
        }
    }

}
