package com.jstarzec.manager;

import com.jstarzec.enums.BoardCoordinate;
import com.jstarzec.enums.ColumnName;
import com.jstarzec.enums.Mark;

import java.util.Map;

public class TicTacToeBoardManager implements GameBoardManager {

    private final char[][] board;

    public TicTacToeBoardManager() {
        board = new char[][]{
                {' ', '1', '2', '3'},
                {ColumnName.A.getName(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark()},
                {ColumnName.B.getName(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark()},
                {ColumnName.C.getName(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark()},
        };
    }

    @Override
    public void printGameBoard() {

        for (char[] row : board) {

            for (char field : row) {
                System.out.print(field);
            }

            System.out.print("\n");
        }
    }

    @Override
    public void markGameBoard(Map<String, Integer> coordinates, char mark) {
        int row = coordinates.get(BoardCoordinate.ROW.getValue());
        int column = coordinates.get(BoardCoordinate.COLUMN.getValue());

        board[row][column] = mark;
    }

    @Override
    public boolean checkForWinner(char mark) {

        //horizontal check
        if ((board[1][1] == mark && board[1][2] == mark && board[1][3] == mark)
                || (board[2][1] == mark && board[2][2] == mark && board[2][3] == mark)
                || (board[3][1]) == mark && board[3][2] == mark && board[3][3] == mark) {

            return true;
        }

        //vertical check
        if ((board[1][1] == mark && board[2][1] == mark && board[3][1] == mark)
                || (board[1][2] == mark && board[2][2] == mark && board[3][2] == mark)
                || (board[1][3]) == mark && board[2][3] == mark && board[3][3] == mark) {

            return true;
        }

        //diagonal check
        return (board[1][1] == mark && board[2][2] == mark && board[3][3] == mark)
                || (board[1][3] == mark && board[2][2] == mark && board[3][1] == mark);
    }

    @Override
    public char[][] getBoard() {
        return board;
    }
}
