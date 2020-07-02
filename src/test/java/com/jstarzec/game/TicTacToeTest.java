package com.jstarzec.game;

import org.junit.*;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    private TicTacToe game;

    @BeforeClass
    public static void beforeClass() {
    }

    @AfterClass
    public static void afterClass() {
    }

    @Before
    public void before() {
        game = new TicTacToe();
    }

    @After
    public void after() {
    }

    @Test
    public void testVariables() {
        assertNull(game.getBoard());
        assertFalse(game.getHasWinner());
        assertEquals(game.getRound(), 0);
        assertEquals(game.getMark(), '\0');
        assertNotNull(game.getGameBoardManager());
        assertNotNull(game.getInputProvider());
    }

//    @Test
//    public void printTest() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        game.play();
//        String expectedOutput  = "Welcome to the Tic Tac Toe game! \n" +
//                "Please type in a position which consists of a capital letter between A-C and is followed by an integer between 1 and 3, i.e A1, B2, C3 123\n" +
//                "A...\n" +
//                "B...\n" +
//                "C...\n";
//
//        assertEquals(expectedOutput, outContent.toString());
//    }


}