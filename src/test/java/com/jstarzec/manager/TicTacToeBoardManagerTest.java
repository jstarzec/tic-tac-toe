package com.jstarzec.manager;

import com.jstarzec.enums.BoardCoordinate;
import com.jstarzec.enums.Mark;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class TicTacToeBoardManagerTest {

    private GameBoardManager boardManager;
    private Map<String, Integer> coordinates;

    @BeforeEach
    public void before() {
        boardManager = new TicTacToeBoardManager();
        coordinates = new HashMap<>();
    }

    @Test
    void printGameBoardOutputShouldBeEqualToExpectedOutput() {
        //Given
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput =
                " 123\n" +
                        "A...\n" +
                        "B...\n" +
                        "C...\n";

        //When
        boardManager.printGameBoard();


        //Then
        assertThat(expectedOutput, equalTo(outContent.toString()));
    }

    @Test
    void printModifiedGameBoardShouldBeEqualToExpectedOutput() {
        //Given
        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 1);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput =
                " 123\n" +
                        "AX..\n" +
                        "B...\n" +
                        "C...\n";

        //When
        boardManager.markGameBoard(coordinates, 'X');
        boardManager.printGameBoard();

        //Then
        assertThat(expectedOutput, equalTo(outContent.toString()));
    }

    @Test
    void gameBoardPositionShouldBeEqualToAMarkX() {
        //Given
        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 1);

        //When
        boardManager.markGameBoard(coordinates, 'X');

        //Then
        assertThat(boardManager.getBoard()[1][1], equalTo('X'));
    }


    @Test
    void gameBoardPositionShouldBeEqualToAMarkO() {
        //Given
        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 3);

        //When
        boardManager.markGameBoard(coordinates, 'O');

        //Then
        assertThat(boardManager.getBoard()[1][3], equalTo('O'));
    }

    @Test
    void gameBoardPositionShouldOverwrite() {
        //Given
        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 3);

        //When
        boardManager.markGameBoard(coordinates, 'O');

        //Then
        assertThat(boardManager.getBoard()[1][3], equalTo('O'));

        //When
        boardManager.markGameBoard(coordinates, 'X');

        //Then
        assertThat(boardManager.getBoard()[1][3], equalTo('X'));
    }

    @Test
    void shouldThrowArrayIndexOutOfBoundsExceptionDueToIncorrectCoordinates() {
        //Given
        coordinates.put(BoardCoordinate.ROW.getValue(), 10);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 10);

        //When
        Exception exception = assertThrows(Exception.class, () -> boardManager.markGameBoard(coordinates, 'O'));

        //Then
        assertThat(exception.getClass(), equalTo(ArrayIndexOutOfBoundsException.class));
    }

    @Test
    void shouldThrowArrayIndexOutOfBoundsExceptionDueToNegativeIndex() {
        //Given
        coordinates.put(BoardCoordinate.ROW.getValue(), -1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), -1);

        //When
        Exception exception = assertThrows(Exception.class, () -> boardManager.markGameBoard(coordinates, 'O'));

        //Then
        assertThat(exception.getClass(), equalTo(ArrayIndexOutOfBoundsException.class));
    }

    @Test
    void shouldHaveWinnerWithGivenInput() {
        //Given
        boardManager.getBoard()[1][1] = Mark.X.getMark();
        boardManager.getBoard()[1][2] = Mark.X.getMark();
        boardManager.getBoard()[1][3] = Mark.X.getMark();

        //When
        boolean hasWinner = boardManager.checkForWinner('X');

        //Then
        assertThat(hasWinner, is(true));
    }

    @Test
    void shouldNotHaveWinnerWithGivenInput() {
        //Given
        boardManager.getBoard()[1][1] = Mark.X.getMark();
        boardManager.getBoard()[1][2] = Mark.O.getMark();
        boardManager.getBoard()[1][3] = Mark.X.getMark();

        //When
        boolean hasWinner = boardManager.checkForWinner('X');

        //Then
        assertThat(hasWinner, is(false));
    }

    @Test
    void shouldNotHaveWinnerWithXMarkAndUnmodifiedBoard() {
        //When
        boolean hasWinner = boardManager.checkForWinner('X');

        //Then
        assertThat(hasWinner, is(false));
    }

    @Test
    void shouldNotHaveWinnerWithOMarkAndUnmodifiedBoard() {
        //When
        boolean hasWinner = boardManager.checkForWinner('O');

        //Then
        assertThat(hasWinner, is(false));
    }

    @Test
    void shouldHaveWinnerWithDotMarkAndUnmodifiedBoard() {
        //When
        boolean hasWinner = boardManager.checkForWinner('.');

        //Then
        assertThat(hasWinner, is(true));
    }

    @Test
    void boardShouldNotBeNull() {
        assertThat(boardManager.getBoard(), notNullValue());
    }

    @Test
    void boardShouldNotBeEmpty() {
        assertThat(Arrays.asList(boardManager.getBoard()), is(not(empty())));
    }

    @Test
    void boardShouldContainGivenValues() {
        assertAll("This is a group of assertion of each position of a board",
                () -> assertThat(boardManager.getBoard()[0][0], equalTo(' ')),
                () -> assertThat(boardManager.getBoard()[0][1], equalTo('1')),
                () -> assertThat(boardManager.getBoard()[0][2], equalTo('2')),
                () -> assertThat(boardManager.getBoard()[0][3], equalTo('3')),
                () -> assertThat(boardManager.getBoard()[1][0], equalTo('A')),
                () -> assertThat(boardManager.getBoard()[1][1], equalTo('.')),
                () -> assertThat(boardManager.getBoard()[1][2], equalTo('.')),
                () -> assertThat(boardManager.getBoard()[1][3], equalTo('.')),
                () -> assertThat(boardManager.getBoard()[2][0], equalTo('B')),
                () -> assertThat(boardManager.getBoard()[2][1], equalTo('.')),
                () -> assertThat(boardManager.getBoard()[2][2], equalTo('.')),
                () -> assertThat(boardManager.getBoard()[2][3], equalTo('.')),
                () -> assertThat(boardManager.getBoard()[3][0], equalTo('C')),
                () -> assertThat(boardManager.getBoard()[3][1], equalTo('.')),
                () -> assertThat(boardManager.getBoard()[3][2], equalTo('.')),
                () -> assertThat(boardManager.getBoard()[3][3], equalTo('.'))
        );
    }
}