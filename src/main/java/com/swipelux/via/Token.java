package com.swipelux.via;

import lombok.ToString;

@ToString
public class Token {
    public String symbol;
    public int decimals;
    public int chainId;
    public String name;
    public String logoURI;
    public String coinKey;
    public String color;
    public String checksumAddress;
}
