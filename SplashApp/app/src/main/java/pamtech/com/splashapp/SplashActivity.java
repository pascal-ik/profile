package pamtech.com.splashapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashActivity extends AppCompatActivity {
    /**
     * simple splash screen activity using Easy splash library and a
     * noActionBar theme
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(2000)
                .withBackgroundColor(Color.parseColor("#a7f0ff"))
                .withHeaderText("WELCOME")
                .withLogo(R.drawable.app_icon);
        View easySplash = config.create();
        setContentView(easySplash);
    }
}
