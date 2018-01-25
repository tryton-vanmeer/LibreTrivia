package io.github.trytonvanmeer.libretrivia.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.shawnlin.numberpicker.NumberPicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.trytonvanmeer.libretrivia.R;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaCategory;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaDifficulty;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuery;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.button_play) Button buttonPlay;
    @BindView(R.id.spinner_category) Spinner spinnerCategory;
    @BindView(R.id.spinner_difficulty) Spinner spinnerDifficulty;
    @BindView(R.id.number_picker) NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = numberPicker.getValue();
                TriviaCategory category = (TriviaCategory) spinnerCategory.getSelectedItem();
                TriviaDifficulty difficulty = (TriviaDifficulty) spinnerDifficulty.getSelectedItem();

                TriviaQuery query = new TriviaQuery.Builder(amount)
                        .category(category)
                        .difficulty(difficulty)
                        .build();
            }
        });

        spinnerCategory.setAdapter(
                new ArrayAdapter<>(
                        this, android.R.layout.simple_list_item_1, TriviaCategory.values()));

        spinnerDifficulty.setAdapter(
                new ArrayAdapter<>(
                        this, android.R.layout.simple_list_item_1, TriviaDifficulty.values()));
    }
}
