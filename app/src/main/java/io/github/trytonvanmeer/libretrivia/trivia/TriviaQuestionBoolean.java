package io.github.trytonvanmeer.libretrivia.trivia;

public class TriviaQuestionBoolean extends TriviaQuestion<Boolean> {

    public TriviaQuestionBoolean(TriviaCategory category, TriviaDifficulty difficulty,
                          String question, boolean correctAnswer) {
        super(category, difficulty, question, correctAnswer);
    }
}
