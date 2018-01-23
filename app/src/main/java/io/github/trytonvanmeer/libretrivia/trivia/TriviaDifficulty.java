package io.github.trytonvanmeer.libretrivia.trivia;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import io.github.trytonvanmeer.libretrivia.R;

public enum TriviaDifficulty {
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

    public String getDisplayName(Context context) {
        return context.getResources().getString(this.displayName);
    }

    public static TriviaDifficulty get(String name) {
        return lookup.get(name);
    }
}
