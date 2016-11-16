package suzp1984.github.io.exapidemo.widget.recycler

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView

import butterknife.BindView
import butterknife.ButterKnife
import suzp1984.github.io.exapidemo.R

/**
 * Created by moses on 10/10/2016.
 */

class CustomScrollbarActivity : AppCompatActivity() {

    @BindView(R.id.recyclor)
    lateinit var mRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.custom_scrollbar_layout)
        ButterKnife.bind(this)

        mRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }
}
