import io.reactivex.Observable;

import java.util.Comparator;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;


public class Tutorial2 {

    public static void main(String[] args) {
        Observable
                .fromIterable(asList(new Test("Howdy!"), new Test("Howdy!"), new Test("Toto")))
                .sorted(comparing(o -> o.name))
                .distinct()
                .zipWith(Observable.range(1, Integer.MAX_VALUE),
                     (test, count)->String.format("%2d. %s", count, test))
                .subscribe(System.out::println);
    }

    private static class Test {
        private String name;

        private Test(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Test{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
