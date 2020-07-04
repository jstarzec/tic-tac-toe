package com.jstarzec.enums;

public enum Message {
    WELCOME("Welcome to the Tic Tac Toe game! \nPlease type in a position which consists of a letter between A-C and is followed by an integer between 1 and 3, i.e a1, A2, b1, B2\n\n"),
    WIN(" has won!\n"),
    TIE("It's a tie!\n"),
    INVALID_POSITION("Given position does not meet requirements.\n" +
            "Position must consist of a letter followed by integer between 1 and 3.\n" +
            "The field of your choice must be empty - marked as . \n" +
            "Provide correct position.\n"),
    INVALID_NUMBER("Invalid number."),
    NEXT_ROUND_QUESTION("Do you want to play next round ? \n1. Yes\n2. No"),
    PLAYER_TURN(" player turn \n");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
