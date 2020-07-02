package com.jstarzec.provider;

import com.jstarzec.enums.BoardCoordinate;
import com.jstarzec.enums.Message;
import com.jstarzec.enums.Pattern;
import com.jstarzec.manager.GameBoardManager;
import com.jstarzec.validator.GameInputValidator;
import com.jstarzec.validator.TicTacToeInputValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TicTacToeInputProvider implements GameInputProvider {

    private final GameInputValidator VALIDATOR;
    private final Scanner SCANNER;
    private final GameBoardManager MANAGER;

    public TicTacToeInputProvider(GameBoardManager manager) {
        this.MANAGER = manager;
        this.VALIDATOR = new TicTacToeInputValidator(Pattern.INPUT_PATTERN.getPattern());
        this.SCANNER = new Scanner(System.in);
    }

    @Override
    public Map<String, Integer> getValidInput() {
        String input;
        boolean isValid = false;
        Map<String, Integer> position = new HashMap<>();

        while (!isValid) {
            input = SCANNER.nextLine();
            position = extractBoardPositions(input);
            isValid = validate(input, position);

            if (!isValid) {
                System.out.println(Message.INVALID_INPUT.getMessage());
                MANAGER.printGameBoard();
            }

        }

        return position;
    }

    public boolean validate(String input, Map<String, Integer> position) {
        return VALIDATOR.validateInput(input) && VALIDATOR.validateBoardPosition(position, MANAGER.getBoard());
    }

    public Map<String, Integer> extractBoardPositions(String input) {
        Map<String, Integer> position = new HashMap<>();
        char row = input.charAt(0);
        int column = Character.getNumericValue(input.charAt(1));

        position.put(BoardCoordinate.COLUMN.getValue(), null);
        position.put(BoardCoordinate.ROW.getValue(), null);

        boolean isRowValid = row == 'A' || row == 'B' || row == 'C';
        boolean isColumnValid = column == 1 || column == 2 || column == 3;

        if (isRowValid && isColumnValid) {
            Integer rowValue = row == 'A' ? 1 : (row == 'B' ? 2 : 3);
            position.put(BoardCoordinate.ROW.getValue(), rowValue);
            position.put(BoardCoordinate.COLUMN.getValue(), column);
        }

        return position;
    }

    public void close() {
        SCANNER.close();
    }
}
