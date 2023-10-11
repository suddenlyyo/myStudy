package com.zx.asyncTask;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @program: myStudy
 * @description: 异步任务处理
 * @author: zhou  xun
 * @create: 2023-10-11 12:54
 */
public class AsyncTaskTest {
    //CompletableFuture、Callable和Future是Java中用于处理异步任务的三种重要接口
    //region 使用场景
    //Future通常用于表示一个单独的异步任务的结果，它提供了获取任务结果的方法（get），但这个方法会阻塞直到任务完成。
    //Future的主要优点是简单直接，但在处理多个异步任务和构建更复杂的异步操作流程时可能会变得复杂和混乱。
    //CompletableFuture是Java 8引入的，提供了更强大的功能，例如链式调用和组合式调用。
    //它不仅可以表示一个异步任务的结果，还可以表示多个异步任务的组合和流程。CompletableFuture支持非阻塞式编程，可以避免使用Future时遇到的阻塞和等待问题。
    //CompletableFuture还提供了多种方法来处理异步任务的结果，例如thenApply、thenAccept、thenRun等。
    //因此，在处理多个异步任务和构建复杂的异步操作流程时，CompletableFuture通常更受欢迎。
    //endregion
    // region 区别
    //Future是单向的，只能获取任务的结果，而不能对任务进行操作或对多个任务进行组合。而CompletableFuture是双向的，可以链式调用多个操作，也可以组合多个任务。
    //Future使用阻塞式等待任务完成，而CompletableFuture使用非阻塞式等待任务完成。
    // 这意味着在使用CompletableFuture时，我们不需要手动调用阻塞式的get()方法来等待任务完成，而是可以继续执行其他任务或操作
    //endregion
    @DisplayName("Future Test")
    @Test
    public void futureTest() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Future<Integer> future = executor.submit(() -> {
            // 异步任务逻辑，返回计算结果
            return 123;
        });

        // 等待任务完成并获取计算结果
        try {
            Integer result = future.get();
            System.out.println("Future的结果是: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 关闭线程池
        executor.shutdown();
    }

    @DisplayName("CompletableFuture Test")
    @Test
    public void completableFutureTest() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 异步任务逻辑，返回计算结果
            return "Hello, World!";
        });
        // 等待任务完成并获取计算结果
        try {
            String result = future.get();
            System.out.println("CompletableFuture的结果是: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("CompletableFuture Test1")
    @Test
    public void completableFutureTest1() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Callable<Integer> callable = () -> {
            // 异步任务逻辑，返回计算结果
            return 123;
        };
        Future<Integer> future = executor.submit(callable);
        try {
            Integer result = future.get();  // 获取异步计算的结果
            System.out.println("CompletableFuture的结果是: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();  // 关闭线程池
        }
    }

    @DisplayName("CompletableFuture Test2")
    @Test
    public void completableFutureTest2() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 异步任务逻辑，返回计算结果
            return "Hello, World!";
        });

        // 等待任务完成并获取计算结果
        try {
            String result = future.get();
            System.out.println("CompletableFuture的结果是: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 链式调用
        future.thenAccept(result -> {
            // 处理计算结果
        });

        // 组合式调用
        CompletableFuture<String> future2 = future.thenCompose(result -> {
            // 继续处理计算结果，并返回新的CompletableFuture
            return CompletableFuture.supplyAsync(() -> {
                // 异步任务逻辑，返回新的计算结果
                return "Hello, World!";
            });
        });

    }

    @DisplayName("CompletableFuture 异常处理")
    @Test
    public void completableFutureTest3() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 抛出一个异常
            throw new RuntimeException("Oops!");
        });

        // 无论之前的任务是否抛出异常，都会打印这个消息
        future.exceptionally(ex -> {
            // 处理异常
            return "Error";
        }).thenAccept(System.out::println);

    }

    @DisplayName("CompletableFuture  链式异步操作")
    @Test
    public void completableFutureTest4() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            // 第一个长时间运行的任务
            return "Task 1 Result";
        });

        // 第二个任务完成后处理结果
        future1.thenApply(result -> {
            // 对结果进行处理
            return "Task 2 Input";
        }).thenAccept(System.out::println);
    }

    @DisplayName("CompletableFuture 并行执行多个异步任务")
    @Test
    public void completableFutureTest5() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello, future1!");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Hello, future2!");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "Hello, future3!");

//        CompletableFuture.allOf(future1, future2, future3)
//                .thenAccept(results -> {
//            // 所有任务都完成了，可以在这里处理结果
//                    System.out.println("CompletableFuture的结果是: " + results);
//        });
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2,future3)
                .thenApply(result -> {
                    // 在所有异步任务完成后执行的操作
                    System.out.println("所有异步任务完成");
                    return null;
                });

        combinedFuture.join(); // 等待异步任务完成
    }
    //thenApply：这个方法用于获取一个异步计算的结果，并对这个结果应用一个函数。这个函数必须返回一个结果，并且这个结果会作为新的CompletableFuture的结果。
    // 换句话说，thenApply方法允许你对异步任务的结果进行某种转换，并返回一个新的结果
    //thenAccept：这个方法用于处理一个异步计算的结果，但是它不返回任何新的结果。它的作用主要是为了执行某些操作，比如把结果写入一个流或者数据库等
}
