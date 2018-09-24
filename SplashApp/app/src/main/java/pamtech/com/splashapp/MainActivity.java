package pamtech.com.splashapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.telephony.SmsManager;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int MY_REQUEST_SMS_CODE = 1;

    private Button dialogueButton;
    private Button alertDialogueButton;
    private Button customDialogueButton;
    private Button sendSMSButton;

    private FragmentManager fm;
    private EditText messageEditText;
    private EditText phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSMSPermission();

        fm = getFragmentManager();

        //bind views
        dialogueButton = findViewById(R.id.btnShowDialogue);
        alertDialogueButton = findViewById(R.id.btnAlertDialogue);
        customDialogueButton = findViewById(R.id.btnCustomAlert);
        sendSMSButton = findViewById(R.id.btnSendSMS);


        messageEditText = findViewById(R.id.messageET);
        phoneEditText = findViewById(R.id.phoneET);


        dialogueButton.setOnClickListener(v -> showDialogue());

        alertDialogueButton.setOnClickListener(v -> showAlertDialogue());

        customDialogueButton.setOnClickListener(v -> showCustomAlertDialog());

        sendSMSButton.setOnClickListener(v -> sendSMS());
    }

    public void showDialogue() {
        final DialogueFragment myDialogue = new DialogueFragment();
        myDialogue.show(fm, "Hello");
        final Timer t = new Timer();//timer to handle auto dialogue dismiss
        t.schedule(new TimerTask() {
            public void run() {
                myDialogue.dismiss(); // when the task active then close the dialog
                t.cancel(); // stop the timer thread, otherwise to avoid any errors
            }
        }, 3000); // start task after 3 seconds
    }

    public void showAlertDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Alert Dialogue");
        builder.setMessage("Press Yes to close");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", (dialog, which) -> dialog.cancel());

        builder.setNeutralButton("No", (dialog, which) -> Toast.makeText(MainActivity.this, "I said PRESS YES, but you wouldn't listen", Toast.LENGTH_SHORT).show());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public ArrayList<Name> getNames() {
        ArrayList<Name> names = new ArrayList<>();

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(getAssets().open("names.xml"));
            NodeList nodeList = doc.getElementsByTagName("name");
            Log.d(TAG, "getNames: " + nodeList.getLength());

            int nodeLength;
            nodeLength = nodeList.getLength();

            for (int i = 0; i < nodeLength; i++) {
                Node node = nodeList.item(i);
                Name mName = new Name();

                String value = node.getFirstChild().getNodeValue();//2hours doing this smh

                Log.d(TAG, "getNames:  node is " + value);
                mName.setName(value);
                names.add(mName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }

    public void showCustomAlertDialog() {
        ArrayList<Name> names = getNames();
        Log.d(TAG, "showCustomAlertDialog: " );
        String[] arr = new String[3];//would use custom names adapter if I had some time but improvising
        for (int i = 0; i < names.size(); i++) {
            arr[i] = names.get(i).getName();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arr);

        Dialog dialog = new Dialog(this);
        dialog.setTitle("Custom");
        dialog.setContentView(R.layout.custom_alert_dialog);
        ListView listView = dialog.findViewById(R.id.namesLV);
        listView.setAdapter(arrayAdapter);
        dialog.show();

    }

    public void sendSMS(){
        Log.d(TAG, "sendSMS: sending sms");
        String message = messageEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        //check for sms permission
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                    MY_REQUEST_SMS_CODE);
        }
        else{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone,null,message,null,null);
        }

    }

    private void getSMSPermission(){
        //check for sms permission
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                    MY_REQUEST_SMS_CODE);
        }
    }


}
