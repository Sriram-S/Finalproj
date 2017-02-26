package AsyncTest;

import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.udacity.gradle.builditbigger.FreeJokesFragment;
import com.udacity.gradle.builditbigger.free.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by HP on 26-02-2017.
 */

@RunWith(value = AndroidJUnit4.class)

public class AsyncTaskTest extends AndroidTestCase {

    FreeJokesFragment.EndpointsAsyncTask endpointsAsyncTask;
    String res=null;


    public AsyncTaskTest() {
        super();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }

    @Test
    public void checkasync() throws InterruptedException, ExecutionException, TimeoutException {
        endpointsAsyncTask.execute();
        res=endpointsAsyncTask.get(20, TimeUnit.SECONDS);
        Log.v("sdf",res);
        assertNotNull(res);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
