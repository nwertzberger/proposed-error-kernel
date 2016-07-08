package example.supervisors;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by nwertzberger on 7/6/16.
 */
public class MyDatabaseConnection {
    public boolean checkTruthiness(String value) throws InvalidArgumentException {
        if (value.equals("TRUE")) {
            return true;
        }
        return false;
    }
}
