package io.github.trytonvanmeer.libretrivia.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.trytonvanmeer.libretrivia.R;
import io.github.trytonvanmeer.libretrivia.exceptions.NoTriviaResultsException;
import io.github.trytonvanmeer.libretrivia.fragments.TriviaGameErrorFragment;
import io.github.trytonvanmeer.libretrivia.fragments.TriviaQuestionFragment;
import io.github.trytonvanmeer.libretrivia.interfaces.IDownloadTriviaQuestionReceiver;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaGame;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuery;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuestion;
import io.github.trytonvanmeer.libretrivia.util.ApiUtil;
import io.github.trytonvanmeer.libretrivia.util.SoundUtil;

public class TriviaGameActivity extends BaseActivity
        implements IDownloadTriviaQuestionReceiver {
    static final String EXTRA_TRIVIA_QUERY = "extra_trivia_query";
    private final String STATE_TRIVIA_GAME = "state_trivia_game";

    private TriviaGame game;

    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.trivia_status_bar) LinearLayout triviaStatusBar;
    @BindView(R.id.text_question_category) TextView textViewQuestionCategory;
    @BindView(R.id.text_question_difficulty) TextView textViewQuestionDifficulty;
    @BindView(R.id.text_question_progress) TextView textViewQuestionProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            this.game = (TriviaGame) savedInstanceState.getSerializable(STATE_TRIVIA_GAME);
        } else {
            Bundle bundle = getIntent().getExtras();
            assert bundle != null;
            TriviaQuery query = (TriviaQuery) bundle.get(EXTRA_TRIVIA_QUERY);

            progressBar.setVisibility(View.VISIBLE);

            DownloadTriviaQuestionsTask task = new DownloadTriviaQuestionsTask();
            task.setReceiver(this);
            task.execute(query);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(STATE_TRIVIA_GAME, this.game);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_trivia_game);

        if (fragment instanceof TriviaGameErrorFragment)  {
            super.onBackPressed();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.ui_quit_game)
                    .setMessage(R.string.ui_quit_game_msg)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            TriviaGameActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    })
                    .show();
        }
    }

    public void onTriviaQuestionsDownloaded(String json) {
        if (json == null) {
            onNetworkError();
            return;
        } else {
            try {
                this.game = new TriviaGame(ApiUtil.jsonToQuestionArray(json));
            } catch (NoTriviaResultsException e) {
                onNoTriviaResults();
                return;
            }
        }

        // Setup game layout
        progressBar.setVisibility(View.GONE);
        triviaStatusBar.setVisibility(View.VISIBLE);
        updateStatusBar();
        updateTriviaQuestion();
    }

    private void updateStatusBar() {
        String progress = getResources().getString(R.string.ui_question_progress,
                game.getQuestionProgress(), game.getQuestionsCount());

        String category = (game.getCurrentQuestion().getCategory() != null)
                        ? game.getCurrentQuestion().getCategory().toString() : "";

        String difficulty = game.getCurrentQuestion().getDifficulty().toString();

        textViewQuestionProgress.setText(progress);
        textViewQuestionCategory.setText(category);
        textViewQuestionDifficulty.setText(difficulty);
    }

    private void updateTriviaQuestion() {
        Fragment fragment = TriviaQuestionFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
        .replace(R.id.frame_trivia_game, fragment)
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .commit();
    }
    
    private void onNetworkError() {
        String msg = getResources().getString(R.string.error_network);
        Fragment errorFragment = TriviaGameErrorFragment.newInstance(msg);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_trivia_game, errorFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private void onNoTriviaResults() {
        String msg = getResources().getString(R.string.error_no_trivia_results);
        Fragment errorFragment = TriviaGameErrorFragment.newInstance(msg);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_trivia_game, errorFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    public TriviaQuestion getCurrentQuestion() {
        return this.game.getCurrentQuestion();
    }

    public void onAnswerClick(Button answer, Button correctAnswer) {
        boolean guess = game.nextQuestion(answer.getText().toString());

        final int green = getResources().getColor(R.color.colorAccentGreen);
        int color = guess ? green
                          : getResources().getColor(R.color.colorAccentRed);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ColorStateList stateList = ColorStateList.valueOf(color);
            answer.setBackgroundTintList(stateList);

            if(!guess) {
                final ColorStateList greenStateList = ColorStateList.valueOf(green);
                correctAnswer.setBackgroundTintList(greenStateList);
            }
        } else {
            answer.getBackground().getCurrent().setColorFilter(
                    new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));

            if(!guess)
                correctAnswer.getBackground().getCurrent().setColorFilter(
                        new PorterDuffColorFilter(green, PorterDuff.Mode.MULTIPLY));
        }

        SoundUtil.playSound(this, guess ?
                SoundUtil.SOUND_ANSWER_CORRECT : SoundUtil.SOUND_ANSWER_WRONG);

        new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (game.isDone()) {
                        Intent intent = new Intent(getApplicationContext(), TriviaGameResultsActivity.class);
                        intent.putExtra(TriviaGameResultsActivity.EXTRA_TRIVIA_GAME, game);
                        startActivity(intent);
                        finish();
                    } else {
                        updateStatusBar();
                        updateTriviaQuestion();
                    }
                }
            }, 500);
    }

    private static class DownloadTriviaQuestionsTask extends AsyncTask<TriviaQuery, Integer, String> {
        private IDownloadTriviaQuestionReceiver receiver;

        @Override
        protected String doInBackground(TriviaQuery... query) {
            String json;
            try {
                json = ApiUtil.GET(query[0]);
            } catch (IOException e) {
                return null;
            }
            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            receiver.onTriviaQuestionsDownloaded(json);
        }

        private void setReceiver(IDownloadTriviaQuestionReceiver receiver) {
            this.receiver = receiver;
        }
    }
}
