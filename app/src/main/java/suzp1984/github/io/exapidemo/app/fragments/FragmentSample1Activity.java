package suzp1984.github.io.exapidemo.app.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by jacobsu on 9/25/16.
 */

public class FragmentSample1Activity extends FragmentInRecyclerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setupRecycler() {
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
