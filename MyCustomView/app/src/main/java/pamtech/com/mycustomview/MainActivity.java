package pamtech.com.mycustomview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pamtech.com.mycustomview.views.LovelyView;

public class MainActivity extends AppCompatActivity {

    private LovelyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView = (LovelyView)findViewById(R.id.custView);
    }

    public void btnPressed(View view) {
        myView.setCircleColor(Color.GREEN);
        myView.setLabelColor(Color.MAGENTA);
        myView.setLabelText("HA");
    }
}
