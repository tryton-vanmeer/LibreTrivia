package io.github.trytonvanmeer.libretrivia.trivia;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TriviaQuestionMultiple extends TriviaQuestion<String> {
    private String[] incorrectAnswers;

    public TriviaQuestionMultiple(TriviaCategory category, TriviaDifficulty difficulty,
                                  String question, String correctAnswer, String[] incorrectAnswers) {
        super(category, difficulty, question, correctAnswer);

        this.incorrectAnswers = incorrectAnswers;
    }

    public String[] getAnswerList() {
        return new String[]{correctAnswer,
                incorrectAnswers[0],
                incorrectAnswers[1],
                incorrectAnswers[2]};
    }

    public static TriviaQuestionMultiple fromJson(JsonObject json) {
        TriviaCategory category = TriviaCategory.get(json.get("category").getAsString());
        TriviaDifficulty difficulty = TriviaDifficulty.get(json.get("difficulty").getAsString());
        String question = json.get("question").getAsString();
        String correctAnswer = json.get("correct_answer").getAsString();

        JsonArray incorrectAnswersJson = json.get("incorrect_answers").getAsJsonArray();
        String[] incorrectAnswers = new String[]{
                incorrectAnswersJson.get(0).getAsString(),
                incorrectAnswersJson.get(1).getAsString(),
                incorrectAnswersJson.get(2).getAsString()
        };

        return new TriviaQuestionMultiple(
                category, difficulty, question, correctAnswer, incorrectAnswers);
    }
}
