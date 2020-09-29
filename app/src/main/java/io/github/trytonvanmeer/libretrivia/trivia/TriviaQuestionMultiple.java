package io.github.trytonvanmeer.libretrivia.trivia;

import androidx.core.text.HtmlCompat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TriviaQuestionMultiple extends TriviaQuestion {
    private final String correctAnswer;
    private final String[] incorrectAnswers;

    public TriviaQuestionMultiple(TriviaCategory category, TriviaDifficulty difficulty,
                                  String question, String correctAnswer, String... incorrectAnswers) {
        super(category, difficulty, question);

        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public static TriviaQuestionMultiple fromJson(JsonObject json) {
        TriviaCategory category = TriviaCategory.get(json.get("category").getAsString());
        TriviaDifficulty difficulty = TriviaDifficulty.get(json.get("difficulty").getAsString());

        String question = HtmlCompat.fromHtml(
                json.get("question").getAsString(), HtmlCompat.FROM_HTML_MODE_LEGACY).toString();

        String correctAnswer = HtmlCompat.fromHtml(
                json.get("correct_answer").getAsString(), HtmlCompat.FROM_HTML_MODE_LEGACY).toString();

        JsonArray incorrectAnswersJson = json.get("incorrect_answers").getAsJsonArray();
        String[] incorrectAnswers = {
                HtmlCompat.fromHtml(incorrectAnswersJson.get(0).getAsString(), HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
                HtmlCompat.fromHtml(incorrectAnswersJson.get(1).getAsString(), HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
                HtmlCompat.fromHtml(incorrectAnswersJson.get(2).getAsString(), HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
        };

        return new TriviaQuestionMultiple(
                category, difficulty, question, correctAnswer, incorrectAnswers);
    }

    public String[] getAnswerList() {
        return new String[]{correctAnswer,
                incorrectAnswers[0],
                incorrectAnswers[1],
                incorrectAnswers[2]};
    }

    @Override
    public boolean checkAnswer(String guess) {
        return this.correctAnswer.equals(guess);
    }
}
