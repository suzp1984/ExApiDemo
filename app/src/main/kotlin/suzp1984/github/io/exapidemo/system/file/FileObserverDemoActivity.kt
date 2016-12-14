package suzp1984.github.io.exapidemo.system.file

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import org.jetbrains.anko.*

/**
 * Created by suzhenxi on 12/14/2016.
 */
class FileObserverDemoActivity : AppCompatActivity() {

    lateinit var fileObserverUI : FileObserverUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fileObserverUI = FileObserverUI()
        fileObserverUI.setContentView(this)
    }

    fun startFileObserver() {
        val intent = Intent(this, FileObserverDemoService::class.java)
        startService(intent)
    }

    fun stopFileObserver() {
        val intent = Intent(this, FileObserverDemoService::class.java)
        stopService(intent)
    }

    class FileObserverUI : AnkoComponent<FileObserverDemoActivity> {
        lateinit var startBtn : Button
        lateinit var stopBtn : Button

        override fun createView(ui: AnkoContext<FileObserverDemoActivity>) : View = with(ui) {
            verticalLayout {
                startBtn = button("Start Service") {
                    onClick { ui.owner.startFileObserver() }
                }.lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(20)
                    leftMargin = dip(20)
                    rightMargin = dip(20)
                    bottomMargin = dip(10)
                }

                stopBtn = button("Stop Service") {
                    onClick { ui.owner.stopFileObserver() }
                }.lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(10)
                    leftMargin = dip(20)
                    rightMargin = dip(20)
                    bottomMargin = dip(10)
                }
            }
        }

    }

}