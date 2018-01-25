package io.github.trytonvanmeer.libretrivia.interfaces;

public interface IDownloadTriviaQuestionReceiver {
    boolean onTriviaQuestionsDownloaded(String json);
}
