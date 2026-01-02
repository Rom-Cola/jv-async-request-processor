package mate.academy;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

public class AsyncRequestProcessor {
    private final Executor executor;

    public AsyncRequestProcessor(Executor executor) {
        this.executor = executor;
    }

    Map<String, UserData> cache = new ConcurrentHashMap<>();

    public CompletableFuture<UserData> processRequest(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (cache.containsKey(userId)) {
                return cache.get(userId);
            } else {
                UserData userData = new UserData(userId, "Some user");
                cache.put(userId, userData);
                return userData;
            }
        }, executor);
    }
}
