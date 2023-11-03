package org.code.shubham;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class VirtualThreadPool {


    public static void main(String[] args) throws InterruptedException {
        Stream<Callable<Integer>> tasks = IntStream.range(0, 10)
                .mapToObj(i -> (Callable) () -> i * i);
        List<Integer> results = Executors.newVirtualThreadPerTaskExecutor()
                .invokeAll(tasks.toList())
                        .stream()
                                .map( f -> {
                                    try {
                                        return f.get();
                                    } catch (ExecutionException e) {
                                        throw new RuntimeException(e);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                .toList();
        System.out.println(results);
    }

}
