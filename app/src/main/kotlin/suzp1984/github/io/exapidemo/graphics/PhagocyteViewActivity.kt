package suzp1984.github.io.exapidemo.graphics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup

/**
 * Created by jacobsu on 9/29/16.
 */

class PhagocyteViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = PhagocyteView(this)
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)

        setContentView(view)
    }
}
