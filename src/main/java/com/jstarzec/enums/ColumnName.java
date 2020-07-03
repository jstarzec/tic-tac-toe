package com.jstarzec.enums;

public enum ColumnName {
    NAME_1('1'),
    NAME_2('2'),
    NAME_3('3');


    private final Character name;

    ColumnName(Character name) {
        this.name = name;
    }

    public Character getName() {
        return name;
    }
}
