package suzp1984.github.io.exapidemo.layout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.*


/**
 * Created by suzhenxi on 11/30/2016.
 */
class AnkoRelativeActivity : AppCompatActivity() {

    lateinit var ankoRelativeTestUI : AnkoRelativeTestUI

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ankoRelativeTestUI = AnkoRelativeTestUI()
        ankoRelativeTestUI.setContentView(this)
    }

    fun testClick() {

    }

    class AnkoRelativeTestUI : AnkoComponent<AnkoRelativeActivity> {

        lateinit var okBtn : Button
        lateinit var txtView: TextView

        override fun createView(ui: AnkoContext<AnkoRelativeActivity>): View = with(ui) {
            val ID_OK = 1
            relativeLayout {

                okBtn = button("Ok") {
                    id = ID_OK
                    onClick { ui.owner.testClick() }
                }.lparams(width = matchParent) {
                    alignParentTop()
                    topMargin = dip(20)
                    leftMargin = dip(20)
                    rightMargin = dip(20)
                    bottomMargin = dip(10)
                }

                button("Cancel") {
                    onClick { ui.owner.testClick() }
                }.lparams(width = matchParent) {
                    below(okBtn)
                    topMargin = dip(10)
                    leftMargin = dip(20)
                    rightMargin = dip(20)
                    bottomMargin = dip(10)
                }

                txtView = textView { text = "Hello world" }.lparams(width = matchParent) {
                    alignParentBottom()
                }
            }
        }

    }
}