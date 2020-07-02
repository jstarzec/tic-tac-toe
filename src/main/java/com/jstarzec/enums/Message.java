package com.jstarzec.enums;

public enum Message {
    WELCOME("Welcome to the Tic Tac Toe game! \nPlease type in a position which consists of a capital letter between A-C and is followed by an integer between 1 and 3, i.e A1, B2, C3"),
    WIN(" has won !"),
    TIE("It's a tie !"),
    INVALID_INPUT("Given position does not meet requirements.\n" +
            "Position must consist of one of letters in uppercase: A, B, C\n" +
            "and be followed by integer between 1 and 3.\n" +
            "The field of your choice must be empty - marked as . \n" +
            "Provide correct position\n");

    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
