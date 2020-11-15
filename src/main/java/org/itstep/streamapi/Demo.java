package org.itstep.streamapi;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.DoubleSupplier;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.*;

public class Demo {
    public static void main(String[] args) throws IOException {
        // Stream API
        // createStreams();

        /*
            1. Конвейерные (не терминальные) — возвращают другой Stream, то есть работают как builder,
            2. Терминальные — возвращают другой объект, такой как коллекция, примитивы,
               объекты, Optional и т.д.
         */
//        class OnlyOdd implements IntPredicate {
//            @Override
//            public boolean test(int value) {
//                return value % 2 == 1;
//            }
//        }
        IntPredicate predicate = value -> value % 2 == 0;
        Test<Integer> supplier = data -> {System.out.println(data);};
        IntStream.range(0, 100)
                .skip(10)
                .limit(10)
                //.filter(new OnlyOdd())
                .filter(value -> value % 2 == 1)
                .map(value -> value + 1000)
                .forEach(System.out::println);

        // Optional<>
        final Random random = new Random();
        OptionalDouble avg =
                IntStream.generate(() -> random.nextInt(100))
                .limit(100)
                .average();
        if(avg.isPresent()) {
            System.out.println(avg.getAsDouble());
        }

        System.out.println(randomString().orElse("").length());

        // Ссылки на методы
        Printable p = System.out::println;

        // Ленивость
        System.out.println("---------");

        Stream<String> testStream = Stream.of("One", "Two", "Three");
        Map<String, Integer>
                map = testStream
                .peek(System.out::println)
                .collect(Collectors.toMap(data -> data, String::length));
        List<String> list = testStream.collect(Collectors.toList());
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    interface Printable {
        void print(String s);
    }

    public static Optional<String> randomString() {
        Random rnd = new Random();
        return rnd.nextBoolean() ? Optional.of("It is good") : Optional.empty();
    }

    interface Test<T> {
        void apply(T data);
    }


    private static void createStreams() throws IOException {
        // Создание стрима
        // Способ 1
        Stream<Object> objStream = Stream.builder().add(1).add(2).add(3).build();
        objStream = Stream.of(1,2,3,4);
        IntStream intStream = IntStream.of(1,2,3);
        LongStream longStream = LongStream.range(0, 1000); // [0, 999] или [0, 1000)
        DoubleStream doubleStream = DoubleStream.generate(new RandomDoubleGenerator());
        //SimpleInterface.staticMethod();

        // Способ 2
        int[] arr = {1,2,3};
        intStream = Arrays.stream(arr);

        // Способ 3
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Stream<Integer> integerStream = list.stream();

        // Способ 4
        Files.list(Paths.get(".")).forEach(System.out::println);
    }

    static class RandomDoubleGenerator implements DoubleSupplier {
        static Random random = new Random();
        @Override
        public double getAsDouble() {
            return random.nextDouble();
        }
    }
}

// since 1.2 add()
// since 1.8 stream()
// @FunctionalInterface
interface ListList<T> {
    int add(T data);
    // методы по умолчанию обеспечивают обратную совместимость
    // с ранее написанным кодом и дополняют новый функционал
    default void stream() {

    }
}

class ArrayListList<T> implements ListList<T> {

    @Override
    public int add(T data) {
        return 0;
    }
}

interface SimpleInterface {
    double PI = 3.14;

    void method();

    // since java 8
    default void defaultMethod() {

    }
    // since java 8
    static void staticMethod() {
        System.out.println("It is work since Java 8");
    }
}
