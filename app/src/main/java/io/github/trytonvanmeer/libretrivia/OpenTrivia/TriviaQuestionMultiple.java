package io.github.trytonvanmeer.libretrivia.OpenTrivia;

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
}
