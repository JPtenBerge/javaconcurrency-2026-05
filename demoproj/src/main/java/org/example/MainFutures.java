package org.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class MainFutures {
    static void main() throws URISyntaxException {
        var future = CompletableFuture.supplyAsync(() -> "JP");

        future
                .thenApply(value -> "Welcome " + value)
                .thenApply(value -> "1 " + value)
                .thenApply(value -> {
                    throw new RuntimeException("yeah");
                })
                .thenApply(value -> "3 " + value)
                .exceptionally(ex -> {
                    System.out.println("err: " + ex.getMessage());
                    return "No results...";
                })
                .thenAccept(result -> System.out.println("Result: " + result))
                .thenRun(() -> System.out.println("Klaar!"));

        CompletableFuture<HttpResponse<String>> userFuture;
        CompletableFuture<HttpResponse<String>> todosFuture;
        try (var http = HttpClient.newHttpClient()) {

            var userRequest = HttpRequest.newBuilder().GET().uri(new URI("https://jsonplaceholder.typicode.com/users/1")).build();
            userFuture = http.sendAsync(userRequest, HttpResponse.BodyHandlers.ofString());

            var todosRequest = HttpRequest.newBuilder().GET().uri(new URI("https://jsonplaceholder.typicode.com/todos?userId=1")).build();
            todosFuture = http.sendAsync(todosRequest, HttpResponse.BodyHandlers.ofString());
        }

        userFuture
                .thenCombine(todosFuture, (userData, todosData) -> "User: " + userData.body() + "\r\n\r\n, Todos: " + todosData.body())
                .thenAccept(System.out::println);

        System.out.println("klaar");

    }
}
