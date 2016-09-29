package suzp1984.github.io.exapidemo.app.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

/**
 * Created by suzhenxi on 9/26/2016.
 */

public class FragmentSample3Activity extends FragmentInRecyclerActivity {
    @Override
    protected void setupRecycler() {
        mRemove.setVisibility(View.GONE);
        mAdd.setVisibility(View.GONE);

        NumFragment3Adapter adapter = new NumFragment3Adapter(getSupportFragmentManager());

        mRecycler.setAdapter(adapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setItemViewCacheSize(adapter.getItemCount());

        adapter.registerFragmentDataObserver(new NumFragment3Adapter.FragmentAdapterDataObserver() {
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
