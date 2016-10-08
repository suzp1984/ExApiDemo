package suzp1984.github.io.exapidemo.app.nav;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.design.internal.NavigationMenu;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import suzp1984.github.io.exapidemo.R;

/**
 * Created by suzhenxi on 10/8/2016.
 */

public class DrawerBuilder {

    private int mMenuRes = 0;
    private OnMenuItemSelectedListener mMenuItemSelectedListener;
    private Activity mActivity;
    private ViewGroup mRootView;
    private DrawerLayout mDrawerLayout;
    private ScrimInsetsFrameLayout mSliderLayout;
    private RecyclerView mRecycler;

    public DrawerBuilder() {

    }

    public DrawerBuilder withMenuRes(@MenuRes int menuRes) {
        mMenuRes = menuRes;

        return this;
    }

    public DrawerBuilder withMenuItemSelectedListener(OnMenuItemSelectedListener listener) {
        mMenuItemSelectedListener = listener;

        return this;
    }

    public DrawerBuilder withActivity(Activity activity) {
        mActivity = activity;
        mRootView = (ViewGroup) activity.findViewById(android.R.id.content);

        return this;
    }

    public DrawerBuilder withDrawerLayout(@LayoutRes int resLayout) {
        if (mActivity == null) {
            throw new RuntimeException("Please pass an activity first");
        }

        if (resLayout != -1) {
            mDrawerLayout = (DrawerLayout) mActivity.getLayoutInflater()
                                                    .inflate(resLayout, mRootView, false);
        } else {
            if (Build.VERSION.SDK_INT < 21) {
                mDrawerLayout = (DrawerLayout) mActivity.getLayoutInflater()
                                    .inflate(R.layout.drawer_not_fit_windows, mRootView, false);
            } else {
                mDrawerLayout = (DrawerLayout) mActivity.getLayoutInflater()
                                    .inflate(R.layout.drawer_fit_windows, mRootView, false);
            }
        }

        return this;
    }

    public Drawer build() {
        if (mActivity == null) {
            throw new RuntimeException("Please pass an activity.");
        }

        if (mMenuRes == 0) {
            throw new RuntimeException("Please pass an Menu Resource");
        }

        if (mDrawerLayout == null) {
            withDrawerLayout(-1);
        }

        View originalRootView = mRootView.getChildAt(0);
        mRootView.removeView(originalRootView);
        mDrawerLayout.addView(originalRootView, 0);
        mRootView.addView(mDrawerLayout);

        mSliderLayout = (ScrimInsetsFrameLayout) mActivity.getLayoutInflater()
                .inflate(R.layout.drawer_slider_layout, mDrawerLayout, false);

        mDrawerLayout.addView(mSliderLayout, 1);

        createContent();

        Drawer drawer = new Drawer(this);

        return drawer;
    }

    private void createContent() {
        // set shadow for the drawer
        if (mRecycler == null) {
            mRecycler = (RecyclerView) mActivity.getLayoutInflater()
                            .inflate(R.layout.drawer_recycler_view, mSliderLayout, false);
            mRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        }

        mSliderLayout.addView(mRecycler);

        NavigationMenu navMenu = new NavigationMenu(mActivity);
        mActivity.getMenuInflater().inflate(mMenuRes, navMenu);

        MenuAdapter adapter = new MenuAdapter();
        adapter.setMenuItems(navMenu);

        mRecycler.setAdapter(adapter);
    }

    public interface OnMenuItemSelectedListener {
        public boolean onMenuItemSelected(MenuItem item);
    }
}
