package suzp1984.github.io.exapidemo.app.menu

import android.os.Bundle
import android.support.design.internal.NavigationMenu
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.menu.MenuBuilder
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuInflater
import android.view.MenuItem
import android.view.SubMenu

import butterknife.BindView
import butterknife.ButterKnife
import suzp1984.github.io.exapidemo.R

/**
 * Created by moses on 9/30/2016.
 */

class MenuInflateActivity : AppCompatActivity() {

    private val TAG = MenuInflateActivity::class.java.name

    @BindView(R.id.recyclor)
    lateinit var mRecycler: RecyclerView

    private lateinit var mNavMenu: NavigationMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.recycler)

        ButterKnife.bind(this)

        mNavMenu = NavigationMenu(this)
        mNavMenu.setCallback(object : MenuBuilder.Callback {
            override fun onMenuItemSelected(menu: MenuBuilder, item: MenuItem): Boolean {
                return false
            }

            override fun onMenuModeChange(menu: MenuBuilder) {

            }
        })

        menuInflater.inflate(R.menu.recycler, mNavMenu)

        val size = mNavMenu.size()
        for (i in 0..size - 1) {
            val item = mNavMenu.getItem(i)
            Log.e(TAG, "* ${item.title}.")
            if (item.icon != null) {
                Log.e(TAG, " -- ${item.icon}")
            }

            if (item.hasSubMenu()) {
                val subMenu = item.subMenu
                for (j in 0..subMenu.size() - 1) {
                    val sub = subMenu.getItem(j)
                    Log.e(TAG, "    ${sub.title}")
                    if (sub.icon != null) {
                        Log.e(TAG, "    -- ${sub.icon}")
                    }
                }

                continue
            }

        }
    }

}
