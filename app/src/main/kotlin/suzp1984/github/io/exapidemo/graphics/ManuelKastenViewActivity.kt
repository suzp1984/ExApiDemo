package suzp1984.github.io.exapidemo.graphics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup

/**
 * Created by jacobsu on 9/29/16.
 */

class ManuelKastenViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = ManuelKastenView(this)
        view.layoutParams = ViewGroup.LayoutParams(1024, 1024)

        setContentView(view)
    }
}
