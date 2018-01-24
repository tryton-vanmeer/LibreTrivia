package io.github.trytonvanmeer.libretrivia.trivia;

import java.util.HashMap;
import java.util.Map;

import io.github.trytonvanmeer.libretrivia.LibreTriviaApplication;
import io.github.trytonvanmeer.libretrivia.R;

public enum TriviaDifficulty {
    ANY("any", R.string.ui_any),

    EASY("easy", R.string.difficulty_easy),
    MEDIUM("medium", R.string.difficulty_medium),
    HARD("hard", R.string.difficulty_hard);

    // Name of difficulty used in queries
    private final String name;
    // Display name of the difficulty
    private final int displayName;

    private static final Map<String, TriviaDifficulty> lookup = new HashMap<>();

    static {
        for (TriviaDifficulty difficulty : TriviaDifficulty.values()) {
            lookup.put(difficulty.getName(), difficulty);
        }
    }

    TriviaDifficulty(String name, int displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public String getName() {
        return this.name;
    }

    public int getDisplayName() {
        return this.displayName;
    }

    public static TriviaDifficulty get(String name) {
        return lookup.get(name);
    }

    @Override
    public String toString() {
        return LibreTriviaApplication.getAppContext().getResources().getString(this.displayName);
    }
}
