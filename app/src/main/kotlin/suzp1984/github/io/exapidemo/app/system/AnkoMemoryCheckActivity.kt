package suzp1984.github.io.exapidemo.app.system

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.*
import suzp1984.github.io.exapidemo.R

/**
 * Created by suzhenxi on 11/23/2016.
 */
class AnkoMemoryCheckActivity : AppCompatActivity() {

    lateinit var memoryCheckUI : AnkoMemoryCheckUI

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        memoryCheckUI = AnkoMemoryCheckUI()
        memoryCheckUI.setContentView(this)
    }

    fun onActivityManagerMemory() {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        val m = manager.memoryClass
        val l = manager.largeMemoryClass

        memoryCheckUI.txtView.text = "memory class: ${m * 1024 * 1024} K = ${m}M; " +
                "large: ${l * 1024 * 1024} K = ${l}M;"
    }

    fun onRuntimeCheck() {
        val runtime = Runtime.getRuntime()
        val free = runtime.freeMemory()
        val max = runtime.maxMemory()
        val total = runtime.totalMemory()

        memoryCheckUI.txtView.text = "free: ${free}K = ${free / 1024L / 1024L}M; " +
                "total: ${total}K = ${total / 1024L / 1024L}M;" +
                "whole: ${max} K = ${max / 1024L / 1024L}M;"

    }

    class AnkoMemoryCheckUI : AnkoComponent<AnkoMemoryCheckActivity> {

        lateinit var txtView : TextView
        lateinit var activityMemoryButton : Button

        override fun createView(ui: AnkoContext<AnkoMemoryCheckActivity>): View = with(ui) {
            val ID_ACTIVITY_MEMORY = 1

            relativeLayout {

                activityMemoryButton = button("activity manager check") {
                    id = ID_ACTIVITY_MEMORY
                    onClick { ui.owner.onActivityManagerMemory() }
                }.lparams(width = matchParent) {
                    alignParentTop()
                    topMargin = dip(40)
                    leftMargin = dip(20)
                    rightMargin = dip(20)
                    bottomMargin = dip(10)
                }

                button("runtime check") {
                    onClick { ui.owner.onRuntimeCheck() }
                }.lparams(width = matchParent) {
                    below(activityMemoryButton)
                    topMargin = dip(10)
                    leftMargin = dip(20)
                    rightMargin = dip(20)
                    bottomMargin = dip(10)
                }

                txtView = textView {

                }.lparams(width = matchParent) {
                    alignParentBottom()
                }
            }
        }

    }
}