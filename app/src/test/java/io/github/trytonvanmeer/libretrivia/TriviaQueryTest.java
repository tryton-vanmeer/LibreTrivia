package io.github.trytonvanmeer.libretrivia;

import org.junit.jupiter.api.Test;

import io.github.trytonvanmeer.libretrivia.trivia.TriviaCategory;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaDifficulty;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuery;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriviaQueryTest {

    @Test
    public void triviaQuery_MatchQuery() {
        TriviaQuery query = new TriviaQuery.Builder().build();

        assertEquals("https://opentdb.com/api.php?amount=10", query.toString());
    }

    @Test
    public void triviaQuery_MatchQuery_WithParams() {
        TriviaQuery query = new TriviaQuery.Builder(20)
                .category(TriviaCategory.GENERAL_KNOWLEDGE)
                .difficulty(TriviaDifficulty.EASY)
                .type(TriviaType.MULTIPLE)
                .build();

        assertEquals(
                "https://opentdb.com/api.php?amount=20&category=9&difficulty=easy&type=multiple",
                query.toString());
    }

    @Test
    public void triviaQuery_AmountExceedFifty() {
        TriviaQuery query = new TriviaQuery.Builder(500).build();

        assertEquals("https://opentdb.com/api.php?amount=50", query.toString());
    }
}
