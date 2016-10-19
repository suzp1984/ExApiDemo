package suzp1984.github.io.exapidemo.app.fragments;

import android.database.Observable;
import android.widget.BaseAdapter;

/**
 * Created by suzhenxi on 10/19/2016.
 */

public abstract class BaseListFragmentAdapter extends BaseAdapter {

    private final FragmentAdapterDataObservable mFragmentDataObservable =
            new FragmentAdapterDataObservable();

    public void registerFragmentAdapterDataObserver(FragmentAdapterDataObserver observer) {
        mFragmentDataObservable.registerObserver(observer);
    }

    public void unregisterFragmentAdapterDataObserver(FragmentAdapterDataObserver observer) {
        mFragmentDataObservable.unregisterObserver(observer);
    }

    public void notifyViewCountChanged(int count) {
        mFragmentDataObservable.notifyViewCountChanged(count);
    }

    public void notifyAttachedFragmentCountChanged(int count) {
        mFragmentDataObservable.notifyAttachedFragmentCountChanged(count);
    }

    public static abstract class FragmentAdapterDataObserver {
        public void onViewCountChanged(int count) {

        }

        public void onAttachedFragmentCountChanged(int count) {

        }
    }

    public static class FragmentAdapterDataObservable
            extends Observable<FragmentAdapterDataObserver> {
        public void notifyViewCountChanged(int count) {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onViewCountChanged(count);
            }
        }

        public void notifyAttachedFragmentCountChanged(int count) {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onAttachedFragmentCountChanged(count);
            }
        }
    }
}
