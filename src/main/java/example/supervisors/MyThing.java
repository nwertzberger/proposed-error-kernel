package example.supervisors;

/**
 * Created by nwertzberger on 7/6/16.
 */
public interface MyThing {
    boolean doThing(String value) throws SupervisableException;
    void restart();
}
