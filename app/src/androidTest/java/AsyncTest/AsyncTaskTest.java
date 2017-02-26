package AsyncTest;

import android.content.Context;
import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.udacity.gradle.builditbigger.FreeJokesFragment;
import com.udacity.gradle.builditbigger.FreeJokesFragment.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.free.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by HP on 26-02-2017.
 */

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest extends AndroidTestCase {

    @Mock Context context;
    MainActivity activity=new MainActivity();
    String res=null;

    @Test
    public void checkasync() throws InterruptedException, ExecutionException, TimeoutException {
        final EndpointsAsyncTask endpointsAsyncTask= new EndpointsAsyncTask(activity);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                        endpointsAsyncTask.execute();
                    }
        });
        res=endpointsAsyncTask.get(20, TimeUnit.SECONDS);
        Log.v("sdf",res);
        assertNotNull(res);
    }

}
