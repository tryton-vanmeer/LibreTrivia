package io.github.trytonvanmeer.libretrivia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.github.trytonvanmeer.libretrivia.R;
import io.github.trytonvanmeer.libretrivia.activities.TriviaGameActivity;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuestion;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuestionMultiple;


public class TriviaQuestionFragment extends Fragment {

    private static final int buttonAnswerOneID = R.id.button_answer_one;
    private static final int buttonAnswerTwoID = R.id.button_answer_two;
    private static final int buttonAnswerThreeID = R.id.button_answer_three;
    private static final int buttonAnswerFourID = R.id.button_answer_four;

    @BindViews({
            buttonAnswerOneID,
            buttonAnswerTwoID,
            buttonAnswerThreeID,
            buttonAnswerFourID
    })
    Button[] buttonAnswers;

    Button buttonAnswerCorrect;

    Button buttonAnswerTrue;
    Button buttonAnswerFalse;

    public TriviaQuestionFragment() {
    }

    public static TriviaQuestionFragment newInstance() {
        return new TriviaQuestionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TriviaQuestion question = ((TriviaGameActivity) getActivity()).getCurrentQuestion();
        View view;

        if (question instanceof TriviaQuestionMultiple) {
            view = inflater.inflate(R.layout.fragment_trivia_question_multiple, container, false);
            ButterKnife.bind(this, view);
        } else {
            view = inflater.inflate(R.layout.fragment_trivia_question_boolean, container, false);
            this.buttonAnswerTrue = view.findViewById(R.id.button_answer_true);
            this.buttonAnswerFalse = view.findViewById(R.id.button_answer_false);
        }

        TextView textViewQuestion = view.findViewById(R.id.text_trivia_question);
        textViewQuestion.setText(question.getQuestion());
        setupButtons();

        return view;
    }

    private void setupButtons() {
        AnswerButtonListener listener = new AnswerButtonListener();
        TriviaQuestion question = ((TriviaGameActivity) getActivity()).getCurrentQuestion();

        if (question instanceof TriviaQuestionMultiple) {
            List<String> answers = Arrays.asList((
                    (TriviaQuestionMultiple) question).getAnswerList());
            Collections.shuffle(answers);

            for (int i = 0; i < buttonAnswers.length; i++) {
                buttonAnswers[i].setText(answers.get(i));
                buttonAnswers[i].setOnClickListener(listener);
                if (question.checkAnswer(answers.get(i))) {
                    buttonAnswerCorrect = buttonAnswers[i];
                }
            }
        } else {
            buttonAnswerTrue.setOnClickListener(listener);
            buttonAnswerFalse.setOnClickListener(listener);
        }
    }

    private void disableButtons() {
        TriviaQuestion question = ((TriviaGameActivity) getActivity()).getCurrentQuestion();
        if (question instanceof TriviaQuestionMultiple) {
            buttonAnswers[0].setEnabled(false);
            buttonAnswers[1].setEnabled(false);
            buttonAnswers[2].setEnabled(false);
            buttonAnswers[3].setEnabled(false);
        } else {
            buttonAnswerTrue.setEnabled(false);
            buttonAnswerFalse.setEnabled(false);
        }
    }

    private class AnswerButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            disableButtons();
            ((TriviaGameActivity) getActivity()).onAnswerClick((Button) v, buttonAnswerCorrect);
        }
    }
}
