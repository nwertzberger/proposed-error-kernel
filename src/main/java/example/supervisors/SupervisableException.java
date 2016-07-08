package example.supervisors;

/**
 * Created by nwertzberger on 7/6/16.
 */
public class SupervisableException extends Exception {
    public SupervisableException(Exception e) {
        super(e);
    }

    public static <P> P wrap(ExceptionThrower<P> function) throws SupervisableException {
        try {
            return function.run();
        } catch (Exception e) {
            throw new SupervisableException(e);
        }
    }

    public static <P> P unsupervise(SupervisedExceptionThrower<P> function) {
        try {
            return function.run();
        } catch (SupervisableException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean canFix(Exception e, Class<? extends Exception> exceptionClass) {
        if (e.getClass().equals(exceptionClass)) {
            return true;
        } else if (e instanceof SupervisableException && e.getCause().getClass().equals (exceptionClass)) {
            return true;
        }
        return false;
    }

    @FunctionalInterface
    public interface SupervisedExceptionThrower<P> {
        P run() throws SupervisableException;
    }

    @FunctionalInterface
    public interface ExceptionThrower<P> {
        P run() throws Exception;
    }
}
