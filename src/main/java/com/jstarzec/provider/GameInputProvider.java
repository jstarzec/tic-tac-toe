package com.jstarzec.provider;

import java.util.Map;

public interface GameInputProvider {
    Map<String, Integer> getValidInput();
    void close();
    boolean validate(String input, Map<String, Integer> position);
    Map<String, Integer> extractBoardPositions(String input);
}
