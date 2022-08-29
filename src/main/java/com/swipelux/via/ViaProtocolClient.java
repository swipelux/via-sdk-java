package com.swipelux.via;

import kong.unirest.JsonNode;
import kong.unirest.JsonObjectMapper;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;

import java.math.BigDecimal;
import java.util.Optional;

public class ViaProtocolClient {
    private static final String DEFAULT_BASE_URL = "https://router-api.via.exchange";

    private static final String ROUTES_PATH = "/api/v1/routes";
    private static final String CHECK_ALLOWANCE_STATUS_PATH = "/api/v2/approval/check-allowance";
    private static final String DEFAULT_API_KEY = "e3db93a3-ae1c-41e5-8229-b8c1ecef5583";

    private static final String NATIVE_TOKEN_ADDRESS = "0x0000000000000000000000000000000000000000";

    private final ObjectMapper mapper = new JsonObjectMapper();

    private JsonNode executeGet(String path, AsMap params) {
        var paramsMap = params.asMap();
        paramsMap.put("apiKey", DEFAULT_API_KEY);

        var response = Unirest.get(DEFAULT_BASE_URL + path)
                .queryString(paramsMap)
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getStatusText());
        return response.getBody();
    }

    public GetRoutesResponse getRoutes(GetRoutesRequest params) {
        var json = executeGet(ROUTES_PATH, params);
        return mapper.readValue(mapper.writeValue(json), GetRoutesResponse.class);
    }

    public JsonNode getAllowanceStatus(CheckAllowanceStatusRequest params) {
        return executeGet(CHECK_ALLOWANCE_STATUS_PATH, params);
    }

    public static void main(String[] args) {
        var walletAddress = "0x6EDeBEC97F3374a3b83c7FC515de6079471Ea0Bf";
        var client = new ViaProtocolClient();
        var params = GetRoutesRequest.builder()
                .fromChainId(1)
                .fromTokenAddress(NATIVE_TOKEN_ADDRESS)
                .fromAmount(BigDecimal.valueOf(1000000))
                .toChainId(56)
                .toTokenAddress(NATIVE_TOKEN_ADDRESS)
                .fromAddress(Optional.of(walletAddress))
                .multiTx(false)
                .limit(Optional.of(1))
                .build();
        var routesResponse = client.getRoutes(params);
        System.out.println(routesResponse);
        System.out.println(routesResponse.routes[0]);

        var allowanceResponse = client.getAllowanceStatus(CheckAllowanceStatusRequest.builder()
                        .owner(walletAddress)
                        .routeId(routesResponse.routes[0].get("routeId").toString())
                .build());
        System.out.println(allowanceResponse);
    }
}
