package io.github.trytonvanmeer.libretrivia;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Test;

import io.github.trytonvanmeer.libretrivia.trivia.TriviaCategory;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaDifficulty;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuestionBoolean;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuestionMultiple;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TriviaQuestionTest {

    @Test
    public void triviaQuestionMultiple_CorrectAnswer() {
        TriviaQuestionMultiple question = new TriviaQuestionMultiple(
                TriviaCategory.GENERAL_KNOWLEDGE,
                TriviaDifficulty.EASY,
                "What is my name?",
                "Bob",
                new String[]{"Joe", "Tom", "James"}
        );

        assertTrue(question.checkAnswer("Bob"));
    }

    @Test
    public void triviaQuestionMultiple_WrongAnswer() {
        TriviaQuestionMultiple question = new TriviaQuestionMultiple(
                TriviaCategory.GENERAL_KNOWLEDGE,
                TriviaDifficulty.EASY,
                "What is my name?",
                "Bob",
                new String[]{"Joe", "Tom", "James"}
        );

        assertFalse(question.checkAnswer("Tom"));
    }

    @Test
    public void triviaQuestionMultiple_FromJson() {
        JsonObject json = new JsonParser().parse(
                "{\n" +
                        "      \"category\": \"General Knowledge\",\n" +
                        "      \"type\": \"multiple\",\n" +
                        "      \"difficulty\": \"easy\",\n" +
                        "      \"question\": \"Which company did Valve cooperate with in the creation of the Vive?\",\n" +
                        "      \"correct_answer\": \"HTC\",\n" +
                        "      \"incorrect_answers\": [\n" +
                        "        \"Oculus\",\n" +
                        "        \"Google\",\n" +
                        "        \"Razer\"\n" +
                        "      ]\n" +
                        "    }"
        ).getAsJsonObject();

        TriviaQuestionMultiple question = TriviaQuestionMultiple.fromJson(json);
        assertTrue(question.checkAnswer("HTC"));
    }

    @Test
    public void triviaQuestionBoolean_CorrectAnswer() {
        TriviaQuestionBoolean question = new TriviaQuestionBoolean(
                TriviaCategory.ANIMALS,
                TriviaDifficulty.EASY,
                "Are cats animals?",
                true
        );

        assertTrue(question.checkAnswer(true));
    }

    @Test
    public void triviaQuestionBoolean_WrongAnswer() {
        TriviaQuestionBoolean question = new TriviaQuestionBoolean(
                TriviaCategory.ANIMALS,
                TriviaDifficulty.EASY,
                "Are cats animals?",
                true
        );

        assertFalse(question.checkAnswer(false));
    }

    @Test
    public void triviaQuestionBoolean_FromJson() {
        JsonObject json = new JsonParser().parse(
                "{\n" +
                        "      \"category\": \"Entertainment: Video Games\",\n" +
                        "      \"type\": \"boolean\",\n" +
                        "      \"difficulty\": \"medium\",\n" +
                        "      \"question\": \"In Portal, the Companion Cube&#039;s ARE sentient.\",\n" +
                        "      \"correct_answer\": \"True\",\n" +
                        "      \"incorrect_answers\": [\n" +
                        "        \"False\"\n" +
                        "      ]\n" +
                        "    }"
        ).getAsJsonObject();

        TriviaQuestionBoolean question = TriviaQuestionBoolean.fromJson(json);
        assertTrue(question.checkAnswer(true));
    }
}
