package com.intelligrape.linksharing

public enum Seriousness {

    CASUAL("Casual"),
    SERIOUS("Serious"),
    VERY_SERIOUS("Very Serious")

    private final String name;

    Seriousness (String name) {
        this.name = name
    }

    public String getKey() { return name() }

    public String getValue() { return toString() }

    public String toString() {
        return this.name;
    }

}