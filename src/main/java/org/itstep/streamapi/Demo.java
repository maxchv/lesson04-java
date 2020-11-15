package org.itstep.streamapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleSupplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) throws IOException {
        // Stream API
        // createStreams();

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

interface SimpleInterface {
    double PI = 3.14;
    void method();
    static void staticMethod() {
        System.out.println("It is work since Java 8");
    }
}
