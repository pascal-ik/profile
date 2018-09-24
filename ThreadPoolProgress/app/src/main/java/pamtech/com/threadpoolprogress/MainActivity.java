package pamtech.com.threadpoolprogress;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ProgressBar pA;
    private ProgressBar pB;
    private ProgressBar pC;
    private ProgressBar pD;
    private Button startButton;
    private int progressStatus;
    private int progressStatus2;
    private int progressStatus3;
    private int progressStatus4;
    private Handler mHandler = new Handler();
    private Handler mHandler2 = new Handler();
    private Handler mHandler3 = new Handler();
    private Handler mHandler4 = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pA = findViewById(R.id.progressBarA);
        pB = findViewById(R.id.progressBarB);
        pC = findViewById(R.id.progressBarC);
        pD = findViewById(R.id.progressBarD);
        startButton = findViewById(R.id.button_start);
        progressStatus = 0;
        progressStatus2 = 0;
        progressStatus3 = 0;
        progressStatus4 = 0;

        startButton.setOnClickListener(view -> {
            doSomeLightWeightBackgroundWork();
            doSomeLightWeightBackgroundWork2();
            doSomeLightWeightBackgroundWork3();
            doSomeLightWeightBackgroundWork4();
        });
    }


    /*
     * Using it for Background Tasks
     */
    public void doSomeBackgroundWork() {
        DefaultExecutorSupplier.getInstance().forBackgroundTasks()
                .execute(() -> {
                    // do some background work here.
                    Log.d(TAG, "doSomeBackgroundWork: work will be done in the background if I so choose");

                });
    }

    /*
     * Using it for Light-Weight Background Tasks
     */
    public void doSomeLightWeightBackgroundWork() {
        DefaultExecutorSupplier.getInstance().forLightWeightBackgroundTasks()
                .execute(() -> new Thread(() -> {

                    while (progressStatus < 100) {
                        progressStatus += 5;//update progress bar
                        mHandler.post(() -> pA.setProgress(progressStatus));//show progress on main ui
                        try {
                            Thread.sleep(200);//sleep thread
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }).start());

    }

    public void doSomeLightWeightBackgroundWork2() {
        DefaultExecutorSupplier.getInstance().forLightWeightBackgroundTasks()
                .execute(() -> new Thread(() -> {

                    while (progressStatus2 < 100) {
                        progressStatus2 += 5;//update progress bar
                        mHandler2.post(() -> pB.setProgress(progressStatus2));//show progress on main ui
                        try {
                            Thread.sleep(200);//sleep thread
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }).start());
    }
    public void doSomeLightWeightBackgroundWork3() {
        DefaultExecutorSupplier.getInstance().forLightWeightBackgroundTasks()
                .execute(() -> new Thread(() -> {

                    while (progressStatus3 < 100) {
                        progressStatus3 += 5;//update progress bar
                        mHandler3.post(() -> pC.setProgress(progressStatus3));//show progress on main ui
                        try {
                            Thread.sleep(200);//sleep thread
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }).start());
    }
    public void doSomeLightWeightBackgroundWork4() {
        DefaultExecutorSupplier.getInstance().forLightWeightBackgroundTasks()
                .execute(() -> new Thread(() -> {

                    while (progressStatus4 < 100) {
                        progressStatus4 += 5;//update progress bar
                        mHandler4.post(() -> pD.setProgress(progressStatus4));//show progress on main ui
                        try {
                            Thread.sleep(200);//sleep thread
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }).start());
    }

}
