package pamtech.com.threadpoolprogress;

import android.os.Process;
import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

public class PriorityThreadFactory implements ThreadFactory {
    final int mThreadPriority;

    public PriorityThreadFactory(int threadPriority){
        mThreadPriority = threadPriority;
    }

    @Override
    public Thread newThread(@NonNull Runnable r) {
        Runnable mRunnable = () -> {
            try{
                Process.setThreadPriority(mThreadPriority);

            }
            catch (Throwable throwable){
                throwable.printStackTrace();
            }
            r.run();
        };
        return new Thread(mRunnable);
    }
}
