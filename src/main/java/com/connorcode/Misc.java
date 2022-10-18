package com.connorcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.stream.Collectors;

public class Misc {
    public static String loadResourceString(String name) {
        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(Misc.class.getClassLoader()
                .getResourceAsStream(name)))).lines()
                .collect(Collectors.joining("\n"));
    }

    public static void slowPrint(String text) {
        System.out.print(text);
    }

    static class CharDelay {
        char character;
        int delay;

        CharDelay(char character, int delay) {
            this.character = character;
            this.delay = delay;
        }
    }
}
