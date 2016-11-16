package suzp1984.github.io.exapidemo.graphics

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup

import suzp1984.github.io.exapidemo.R

class PixelArtActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pixel = PixelArtView(this)
        pixel.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)

        setContentView(pixel)
    }
}
