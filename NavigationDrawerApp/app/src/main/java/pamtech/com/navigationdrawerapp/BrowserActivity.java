package pamtech.com.navigationdrawerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BrowserActivity extends AppCompatActivity {

    Button mGo, flag;
    EditText URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        mGo = findViewById(R.id.go_button);
        flag = findViewById(R.id.flag_button);
        URL = findViewById(R.id.web_editText);

        mGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUrl();

            }
        });
        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBrowser();
            }
        });

    }

    /**
     * send get url from user and send to webView activity
     */
    public void sendUrl(){
        String url = URL.getText().toString();
        Intent container = new Intent(this, WebViewActivity.class);
        container.putExtra("url", url);
        startActivity(container);

    }

    public void goToBrowser(){
        Intent web = new Intent(this, BrowserActivity.class);
        web.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(web);
    }




}
