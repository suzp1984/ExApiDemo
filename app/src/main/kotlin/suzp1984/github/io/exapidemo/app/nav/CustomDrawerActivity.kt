package suzp1984.github.io.exapidemo.app.nav

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import butterknife.BindView
import butterknife.ButterKnife
import suzp1984.github.io.exapidemo.R

/**
 * Created by moses on 10/8/2016.
 */

class CustomDrawerActivity : AppCompatActivity() {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    lateinit var drawer: Drawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.custom_drawer_layout)
        ButterKnife.bind(this)

        toolbar?.let {
            setSupportActionBar(toolbar)
        }

        drawer = DrawerBuilder().withActivity(this).withMenuRes(R.menu.recycler).build()
    }
}
