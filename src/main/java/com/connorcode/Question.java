package com.connorcode;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Question {
    String text;
    boolean end;
    Optional<String> answer;
    List<Question> options;

    Question(JsonObject json) {
        this.end = json.has("end");
        this.text = json.get(end ? "end" : "text")
                .getAsString();
        this.answer = json.has("answer") ? Optional.of(json.get("answer")
                .getAsString()) : Optional.empty();
        if (end) return;

        this.options = new ArrayList<>();
        JsonArray arr = json.get("options")
                .getAsJsonArray();
        for (int i = 0; i < arr.size(); i++)
            options.add(new Question(arr.get(i)
                    .getAsJsonObject()));
    }
}
