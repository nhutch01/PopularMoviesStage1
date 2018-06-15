package com.example.android.popularmoviesstage1;

/**
 * Created by Nicki Hutchens on 6/11/2018.
 */

public interface BackgroundTaskCallBacks<T, D> {

    /**
     * this method runs the a parallel thread with data type
     */
    T onBackground(D data);

    /**
     * This method gets called when the parallel thread finishes
     */
    void onTaskFinished(T result);
}
