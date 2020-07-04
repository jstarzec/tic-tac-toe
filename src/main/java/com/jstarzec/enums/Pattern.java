package com.jstarzec.enums;

public enum Pattern {
    BOARD_POSITION("^[A-Ca-c][1-3]$"),
    MENU_INPUT("^[1-2]$");

    private final String pattern;

    Pattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
