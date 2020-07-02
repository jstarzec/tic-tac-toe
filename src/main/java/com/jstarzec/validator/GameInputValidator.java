package com.jstarzec.validator;

import java.util.Map;

public interface GameInputValidator {

    boolean validateBoardPosition(Map<String, Integer> position, char[][] board);

    boolean validateInput(String position);
}
