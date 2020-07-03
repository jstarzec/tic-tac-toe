package com.jstarzec.game;

import com.jstarzec.enums.Mark;
import com.jstarzec.enums.Message;
import com.jstarzec.manager.GameBoardManager;
import com.jstarzec.manager.TicTacToeBoardManager;
import com.jstarzec.provider.GameInputProvider;
import com.jstarzec.provider.TicTacToeInputProvider;

import java.util.Map;
import java.util.Scanner;

public class TicTacToe {

    private char[][] board;
    private boolean hasWinner;
    private int round;
    private char mark;
    private final GameBoardManager GAME_BOARD_MANAGER;
    private final GameInputProvider INPUT_PROVIDER;
    private final Scanner SCANNER;


    public TicTacToe() {
        this.GAME_BOARD_MANAGER = new TicTacToeBoardManager();
        this.INPUT_PROVIDER = new TicTacToeInputProvider(GAME_BOARD_MANAGER);
        this.SCANNER = new Scanner(System.in);
    }

    public void play() {
        initialize();
        run();
        close();
    }

    private void initialize() {
        board = getGameBoardManager().getBoard();
        hasWinner = false;
        round = 0;
        mark = Mark.X.getMark();

        System.out.print(Message.WELCOME.getMessage());
        getGameBoardManager().printGameBoard();
    }

    private void run() {
        while (!hasWinner) {

            if (round == 9) {
                System.out.println(Message.TIE.getMessage());
                return;
            }

            Map<String, Integer> position = getInputProvider().getValidInput(SCANNER);
            getGameBoardManager().markGameBoard(position, mark);
            getGameBoardManager().printGameBoard();

            if (round >= 3) {
                hasWinner = getGameBoardManager().checkForWinner(mark);
            }

            if (hasWinner) {
                System.out.println(mark + Message.WIN.getMessage());
                return;
            }

            mark = round % 2 == 0 ? Mark.O.getMark() : Mark.X.getMark();
            round++;
        }
    }

    private void close() {
        SCANNER.close();
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean getHasWinner() {
        return hasWinner;
    }

    public int getRound() {
        return round;
    }

    public char getMark() {
        return mark;
    }

    public GameBoardManager getGameBoardManager() {
        return GAME_BOARD_MANAGER;
    }

    public GameInputProvider getInputProvider() {
        return INPUT_PROVIDER;
    }
}
