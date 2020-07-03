package com.jstarzec.provider;

import java.util.Map;
import java.util.Scanner;

public interface GameInputProvider {
    Map<String, Integer> getValidInput(Scanner scanner);

    Map<String, Integer> extractBoardPositions(String input);
}
