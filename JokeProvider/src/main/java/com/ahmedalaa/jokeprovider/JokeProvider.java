package com.ahmedalaa.jokeprovider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeProvider {
    List<String> jokes;

    public JokeProvider() {
        jokes = new ArrayList<>();
        jokes.add("Joke 1");
        jokes.add("Joke 2");

        jokes.add("Joke 3");

        jokes.add("Joke 4");

        jokes.add("Joke 5");

        jokes.add("Joke 6");
    }

    public void insertJoke(String joke) {
        jokes.add(joke);
    }

    public String getJoke() {
        Random random = new Random();
        return jokes.get(random.nextInt(jokes.size() - 1));
    }
}
