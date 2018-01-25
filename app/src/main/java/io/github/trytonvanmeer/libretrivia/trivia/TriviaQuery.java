package io.github.trytonvanmeer.libretrivia.trivia;

import java.io.Serializable;

public class TriviaQuery implements Serializable {
    private static final String BASE = "https://opentdb.com/api.php?";
    private static final int DEFAULT_AMOUNT = 10;

    private final int amount;
    private final TriviaCategory category;
    private final TriviaDifficulty difficulty;
    private final TriviaType type;

    private TriviaQuery(Builder builder) {
        this.amount = builder.amount;
        this.category = builder.category;
        this.difficulty = builder.difficulty;
        this.type = builder.type;
    }

    public static class Builder {
        private final int amount;
        private TriviaCategory category;
        private TriviaDifficulty difficulty;
        private TriviaType type;

        public Builder() {
            this.amount = DEFAULT_AMOUNT;
        }

        public Builder(int amount) {
            if (amount > 50) {
                this.amount = 50;
            } else {
                this.amount = amount;
            }
        }

        public Builder category(TriviaCategory category) {
            this.category = category;
            return this;
        }

        public Builder difficulty(TriviaDifficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public Builder type(TriviaType type) {
            this.type = type;
            return this;
        }

        public TriviaQuery build() {
            return new TriviaQuery(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder url = new StringBuilder();

        url.append(BASE);
        url.append("amount=").append(this.amount);

        if (this.category != null & this.category != TriviaCategory.ANY) {
            url.append("&category=").append(this.category.getID());
        }
        if (this.difficulty != null & this.difficulty != TriviaDifficulty.ANY) {
            url.append("&difficulty=").append(this.difficulty.getName());
        }
        if (this.type != null & this.type != TriviaType.ANY) {
            url.append("&type=").append(this.type.getName());
        }

        return url.toString();
    }
}
