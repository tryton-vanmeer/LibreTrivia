package io.github.trytonvanmeer.libretrivia.trivia;

import java.util.List;

public class TriviaGame {
    private static final String TAG = "TriviaGame";

    private int currentQuestion;
    private boolean[] questionsAnsweredStatus;
    private List<TriviaQuestion> questions;

    public TriviaGame(List<TriviaQuestion> questions) {
        this.currentQuestion = 1;
        this.questions = questions;
        this.questionsAnsweredStatus = new boolean[questions.size()];
    }

    public TriviaQuestion getCurrentQuestion() {
        return this.questions.get(currentQuestion - 1);
    }

    public int getCurrentQuestionCount() {
        return this.currentQuestion;
    }

    public int getQuestionsCount() {
        return this.questions.size();
    }
}
