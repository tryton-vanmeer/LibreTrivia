package io.github.trytonvanmeer.libretrivia.activities;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.trytonvanmeer.libretrivia.R;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaGame;

public class TriviaGameResultsActivity extends BaseActivity {
    protected static final String EXTRA_TRIVIA_GAME = "extra_trivia_game";

    @BindView(R.id.text_results_msg) TextView textViewResultsMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game_results);
        ButterKnife.bind(this);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TriviaGame game;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            game = (TriviaGame) bundle.get(EXTRA_TRIVIA_GAME);

            if (game != null) {
                int correctTotal = 0;

                for (boolean result : game.getResults()) {
                    if (result) {
                        correctTotal++;
                    }
                }

                String msg = String.format(Locale.US,"<sup>%d</sup>&frasl;<sub>%d</sub>",
                        correctTotal, game.getQuestionsCount());
                textViewResultsMsg.setText(Html.fromHtml(msg));
            }
        }
    }
}
