package suzp1984.github.io.exapidemo.app.fragments

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

import java.util.LinkedList

import suzp1984.github.io.exapidemo.R

/**
 * Created by suzhenxi on 9/30/2016.
 */

class NumFragment5Adapter(private val mFragmentManager: FragmentManager) : NumFragmentBaseAdapter() {

    private val TAG = NumFragment5Adapter::class.java.name
    private val COUNT = 100
    private val mNumFragments = LinkedList<NumberFragment>()

    private var mViewHolderCount = 0

    init {

        for (i in 0..COUNT - 1) {
            mNumFragments.add(NumberFragment.newInstance(i.toString()))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumFragmentBaseAdapter.NumberViewHolder {
        mViewHolderCount++
        Log.e(TAG, "--- onCreateViewHolder: type $viewType, count: $mViewHolderCount")

        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.num_fragment_item, null)

        val rootContainer = view as ViewGroup

        val innerContainer = FrameLayout(parent.context)
        innerContainer.layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)

        // innerContainer.setMinimumHeight(100);
        innerContainer.id = View.generateViewId()

        rootContainer.addView(innerContainer)

        notifyViewHolderCreate(viewType)
        notifyViewHolderCountChanged(mViewHolderCount)

        return NumFragmentBaseAdapter.NumberViewHolder(view, innerContainer)
    }

    override fun onBindViewHolder(holder: NumFragmentBaseAdapter.NumberViewHolder, position: Int) {
        val numberFragment = mNumFragments[position]
        holder.itemView.tag = numberFragment

        Log.e(TAG, "fragment: #" + numberFragment.number + " isAdded " + numberFragment.isAdded)
        Log.e(TAG, "fragment: #" + numberFragment.number + " isDetached " + numberFragment.isDetached)
        Log.e(TAG, "fragment #" + numberFragment.number + " isInLayout " + numberFragment.isInLayout)

        if (!numberFragment.isAdded) {
            mFragmentManager.beginTransaction()
                    .replace(holder.fragmentContainer.id, numberFragment)
                    .commit()
        }

        if (mFragmentManager.fragments == null) {
            notifyAttachedFragmentChanged(0)
        } else {
            notifyAttachedFragmentChanged(mFragmentManager.fragments.size)
        }
    }

    override fun getItemCount(): Int {
        return mNumFragments.size
    }

    override fun onViewRecycled(viewHolder: NumFragmentBaseAdapter.NumberViewHolder?) {
        super.onViewRecycled(viewHolder)

        val f = viewHolder?.itemView?.getTag() as NumberFragment?

        if (f != null) {

            Log.e(TAG, "&&& onViewRecycled: " + f.number)
            Log.e(TAG, "&&& #" + f.number + " isAdded " + f.isAdded)
            Log.e(TAG, "&&& #" + f.number + " isDetached " + f.isDetached)
            Log.e(TAG, "&&& #" + f.number + " isInLayout " + f.isInLayout)

            if (f.isAdded) {
                mFragmentManager.beginTransaction().remove(f).commit()
            }
        }
    }

    fun resetAdapter(size: Int) {
        val transaction = mFragmentManager.beginTransaction()
        for (fragment in mNumFragments) {
            if (fragment.isAdded) {
                transaction.remove(fragment)
            }
        }

        transaction.commit()

        mNumFragments.clear()

        notifyDataSetChanged()
        mViewHolderCount = 0

        for (i in 0..size - 1) {
            mNumFragments.add(NumberFragment.newInstance("new $size - $i"))
        }

        notifyDataSetChanged()
    }

    fun checkFragments() {

        if (mFragmentManager.fragments == null) {
            notifyAttachedFragmentChanged(0)
        } else {
            notifyAttachedFragmentChanged(mFragmentManager.fragments.size)
        }

        notifyViewHolderCountChanged(mViewHolderCount)
    }
}
