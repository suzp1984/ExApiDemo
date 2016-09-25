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

import suzp1984.github.io.exapidemo.R;

/**
 * Created by jacobsu on 9/25/16.
 */

public class NumFragment2Adapter extends RecyclerView.Adapter<NumFragment2Adapter.NumberViewHolder> {

    private final String TAG = NumFragment2Adapter.class.getName();

    private final FragmentManager mFragmentManager;
    private final int COUNT = 100;
    private final NumberFragment[] mNumFragments = new NumberFragment[COUNT];

    private int mViewHolderCount = 0;
    private final NumFragment2Adapter.FragmentAdapterDataObservable mFramgentAdapterObservable =
                                            new NumFragment2Adapter.FragmentAdapterDataObservable();

    public NumFragment2Adapter(FragmentManager manager) {
        mFragmentManager = manager;

        for(int i = 0; i < COUNT; i++) {
            mNumFragments[i] =  NumberFragment.newInstance(String.valueOf(i));
        }
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
        innerContainer.setId(View.generateViewId());

        rootContainer.addView(innerContainer);

        notifyViewHolderCreate(viewType);
        notifyViewHolderCountChanged(mViewHolderCount);

        return new NumFragment2Adapter.NumberViewHolder(view, innerContainer);
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        NumberFragment numberFragment = mNumFragments[position];

//        if (!isFragmentInManager(numberFragment)) {
//            mFragmentManager.beginTransaction().replace(holder.fragmentContainer.getId(), numberFragment).commitNow();
//        }
        // mFragmentManager.executePendingTransactions();

//        if (numberFragment.isAdded()) {
//            mFragmentManager.beginTransaction().attach(numberFragment).commit();
//        } else {
//            mFragmentManager.beginTransaction().replace(holder.fragmentContainer.getId(), numberFragment).commit();
//        }
//        mFragmentManager.executePendingTransactions();
        Log.e(TAG, "fragment: #" + numberFragment.getNumber() + " isAdded " + numberFragment.isAdded());
        Log.e(TAG, "fragment: #" + numberFragment.getNumber() + " isDetached " + numberFragment.isDetached());
        Log.e(TAG, "fragment #" + numberFragment.getNumber() + " isInLayout " + numberFragment.isInLayout());

        if (!numberFragment.isAdded()) {
            mFragmentManager.beginTransaction().replace(holder.fragmentContainer.getId(), numberFragment).commit();
        } else if (numberFragment.isDetached()) {
            // mFragmentManager.beginTransaction().attach(numberFragment).commit();
        }

        holder.fragmentContainer.setTag(numberFragment);

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

    @Override
    public void onViewRecycled(NumberViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);

        if (viewHolder.fragmentContainer.getTag() != null &&
                viewHolder.fragmentContainer.getTag() instanceof NumberFragment) {
            NumberFragment fragment = (NumberFragment) viewHolder.fragmentContainer.getTag();
            Log.e(TAG, "&&& onViewRecycled: " + fragment.getNumber());
            Log.e(TAG, "&&& #" + fragment.getNumber() + " isAdded " + fragment.isAdded());
            Log.e(TAG, "&&& #" + fragment.getNumber() + " isDetached " + fragment.isDetached());
            Log.e(TAG, "&&& #" + fragment.getNumber() + " isInLayout " + fragment.isInLayout());
            // mFragmentManager.beginTransaction().detach(fragment).commit();
//            if (fragment.isAdded()) {
//                mFragmentManager.beginTransaction().detach(fragment).commit();
//                mFragmentManager.executePendingTransactions();
//            }
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
