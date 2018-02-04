package io.github.trytonvanmeer.libretrivia.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.trytonvanmeer.libretrivia.R;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaGame;

public class TriviaGameResultsActivity extends BaseActivity {
    static final String EXTRA_TRIVIA_GAME = "extra_trivia_game";

    @BindView(R.id.text_results_correct) TextView textResultsCorrect;
    @BindView(R.id.text_results_wrong) TextView textResultsWrong;
    @BindView(R.id.text_results_total) TextView textResultsTotal;
    @BindView(R.id.button_return_to_menu) Button buttonReturnToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game_results);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        TriviaGame game = (TriviaGame) bundle.get(EXTRA_TRIVIA_GAME);

        int correctTotal = 0;

        for (boolean result : game.getResults()) {
            if (result) {
                correctTotal++;
            }
        }

        textResultsCorrect.setText(String.valueOf(correctTotal));
        textResultsWrong.setText(String.valueOf(game.getQuestionsCount() - correctTotal));
        textResultsTotal.setText(String.valueOf(game.getQuestionsCount()));

        buttonReturnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
