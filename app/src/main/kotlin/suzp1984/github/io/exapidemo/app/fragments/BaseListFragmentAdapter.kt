package suzp1984.github.io.exapidemo.app.fragments

import android.database.Observable
import android.widget.BaseAdapter

/**
 * Created by moses on 10/19/2016.
 */

abstract class BaseListFragmentAdapter : BaseAdapter() {

    private val mFragmentDataObservable = FragmentAdapterDataObservable()

    fun registerFragmentAdapterDataObserver(observer: FragmentAdapterDataObserver) {
        mFragmentDataObservable.registerObserver(observer)
    }

    fun unregisterFragmentAdapterDataObserver(observer: FragmentAdapterDataObserver) {
        mFragmentDataObservable.unregisterObserver(observer)
    }

    fun notifyViewCountChanged(count: Int) {
        mFragmentDataObservable.notifyViewCountChanged(count)
    }

    fun notifyAttachedFragmentCountChanged(count: Int) {
        mFragmentDataObservable.notifyAttachedFragmentCountChanged(count)
    }

    abstract class FragmentAdapterDataObserver {
        open fun onViewCountChanged(count: Int) {

        }

        open fun onAttachedFragmentCountChanged(count: Int) {

        }
    }

    class FragmentAdapterDataObservable : Observable<FragmentAdapterDataObserver>() {
        fun notifyViewCountChanged(count: Int) {
            for (i in mObservers.indices.reversed()) {
                mObservers[i].onViewCountChanged(count)
            }
        }

        fun notifyAttachedFragmentCountChanged(count: Int) {
            for (i in mObservers.indices.reversed()) {
                mObservers[i].onAttachedFragmentCountChanged(count)
            }
        }
    }
}
