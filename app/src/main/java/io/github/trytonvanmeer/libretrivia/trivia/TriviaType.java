package io.github.trytonvanmeer.libretrivia.trivia;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import io.github.trytonvanmeer.libretrivia.R;

public enum TriviaType {
    MULTIPLE("multiple", R.string.question_type_multiple),
    BOOLEAN("boolean", R.string.question_type_boolean);

    // Name of type used in queries
    private final String name;
    // Display name of the type
    private final int displayName;

    private static final Map<String, TriviaType> lookup = new HashMap<>();

    static {
        for (TriviaType type : TriviaType.values()) {
            lookup.put(type.getName(), type);
        }
    }

    TriviaType(String name, int displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public String getName() {
        return this.name;
    }

    public String getDisplayName(Context context) {
        return context.getResources().getString(this.displayName);
    }

    public static TriviaType get(String name) {
        return lookup.get(name);
    }
}
