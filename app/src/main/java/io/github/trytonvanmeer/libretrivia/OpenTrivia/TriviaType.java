package io.github.trytonvanmeer.libretrivia.OpenTrivia;

import android.content.Context;

import io.github.trytonvanmeer.libretrivia.R;

public enum TriviaType {
    MULTIPLE(R.string.question_type_multiple),
    BOOLEAN(R.string.question_type_boolean);

    // The display name of the category
    private int displayName;

    TriviaType(int displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName(Context context) {
        return context.getResources().getString(this.displayName);
    }

    @Override
    public String toString() {
        switch (this) {
            case MULTIPLE:
                return "multiple";
            case BOOLEAN:
                return "boolean";
            default:
                return "";
        }
    }
}
