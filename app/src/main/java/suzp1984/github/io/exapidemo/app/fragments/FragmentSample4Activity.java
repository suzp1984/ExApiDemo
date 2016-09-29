package suzp1984.github.io.exapidemo.app.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by suzhenxi on 9/28/2016.
 */

public class FragmentSample4Activity extends FragmentInRecyclerActivity {

    @Override
    protected void setupRecycler() {
        final NumFragment4Adapter adapter = new NumFragment4Adapter(getSupportFragmentManager());

        mRecycler.setAdapter(adapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setItemViewCacheSize(adapter.getItemCount());

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                super.onItemRangeChanged(positionStart, itemCount, payload);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);

                for (int i = 0; i < itemCount; i++) {
                    NumberFragment fragment = adapter.getFragment(positionStart + i);

                    if (fragment != null) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }
                }
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            }
        });

        adapter.registerFragmentDataObserver(new NumFragment4Adapter.FragmentAdapterDataObserver() {
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

        mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeFragment(2);
            }
        });

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addFragment(2);
                mRecycler.setItemViewCacheSize(adapter.getItemCount());
            }
        });
    }
}
