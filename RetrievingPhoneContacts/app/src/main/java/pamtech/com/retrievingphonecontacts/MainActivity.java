package pamtech.com.retrievingphonecontacts;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final int MY_PERMISSION_REQUEST_READ_CONTACTS = 10;

    EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.etContactName);
        checkPermission();
    }

    //check that permission is granted every time because user can revoke access at anytime
    private void checkPermission(){
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            //should we show an explanation?
            if(ActivityCompat
                    .shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS)){
                //show explanation to user asynchronously -- don't block this thread waiting for the user's response!
                //After the user sees the explanation, try again to request the permission
                Log.d(TAG, "checkPermission: Please allow this permission");
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},MY_PERMISSION_REQUEST_READ_CONTACTS);
            }
            else{
                //No explanation needed, we can request the permission.
                Log.d(TAG, "checkPermission: requesting permission");
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSION_REQUEST_READ_CONTACTS);
                //MY_PERMISSION_REQUEST_READ_CONTACTS is an app-defined int constant. The callback method gets the
                //result of the request
            }
        }
        else{
            Log.d(TAG, "checkPermission: permission is already granted");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_PERMISSION_REQUEST_READ_CONTACTS:
                //if result is cancelled, the request arrays are empty
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d(TAG, "onRequestPermissionsResult: Granted");

                }
                else{
                    Log.d(TAG, "onRequestPermissionsResult: Denied");
                }
        }
    }

    @SuppressLint("Recycle")
    public void readPhoneContacts(View view){
        Uri contentUri = ContactsContract.Contacts.CONTENT_URI;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Cursor cursor;
        cursor = getContentResolver().query(contentUri,null,null,null,null);
        int hasPhone = 0;

        assert cursor != null;
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                String CONTACT_NAME = mEditText.getText().toString();//cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                Log.d(TAG, "readPhoneContacts: " + CONTACT_NAME);
                hasPhone = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));

                if(hasPhone > 0){
                    Uri phoneURI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

                    String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
                    String[] projection = new String[]{NUMBER};
                    String selection = DISPLAY_NAME + "=?";
                    String[] selectionArgs = new String[]{CONTACT_NAME};

                    @SuppressLint("Recycle") Cursor phoneCursor = getContentResolver()
                            .query(phoneURI,
                                    projection,
                                    selection,
                                    selectionArgs,
                                    null);

                    assert phoneCursor != null;
                    while (phoneCursor.moveToNext()){
                        Log.d(TAG, "searchedContacts: "
                        + phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER)));
                    }
                }
            }
        }
    }
}
