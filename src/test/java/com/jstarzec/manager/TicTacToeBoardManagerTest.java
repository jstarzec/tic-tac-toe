package com.jstarzec.manager;

import com.jstarzec.enums.BoardCoordinate;
import com.jstarzec.game.TicTacToe;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TicTacToeBoardManagerTest {

    private GameBoardManager boardManager;

    @BeforeEach
    public void before() {
        boardManager = new TicTacToeBoardManager();
    }

    @Test
    public void testBoardManagerInstance() {
        assertNotNull(boardManager);
    }

    @Test
    void printGameBoard() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        boardManager.printGameBoard();
        String expectedOutput =
                " 123\n" +
                        "A...\n" +
                        "B...\n" +
                        "C...\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void markGameBoard() {
        Map<String, Integer> coordinates = new HashMap<>();

        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 1);
        boardManager.markGameBoard(coordinates, 'X');

        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 2);
        boardManager.markGameBoard(coordinates, 'X');

        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 3);
        boardManager.markGameBoard(coordinates, 'X');

        coordinates.put(BoardCoordinate.ROW.getValue(), 2);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 1);
        boardManager.markGameBoard(coordinates, 'O');

        coordinates.put(BoardCoordinate.ROW.getValue(), 2);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 2);
        boardManager.markGameBoard(coordinates, 'O');

        assertEquals(boardManager.getBoard()[0][0], ' ');
        assertEquals(boardManager.getBoard()[0][1], '1');
        assertEquals(boardManager.getBoard()[0][2], '2');
        assertEquals(boardManager.getBoard()[0][3], '3');
        assertEquals(boardManager.getBoard()[1][0], 'A');
        assertEquals(boardManager.getBoard()[1][1], 'X');
        assertEquals(boardManager.getBoard()[1][2], 'X');
        assertEquals(boardManager.getBoard()[1][3], 'X');
        assertEquals(boardManager.getBoard()[2][0], 'B');
        assertEquals(boardManager.getBoard()[2][1], 'O');
        assertEquals(boardManager.getBoard()[2][2], 'O');
        assertEquals(boardManager.getBoard()[2][3], '.');
        assertEquals(boardManager.getBoard()[3][0], 'C');
        assertEquals(boardManager.getBoard()[3][1], '.');
        assertEquals(boardManager.getBoard()[3][2], '.');
        assertEquals(boardManager.getBoard()[3][3], '.');

        Exception exception = assertThrows(Exception.class, () -> {
            coordinates.put(BoardCoordinate.ROW.getValue(), 10);
            coordinates.put(BoardCoordinate.COLUMN.getValue(), 10);
            boardManager.markGameBoard(coordinates, 'O');
        });

        assertTrue(exception instanceof ArrayIndexOutOfBoundsException);

        exception = assertThrows(Exception.class, () -> {
            coordinates.put(BoardCoordinate.ROW.getValue(), -1);
            coordinates.put(BoardCoordinate.COLUMN.getValue(), -1);
            boardManager.markGameBoard(coordinates, 'O');
        });

        assertTrue(exception instanceof ArrayIndexOutOfBoundsException);

        exception = assertThrows(Exception.class, () -> {
            coordinates.put(BoardCoordinate.ROW.getValue(), -10);
            coordinates.put(BoardCoordinate.COLUMN.getValue(), -10);
            boardManager.markGameBoard(coordinates, 'O');
        });

        assertTrue(exception instanceof ArrayIndexOutOfBoundsException);
    }

    @Test
    void checkForWinner() {
        assertFalse(boardManager.checkForWinner('X'));
        assertFalse(boardManager.checkForWinner('O'));
        assertFalse(boardManager.checkForWinner('1'));
        assertFalse(boardManager.checkForWinner('A'));
        assertTrue(boardManager.checkForWinner('.'));

        Map<String, Integer> coordinates = new HashMap<>();
        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 1);
        boardManager.markGameBoard(coordinates, 'X');

        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 2);
        boardManager.markGameBoard(coordinates, 'X');

        coordinates.put(BoardCoordinate.ROW.getValue(), 1);
        coordinates.put(BoardCoordinate.COLUMN.getValue(), 3);
        boardManager.markGameBoard(coordinates, 'X');
        assertTrue(boardManager.checkForWinner('X'));
    }

    @Test
    void getBoard() {
        assertNotNull(boardManager.getBoard());
        assertEquals(boardManager.getBoard().length, 4);

        for(char[] row : boardManager.getBoard()){
            assertEquals(row.length, 4);
        }

    }
}