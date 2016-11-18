package suzp1984.github.io.exapidemo.app.nav

import android.app.Activity
import android.os.Build
import android.support.annotation.LayoutRes
import android.support.annotation.MenuRes
import android.support.design.internal.NavigationMenu
import android.support.design.internal.ScrimInsetsFrameLayout
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import suzp1984.github.io.exapidemo.R

/**
 * Created by moses on 10/8/2016.
 */

class DrawerBuilder {

    private var mMenuRes = 0
    private var mMenuItemSelectedListener: OnMenuItemSelectedListener? = null
    private lateinit var mActivity: Activity
    private lateinit var mRootView: ViewGroup
    private var mDrawerLayout: DrawerLayout? = null
    private var mSliderLayout: ScrimInsetsFrameLayout? = null
    private var mRecycler: RecyclerView? = null

    fun withMenuRes(@MenuRes menuRes: Int): DrawerBuilder {
        mMenuRes = menuRes

        return this
    }

    fun withMenuItemSelectedListener(listener: OnMenuItemSelectedListener): DrawerBuilder {
        mMenuItemSelectedListener = listener

        return this
    }

    fun withActivity(activity: Activity): DrawerBuilder {
        mActivity = activity
        mRootView = activity.findViewById(android.R.id.content) as ViewGroup

        return this
    }

    fun withDrawerLayout(@LayoutRes resLayout: Int): DrawerBuilder {

        if (resLayout != -1) {
            mDrawerLayout = mActivity.layoutInflater
                    .inflate(resLayout, mRootView, false) as DrawerLayout
        } else {
            if (Build.VERSION.SDK_INT < 21) {
                mDrawerLayout = mActivity.layoutInflater
                        .inflate(R.layout.drawer_not_fit_windows, mRootView, false) as DrawerLayout
            } else {
                mDrawerLayout = mActivity.layoutInflater
                        .inflate(R.layout.drawer_fit_windows, mRootView, false) as DrawerLayout
            }
        }

        return this
    }

    fun build(): Drawer {

        if (mMenuRes == 0) {
            throw RuntimeException("Please pass an Menu Resource")
        }

        if (mDrawerLayout == null) {
            withDrawerLayout(-1)
        }

        val originalRootView = mRootView.getChildAt(0)
        mRootView.removeView(originalRootView)
        mDrawerLayout!!.addView(originalRootView, 0)
        mRootView.addView(mDrawerLayout)

        mSliderLayout = mActivity.layoutInflater
                .inflate(R.layout.drawer_slider_layout, mDrawerLayout, false) as ScrimInsetsFrameLayout

        mDrawerLayout!!.addView(mSliderLayout, 1)

        createContent()

        val drawer = Drawer(this)

        return drawer
    }

    private fun createContent() {
        // set shadow for the drawer
        if (mRecycler == null) {
            mRecycler = mActivity.layoutInflater
                    .inflate(R.layout.drawer_recycler_view, mSliderLayout, false) as RecyclerView
            mRecycler!!.layoutManager = LinearLayoutManager(mActivity)
        }

        mSliderLayout!!.addView(mRecycler)

        val navMenu = NavigationMenu(mActivity)
        mActivity.menuInflater.inflate(mMenuRes, navMenu)

        val adapter = MenuAdapter()
        adapter.setMenuItems(navMenu)

        mRecycler!!.adapter = adapter
    }

    interface OnMenuItemSelectedListener {
        fun onMenuItemSelected(item: MenuItem): Boolean
    }
}
