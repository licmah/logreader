import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.HashMap;
import java.lang.Math;

import org.json.JSONArray;
import org.json.JSONObject;

public class LogReader {
    static HashMap<String, Long> incomplete = new HashMap<>();
    public static void parseJSON(String line) {
        JSONObject obj = new JSONObject(line);
        String id = obj.getString("id");
        long timestamp = obj.getLong("timestamp");
        if(incomplete.containsKey(id))
        {
            long otherTimestamp = incomplete.get(id);
            long duration =  Math.abs(otherTimestamp - timestamp);
            String type = obj.optString("type");
            String host = obj.optString("host");
            //save a completed event in HSQLDB...
            System.out.println(new Event(id, duration, type, host).toString());
            incomplete.remove(id);
        }
        else
        {
            incomplete.put(id,timestamp);
        }
    }
    public static void main(String args[]) {

        // args[0]: fullpath to the logfile
        String fileName = args[0];

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(line -> parseJSON(line));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

