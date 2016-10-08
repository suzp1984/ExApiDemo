package suzp1984.github.io.exapidemo.app.nav;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import suzp1984.github.io.exapidemo.R;

/**
 * Created by suzhenxi on 10/8/2016.
 */

public class CustomDrawerActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom_drawer_layout);
        ButterKnife.bind(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        drawer = new DrawerBuilder().withActivity(this).withMenuRes(R.menu.recycler).build();
    }
}
