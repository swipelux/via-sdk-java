package com.swipelux.via;

import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Optional;

public class ViaProtocolClient {
    private static final String DEFAULT_BASE_URL = "https://router-api.via.exchange";

    private static final String ROUTES_PATH = "/api/v1/routes";
    private static final String DEFAULT_API_KEY = "e3db93a3-ae1c-41e5-8229-b8c1ecef5583";

    private static final String NATIVE_TOKEN_ADDRESS = "0x0000000000000000000000000000000000000000";

    public JsonNode getRoutes(GetRoutesRequest reqParams) {
        var paramsMap = new HashMap<String, Object>();
        paramsMap.put("apiKey", DEFAULT_API_KEY);
        paramsMap.put("fromChainId", reqParams.fromChainId);
        paramsMap.put("fromTokenAddress", reqParams.fromTokenAddress);
        paramsMap.put("fromAmount", reqParams.fromAmount.longValue());
        paramsMap.put("toChainId", reqParams.toChainId);
        paramsMap.put("toTokenAddress", reqParams.toTokenAddress);
        paramsMap.put("multiTx", reqParams.multiTx);

        reqParams.fromAddress.ifPresent((fromAddress) -> paramsMap.put("fromAddress", fromAddress));
        reqParams.fromAddress.ifPresent((toAddress) -> paramsMap.put("toAddress", toAddress));
        reqParams.limit.ifPresent((limit) -> paramsMap.put("limit", limit));
        reqParams.offset.ifPresent((offset) -> paramsMap.put("offset", offset));

        var response = Unirest.get(DEFAULT_BASE_URL + ROUTES_PATH)
                .queryString(paramsMap)
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getStatusText());
        return response.getBody();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        var client = new ViaProtocolClient();
        var params = GetRoutesRequest.builder()
                .fromChainId(1)
                .fromTokenAddress(NATIVE_TOKEN_ADDRESS)
                .fromAmount(BigDecimal.valueOf(1000000))
                .toChainId(56)
                .toTokenAddress(NATIVE_TOKEN_ADDRESS)
                .fromAddress(Optional.of("0x856cc59aaE47997a1C8D5472Fc8dfef27821235d"))
                .multiTx(false)
                .limit(Optional.of(1))
                .build();
        System.out.println(client.getRoutes(params));

    }
}
