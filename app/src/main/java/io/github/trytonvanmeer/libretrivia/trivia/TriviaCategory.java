package io.github.trytonvanmeer.libretrivia.trivia;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import io.github.trytonvanmeer.libretrivia.R;

/*
    Categories that a Trivia Question can fall into
 */
public enum TriviaCategory {
    GENERAL_KNOWLEDGE(9, R.string.category_general_knowledge),
    ENTERTAINMENT_BOOKS(10, R.string.category_entertainment_books),
    ENTERTAINMENT_FILM(11, R.string.category_entertainment_film),
    ENTERTAINMENT_MUSIC(12, R.string.category_entertainment_music),
    ENTERTAINMENT_MUSICALS_THEATRES(13, R.string.category_entertainment_musicals_theatres),
    ENTERTAINMENT_TELEVISION(14, R.string.category_entertainment_television),
    ENTERTAINMENT_VIDEO_GAMES(15, R.string.category_entertainment_video_games),
    ENTERTAINMENT_BOARD_GAMES(16, R.string.category_entertainment_board_games),
    ENTERTAINMENT_JAPANESE_ANIME_MANGA(31, R.string.category_entertainment_japanese_anime_manga),
    ENTERTAINMENT_CARTOON_ANIMATIONS(32, R.string.category_entertainment_cartoon_animations),
    ENTERTAINMENT_COMICS(29, R.string.category_entertainment_comics),
    SCIENCE_NATURE(17, R.string.category_science_nature),
    SCIENCE_COMPUTERS(18, R.string.category_science_computers),
    SCIENCE_MATHEMATICS(19, R.string.category_science_mathematics),
    SCIENCE_GADGETS(30, R.string.category_science_gadgets),
    MYTHOLOGY(20, R.string.category_mythology),
    SPORTS(21, R.string.category_sports),
    GEOGRAPHY(22, R.string.category_geography),
    HISTORY(23, R.string.category_history),
    POLITICS(24, R.string.category_politics),
    ART(25, R.string.category_art),
    CELEBRITIES(26, R.string.category_celebrities),
    ANIMALS(27, R.string.category_animals),
    VEHICLES(28, R.string.category_vehicles);

    // The id of the category in the opentdb api
    // see <https://opentdb.com/api_category.php>
    private final int ID;
    // The display name of the category
    private final int displayName;

    private static final Map<Integer, TriviaCategory> lookup = new HashMap<>();

    static {
        for (TriviaCategory category : TriviaCategory.values()) {
            lookup.put(category.getID(), category);
        }
    }

    TriviaCategory(int ID, int name) {
        this.ID = ID;
        this.displayName = name;
    }

    public int getID() {
        return this.ID;
    }

    public String getDisplayName(Context context) {
        return context.getResources().getString(this.displayName);
    }

    public static TriviaCategory get(int id) {
        return lookup.get(id);
    }
}
