package io.github.novig4j.http;

import java.util.List;

public record RateLimitPolicy(long intervalMillis, int permits) {
    static final RateLimitPolicy DEFAULT = new RateLimitPolicy(1_000, 256);
    static final RateLimitPolicy ORDER_PLACEMENT = new RateLimitPolicy(1_000, 64);
    static final RateLimitPolicy BATCH_ORDER = new RateLimitPolicy(1_000, 64);
    static final RateLimitPolicy ORDER_CANCELLATION = new RateLimitPolicy(1_000, 512);
    static final RateLimitPolicy KILL_SWITCH = new RateLimitPolicy(30_000, 1);
    static final RateLimitPolicy FALLBACK = new RateLimitPolicy(1_000, 256);
    static final List<RateLimitPolicy> USER_HISTORY = List.of(new RateLimitPolicy(1_000, 32), new RateLimitPolicy(60_000, 512));
    static final int USER_HISTORY_MAX_PAGE_SIZE = 256;


}
