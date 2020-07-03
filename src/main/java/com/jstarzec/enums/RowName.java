package com.jstarzec.enums;

public enum RowName {
    A('A'),
    B('B'),
    C('C');

    private final Character name;

    RowName(Character name) {
        this.name = name;
    }

    public Character getName() {
        return name;
    }
}
