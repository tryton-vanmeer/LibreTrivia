package io.github.trytonvanmeer.libretrivia.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.trytonvanmeer.libretrivia.R;
import io.github.trytonvanmeer.libretrivia.Util;
import io.github.trytonvanmeer.libretrivia.exceptions.NoTriviaResultsException;
import io.github.trytonvanmeer.libretrivia.fragments.TriviaGameErrorFragment;
import io.github.trytonvanmeer.libretrivia.fragments.TriviaQuestionFragment;
import io.github.trytonvanmeer.libretrivia.interfaces.IDownloadTriviaQuestionReceiver;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaGame;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuery;

public class TriviaGameActivity extends BaseActivity implements IDownloadTriviaQuestionReceiver {
    protected static final String EXTRA_TRIVIA_QUERY = "extra_trivia_query";

    private TriviaGame game;

    @BindView(R.id.trivia_status_bar) LinearLayout triviaStatusBar;
    @BindView(R.id.text_question_category) TextView textViewQuestionCategory;
    @BindView(R.id.text_question_difficulty) TextView textViewQuestionDifficulty;
    @BindView(R.id.progress_questions) DonutProgress progressBar;
    @BindView(R.id.button_next_question) Button buttonNextQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        TriviaQuery query = (TriviaQuery) bundle.get(EXTRA_TRIVIA_QUERY);

        DownloadTriviaQuestionsTask task = new DownloadTriviaQuestionsTask();
        task.setReceiver(this);
        task.execute(query);
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

    public boolean onTriviaQuestionsDownloaded(String json) {
        if (json == null) {
            onNetworkError();
            return false;
        } else {
            try {
                this.game = new TriviaGame(Util.jsonToQuestionArray(json));
            } catch (NoTriviaResultsException e) {
                onNoTriviaResults();
                return false;
            }
        }

        // Setup game layout
        progressBar.setMax(game.getQuestionsCount());
        triviaStatusBar.setVisibility(View.VISIBLE);
        buttonNextQuestion.setOnClickListener(new NextButtonListener());
        buttonNextQuestion.setVisibility(View.VISIBLE);
        updateStatusBar();
        updateTriviaQuestion();
        return true;
    }

    private void updateStatusBar() {
        progressBar.setProgress(game.getQuestionProgress());
        progressBar.setText(Integer.toString(game.getQuestionProgress()));
        textViewQuestionCategory.setText(
                game.getCurrentQuestion().getCategory().toString());
        textViewQuestionDifficulty.setText(
                game.getCurrentQuestion().getDifficulty().toString());
    }

    public void updateTriviaQuestion() {
        Fragment fragment = TriviaQuestionFragment.newInstance(game.getCurrentQuestion());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_trivia_game, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
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

    private class NextButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            FragmentManager manager = getSupportFragmentManager();
            TriviaQuestionFragment fragment =
                    (TriviaQuestionFragment) manager.findFragmentById(R.id.frame_trivia_game);

            String selectedAnswer = fragment.getSelectedAnswer();
            boolean guess = game.nextQuestion(selectedAnswer);

            int msg = guess ? R.string.ui_correct : R.string.ui_wrong;
            Toast.makeText(TriviaGameActivity.this, msg, Toast.LENGTH_SHORT).show();

            manager.beginTransaction()
                    .remove(fragment)
                    .commit();

            if (game.isDone()) {
                // TODO: Display results screen if reached end of questions
                finish();
            } else {
                updateStatusBar();
                updateTriviaQuestion();
            }
        }
    }

    private static class DownloadTriviaQuestionsTask extends AsyncTask<TriviaQuery, Integer, String> {
        private IDownloadTriviaQuestionReceiver receiver;

        @Override
        protected String doInBackground(TriviaQuery... query) {
            String json;
            try {
                json = Util.GET(query[0]);
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
