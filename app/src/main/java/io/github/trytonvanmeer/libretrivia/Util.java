package io.github.trytonvanmeer.libretrivia;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import io.github.trytonvanmeer.libretrivia.OpenTrivia.TriviaQuery;

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
}
