package suzp1984.github.io.exapidemo.app.fragments;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by suzhenxi on 9/30/2016.
 */

public class FragmentSample5Activity extends FragmentInRecyclerActivity {
    @Override
    protected void setupRecycler() {
        mRemove.setVisibility(View.VISIBLE);
        mAdd.setVisibility(View.VISIBLE);

        final NumFragment5Adapter adapter = new NumFragment5Adapter(getSupportFragmentManager());

        mRecycler.setAdapter(adapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setItemViewCacheSize(adapter.getItemCount());

        adapter.registerFragmentDataObserver(new NumFragment5Adapter.FragmentAdapterDataObserver() {
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

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = 50 + (int) (Math.random() * 100 * 2);
                mRecycler.setItemViewCacheSize(size);
                adapter.resetAdapter(size);
            }
        });

        mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.checkFragments();
            }
        });
    }
}
