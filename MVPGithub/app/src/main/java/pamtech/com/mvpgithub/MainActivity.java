package pamtech.com.mvpgithub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import pamtech.com.mvpgithub.api.model.GitHubRepo;
import pamtech.com.mvpgithub.api.service.GitHubClient;
import pamtech.com.mvpgithub.ui.adapter.GitHubRepoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.repo_editText);
        mButton = findViewById(R.id.btn_display_repo);

        mButton.setOnClickListener(view -> {
            String repo = mEditText.getText().toString();
            Intent messageIntent = new Intent(MainActivity.this, SecondActivity.class);
            messageIntent.putExtra("repoName", repo);
            startActivity(messageIntent);
        });

    }
}
