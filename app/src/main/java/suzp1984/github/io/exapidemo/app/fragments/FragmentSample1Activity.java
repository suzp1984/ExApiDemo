package suzp1984.github.io.exapidemo.app.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by jacobsu on 9/25/16.
 */

public class FragmentSample1Activity extends FragmentInRecyclerActivity {

    private static final String TAG = FragmentSample1Activity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setupRecycler() {
        final NumFragment1Adapter adapter = new NumFragment1Adapter(getSupportFragmentManager());

        mRecycler.setAdapter(adapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Log.e(TAG, "onScrolled: " + dx + ", " + dy);
                Log.e(TAG, "scroll extent: " + mRecycler.computeVerticalScrollExtent() +
                            ", offet: " + mRecycler.computeVerticalScrollOffset() +
                            ", range: " + mRecycler.computeVerticalScrollRange());
            }
        });

        adapter.registerFragmentDataObserver(new NumFragment1Adapter.FragmentAdapterDataObserver() {
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
