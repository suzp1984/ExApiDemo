package suzp1984.github.io.exapidemo.app.fragments

import android.database.Observable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by suzhenxi on 9/28/2016.
 */

abstract class NumFragmentBaseAdapter : RecyclerView.Adapter<NumFragmentBaseAdapter.NumberViewHolder>() {

    private val mFramgentAdapterObservable = NumFragmentBaseAdapter.FragmentAdapterDataObservable()

    fun registerFragmentDataObserver(observer: NumFragmentBaseAdapter.FragmentAdapterDataObserver) {
        mFramgentAdapterObservable.registerObserver(observer)
    }

    fun unregisterFragmentDataObserver(observer: NumFragmentBaseAdapter.FragmentAdapterDataObserver) {
        mFramgentAdapterObservable.unregisterObserver(observer)
    }

    fun notifyViewHolderCreate(type: Int) {
        mFramgentAdapterObservable.notifyViewHolderCreate(type)
    }

    fun notifyViewHolderCountChanged(count: Int) {
        mFramgentAdapterObservable.notifyViewHolderCountChanged(count)
    }

    fun notifyAttachedFragmentChanged(count: Int) {
        mFramgentAdapterObservable.notifyAttachedFragmentCountChanged(count)
    }

    abstract class FragmentAdapterDataObserver {
        open fun onViewHolderCreate(type: Int) {

        }

        open fun onViewHolderCountChanged(count: Int) {

        }

        open fun onAttachedFragmentCountChanged(count: Int) {

        }
    }

    class FragmentAdapterDataObservable : Observable<NumFragmentBaseAdapter.FragmentAdapterDataObserver>() {
        fun hasObservers(): Boolean {
            return !mObservers.isEmpty()
        }

        fun notifyViewHolderCreate(type: Int) {
            for (i in mObservers.indices.reversed()) {
                mObservers[i].onViewHolderCreate(type)
            }
        }

        fun notifyViewHolderCountChanged(count: Int) {

            for (i in mObservers.indices.reversed()) {
                mObservers[i].onViewHolderCountChanged(count)
            }
        }

        fun notifyAttachedFragmentCountChanged(count: Int) {
            for (i in mObservers.indices.reversed()) {
                mObservers[i].onAttachedFragmentCountChanged(count)
            }
        }

    }

    class NumberViewHolder : RecyclerView.ViewHolder {

        lateinit var fragmentContainer: ViewGroup

        constructor(itemView: View) : super(itemView) {
        }

        constructor(itemView: View, fragmentContainer: ViewGroup) : super(itemView) {

            this.fragmentContainer = fragmentContainer
        }
    }
}
