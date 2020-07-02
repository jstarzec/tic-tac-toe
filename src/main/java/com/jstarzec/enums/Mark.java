package com.jstarzec.enums;

public enum Mark {
    X('X'),
    O('O');

    private char mark;

    Mark(char mark) {
        this.mark = mark;
    }

    public char getMark() {
        return mark;
    }
}
