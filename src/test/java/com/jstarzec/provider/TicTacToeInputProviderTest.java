package com.jstarzec.provider;

import com.jstarzec.enums.BoardCoordinate;
import com.jstarzec.manager.TicTacToeBoardManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeInputProviderTest {

    private GameInputProvider provider;

    @BeforeEach
    public void before(){
        provider = new TicTacToeInputProvider(new TicTacToeBoardManager());
    }

    @Test
    void validate() {
        String input = "A1";
        Map<String, Integer> position = provider.extractBoardPositions(input);
        provider.validate(input, position);
        assertTrue(provider.validate(input, position));

        input = "A2";
        position = provider.extractBoardPositions(input);
        assertTrue(provider.validate(input, position));

        input = "A3";
        position = provider.extractBoardPositions(input);
        assertTrue(provider.validate(input, position));

        input = "B1";
        position = provider.extractBoardPositions(input);
        assertTrue(provider.validate(input, position));

        input = "B2";
        position = provider.extractBoardPositions(input);
        assertTrue(provider.validate(input, position));

        input = "B3";
        position = provider.extractBoardPositions(input);
        assertTrue(provider.validate(input, position));

        input = "C1";
        position = provider.extractBoardPositions(input);
        assertTrue(provider.validate(input, position));

        input = "C2";
        position = provider.extractBoardPositions(input);
        assertTrue(provider.validate(input, position));

        input = "C3";
        position = provider.extractBoardPositions(input);
        assertTrue(provider.validate(input, position));

        input = "!@#@#$%#^%";
        position = provider.extractBoardPositions(input);
        assertFalse(provider.validate(input, position));

        input = "A0";
        position = provider.extractBoardPositions(input);
        assertFalse(provider.validate(input, position));

        input = "12";
        position = provider.extractBoardPositions(input);
        assertFalse(provider.validate(input, position));

        input = "1000000000000000000000000";
        position = provider.extractBoardPositions(input);
        assertFalse(provider.validate(input, position));

        input = "AA";
        position = provider.extractBoardPositions(input);
        assertFalse(provider.validate(input, position));

        input = "LONG STRING WITH SPACES";
        position = provider.extractBoardPositions(input);
        assertFalse(provider.validate(input, position));

        input = "LONGSTRINGWITHNOSPACES";
        position = provider.extractBoardPositions(input);
        assertFalse(provider.validate(input, position));
    }

    @Test
    void extractBoardPositions() {
        String input = "A1";
        Map<String, Integer> position = provider.extractBoardPositions(input);
        assertEquals(1, position.get(BoardCoordinate.ROW.getValue()));
        assertEquals(1, position.get(BoardCoordinate.COLUMN.getValue()));

        input = "B2";
        position = provider.extractBoardPositions(input);
        assertEquals(2, position.get(BoardCoordinate.ROW.getValue()));
        assertEquals(2, position.get(BoardCoordinate.COLUMN.getValue()));

        input = "g8";
        position = provider.extractBoardPositions(input);
        assertNull(position.get(BoardCoordinate.ROW.getValue()));
        assertNull(position.get(BoardCoordinate.COLUMN.getValue()));

        input = "LongInput123";
        position = provider.extractBoardPositions(input);
        assertNull(position.get(BoardCoordinate.ROW.getValue()));
        assertNull(position.get(BoardCoordinate.COLUMN.getValue()));

        input = "1234567890";
        position = provider.extractBoardPositions(input);
        assertNull(position.get(BoardCoordinate.ROW.getValue()));
        assertNull(position.get(BoardCoordinate.COLUMN.getValue()));

        input = "14";
        position = provider.extractBoardPositions(input);
        assertNull(position.get(BoardCoordinate.ROW.getValue()));
        assertNull(position.get(BoardCoordinate.COLUMN.getValue()));
    }
}