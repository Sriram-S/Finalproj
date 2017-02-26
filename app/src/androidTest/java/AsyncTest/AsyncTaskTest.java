package AsyncTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.util.Log;


import com.udacity.gradle.builditbigger.MainActivityFragment;

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
    String res=null;

    @Test
    public void checkasync() throws InterruptedException, ExecutionException, TimeoutException {
        MainActivityFragment.EndpointsAsyncTask endpointsAsyncTask= new MainActivityFragment.EndpointsAsyncTask(InstrumentationRegistry.getTargetContext());
        endpointsAsyncTask.execute();
        res=endpointsAsyncTask.get(20, TimeUnit.SECONDS);
        assertNotNull(res);
    }

}
