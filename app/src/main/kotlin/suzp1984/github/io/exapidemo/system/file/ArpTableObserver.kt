package suzp1984.github.io.exapidemo.system.file

import android.os.FileObserver
import android.util.Log

/**
 * Created by suzhenxi on 12/14/2016.
 */
class ArpTableObserver(path: String, mask: Int) : FileObserver(path, mask) {

    companion object {
        // val path: String = "/proc/net/arp"
        val TAG : String = ArpTableObserver::class.java.simpleName
    }

    override fun onEvent(event: Int, path: String?) {
        Log.d(TAG, "path: " + path)

        when(event) {
            ACCESS -> { Log.e(TAG, "Access") }
            // ALL_EVENTS -> {}
            OPEN -> { Log.e(TAG, "Open") }
            MODIFY -> { Log.e(TAG, "Modify") }
            DELETE_SELF -> { Log.e(TAG, "Delete self") }
            DELETE -> { Log.e(TAG, "Delete") }
            CREATE -> { Log.e(TAG, "Create") }
            CLOSE_NOWRITE -> { Log.e(TAG, "Close NoWrite") }
            CLOSE_WRITE -> { Log.e(TAG, "Close write") }
            ATTRIB -> { Log.e(TAG, "attrib") }
            else -> {
                Log.d(TAG, "event: " + event)
            }
        }
    }
}