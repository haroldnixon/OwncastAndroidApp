package com.example.owncastplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "OwncastPrefs";
    private static final String KEY_SERVER_URL = "server_url";
    private static final String KEY_STREAM_PATH = "stream_path";

    private EditText etServerUrl;
    private EditText etStreamPath;
    private Button btnSave;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        etServerUrl = findViewById(R.id.et_server_url);
        etStreamPath = findViewById(R.id.et_stream_path);
        btnSave = findViewById(R.id.btn_save);

        // Load saved values if they exist
        String savedUrl = prefs.getString(KEY_SERVER_URL, "");
        String savedPath = prefs.getString(KEY_STREAM_PATH, "/hls/stream.m3u8");

        if (!TextUtils.isEmpty(savedUrl)) {
            etServerUrl.setText(savedUrl);
        }

        if (!TextUtils.isEmpty(savedPath)) {
            etStreamPath.setText(savedPath);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
    }

    private void saveSettings() {
        String serverUrl = etServerUrl.getText().toString().trim();
        String streamPath = etStreamPath.getText().toString().trim();

        // Validate input
        if (TextUtils.isEmpty(serverUrl)) {
            Toast.makeText(this, "Please enter server URL", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(streamPath)) {
            streamPath = "/hls/stream.m3u8"; // Default path
        }

        // Make sure URL doesn't end with a slash
        if (serverUrl.endsWith("/")) {
            serverUrl = serverUrl.substring(0, serverUrl.length() - 1);
        }

        // Make sure path starts with a slash
        if (!streamPath.startsWith("/")) {
            streamPath = "/" + streamPath;
        }

        // Save to SharedPreferences
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_SERVER_URL, serverUrl);
        editor.putString(KEY_STREAM_PATH, streamPath);
        editor.apply();

        // Update the StreamConfig
        updateStreamConfig(serverUrl, streamPath);

        // Navigate to MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateStreamConfig(String serverUrl, String streamPath) {
        // We would typically use reflection to modify the constants in StreamConfig,
        // but for simplicity, we'll use static methods to retrieve the values instead
        // The actual implementation happens in StreamConfig class
    }
}