package com.swipelux.via;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Builder
public class GetRoutesRequest implements AsMap {
    public final int fromChainId;
    public final String fromTokenAddress;
    public final BigDecimal fromAmount;
    public final int toChainId;
    public final String toTokenAddress;

    @Builder.Default
    public final Optional<String> fromAddress = Optional.empty();

    @Builder.Default
    public final Optional<String> toAddress = Optional.empty();

    @Builder.Default
    public final Optional<Integer> limit = Optional.empty();

    @Builder.Default
    public final Optional<Integer> offset = Optional.empty();

    public final boolean multiTx;

    @Override
    public Map<String, Object> asMap() {
        var map = new HashMap<String, Object>();
        map.put("fromChainId", fromChainId);
        map.put("fromTokenAddress", fromTokenAddress);
        map.put("fromAmount", fromAmount.longValue());
        map.put("toChainId", toChainId);
        map.put("toTokenAddress", toTokenAddress);
        map.put("multiTx", multiTx);

        fromAddress.ifPresent(v -> map.put("fromAddress", v));
        fromAddress.ifPresent(v -> map.put("toAddress", v));
        limit.ifPresent(v -> map.put("limit", v));
        offset.ifPresent(v -> map.put("offset", v));
        return map;
    }
}
