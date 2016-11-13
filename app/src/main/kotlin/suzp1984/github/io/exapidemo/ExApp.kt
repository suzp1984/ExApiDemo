package suzp1984.github.io.exapidemo

import android.app.Application
import android.os.StrictMode

import butterknife.ButterKnife

/**
 * Created by jacobsu on 9/25/16.
 */

class ExApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDialog()
                    .build())

            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build())

            ButterKnife.setDebug(true)
        }
    }
}
