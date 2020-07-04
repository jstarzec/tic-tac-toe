package com.jstarzec.manager;

import java.util.Map;

public interface GameBoardManager {
    void markGameBoard(Map<String, Integer> position, char mark);

    void printGameBoard();

    char[][] getBoard();

    boolean checkForWinner(char mark);

    void setNewGameBoard();
}
