package suzp1984.github.io.exapidemo.app.menu;

import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import suzp1984.github.io.exapidemo.R;

/**
 * Created by suzhenxi on 9/30/2016.
 */

public class MenuInflateActivity extends AppCompatActivity {

    private final String TAG = MenuInflateActivity.class.getName();

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private NavigationMenu mNavMenu;
    private MenuInflater mMenuInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycler);

        ButterKnife.bind(this);

        mNavMenu = new NavigationMenu(this);
        mNavMenu.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                return false;
            }

            @Override
            public void onMenuModeChange(MenuBuilder menu) {

            }
        });

        getMenuInflater().inflate(R.menu.recycler, mNavMenu);

        int size = mNavMenu.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = mNavMenu.getItem(i);
            Log.e(TAG, "* " + item.getTitle() + ".");
            if (item.getIcon() != null) {
                Log.e(TAG, " --" + item.getIcon().toString());
            }

            if (item.hasSubMenu()) {
                SubMenu subMenu = item.getSubMenu();
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem sub = subMenu.getItem(j);
                    Log.e(TAG, "    " + sub.getTitle().toString());
                    if (sub.getIcon() != null) {
                        Log.e(TAG, "    --" + sub.getIcon().toString());
                    }
                }

                continue;
            }

        }
    }
    
}
