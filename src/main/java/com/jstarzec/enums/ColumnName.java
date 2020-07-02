package com.jstarzec.enums;

public enum ColumnName {
    A('A'),
    B('B'),
    C('C');

    private final Character name;

    ColumnName(Character name) {
        this.name = name;
    }

    public Character getName() {
        return name;
    }
}
