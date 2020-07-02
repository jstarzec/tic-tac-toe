package com.jstarzec.validator;

import com.jstarzec.enums.BoardCoordinate;
import com.jstarzec.enums.Mark;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicTacToeInputValidator implements GameInputValidator {

    private final Pattern PATTERN;

    public TicTacToeInputValidator(String pattern) {
        this.PATTERN = Pattern.compile(pattern);
    }

    @Override
    public boolean validateBoardPosition(Map<String, Integer> positions, char[][] board) {
        int row = positions.get(BoardCoordinate.ROW.getValue());
        int column = positions.get(BoardCoordinate.COLUMN.getValue());

        return board[row][column] == Mark.EMPTY_FIELD.getMark();
    }

    @Override
    public boolean validateInput(String position) {
        Matcher matcher = this.PATTERN.matcher(position);

        return matcher.matches();
    }

}
