package suzp1984.github.io.exapidemo.system

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.*

/**
 * Created by suzhenxi on 1/10/2017.
 */
class ConnectivityStateActivity : AppCompatActivity() {

    lateinit var connectivityUI : ConnectivityUI

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityUI = ConnectivityUI()
        connectivityUI.setContentView(this)
    }

    fun getConnectionInfo() {
        val connMgr : ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo : NetworkInfo? = connMgr.activeNetworkInfo
//        val network : Network = connMgr.activeNetwork

    }

    class ConnectivityUI : AnkoComponent<ConnectivityStateActivity> {
        lateinit var connectBtn : Button
        lateinit var resultTxt  : TextView

        override fun createView(ui: AnkoContext<ConnectivityStateActivity>): View = with(ui) {
            relativeLayout {
                connectBtn = button("Connection Btn") {
                    onClick {  }
                }
            }
        }

    }
}