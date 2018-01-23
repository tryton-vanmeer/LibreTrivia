package io.github.trytonvanmeer.libretrivia;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import io.github.trytonvanmeer.libretrivia.OpenTrivia.TriviaCategory;
import io.github.trytonvanmeer.libretrivia.OpenTrivia.TriviaDifficulty;
import io.github.trytonvanmeer.libretrivia.OpenTrivia.TriviaQuery;
import io.github.trytonvanmeer.libretrivia.OpenTrivia.TriviaType;

public class TriviaQueryTest {

    @Test
    public void triviaQuery_MatchQuery() {
        TriviaQuery query = new TriviaQuery.Builder().build();

        assertTrue(query.toString().equals("https://opentdb.com/api.php?amount=10"));
    }

    @Test
    public void triviaQuery_MatchQuery_WithParams() {
        TriviaQuery query = new TriviaQuery.Builder()
                .category(TriviaCategory.GENERAL_KNOWLEDGE)
                .difficulty(TriviaDifficulty.EASY)
                .type(TriviaType.MULTIPLE)
                .build();

        assertTrue(query.toString().equals("https://opentdb.com/api.php?amount=10&category=9&difficulty=easy&type=multiple"));
    }
}
