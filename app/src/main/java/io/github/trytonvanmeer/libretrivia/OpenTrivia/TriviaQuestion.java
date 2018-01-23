package io.github.trytonvanmeer.libretrivia.OpenTrivia;

public abstract class TriviaQuestion<T> {
    private TriviaCategory category;
    private TriviaDifficulty difficulty;

    protected T correctAnswer;

    protected TriviaQuestion(TriviaCategory category, TriviaDifficulty difficulty, T correctAnswer) {
        this.category = category;
        this.difficulty = difficulty;

        this.correctAnswer = correctAnswer;
    }

    public TriviaCategory getCategory() {
        return this.category;
    }

    public TriviaDifficulty getDifficulty() {
        return this.difficulty;
    }

    public boolean checkAnswer(T guess) {
        return this.correctAnswer.equals(guess);
    }
}
