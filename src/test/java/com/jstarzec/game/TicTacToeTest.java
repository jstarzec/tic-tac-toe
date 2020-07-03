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
    public void shouldBoardBeNull() {
        assertThat(game.getBoard(), nullValue());
    }

    @Test
    public void shouldHasWinnerBeFalse() {
        assertThat(game.getHasWinner(), is(false));
    }

    @Test
    public void shouldRoundBeEqual0() {
        assertThat(game.getRound(), equalTo(0));
    }

    @Test
    public void shouldMarkBeEqualToEmptyChar() {
        assertThat(game.getMark(), equalTo('\0'));
    }

    @Test
    public void shouldGameBoardManagerBeNull() {
        assertThat(game.getGameBoardManager(), is(not(nullValue())));
    }

    @Test
    public void shouldInputProviderBeNull() {
        assertThat(game.getInputProvider(), is(not(nullValue())));
    }
}