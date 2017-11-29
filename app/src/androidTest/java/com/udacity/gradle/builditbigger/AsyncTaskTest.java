package com.udacity.gradle.builditbigger;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    CountDownLatch countDownLatch;
    String joke = null;

    @Test
    public void asyncTaskTest() {
        countDownLatch = new CountDownLatch(1);
        JokeAsyncTask jokeAsyncTask = new JokeAsyncTask(new SendInterface() {
            @Override
            public void onPostExecute(String s) {
                joke = s;
            }
        }, mActivityTestRule.getActivity());
        jokeAsyncTask.execute();
        try {
            countDownLatch.await(10, TimeUnit.SECONDS);
            assertNotNull(joke);
            assertFalse(joke.isEmpty());
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}
