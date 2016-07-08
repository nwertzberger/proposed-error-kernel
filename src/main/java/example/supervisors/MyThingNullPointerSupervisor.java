package example.supervisors;

import static example.supervisors.SupervisableException.canFix;

/**
 * Created by nwertzberger on 7/6/16.
 */
public class MyThingNullPointerSupervisor implements MyThing {
    private MyThing thing;

    public MyThingNullPointerSupervisor(MyThing thing) {
        this.thing = thing;
    }


    public boolean doThing(String value) throws SupervisableException {
        try {
            return thing.doThing(value);
        } catch(RuntimeException | SupervisableException e) {
            if (canFix(e, NullPointerException.class)) {
                return false;
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
