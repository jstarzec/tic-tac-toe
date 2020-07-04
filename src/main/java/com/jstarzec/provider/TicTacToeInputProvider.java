package com.jstarzec.provider;

import com.jstarzec.enums.BoardCoordinate;
import com.jstarzec.enums.Pattern;
import com.jstarzec.enums.RowName;
import com.jstarzec.enums.Message;
import com.jstarzec.manager.GameBoardManager;
import com.jstarzec.validator.GameInputValidator;
import com.jstarzec.validator.TicTacToeInputValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TicTacToeInputProvider implements GameInputProvider {

    private final GameInputValidator VALIDATOR;
    private final GameBoardManager MANAGER;

    public TicTacToeInputProvider(GameBoardManager manager) {
        this.MANAGER = manager;
        this.VALIDATOR = new TicTacToeInputValidator();
    }

    @Override
    public Map<String, Integer> getValidCoordinates(Scanner scanner) {
        String input;
        boolean isValid = false;
        Map<String, Integer> position = new HashMap<>();

        while (!isValid) {
            input = scanner.next();

            if (null == input) {
                input = scanner.next();
            }

            boolean isInputValid = VALIDATOR.validateInput(input, Pattern.BOARD_POSITION.getPattern());
            boolean isPositionValid = false;

            char rowIndicator = input.charAt(0);

            if (Character.isLowerCase(rowIndicator)) {
                input = input.replace(rowIndicator, Character.toUpperCase(rowIndicator));
            }

            if (isInputValid) {
                position = extractBoardPositions(input);
                isPositionValid = VALIDATOR.validateBoardPosition(position, MANAGER.getBoard());
            }

            isValid = isInputValid && isPositionValid;

            if (!isValid) {
                System.out.println(Message.INVALID_POSITION.getMessage());
                MANAGER.printGameBoard();
            }

        }

        return position;
    }

    public Map<String, Integer> extractBoardPositions(String input) {
        Map<String, Integer> position = new HashMap<>();

        if (null == input || input.isEmpty()) {
            return position;
        }

        char row = input.charAt(0);
        int column = Character.getNumericValue(input.charAt(1));
        char columnA = RowName.A.getName();
        char columnB = RowName.B.getName();
        char columnC = RowName.C.getName();

        position.put(BoardCoordinate.COLUMN.getValue(), null);
        position.put(BoardCoordinate.ROW.getValue(), null);

        boolean isRowValid = row == columnA || row == columnB || row == columnC;
        boolean isColumnValid = column == 1 || column == 2 || column == 3;

        if (isRowValid && isColumnValid) {
            Integer rowValue = row == columnA ? 1 : (row == columnB ? 2 : 3);
            position.put(BoardCoordinate.ROW.getValue(), rowValue);
            position.put(BoardCoordinate.COLUMN.getValue(), column);
        }

        return position;
    }

    @Override
    public int getValidMenuNumber(Scanner scanner) {
        String input = "";
        boolean isValid = false;

        while (!isValid) {
            input = scanner.next();
            isValid = VALIDATOR.validateInput(input, Pattern.MENU_INPUT.getPattern());

            if(!isValid){
                System.out.println(Message.INVALID_NUMBER.getMessage());
                System.out.println(Message.NEXT_ROUND_QUESTION.getMessage());
            }
        }

        return Integer.parseInt(input);
    }
}
