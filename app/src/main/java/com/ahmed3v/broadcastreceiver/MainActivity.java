package com.ahmed3v.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * The Power Receiver app responds to system broadcasts about the power
 * connected state as well as a custom broadcast that is sent when the user
 * taps the button.
 */
public class MainActivity extends AppCompatActivity {

    // String constant that defines the custom broadcast Action.
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    private CustomReceiver mReceiver = new CustomReceiver();

    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = findViewById(R.id.send_broadcast_btn);

        //Define the intent
        IntentFilter filter = new IntentFilter();

        // Add system broadcast actions sent by the system when the power is
        // connected and disconnected.
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);


        // Register the receiver using the activity context.
        this.registerReceiver(mReceiver, filter);


        // Register the receiver to receive custom broadcast.
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));

        /*
         * Click event handler for the button, that sends custom broadcast using the
         * LocalBroadcastManager.
         */
        sendButton.setOnClickListener(v -> {

            Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);

            LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);

        });

    }

    /**
     * Unregisters the broadcast receivers when the app is destroyed.
     */
    @Override
    protected void onDestroy() {

        //Unregister the receiver
        this.unregisterReceiver(mReceiver);

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);

        super.onDestroy();
    }
}