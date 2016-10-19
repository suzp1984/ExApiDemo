package suzp1984.github.io.exapidemo.app.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import suzp1984.github.io.exapidemo.R;

/**
 * Created by suzhenxi on 10/19/2016.
 */

public abstract class FragmentInListViewActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView mListView;

    @BindView(R.id.viewholder_count)
    TextView mViewHolderCount;

    @BindView(R.id.fragments_count)
    TextView mFragmentsCount;

    @BindView(R.id.remove)
    FloatingActionButton mRemove;

    @BindView(R.id.add)
    FloatingActionButton mAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment_in_listview);
        ButterKnife.bind(this);

        setupListView();
    }

    protected abstract void setupListView();
}
