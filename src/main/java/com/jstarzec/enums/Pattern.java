package com.jstarzec.enums;

public enum Pattern {
    INPUT_PATTERN("^[A-C][1-3]$");

    private String pattern;

    Pattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
