package io.github.trytonvanmeer.libretrivia.trivia;

import android.text.Html;

import com.google.gson.JsonObject;

public class TriviaQuestionBoolean extends TriviaQuestion<Boolean> {

    public TriviaQuestionBoolean(TriviaCategory category, TriviaDifficulty difficulty,
                          String question, boolean correctAnswer) {
        super(category, difficulty, question, correctAnswer);
    }

    public static TriviaQuestionBoolean fromJson(JsonObject json) {
        TriviaCategory category = TriviaCategory.get(json.get("category").getAsString());
        TriviaDifficulty difficulty = TriviaDifficulty.get(json.get("difficulty").getAsString());
        String question = Html.fromHtml(json.get("question").getAsString()).toString();
        Boolean correctAnswer = json.get("correct_answer").getAsBoolean();

        return new TriviaQuestionBoolean(category, difficulty, question, correctAnswer);
    }
}
