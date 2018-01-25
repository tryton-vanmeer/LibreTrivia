package io.github.trytonvanmeer.libretrivia.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.trytonvanmeer.libretrivia.R;
import io.github.trytonvanmeer.libretrivia.Util;
import io.github.trytonvanmeer.libretrivia.exceptions.NoTriviaResultsException;
import io.github.trytonvanmeer.libretrivia.fragments.TriviaGameErrorFragment;
import io.github.trytonvanmeer.libretrivia.interfaces.IDownloadTriviaQuestionReceiver;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaGame;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuery;

public class TriviaGameActivity extends BaseActivity implements IDownloadTriviaQuestionReceiver {
    protected static final String EXTRA_TRIVIA_QUERY = "extra_trivia_query";

    private TriviaGame game;

    @BindView(R.id.trivia_status_bar) LinearLayout triviaStatusBar;
    @BindView(R.id.text_question_category) TextView textViewQuestionCategory;
    @BindView(R.id.progress_questions) DonutProgress progressBar;
    @BindView(R.id.frame_trivia_game) FrameLayout frameLayout;
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
        super.onBackPressed();
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
        buttonNextQuestion.setVisibility(View.VISIBLE);
        updateStatusBar();
        return true;
    }

    private void updateStatusBar() {
        progressBar.setProgress(game.getCurrentQuestionCount());
        progressBar.setText(Integer.toString(game.getCurrentQuestionCount()));

        textViewQuestionCategory.setText(
                game.getCurrentQuestion().getCategory().toString());
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
