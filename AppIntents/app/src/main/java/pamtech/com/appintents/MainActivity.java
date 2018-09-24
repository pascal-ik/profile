package pamtech.com.appintents;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //text
    EditText email, alarm, browser, phone;
    //buttons
    Button emailB, alarmB, browserB, phoneB, cameraB;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //text
        email = findViewById(R.id.email_editText);
        alarm = findViewById(R.id.alarm_editText);
        browser = findViewById(R.id.web_editText);
        phone = findViewById(R.id.phone_editText);

        //buttons
        emailB = findViewById(R.id.email_button);
        alarmB = findViewById(R.id.alarm_button);
        browserB = findViewById(R.id.web_button);
        phoneB = findViewById(R.id.phone_button);
        cameraB = findViewById(R.id.camera_button);

         String alarmr = alarm.getText().toString();
         System.out.println(alarmr);

        browserB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPage();
            }
        });

        //open camera app
        cameraB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        phoneB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone();
            }
        });

        emailB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

        alarmB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "hey", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * intent to open camera app
     */
    private void takePicture(){
        Intent pic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(pic.resolveActivity(getPackageManager()) != null){
            startActivityForResult(pic, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * intent to open webpage
     */
    private void viewPage(){
        String page = browser.getText().toString();
        if((!page.startsWith("http://") && (!page.startsWith("https://")))){
            page = "http://" + page;
        }

        Intent httpIntent = new Intent(Intent.ACTION_VIEW);
        httpIntent.setData(Uri.parse(page));
        startActivity(httpIntent);
    }

    private void callPhone(){
        String num = phone.getText().toString();
        if((!num.startsWith("tel:"))){
            num = "tel:" + num;
        }
        Uri number = Uri.parse(num);
        Intent call = new Intent(Intent.ACTION_DIAL, number);
        startActivity(call);
    }

    private void sendEmail(){
        String address = email.getText().toString();

        Intent send = new Intent(Intent.ACTION_SEND);
        send.setType("plain/text");
        send.putExtra(Intent.EXTRA_EMAIL,address);
        startActivity(send);
    }


}
