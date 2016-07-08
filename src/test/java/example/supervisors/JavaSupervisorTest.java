package example.supervisors;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by nwertzberger on 7/6/16.
 */
public class JavaSupervisorTest {
    @Test
    public void supervisorTest() throws Exception {
        MyThing thing = new MyThingNullPointerSupervisor(
            new MyThingProcessor(
                new MyDatabaseConnection()
            )
        );
        assertFalse(thing.doThing(null));
        assertTrue(thing.doThing("true"));
        assertTrue(thing.doThing("TRUE"));
        assertFalse(thing.doThing("false"));
    }

    @Test
    public void supervisorDatabaseIssueTest() throws Exception {
        MyThing thing = new MyThingNullPointerSupervisor(
            new MyThingProcessor(
                new MyDatabaseConnection() {
                    @Override
                    public boolean checkTruthiness(String value) throws InvalidArgumentException {
                        throw new InvalidArgumentException(new String[]{"Invalid"});
                    }
                }
            )
        );

        thing = new MyThingDatabaseSupervisor(thing);

        assertTrue(thing.doThing("TRUE"));
    }

}
