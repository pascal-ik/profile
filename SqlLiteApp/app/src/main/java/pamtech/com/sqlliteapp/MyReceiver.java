package pamtech.com.sqlliteapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = "MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Bundle data = intent.getExtras();
        Employee employee = data.getParcelable("person");
        Log.d(TAG, "onReceive: " + employee.getFirstName());

    }
}
