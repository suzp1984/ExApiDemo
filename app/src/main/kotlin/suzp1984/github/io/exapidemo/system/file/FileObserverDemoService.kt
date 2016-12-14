package suzp1984.github.io.exapidemo.system.file

import android.app.Service
import android.content.Intent
import android.os.FileObserver
import android.os.IBinder
import android.util.Log

/**
 * Created by suzhenxi on 12/14/2016.
 */
class FileObserverDemoService : Service() {

    lateinit var arpObserver : ArpTableObserver
    companion object {
        val path: String = "/proc/net/arp"
        // val path: String = "/sys/class/net/wlan0/statistics/rx_bytes"
        val TAG = FileObserverDemoService::class.java.simpleName
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")

        arpObserver = ArpTableObserver(path, FileObserver.ALL_EVENTS)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")

        arpObserver.startWatching()

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onDestroy() {
        arpObserver.stopWatching()

        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}