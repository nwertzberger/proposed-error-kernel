package example.supervisors;

import com.sun.javaws.exceptions.InvalidArgumentException;

import static example.supervisors.SupervisableException.wrap;

/**
 * Created by nwertzberger on 7/6/16.
 */
public class MyThingProcessor implements MyThing {
    private MyDatabaseConnection databaseConnection;

    public MyThingProcessor(MyDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public boolean doThing(String value) throws SupervisableException {
        if (value.equals("true")) {
            return true;
        } else {
            return wrap(() -> databaseConnection.checkTruthiness(value));
        }
    }

    @Override
    public void restart() {
        databaseConnection = new MyDatabaseConnection();
    }


}
