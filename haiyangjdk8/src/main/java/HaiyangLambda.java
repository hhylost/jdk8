import jdk.internal.instrumentation.Logger;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class HaiyangLambda {
    public static void main(String[] args) {
        List<Integer> origin = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            origin.add(i);
        }
        // 获取偶数集合
        List<Integer> result = new ArrayList<>();
        for (Integer data : origin) {
            if (data % 2 == 0) {
                result.add(data);
            }
        }
    }

    @Test
    public void test() {
        List<Integer> origin = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        //惰性求值方法 只描述不产生新集合
        Stream<Integer> stream = origin.stream().filter(data -> data % 2 == 0);
        //及早求值 最终会从Stream 中产生值
        origin.stream().filter(data -> data % 2 == 0).collect(Collectors.toList());

        // 及早求值
        stream.count();
        Logger logger = new Logger() {
            @Override
            public void error(String s) {

            }

            @Override
            public void warn(String s) {

            }

            @Override
            public void info(String s) {

            }

            @Override
            public void debug(String s) {

            }

            @Override
            public void trace(String s) {

            }

            @Override
            public void error(String s, Throwable throwable) {

            }
        };
    }

    /**
     * 计算大于5的个数
     */
    @Test
    public void addUp8() {
        List<Integer> data = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        Stream<Integer> stream = data.stream().filter(it -> {
            System.out.println("456");
            return it > 5;
        });
        System.out.println("123");
        System.out.println(stream.count());
        IntSummaryStatistics tra = data.stream().mapToInt(a -> a.intValue()).summaryStatistics();
    }

    public static class Person {
        private NameChangeListener listener;

        public void setListener(NameChangeListener listener) {
            this.listener = listener;
        }

        public static interface NameChangeListener {
            void change(String name);
        }
    }

    public static class God {
        private NameChangeListener listener;

        public void setListener(NameChangeListener listener) {
            this.listener = listener;
        }

        public static interface NameChangeListener {
            void change(String name);
        }
    }

    public void example() {
        Person person = new Person();
        person.setListener(new Person.NameChangeListener() {
            @Override
            public void change(String name) {
                System.out.print("new name: " + name);
            }
        });
        God god = new God();
        god.setListener(new God.NameChangeListener() {
            @Override
            public void change(String name) {
                System.out.print("new name: " + name);
            }
        });

        Predicate<Integer> test = x -> x > 5;
    }

    @Test
    public void test12() {
        InitClass initClass = new InitClass();
        for (int i = 0; i < 10; i++) {
            initClass.init = 0L;
            LongStream.rangeClosed(1, 100).parallel().forEach(data -> initClass.init += data);
            System.out.println(initClass.init);
        }
    }

    class InitClass {
        public Long init = 0L;
    }

    @Test
    public void test123() {
        List<String> list = Stream.of("a", "ab", "hello").map(s -> s.toUpperCase()).collect(Collectors.toList());

        Stream.of(asList(1, 2), asList(3, 4)).flatMap(data -> data.stream()).collect(Collectors.toList());

        Integer result = Stream.of(asList(1, 2), asList(3, 4)).flatMap(data -> data.stream()).max(Comparator.comparing(num -> num.intValue())).get();
        System.out.println(result);
    }
}
