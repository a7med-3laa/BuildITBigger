package com.udacity.gradle.builditbigger;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void asyncTaskTest() {

        JokeAsyncTask jokeAsyncTask = new JokeAsyncTask(new SendInterface() {
            @Override
            public void onPostExecute(String s) {
                assertNotNull(s);
                assertFalse(s.isEmpty());
            }
        }, mActivityTestRule.getActivity());
        jokeAsyncTask.execute();
    }
}
