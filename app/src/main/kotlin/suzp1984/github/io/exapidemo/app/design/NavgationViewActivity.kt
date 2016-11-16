package suzp1984.github.io.exapidemo.app.design

import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View

import butterknife.BindView
import butterknife.ButterKnife
import suzp1984.github.io.exapidemo.R

class NavgationViewActivity : AppCompatActivity() {

    @BindView(R.id.recyclor)
    internal lateinit var mRecycler: RecyclerView

    @BindView(R.id.toolbar)
    internal lateinit var mToolbar: Toolbar

    @BindView(R.id.drawer_layout)
    internal lateinit var mDrawer: DrawerLayout

    @BindView(R.id.menu_list)
    internal lateinit var mMenuList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navgation_view)

        ButterKnife.bind(this)

        setSupportActionBar(mToolbar)
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher)
        mToolbar.setNavigationOnClickListener { mDrawer.openDrawer(Gravity.LEFT) }

        mRecycler.adapter = HeaderAdapter()
        mRecycler.layoutManager = LinearLayoutManager(this)

        mMenuList.adapter = HeaderAdapter()
        mMenuList.layoutManager = LinearLayoutManager(this)
    }
}
