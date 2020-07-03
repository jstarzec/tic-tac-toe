package com.jstarzec.provider;

import java.util.Map;

public interface GameInputProvider {
    Map<String, Integer> getValidInput();
    void close();
    Map<String, Integer> extractBoardPositions(String input);
}
