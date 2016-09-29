package suzp1984.github.io.exapidemo.app.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

/**
 * Created by jacobsu on 9/25/16.
 */

public class FragmentSample2Activity extends FragmentInRecyclerActivity {
    @Override
    protected void setupRecycler() {
        mRemove.setVisibility(View.GONE);
        mAdd.setVisibility(View.GONE);

        NumFragment2Adapter adapter = new NumFragment2Adapter(getSupportFragmentManager());

        mRecycler.setAdapter(adapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        adapter.registerFragmentDataObserver(new NumFragment2Adapter.FragmentAdapterDataObserver() {
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
