package com.jstarzec.manager;

import com.jstarzec.enums.BoardCoordinate;
import com.jstarzec.enums.ColumnName;
import com.jstarzec.enums.Mark;
import com.jstarzec.enums.RowName;
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
    private char mark;

    @BeforeEach
    public void before() {
        boardManager = new TicTacToeBoardManager();
        coordinates = new HashMap<>();
    }

    @Test
    void ShouldBeEqualToExpectedOutputWhenPrintGameBoarMethodCalled() {
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
    void shouldBeEqualToExpectedOutputWhenPrintModifiedGameBoard() {
        //Given
        mark = Mark.X.getMark();
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
        boardManager.markGameBoard(coordinates, mark);
        boardManager.printGameBoard();

        //Then
        assertThat(expectedOutput, equalTo(outContent.toString()));
    }

    @Test
    void shouldBeEqualToMarkXWhenMarkedGameBoard() {
        //Given
        mark = Mark.X.getMark();
        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 1);

        //When
        boardManager.markGameBoard(coordinates, mark);

        //Then
        assertThat(boardManager.getBoard()[1][1], equalTo(mark));
    }


    @Test
    void shouldBeEqualToMarkOWhenMarkedGameBoard() {
        //Given
        mark = Mark.O.getMark();
        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 3);

        //When
        boardManager.markGameBoard(coordinates, mark);

        //Then
        assertThat(boardManager.getBoard()[1][3], equalTo(mark));
    }

    @Test
    void shouldOverwriteGameBoardPosition() {
        //Given
        mark = Mark.O.getMark();
        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 3);

        //When
        boardManager.markGameBoard(coordinates, mark);

        //Then
        assertThat(boardManager.getBoard()[1][3], equalTo(mark));

        //When
        mark = Mark.X.getMark();
        boardManager.markGameBoard(coordinates, mark);

        //Then
        assertThat(boardManager.getBoard()[1][3], equalTo(mark));
    }

    @Test
    void shouldThrowArrayIndexOutOfBoundsExceptionWhenIndexIncorrect() {
        //Given
        mark = Mark.O.getMark();
        coordinates.put(BoardCoordinate.ROW.getValue(), 10);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 10);

        //When
        Exception exception = assertThrows(Exception.class, () -> boardManager.markGameBoard(coordinates, mark));

        //Then
        assertThat(exception.getClass(), equalTo(ArrayIndexOutOfBoundsException.class));
    }

    @Test
    void shouldThrowArrayIndexOutOfBoundsExceptionWhenIndexIsNegative() {
        //Given
        mark = Mark.O.getMark();
        coordinates.put(BoardCoordinate.ROW.getValue(), -1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), -1);

        //When
        Exception exception = assertThrows(Exception.class, () -> boardManager.markGameBoard(coordinates, mark));

        //Then
        assertThat(exception.getClass(), equalTo(ArrayIndexOutOfBoundsException.class));
    }

    @Test
    void shouldHaveWinnerWithGivenInput() {
        //Given
        mark = Mark.X.getMark();
        boardManager.getBoard()[1][1] = Mark.X.getMark();
        boardManager.getBoard()[1][2] = Mark.X.getMark();
        boardManager.getBoard()[1][3] = Mark.X.getMark();

        //When
        boolean hasWinner = boardManager.checkForWinner(mark);

        //Then
        assertThat(hasWinner, is(true));
    }

    @Test
    void shouldNotHaveWinnerWithGivenInput() {
        //Given
        mark = Mark.X.getMark();
        boardManager.getBoard()[1][1] = Mark.X.getMark();
        boardManager.getBoard()[1][2] = Mark.O.getMark();
        boardManager.getBoard()[1][3] = Mark.X.getMark();

        //When
        boolean hasWinner = boardManager.checkForWinner(mark);

        //Then
        assertThat(hasWinner, is(false));
    }

    @Test
    void shouldNotHaveWinnerWhenMarkXAndBoardUnmodified() {
        //Given
        mark = Mark.X.getMark();

        //When
        boolean hasWinner = boardManager.checkForWinner(mark);

        //Then
        assertThat(hasWinner, is(false));
    }

    @Test
    void shouldNotHaveWinnerWhenMarkOAndBoardUnmodified() {
        //Given
        mark = Mark.O.getMark();

        //When
        boolean hasWinner = boardManager.checkForWinner(mark);

        //Then
        assertThat(hasWinner, is(false));
    }

    @Test
    void shouldHaveWinnerWithDotMarkWhenBoardNotModified() {
        //Given
        mark = Mark.EMPTY_FIELD.getMark();

        //When
        boolean hasWinner = boardManager.checkForWinner(mark);

        //Then
        assertThat(hasWinner, is(true));
    }

    @Test
    void shouldNotBeNull() {
        assertThat(boardManager.getBoard(), notNullValue());
    }

    @Test
    void shouldNotBeEmpty() {
        assertThat(Arrays.asList(boardManager.getBoard()), is(not(empty())));
    }

    @Test
    void shouldContainGivenValues() {
        assertAll("This is a group of assertion of each position of a board",
                () -> assertThat(boardManager.getBoard()[0][0], equalTo(' ')),
                () -> assertThat(boardManager.getBoard()[0][1], equalTo(ColumnName.NAME_1.getName())),
                () -> assertThat(boardManager.getBoard()[0][2], equalTo(ColumnName.NAME_2.getName())),
                () -> assertThat(boardManager.getBoard()[0][3], equalTo(ColumnName.NAME_3.getName())),
                () -> assertThat(boardManager.getBoard()[1][0], equalTo(RowName.A.getName())),
                () -> assertThat(boardManager.getBoard()[1][1], equalTo(Mark.EMPTY_FIELD.getMark())),
                () -> assertThat(boardManager.getBoard()[1][2], equalTo(Mark.EMPTY_FIELD.getMark())),
                () -> assertThat(boardManager.getBoard()[1][3], equalTo(Mark.EMPTY_FIELD.getMark())),
                () -> assertThat(boardManager.getBoard()[2][0], equalTo(RowName.B.getName())),
                () -> assertThat(boardManager.getBoard()[2][1], equalTo(Mark.EMPTY_FIELD.getMark())),
                () -> assertThat(boardManager.getBoard()[2][2], equalTo(Mark.EMPTY_FIELD.getMark())),
                () -> assertThat(boardManager.getBoard()[2][3], equalTo(Mark.EMPTY_FIELD.getMark())),
                () -> assertThat(boardManager.getBoard()[3][0], equalTo(RowName.C.getName())),
                () -> assertThat(boardManager.getBoard()[3][1], equalTo(Mark.EMPTY_FIELD.getMark())),
                () -> assertThat(boardManager.getBoard()[3][2], equalTo(Mark.EMPTY_FIELD.getMark())),
                () -> assertThat(boardManager.getBoard()[3][3], equalTo(Mark.EMPTY_FIELD.getMark()))
        );
    }
}