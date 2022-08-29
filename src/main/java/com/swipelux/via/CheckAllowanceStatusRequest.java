package com.swipelux.via;

import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

@Builder
public class CheckAllowanceStatusRequest implements AsMap {

    /**
     * Spending wallet address
     */
    public final String owner;

    /**
     * routeId from the getRoute response
     */
    public final String routeId;

    /**
     * Number of action in routes
     */
    @Builder.Default
    public final int numAction = 0;

    @Override
    public Map<String, Object> asMap() {
        var map = new HashMap<String, Object>();
        map.put("owner", owner);
        map.put("routeId", routeId);
        map.put("numAction", numAction);
        return map;
    }
}
