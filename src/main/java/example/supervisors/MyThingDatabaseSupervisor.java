package example.supervisors;

import com.sun.javaws.exceptions.InvalidArgumentException;

import static example.supervisors.SupervisableException.canFix;

/**
 * Created by nwertzberger on 7/6/16.
 */
public class MyThingDatabaseSupervisor implements MyThing {

    private MyThing thing;

    public MyThingDatabaseSupervisor(MyThing thing) {
        this.thing = thing;
    }

    @Override
    public boolean doThing(String value) throws SupervisableException {
        try {
            return thing.doThing(value);
        } catch (Exception e) {
            if(canFix(e, InvalidArgumentException.class)) {
                thing.restart();
                return doThing(value);
            } else {
                throw e;
            }
        }
    }

    @Override
    public void restart() {
        thing.restart();
    }
}
