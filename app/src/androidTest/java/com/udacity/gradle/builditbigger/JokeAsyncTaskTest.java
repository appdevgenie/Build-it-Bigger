package com.udacity.gradle.builditbigger;


import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest {

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Test
    public void testNonEmptyJoke() {

        JokeAsyncTask jokeAsyncTask = new JokeAsyncTask(new JokeAsyncTask.AsyncRequestComplete() {
            @Override
            public void requestComplete(String joke) {
                countDownLatch.countDown();
            }
        });
        jokeAsyncTask.execute();

        try {
            countDownLatch.await();
            //Thread.sleep(10000);
            String jokeString = jokeAsyncTask.get();
            assertNotNull("Task completed successfully", jokeString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
