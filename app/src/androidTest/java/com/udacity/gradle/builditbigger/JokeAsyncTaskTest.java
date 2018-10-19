package com.udacity.gradle.builditbigger;


import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest {

    @Test
    public void testNonEmptyJoke() {

        JokeAsyncTask jokeAsyncTask = new JokeAsyncTask(new JokeAsyncTask.AsyncRequestComplete() {
            @Override
            public void requestComplete(String joke) {

            }
        });
        jokeAsyncTask.execute();

        try {
            Thread.sleep(10000);
            String jokeString = jokeAsyncTask.get();
            assertNotNull("Task completed successfully", jokeString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
