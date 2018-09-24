package pamtech.com.mvpgithub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mListView = findViewById(R.id.repoListView);

        Intent message = getIntent();
        String repoName = message.getStringExtra("repoName");
        Log.d(TAG, "onCreate: message gotten" + repoName);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        retrofit.create(GitHubClient.class);


        GitHubClient client = retrofit.create(GitHubClient.class);
        Call<List<GitHubRepo>> call = client.reposForUser(repoName);

        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                List<GitHubRepo> repos = response.body();
                mListView.setAdapter(new GitHubRepoAdapter(SecondActivity.this, repos));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(SecondActivity.this, "error:(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
