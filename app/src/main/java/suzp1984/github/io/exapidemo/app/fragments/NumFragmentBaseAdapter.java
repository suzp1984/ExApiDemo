package suzp1984.github.io.exapidemo.app.fragments;

import android.database.Observable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by suzhenxi on 9/28/2016.
 */

public abstract class NumFragmentBaseAdapter extends RecyclerView.Adapter<NumFragmentBaseAdapter.NumberViewHolder> {

    private final NumFragmentBaseAdapter.FragmentAdapterDataObservable mFramgentAdapterObservable =
            new NumFragmentBaseAdapter.FragmentAdapterDataObservable();

    public void registerFragmentDataObserver(NumFragmentBaseAdapter.FragmentAdapterDataObserver observer) {
        mFramgentAdapterObservable.registerObserver(observer);
    }

    public void unregisterFragmentDataObserver(NumFragmentBaseAdapter.FragmentAdapterDataObserver observer) {
        mFramgentAdapterObservable.unregisterObserver(observer);
    }

    void notifyViewHolderCreate(int type) {
        mFramgentAdapterObservable.notifyViewHolderCreate(type);
    }

    void notifyViewHolderCountChanged(int count) {
        mFramgentAdapterObservable.notifyViewHolderCountChanged(count);
    }

    void notifyAttachedFragmentChanged(int count) {
        mFramgentAdapterObservable.notifyAttachedFragmentCountChanged(count);
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
            Observable<NumFragmentBaseAdapter.FragmentAdapterDataObserver> {
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

    public static class NumberViewHolder extends RecyclerView.ViewHolder {

        ViewGroup fragmentContainer;

        public NumberViewHolder(View itemView) {
            super(itemView);
        }

        public NumberViewHolder(View itemView, ViewGroup fragmentContainer) {
            super(itemView);

            this.fragmentContainer = fragmentContainer;
        }
    }
}
