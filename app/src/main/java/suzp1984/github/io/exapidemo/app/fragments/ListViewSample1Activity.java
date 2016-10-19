package suzp1984.github.io.exapidemo.app.fragments;

import android.view.View;

/**
 * Created by suzhenxi on 10/19/2016.
 */

public class ListViewSample1Activity extends FragmentInListViewActivity {

    @Override
    protected void setupListView() {
        mAdd.setVisibility(View.GONE);
        mRemove.setVisibility(View.GONE);

        ListAdapter1 adapter = new ListAdapter1(getSupportFragmentManager());

        mListView.setAdapter(adapter);

        adapter.registerFragmentAdapterDataObserver(new BaseListFragmentAdapter.FragmentAdapterDataObserver() {
            @Override
            public void onViewCountChanged(int count) {
                super.onViewCountChanged(count);

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
