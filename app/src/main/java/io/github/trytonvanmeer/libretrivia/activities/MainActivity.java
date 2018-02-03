package io.github.trytonvanmeer.libretrivia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.trytonvanmeer.libretrivia.R;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaCategory;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaDifficulty;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuery;

public class MainActivity extends BaseActivity {

    @BindView(R.id.button_play) Button buttonPlay;
    @BindView(R.id.spinner_number) Spinner spinnerNumber;
    @BindView(R.id.spinner_category) Spinner spinnerCategory;
    @BindView(R.id.spinner_difficulty) Spinner spinnerDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = (int) spinnerNumber.getSelectedItem();
                TriviaCategory category = (TriviaCategory) spinnerCategory.getSelectedItem();
                TriviaDifficulty difficulty = (TriviaDifficulty) spinnerDifficulty.getSelectedItem();

                TriviaQuery query = new TriviaQuery.Builder(amount)
                .category(category)
                .difficulty(difficulty)
                .build();

                Intent intent = new Intent(getApplicationContext(), TriviaGameActivity.class);
                intent.putExtra(TriviaGameActivity.EXTRA_TRIVIA_QUERY, query);
                startActivity(intent);
            }
        });


        Integer[] numbers = new Integer[50];
        for (int i = 0; i < 50;) {
            numbers[i] = ++i;
        }
        spinnerNumber.setAdapter(
                new ArrayAdapter<>(
                        this, android.R.layout.simple_list_item_1, numbers)
        );
        spinnerNumber.setSelection(9);

        spinnerCategory.setAdapter(
                new ArrayAdapter<>(
                        this, android.R.layout.simple_list_item_1, TriviaCategory.values()));

        spinnerDifficulty.setAdapter(
                new ArrayAdapter<>(
                        this, android.R.layout.simple_list_item_1, TriviaDifficulty.values()));
    }
}
