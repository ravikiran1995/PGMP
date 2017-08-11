import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by RaviKiran on 8/11/2017.
 */

public class PGMP_MOCK_TEST  extends Application{

      @Override
    public void onCreate(){
          super.onCreate();
          Firebase.setAndroidContext(this);

      }
}
