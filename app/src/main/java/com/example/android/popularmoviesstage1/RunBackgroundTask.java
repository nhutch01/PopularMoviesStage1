package com.example.android.popularmoviesstage1;
import android.os.AsyncTask;
/**
 * Created by Nicki Hutchens on 6/11/2018.
 */

public class RunBackgroundTask extends android.os.AsyncTask<Object, Object, String> {
    private String mApi;
    private BackgroundTaskCallBacks<String,String> mCallBacks;
    public RunBackgroundTask(String api, BackgroundTaskCallBacks callBacks){
        mApi = api;
        mCallBacks = callBacks;
    }
    @Override
    protected String doInBackground(Object... objects) {
        return mCallBacks.onBackground(mApi);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mCallBacks.onTaskFinished(s);
    }

}
