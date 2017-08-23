import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;


public class Tutorial {
    private static Logger logger = LoggerFactory.getLogger(Tutorial.class);

    public static void main(String[] args) {
        Observable
                .fromCallable(thatReturnsNumberOne())
                .subscribeOn(Schedulers.newThread())
                .map(numberToString())
//                .observeOn(Schedulers.newThread())
                .subscribe(printResult());
    }

    private static Callable<Integer> thatReturnsNumberOne() {
        return () -> {
            logger.info("Observable thread: " + Thread.currentThread().getName());
            return 1;
        };
    }

    private static Function<Integer, String> numberToString() {
        return number -> {
            logger.info("Operator thread: " + Thread.currentThread().getName());
            return String.valueOf(number);
        };
    }

    private static Consumer<String> printResult() {
        return result -> {
            logger.info("Subscriber thread: " + Thread.currentThread().getName());
            logger.info("Result: " + result);
        };
    }
}
