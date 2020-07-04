package com.jstarzec.validator;

import com.jstarzec.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class TicTacToeInputValidatorTest {

    private final GameInputValidator VALIDATOR = new TicTacToeInputValidator();
    private char[][] board;
    private Map<String, Integer> coordinates;

    @BeforeEach
    public void before() {
        coordinates = new HashMap<>();
        board = new char[][]{
                {' ', ColumnName.NAME_1.getName(), ColumnName.NAME_2.getName(), ColumnName.NAME_3.getName()},
                {RowName.A.getName(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark()},
                {RowName.B.getName(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark()},
                {RowName.C.getName(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark(), Mark.EMPTY_FIELD.getMark()},
        };
    }

    @Test
    void shouldReturnTrueWhenCoordinatesIndicateAnEmptyPositionMark() {
        //Given
        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 1);

        //When
        boolean isValid = VALIDATOR.validateBoardPosition(coordinates, board);

        //Then
        assertThat(isValid, is(true));
    }

    @Test
    void shouldReturnFalseWhenCoordinatesIndicateAnyOtherThanAnEmptyPositionMark() {
        //Given
        coordinates.put(BoardCoordinate.ROW.getValue(), 0);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 0);

        //When
        boolean isValid = VALIDATOR.validateBoardPosition(coordinates, board);

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenColumnCoordinatesIsGreaterThanBoardLength() {
        //Given
        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 100);

        //When
        boolean isValid = VALIDATOR.validateBoardPosition(coordinates, board);

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenRowCoordinatesIsGreaterThanBoardLength() {
        //Given
        coordinates.put(BoardCoordinate.ROW.getValue(), 100);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 1);

        //When
        boolean isValid = VALIDATOR.validateBoardPosition(coordinates, board);

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
        boolean isValid = VALIDATOR.validateBoardPosition(coordinates, board);

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
        boolean isValid = VALIDATOR.validateBoardPosition(coordinates, board);

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassEmptyString() {
        //When
        boolean isValid = VALIDATOR.validateInput("", Pattern.BOARD_POSITION.getPattern());

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassOneCharacter() {
        //When
        boolean isValid = VALIDATOR.validateInput("A", Pattern.BOARD_POSITION.getPattern());

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassOneInteger() {
        //When
        boolean isValid = VALIDATOR.validateInput("1", Pattern.BOARD_POSITION.getPattern());

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassOneSpecialCharacter() {
        //When
        boolean isValid = VALIDATOR.validateInput("*", Pattern.BOARD_POSITION.getPattern());

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassStringOfInvalidLength() {
        //When
        boolean isValid = VALIDATOR.validateInput("LongString", Pattern.BOARD_POSITION.getPattern());

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassInputWithInvalidColumnIndicator() {
        //When
        boolean isValid = VALIDATOR.validateInput("A4", Pattern.BOARD_POSITION.getPattern());

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassInputWithInvalidRowIndicator() {
        //When
        boolean isValid = VALIDATOR.validateInput("D1", Pattern.BOARD_POSITION.getPattern());

        //Then
        assertThat(isValid, is(false));
    }

    @Test
    void shouldReturnFalseWhenPassNullAsInput() {
        //When
        boolean isValid = VALIDATOR.validateInput(null, Pattern.BOARD_POSITION.getPattern());

        //Then
        assertThat(isValid, is(false));
    }
}