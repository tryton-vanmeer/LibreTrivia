package io.github.trytonvanmeer.libretrivia;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.github.trytonvanmeer.libretrivia.trivia.TriviaCategory;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaDifficulty;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuestion;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuestionMultiple;

public class TriviaQuestionTest {

    private static TriviaQuestion<String> question = new TriviaQuestionMultiple(
            TriviaCategory.GENERAL_KNOWLEDGE,
            TriviaDifficulty.EASY,
            "What is my name?",
            "Bob",
            new String[]{"Joe", "Tom", "James"}
    );

    @Test
    public void triviaQuestion_CorrectAnswer_ReturnsTrue() {
        assertTrue(question.checkAnswer("Bob"));
    }

    @Test
    public void triviaQuestion_WrongAnswer_ReturnsFalse() {
        assertFalse(question.checkAnswer("Tom"));
    }
}
