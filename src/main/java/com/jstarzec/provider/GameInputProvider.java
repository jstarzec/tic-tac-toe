package com.jstarzec.provider;

import java.util.Map;
import java.util.Scanner;

public interface GameInputProvider {
    Map<String, Integer> getValidCoordinates(Scanner scanner);

    Map<String, Integer> extractBoardPositions(String input);

    int getValidMenuNumber(Scanner scanner);
}
