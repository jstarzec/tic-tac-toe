package com.jstarzec.validator;

import com.jstarzec.enums.BoardCoordinate;
import com.jstarzec.enums.ColumnName;
import com.jstarzec.enums.Mark;
import com.jstarzec.enums.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class TicTacToeInputValidatorTest {

    private GameInputValidator validator = new TicTacToeInputValidator(Pattern.INPUT_PATTERN.getPattern());
    private char[][] board;
    private Map<String, Integer> coordinates;

    @BeforeEach
    public void before() {
        coordinates = new HashMap<>();
        board = new char[][]{
                {' ', '1', '2', '3'},
                {
                        ColumnName.A.getName(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark()},
                {ColumnName.B.getName(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark()},
                {ColumnName.C.getName(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark()},
        };
    }

    @Test
    void shouldReturnTrueWhenCoordinatesIndicateAnEmptyPositionMark() {
        //Given
        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 1);

        //When
        boolean isValid = validator.validateBoardPosition(coordinates, board);

        //Then
        assertThat(isValid, is(true));
    }

    @Test
    void shouldReturnFalseWhenCoordinatesIndicateAnyOtherThanAnEmptyPositionMark() {
        //Given
        coordinates.put(BoardCoordinate.ROW.getValue(), 0);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 0);

        //When
        boolean isValid = validator.validateBoardPosition(coordinates, board);

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenColumnCoordinatesIsGreaterThanBoardLength() {
        //Given
        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 100);

        //When
        boolean isValid = validator.validateBoardPosition(coordinates, board);

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenRowCoordinatesIsGreaterThanBoardLength() {
        //Given
        coordinates.put(BoardCoordinate.ROW.getValue(), 100);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 1);

        //When
        boolean isValid = validator.validateBoardPosition(coordinates, board);

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenAnyOfCoordinatesIsNegative() {
        //Given
        assertThat(coordinates, is(not(nullValue())));
        coordinates.put(BoardCoordinate.ROW.getValue(), -1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 1);

        //When
        boolean isValid = validator.validateBoardPosition(coordinates, board);

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenAnyOfCoordinatesIsNull() {
        //Given
        assertThat(coordinates, is(not(nullValue())));
        coordinates.put(BoardCoordinate.ROW.getValue(), null);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 1);

        //When
        boolean isValid = validator.validateBoardPosition(coordinates, board);

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassEmptyString() {
        //When
        boolean isValid = validator.validateInput("");

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassOneCharacter() {
        //When
        boolean isValid = validator.validateInput("A");

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassOneInteger() {
        //When
        boolean isValid = validator.validateInput("1");

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassOneSpecialCharacter() {
        //When
        boolean isValid = validator.validateInput("*");

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassStringOfInvalidLength() {
        //When
        boolean isValid = validator.validateInput("LongString");

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassInputWithInvalidColumnIndicator() {
        //When
        boolean isValid = validator.validateInput("A4");

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassInputWithInvalidRowIndicator() {
        //When
        boolean isValid = validator.validateInput("D1");

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassNullAsInput() {
        //When
        boolean isValid = validator.validateInput(null);

        //Then
        assertThat(isValid, is(false));
    }
}