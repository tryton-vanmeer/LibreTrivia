package io.github.trytonvanmeer.libretrivia.OpenTrivia;

import android.content.Context;

import io.github.trytonvanmeer.libretrivia.R;

public enum TriviaDifficulty {
    EASY(R.string.difficulty_easy),
    MEDIUM(R.string.difficulty_medium),
    HARD(R.string.difficulty_hard);

    // The display name of the difficulty
    private int displayName;

    TriviaDifficulty(int displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName(Context context) {
        return context.getResources().getString(this.displayName);
    }
}
