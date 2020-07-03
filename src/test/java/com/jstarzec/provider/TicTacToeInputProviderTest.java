package com.jstarzec.provider;

import com.jstarzec.enums.BoardCoordinate;
import com.jstarzec.manager.TicTacToeBoardManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TicTacToeInputProviderTest {

    private GameInputProvider provider;
    private Map<String, Integer> coordinates;
    private String input;

    @BeforeEach
    public void before() {
        provider = new TicTacToeInputProvider(new TicTacToeBoardManager());
        coordinates = new HashMap<>();
    }

    @Test
    void shouldSetProperCoordinatesWhenInputIsValid() {
        //Given
        input = "A1";

        //When
        coordinates = provider.extractBoardPositions(input);

        //Then
        assertAll("This is a group of assertions for coordinates",
                () -> assertThat(coordinates.get(BoardCoordinate.COLUMN.getValue()), equalTo(1)),
                () -> assertThat(coordinates.get(BoardCoordinate.ROW.getValue()), equalTo(1)));
    }

    @Test
    void shouldNotSetProperCoordinatesWhenColumnIsLowerCase() {
        //Given
        input = "a1";

        //When
        coordinates = provider.extractBoardPositions(input);

        //Then
        assertAll("This is a group of assertions for coordinates",
                () -> assertThat(coordinates.get(BoardCoordinate.COLUMN.getValue()), is(nullValue())),
                () -> assertThat(coordinates.get(BoardCoordinate.ROW.getValue()), is(nullValue())));
    }

    @Test
    void shouldNotSetProperCoordinatesWhenInputIsTooLong() {
        //Given
        input = "LongInput123";

        //When
        coordinates = provider.extractBoardPositions(input);

        //Then
        assertAll("This is a group of assertions for coordinates",
                () -> assertThat(coordinates.get(BoardCoordinate.COLUMN.getValue()), is(nullValue())),
                () -> assertThat(coordinates.get(BoardCoordinate.ROW.getValue()), is(nullValue())));
    }

    @Test
    void shouldNotSetProperCoordinatesWhenInputConsistsOnlyFromIntegers() {
        ///Given
        input = "12";

        //When
        coordinates = provider.extractBoardPositions(input);

        //Then
        assertAll("This is a group of assertions for coordinates",
                () -> assertThat(coordinates.get(BoardCoordinate.COLUMN.getValue()), is(nullValue())),
                () -> assertThat(coordinates.get(BoardCoordinate.ROW.getValue()), is(nullValue())));
    }

    @Test
    void shouldNotSetProperCoordinatesWhenInputConsistsAnySpecialCharacter() {
        //Given
        input = "A#";

        //When
        coordinates = provider.extractBoardPositions(input);

        //Then
        assertAll("This is a group of assertions for coordinates",
                () -> assertThat(coordinates.get(BoardCoordinate.COLUMN.getValue()), is(nullValue())),
                () -> assertThat(coordinates.get(BoardCoordinate.ROW.getValue()), is(nullValue())));
    }

    @Test
    void shouldNotSetProperCoordinatesWhenColumnNumberDoesNotExist() {
        //Given
        input = "A4";

        //When
        coordinates = provider.extractBoardPositions(input);

        //Then
        assertAll("This is a group of assertions for coordinates",
                () -> assertThat(coordinates.get(BoardCoordinate.COLUMN.getValue()), is(nullValue())),
                () -> assertThat(coordinates.get(BoardCoordinate.ROW.getValue()), is(nullValue())));

    }

    @Test
    void shouldNotSetProperCoordinatesWhenRowDoesNotExist() {

        //Given
        input = "D1";

        //When
        coordinates = provider.extractBoardPositions(input);

        //Then
        assertAll("This is a group of assertions for coordinates",
                () -> assertThat(coordinates.get(BoardCoordinate.COLUMN.getValue()), is(nullValue())),
                () -> assertThat(coordinates.get(BoardCoordinate.ROW.getValue()), is(nullValue())));
    }

    @Test
    void shouldNotSetProperCoordinatesWhenInputIsNull() {
        //When
        coordinates = provider.extractBoardPositions(null);

        //Then
        assertAll("This is a group of assertions for coordinates",
                () -> assertThat(coordinates.get(BoardCoordinate.COLUMN.getValue()), is(nullValue())),
                () -> assertThat(coordinates.get(BoardCoordinate.ROW.getValue()), is(nullValue())));
    }

    @Test
    void shouldReturnAMapOfValidCoordinatesWhenHasAtLeastOneValidInput() {
        //Given
        Scanner scanner = new Scanner("G1 H2 U5 a1 b2 c3 $% 1 2 3 A B C A2").useDelimiter(" ");

        //When
        Map<String, Integer> coordinates = provider.getValidInput(scanner);

        //Then
        assertAll("This is a group of assertions for coordinates",
                () -> assertThat(coordinates.get(BoardCoordinate.COLUMN.getValue()), equalTo(2)),
                () -> assertThat(coordinates.get(BoardCoordinate.ROW.getValue()), equalTo(1)));
    }

    @Test
    void shouldReturnAMapOfValidCoordinatesWhenHasValidInput() {
        //Given
        Scanner scanner = new Scanner("A1");

        //When
        Map<String, Integer> coordinates = provider.getValidInput(scanner);

        //Then
        assertAll("This is a group of assertions for coordinates",
                () -> assertThat(coordinates.get(BoardCoordinate.COLUMN.getValue()), equalTo(1)),
                () -> assertThat(coordinates.get(BoardCoordinate.ROW.getValue()), equalTo(1)));
    }

    @Test
    void shouldThrowNoSuchElementExceptionWhenHasNoValidInput() {
        //Given
        Scanner scanner = new Scanner("no valid input");

        //When
        Exception exception = assertThrows(Exception.class, () -> provider.getValidInput(scanner));

        //Then
        assertThat(exception.getClass(), equalTo(NoSuchElementException.class));
    }
}