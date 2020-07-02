package com.jstarzec.enums;

public enum BoardCoordinate {
    ROW("row"),
    COLUMN("column");

    private String value;

    BoardCoordinate(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
