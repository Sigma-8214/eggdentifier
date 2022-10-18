package com.connorcode;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String tree = Misc.loadResourceString("tree.json");
        JsonObject element = JsonParser.parseString(tree)
                .getAsJsonObject();
        Question question = new Question(element);
        Scanner in = new Scanner(System.in);

        while (true) {
            // If we have gotten to the end of a branch, print and exit
            if (question.end) {
                Misc.slowPrint(question.text);
                break;
            }

            // Make new text to print
            // Question + Options
            StringBuilder toPrint = new StringBuilder();
            toPrint.append("[?] ")
                    .append(question.text)
                    .append("\n");

            int options = question.options.size();
            for (int i = 0; i < options; i++) {
                Optional<String> answer = question.options.get(i).answer;
                if (answer.isEmpty()) continue;
                toPrint.append(String.format(" %s %d) %s\n", i == options - 1 ? "┕" : "│", i + 1, answer.get()));
            }

            // Print new question
            toPrint.append("> ");
            Misc.slowPrint(toPrint.toString());

            // Take new input
            int index = in.nextInt();

            if (index > question.options.size()) {
                Misc.slowPrint(String.format("Invalid index (0 < %d < %d)\n", index, question.options.size() + 1));
                continue;
            }

            // Set new question
            question = question.options.get(index - 1);
        }
    }
}