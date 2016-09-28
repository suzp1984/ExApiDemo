package suzp1984.github.io.exapidemo.app.fragments;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import suzp1984.github.io.exapidemo.R;

public abstract class FragmentInRecyclerActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

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
        setContentView(R.layout.activity_fragment_in_recycler);

        ButterKnife.bind(this);

        setupRecycler();
    }

    protected abstract void setupRecycler();
}
