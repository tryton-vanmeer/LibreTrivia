package io.github.trytonvanmeer.libretrivia.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.trytonvanmeer.libretrivia.R;

public class TriviaQuestionResultFragment extends Fragment {
    private static final String ARG_RESULT = "arg_result";

    @BindView(R.id.button_question_result) Button buttonQuestionResult;

    public TriviaQuestionResultFragment() {}

    public static TriviaQuestionResultFragment newInstance(boolean result) {
        Bundle args = new Bundle();
        args.putBoolean(ARG_RESULT, result);

        TriviaQuestionResultFragment fragment = new TriviaQuestionResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trivia_question_result, container, false);
        ButterKnife.bind(this, view);

        Bundle args;
        boolean result;
        if ((args = getArguments()) != null) {
            result = args.getBoolean(ARG_RESULT);
            buttonQuestionResult.setText(result ? R.string.ui_correct : R.string.ui_wrong);
            buttonQuestionResult.setBackgroundColor(
                    result ? getResources().getColor(R.color.colorAccentGreen)
                           : getResources().getColor(R.color.colorAccentRed));
        }
        return view;
    }
}
