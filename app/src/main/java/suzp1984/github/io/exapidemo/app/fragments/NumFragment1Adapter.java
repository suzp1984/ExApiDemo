package suzp1984.github.io.exapidemo.app.fragments;

import android.database.Observable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import suzp1984.github.io.exapidemo.R;

/**
 * Created by jacobsu on 9/24/16.
 */

public class NumFragment1Adapter extends RecyclerView.Adapter<NumFragment1Adapter.NumberViewHolder> {

    private final String TAG = NumFragment1Adapter.class.getName();

    private final int COUNT = 100;
    private final NumberFragment[] mNumFragments = new NumberFragment[COUNT];

    private int mViewHolderCount = 0;

    private final FragmentManager mFragmentManager;

    private final FragmentAdapterDataObservable mFramgentAdapterObservable =
                                            new FragmentAdapterDataObservable();

    public NumFragment1Adapter(FragmentManager manager) {
        mFragmentManager = manager;

        for (int i = 0; i < COUNT; i++) {
            mNumFragments[i] = NumberFragment.newInstance(String.valueOf(i));
        }
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mViewHolderCount++;
        Log.e(TAG, "--- onCreateViewHolder: type " + viewType + ", count: " + mViewHolderCount);

        View view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.num_fragment_item, null);

        View fragment_container = view.findViewWithTag("fragment_container");
        fragment_container.setId(View.generateViewId());
        // fragment_container.setMinimumHeight(50);
        NumberFragment numberFragment = mNumFragments[viewType];

        view.setTag(numberFragment);

        notifyViewHolderCreate(viewType);
        notifyViewHolderCountChanged(mViewHolderCount);

        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        Log.e(TAG, "--- onBindViewHolder: " + position);

        NumberFragment numberFragment = (NumberFragment) holder.itemView.getTag();

        if (!numberFragment.isAdded()) {

            mFragmentManager.beginTransaction().replace(holder.itemView.getId(), numberFragment).commit();
        }

        if (mFragmentManager.getFragments() == null) {
            notifyAttachedFragmentChanged(0);
        } else {
            notifyAttachedFragmentChanged(mFragmentManager.getFragments().size());
        }
    }

    @Override
    public int getItemCount() {
        return COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void registerFragmentDataObserver(FragmentAdapterDataObserver observer) {
        mFramgentAdapterObservable.registerObserver(observer);
    }

    public void unregisterFragmentDataObserver(FragmentAdapterDataObserver observer) {
        mFramgentAdapterObservable.unregisterObserver(observer);
    }

    private void notifyViewHolderCreate(int type) {
        mFramgentAdapterObservable.notifyViewHolderCreate(type);
    }

    private void notifyViewHolderCountChanged(int count) {
        mFramgentAdapterObservable.notifyViewHolderCountChanged(count);
    }

    private void notifyAttachedFragmentChanged(int count) {
        mFramgentAdapterObservable.notifyAttachedFragmentCountChanged(count);
    }

    private boolean isFragmentAdded(Fragment fragment) {
        return mFragmentManager.getFragments() != null &&
                mFragmentManager.getFragments().contains(fragment);
    }

    public static class NumberViewHolder extends RecyclerView.ViewHolder {

        public NumberViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static abstract class FragmentAdapterDataObserver {
        public void onViewHolderCreate(int type) {

        }

        public void onViewHolderCountChanged(int count) {

        }

        public void onAttachedFragmentCountChanged(int count) {

        }
    }

    public static class FragmentAdapterDataObservable extends
                                                    Observable<FragmentAdapterDataObserver> {
        public boolean hasObservers() {
            return !mObservers.isEmpty();
        }

        public void notifyViewHolderCreate(int type) {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onViewHolderCreate(type);
            }
        }

        public void notifyViewHolderCountChanged(int count) {

            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onViewHolderCountChanged(count);
            }
        }

        public void notifyAttachedFragmentCountChanged(int count) {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onAttachedFragmentCountChanged(count);
            }
        }

    }
}
