package com.example.owncastplayer;

import android.content.Context;
import android.content.SharedPreferences;

public class StreamConfig {
    // Default values
    private static final String DEFAULT_BASE_URL = "http://your-owncast-server.com";
    private static final String DEFAULT_STREAM_PATH = "/hls/stream.m3u8";

    // SharedPreferences constants
    private static final String PREFS_NAME = "OwncastPrefs";
    private static final String KEY_SERVER_URL = "server_url";
    private static final String KEY_STREAM_PATH = "stream_path";

    public static String getStreamUrl(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String baseUrl = prefs.getString(KEY_SERVER_URL, DEFAULT_BASE_URL);
        String streamPath = prefs.getString(KEY_STREAM_PATH, DEFAULT_STREAM_PATH);

        return baseUrl + streamPath;
    }

    public static String getBaseUrl(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_SERVER_URL, DEFAULT_BASE_URL);
    }

    public static String getStreamPath(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_STREAM_PATH, DEFAULT_STREAM_PATH);
    }
}