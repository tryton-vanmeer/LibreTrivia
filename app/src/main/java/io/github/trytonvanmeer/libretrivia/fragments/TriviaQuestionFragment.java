package io.github.trytonvanmeer.libretrivia.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.trytonvanmeer.libretrivia.R;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuestion;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuestionMultiple;


public class TriviaQuestionFragment extends Fragment {
    public final static String ARG_TRIVIA_QUESTION = "arg_trivia_question";

    private TriviaQuestion question;

    public TriviaQuestionFragment() {}

    public static TriviaQuestionFragment newInstance(TriviaQuestion question) {
        TriviaQuestionFragment fragment = new TriviaQuestionFragment();
        fragment.question = question;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;

        if (question instanceof TriviaQuestionMultiple) {
            view = inflater.inflate(R.layout.fragment_trivia_question_multiple, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_trivia_question_boolean, container, false);
        }

        return view;
    }
}
