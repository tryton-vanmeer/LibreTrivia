package io.github.trytonvanmeer.libretrivia.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.trytonvanmeer.libretrivia.R;

public class TriviaGameErrorFragment extends Fragment {
    private final static String ARG_ERROR_MSG = "arg_error_msg";

    @BindView(R.id.text_error_msg) TextView textView;

    public TriviaGameErrorFragment() {}

    public static TriviaGameErrorFragment newInstance(String msg) {
        Bundle args = new Bundle();
        args.putString(ARG_ERROR_MSG, msg);

        TriviaGameErrorFragment fragment = new TriviaGameErrorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trivia_game_error, container, false);
        ButterKnife.bind(this, view);

        Bundle args;
        if ((args = getArguments()) != null) {
            textView.setText(args.getString(ARG_ERROR_MSG));
        }

        return view;
    }
}
