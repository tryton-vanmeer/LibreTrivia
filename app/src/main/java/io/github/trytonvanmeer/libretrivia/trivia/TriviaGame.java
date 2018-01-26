package io.github.trytonvanmeer.libretrivia.trivia;

import java.io.Serializable;
import java.util.List;

public class TriviaGame implements Serializable {
    private int currentQuestion;
    private boolean[] results;
    private List<TriviaQuestion> questions;

    public TriviaGame(List<TriviaQuestion> questions) {
        this.currentQuestion = 0;
        this.questions = questions;
        this.results = new boolean[questions.size()];
    }

    public TriviaQuestion getCurrentQuestion() {
        return this.questions.get(currentQuestion);
    }

    public int getQuestionProgress() {
        return this.currentQuestion + 1;
    }

    public int getQuestionsCount() {
        return this.questions.size();
    }

    public boolean[] getResults() {
        return this.results;
    }

    public boolean nextQuestion(String guess) {
        TriviaQuestion question = getCurrentQuestion();
        boolean answer = question.checkAnswer(guess);

        results[currentQuestion] = answer;
        currentQuestion++;

        return answer;
    }

    public boolean isDone() {
        return (this.currentQuestion == questions.size());
    }
}
