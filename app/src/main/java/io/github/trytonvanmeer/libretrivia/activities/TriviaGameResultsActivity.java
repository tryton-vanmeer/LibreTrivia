package io.github.trytonvanmeer.libretrivia.activities;

import android.os.Bundle;

import butterknife.ButterKnife;
import io.github.trytonvanmeer.libretrivia.R;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaGame;

public class TriviaGameResultsActivity extends BaseActivity {
    protected static final String EXTRA_TRIVIA_GAME = "extra_trivia_game";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game_results);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        TriviaGame game = (TriviaGame) bundle.get(EXTRA_TRIVIA_GAME);
    }
}
