package com.jstarzec.game;

import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    private TicTacToe game;

    @Before
    public void before() {

        game = new TicTacToe();
    }

    @Test
    public void testVariables() {
        assertAll("This is a group of assertion of a main game class",
                () -> assertThat(game.getBoard(), nullValue()),
                () -> assertThat(game.getHasWinner(), is(false)),
                () -> assertThat(game.getRound(), equalTo(0)),
                () -> assertThat(game.getMark(), equalTo('\0')),
                () -> assertThat(game.getGameBoardManager(), is(not(nullValue()))),
                () -> assertThat(game.getInputProvider(), is(not(nullValue())))
        );
    }
}