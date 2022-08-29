package com.swipelux.via;

import lombok.ToString;

import java.util.Map;

@ToString
public class GetRoutesResponse {
    public Token fromToken;
    public Token toToken;
    public String fromTokenAmount;
    public Map<String, Object> routes[];
}
