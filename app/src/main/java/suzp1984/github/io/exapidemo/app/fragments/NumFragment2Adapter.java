package suzp1984.github.io.exapidemo.app.fragments;

import android.database.Observable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.LinkedList;

import suzp1984.github.io.exapidemo.R;

/**
 * Created by jacobsu on 9/25/16.
 */

public class NumFragment2Adapter extends RecyclerView.Adapter<NumFragment2Adapter.NumberViewHolder> {

    private final String TAG = NumFragment2Adapter.class.getName();

    private final FragmentManager mFragmentManager;
    private final int COUNT = 100;

    private int mViewHolderCount = 0;
    private final NumFragment2Adapter.FragmentAdapterDataObservable mFramgentAdapterObservable =
                                            new NumFragment2Adapter.FragmentAdapterDataObservable();

    public NumFragment2Adapter(FragmentManager manager) {
        mFragmentManager = manager;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mViewHolderCount++;
        Log.e(TAG, "--- onCreateViewHolder: type " + viewType + ", count: " + mViewHolderCount);

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.num_fragment_item, null);

        ViewGroup rootContainer = (ViewGroup) view;

        FrameLayout innerContainer = new FrameLayout(parent.getContext());
        innerContainer.setLayoutParams(new FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));

        innerContainer.setMinimumHeight(100);
        innerContainer.setId(View.generateViewId());

        rootContainer.addView(innerContainer);
        NumberFragment fragment = new NumberFragment();
        view.setTag(fragment);

        notifyViewHolderCreate(viewType);
        notifyViewHolderCountChanged(mViewHolderCount);

        return new NumFragment2Adapter.NumberViewHolder(view, innerContainer);
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {

        NumberFragment numberFragment = (NumberFragment) holder.itemView.getTag();
        numberFragment.setNumber(String.valueOf(position));

        Log.e(TAG, "fragment: #" + numberFragment.getNumber() + " isAdded " + numberFragment.isAdded());
        Log.e(TAG, "fragment: #" + numberFragment.getNumber() + " isDetached " + numberFragment.isDetached());
        Log.e(TAG, "fragment #" + numberFragment.getNumber() + " isInLayout " + numberFragment.isInLayout());

        if (!numberFragment.isAdded()) {
            mFragmentManager.beginTransaction().replace(holder.fragmentContainer.getId(), numberFragment).commit();
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

//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }

    @Override
    public void onViewRecycled(NumberViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);

        if (viewHolder.itemView.getTag() != null &&
                viewHolder.itemView.getTag() instanceof NumberFragment) {
            NumberFragment fragment = (NumberFragment) viewHolder.itemView.getTag();
            Log.e(TAG, "&&& onViewRecycled: " + fragment.getNumber());
            Log.e(TAG, "&&& #" + fragment.getNumber() + " isAdded " + fragment.isAdded());
            Log.e(TAG, "&&& #" + fragment.getNumber() + " isDetached " + fragment.isDetached());
            Log.e(TAG, "&&& #" + fragment.getNumber() + " isInLayout " + fragment.isInLayout());
        }

    }

    public void registerFragmentDataObserver(NumFragment2Adapter.FragmentAdapterDataObserver observer) {
        mFramgentAdapterObservable.registerObserver(observer);
    }

    public void unregisterFragmentDataObserver(NumFragment2Adapter.FragmentAdapterDataObserver observer) {
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

    private boolean isFragmentInManager(Fragment fragment) {

        return mFragmentManager.getFragments() != null &&
                mFragmentManager.getFragments().contains(fragment);
    }

    public static class NumberViewHolder extends RecyclerView.ViewHolder {

        FrameLayout fragmentContainer;

        public NumberViewHolder(View itemView, FrameLayout fragmentContainer) {
            super(itemView);

            this.fragmentContainer = fragmentContainer;
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
            Observable<NumFragment2Adapter.FragmentAdapterDataObserver> {
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
