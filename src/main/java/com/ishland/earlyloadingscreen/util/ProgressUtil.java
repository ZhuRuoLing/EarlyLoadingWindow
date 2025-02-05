package com.ishland.earlyloadingscreen.util;

import com.ishland.earlyloadingscreen.LoadingScreenManager;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgressUtil {

    public static void createProgress(List<? extends CompletionStage<?>> futures, CompletionStage<?> combined, String name) {
        final LoadingScreenManager.RenderLoop.ProgressHolder holder = LoadingScreenManager.tryCreateProgressHolder();
        if (holder != null) {
            AtomicInteger counter = new AtomicInteger();
            int total = futures.size();
            for (CompletionStage<?> future : futures) {
                future.whenComplete((v, throwable) -> {
                    final int i = counter.incrementAndGet();
                    holder.update(() -> String.format("Loading %s... (%d/%d)", name, i, total));
                });
            }
            combined.whenComplete((vs, throwable) -> holder.close());
        }
    }

}
