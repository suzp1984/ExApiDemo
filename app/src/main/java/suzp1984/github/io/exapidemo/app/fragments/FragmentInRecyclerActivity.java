package suzp1984.github.io.exapidemo.app.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import suzp1984.github.io.exapidemo.R;

public class FragmentInRecyclerActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @BindView(R.id.viewholder_count)
    TextView mViewHolderCount;

    @BindView(R.id.fragments_count)
    TextView mFragmentsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_in_recycler);

        ButterKnife.bind(this);
        NumFragmentAdapter adapter = new NumFragmentAdapter(getSupportFragmentManager());

        mRecycler.setAdapter(adapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        adapter.registerFragmentDataObserver(new NumFragmentAdapter.FragmentAdapterDataObserver() {
            @Override
            public void onViewHolderCreate(int type) {
                super.onViewHolderCreate(type);
            }

            @Override
            public void onViewHolderCountChanged(int count) {
                super.onViewHolderCountChanged(count);

                mViewHolderCount.setText(String.valueOf(count));
            }

            @Override
            public void onAttachedFragmentCountChanged(int count) {
                super.onAttachedFragmentCountChanged(count);

                mFragmentsCount.setText(String.valueOf(count));
            }
        });
    }
}
