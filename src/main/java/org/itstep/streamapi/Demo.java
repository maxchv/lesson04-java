package org.itstep.streamapi;

import java.util.Random;
import java.util.function.DoubleSupplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) {
        // Stream API
        Stream<Object> objStream = Stream.builder().add(1).add(2).add(3).build();
        objStream = Stream.of(1,2,3,4);
        IntStream intStream = IntStream.of(1,2,3);
        LongStream longStream = LongStream.range(0, 1000); // [0, 999] или [0, 1000)
        DoubleStream doubleStream = DoubleStream.generate(new RandomDoubleGenerator());
    }

    static class RandomDoubleGenerator implements DoubleSupplier {
        static Random random = new Random();
        @Override
        public double getAsDouble() {
            return random.nextDouble();
        }
    }
}
