import io.reactivex.Observable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;


public class Tutorial3 {

    public static void main(String[] args) throws InterruptedException {
        Observable<Long> fast = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> slow = Observable.interval(3, TimeUnit.SECONDS);
        Observable<Long> clock = Observable.merge(
               slow.filter(tick-> isSlowTickTime()),
               fast.filter(tick-> !isSlowTickTime())
        );
        clock.subscribe(tick-> System.out.println(new Date()));
        Thread.sleep(60_000);
    }

    private static long start = System.currentTimeMillis();
    public static Boolean isSlowTickTime() {
       return (System.currentTimeMillis() - start) % 30_000 >= 15_000;
    }
}
