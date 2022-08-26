package com.swipelux.via;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Optional;

@Builder
public class GetRoutesRequest {
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
}
