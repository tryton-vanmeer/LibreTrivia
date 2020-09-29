package io.github.trytonvanmeer.libretrivia.trivia;

import java.util.HashMap;
import java.util.Map;

import io.github.trytonvanmeer.libretrivia.LibreTriviaApplication;
import io.github.trytonvanmeer.libretrivia.R;

public enum TriviaType {
    ANY("any", R.string.ui_any),

    MULTIPLE("multiple", R.string.question_type_multiple),
    BOOLEAN("boolean", R.string.question_type_boolean);

    private static final Map<String, TriviaType> lookup = new HashMap<>();

    static {
        for (TriviaType type : TriviaType.values()) {
            lookup.put(type.getName(), type);
        }
    }

    // Name of type used in queries
    private final String name;
    // Display name of the type
    private final int displayName;

    TriviaType(String name, int displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public static TriviaType get(String name) {
        return lookup.get(name);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return LibreTriviaApplication.getAppContext().getResources().getString(this.displayName);
    }
}
