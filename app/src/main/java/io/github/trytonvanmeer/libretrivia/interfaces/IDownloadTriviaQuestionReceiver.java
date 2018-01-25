package io.github.trytonvanmeer.libretrivia.interfaces;

public interface IDownloadTriviaQuestionReceiver {
    void onTriviaQuestionsDownloaded(String json);
}
