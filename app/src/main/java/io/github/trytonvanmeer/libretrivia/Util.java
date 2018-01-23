package io.github.trytonvanmeer.libretrivia;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuery;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuestion;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuestionBoolean;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuestionMultiple;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaType;

public class Util {

    private static String readStream(InputStream in) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in), 1000);

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            builder.append(line);
        }

        in.close();
        return builder.toString();
    }

    private static String GET(String query) throws IOException {
        String response;

        URL url = new URL(query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = new BufferedInputStream(connection.getInputStream());
            response = readStream(in);
        } finally {
            connection.disconnect();
        }

        return response;
    }

    public static String GET(TriviaQuery query) throws IOException {
        return GET(query.toString());
    }

    public static ArrayList<TriviaQuestion> jsonToQuestionArray(String json) {
        JsonArray jsonArray = new JsonParser().parse(json).getAsJsonObject()
                .getAsJsonArray("results");

        ArrayList<TriviaQuestion> questions = new ArrayList<>();

        for (JsonElement element : jsonArray) {
            JsonObject object = element.getAsJsonObject();
            TriviaType type = TriviaType.get(object.get("type").getAsString());

            if (type == TriviaType.MULTIPLE) {
                questions.add(TriviaQuestionMultiple.fromJson(object));
            } else {
                questions.add(TriviaQuestionBoolean.fromJson(object));
            }
        }

        return questions;
    }
}
