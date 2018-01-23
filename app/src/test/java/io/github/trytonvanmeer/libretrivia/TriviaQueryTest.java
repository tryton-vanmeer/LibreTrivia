package io.github.trytonvanmeer.libretrivia;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import io.github.trytonvanmeer.libretrivia.trivia.TriviaCategory;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaDifficulty;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuery;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaType;

public class TriviaQueryTest {

    @Test
    public void triviaQuery_MatchQuery() {
        TriviaQuery query = new TriviaQuery.Builder().build();

        assertTrue(query.toString().equals("https://opentdb.com/api.php?amount=10"));
    }

    @Test
    public void triviaQuery_MatchQuery_WithParams() {
        TriviaQuery query = new TriviaQuery.Builder(20)
                .category(TriviaCategory.GENERAL_KNOWLEDGE)
                .difficulty(TriviaDifficulty.EASY)
                .type(TriviaType.MULTIPLE)
                .build();

        assertTrue(query.toString().equals("https://opentdb.com/api.php?amount=20&category=9&difficulty=easy&type=multiple"));
    }
}
