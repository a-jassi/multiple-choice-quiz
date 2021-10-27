package persistence;

import org.json.JSONObject;

// The idea for creating a Writable interface to make sure every Class implements a method that converts it to JSON
// comes from the Writable interface in the JsonSerialization demo
// link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/Writable.java

// an interface for objects that are writable to file in order to save progress
public interface Writable {

    //EFFECTS: returns this as a JSON object
    JSONObject toJson();

}
