package AsyncTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.util.Log;


import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by HP on 26-02-2017.
 */

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {
    String res=null;

    @Test
    public void checkasync() throws InterruptedException, ExecutionException, TimeoutException {
        EndpointsAsyncTask endpointsAsyncTask= new EndpointsAsyncTask(InstrumentationRegistry.getTargetContext());
        endpointsAsyncTask.execute();
        res=endpointsAsyncTask.get(20, TimeUnit.SECONDS);
        assertNotNull(res);
    }

}
