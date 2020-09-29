package io.github.trytonvanmeer.libretrivia.trivia;

import java.util.HashMap;
import java.util.Map;

import io.github.trytonvanmeer.libretrivia.LibreTriviaApplication;
import io.github.trytonvanmeer.libretrivia.R;

/*
    Categories that a Trivia Question can fall into
 */
public enum TriviaCategory {
    ANY(-1, "Any", R.string.ui_any),

    GENERAL_KNOWLEDGE(9, "General Knowledge",
            R.string.category_general_knowledge),
    ENTERTAINMENT_BOOKS(10, "Entertainment: Books",
            R.string.category_entertainment_books),
    ENTERTAINMENT_FILM(11, "Entertainment: Film",
            R.string.category_entertainment_film),
    ENTERTAINMENT_MUSIC(12, "Entertainment: Music",
            R.string.category_entertainment_music),
    ENTERTAINMENT_MUSICALS_THEATRES(13, "Entertainment: Musicals & Theatres",
            R.string.category_entertainment_musicals_theatres),
    ENTERTAINMENT_TELEVISION(14, "Entertainment: Television",
            R.string.category_entertainment_television),
    ENTERTAINMENT_VIDEO_GAMES(15, "Entertainment: Video Games",
            R.string.category_entertainment_video_games),
    ENTERTAINMENT_BOARD_GAMES(16, "Entertainment: Board Games",
            R.string.category_entertainment_board_games),
    ENTERTAINMENT_JAPANESE_ANIME_MANGA(31, "Entertainment: Japanese Anime & Manga",
            R.string.category_entertainment_japanese_anime_manga),
    ENTERTAINMENT_CARTOON_ANIMATIONS(32, "Entertainment: Cartoons & Animation",
            R.string.category_entertainment_cartoon_animations),
    ENTERTAINMENT_COMICS(29, "Entertainment: Comics",
            R.string.category_entertainment_comics),
    SCIENCE_NATURE(17, "Science & Nature",
            R.string.category_science_nature),
    SCIENCE_COMPUTERS(18, "Science: Computers",
            R.string.category_science_computers),
    SCIENCE_MATHEMATICS(19, "Science: Mathematics",
            R.string.category_science_mathematics),
    SCIENCE_GADGETS(30, "Science: Gadgets",
            R.string.category_science_gadgets),
    MYTHOLOGY(20, "Mythology",
            R.string.category_mythology),
    SPORTS(21, "Sports",
            R.string.category_sports),
    GEOGRAPHY(22, "Geography",
            R.string.category_geography),
    HISTORY(23, "History",
            R.string.category_history),
    POLITICS(24, "Politics",
            R.string.category_politics),
    ART(25, "Art",
            R.string.category_art),
    CELEBRITIES(26, "Celebrities",
            R.string.category_celebrities),
    ANIMALS(27, "Animals",
            R.string.category_animals),
    VEHICLES(28, "Vehicles",
            R.string.category_vehicles);

    private static final Map<String, TriviaCategory> lookup = new HashMap<>();

    static {
        for (TriviaCategory category : TriviaCategory.values()) {
            lookup.put(category.getName(), category);
        }
    }

    // The id of the category in the opentdb api
    // see <https://opentdb.com/api_category.php>
    private final int ID;
    // The name of the category in the JSON response
    private final String name;
    // The display name of the category
    private final int displayName;

    TriviaCategory(int ID, String name, int displayName) {
        this.ID = ID;
        this.name = name;
        this.displayName = displayName;
    }

    public static TriviaCategory get(String name) {
        return lookup.get(name);
    }

    public int getID() {
        return this.ID;
    }

    private String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return LibreTriviaApplication.getAppContext().getResources().getString(this.displayName);
    }
}
