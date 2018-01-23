package io.github.trytonvanmeer.libretrivia;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuery;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuestion;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaType;

public class TriviaQuestionNetworkTest {

    @Test
    public void triviaQuestion_JsonToQuestionArray() throws IOException{
        TriviaQuery query = new TriviaQuery.Builder(25)
                .build();

        List<TriviaQuestion> questions = Util.jsonToQuestionArray(Util.GET(query));

        assertEquals(questions.size(), 25);
    }

    @Test
    public void triviaQuestionMultiple_JsonToQuestionArray() throws IOException{
        TriviaQuery query = new TriviaQuery.Builder(25)
                .type(TriviaType.MULTIPLE)
                .build();

        List<TriviaQuestion> questions = Util.jsonToQuestionArray(Util.GET(query));

        assertEquals(questions.size(), 25);
    }
}
