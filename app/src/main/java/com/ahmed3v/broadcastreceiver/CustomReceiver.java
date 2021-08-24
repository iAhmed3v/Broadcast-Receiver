package com.ahmed3v.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Toast;

import io.github.muddz.styleabletoast.StyleableToast;

public class CustomReceiver extends BroadcastReceiver {

    // String constant that defines the custom broadcast Action.
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    /**
     * This callback method gets called when the Broadcast Receiver receives a
     * broadcast that it is registered for.
     *
     * @param context The context in which broadcast receiver is running.
     * @param intent The broadcast is delivered in the form of an intent which
     *               contains the broadcast action.
     */
    @Override
    public void onReceive(Context context , Intent intent) {

        String intentAction = intent.getAction();


        if (intentAction != null) {

            String toastMessage = "unknown intent action";

            switch(intentAction) {

                case Intent.ACTION_POWER_CONNECTED:
                   StyleableToast.makeText(context, "Power Connected", R.style.MyToastConnected).show();
                break;

                case Intent.ACTION_POWER_DISCONNECTED:
                    StyleableToast.makeText(context, "Power Disconnected!", R.style.MyToastDisconnected).show();
                    break;

                case ACTION_CUSTOM_BROADCAST:
                    StyleableToast.makeText(context, "Custom Broadcast Received!", R.style.MyCustomToast).show();
            }

            Toast.makeText(context , toastMessage, Toast.LENGTH_LONG).show();

        }
    }
}