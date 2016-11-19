package suzp1984.github.io.exapidemo.app.fragments

import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

import suzp1984.github.io.exapidemo.R

/**
 * Created by jacobsu on 9/25/16.
 */

class NumFragment2Adapter(private val mFragmentManager: FragmentManager) : NumFragmentBaseAdapter() {

    private val TAG = NumFragment2Adapter::class.java.name
    private val COUNT = 100

    private var mViewHolderCount = 0

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

        innerContainer.minimumHeight = 100
        innerContainer.id = View.generateViewId()

        rootContainer.addView(innerContainer)
        val fragment = NumberFragment()
        view.setTag(fragment)

        notifyViewHolderCreate(viewType)
        notifyViewHolderCountChanged(mViewHolderCount)

        return NumFragmentBaseAdapter.NumberViewHolder(view, innerContainer)
    }

    override fun onBindViewHolder(holder: NumFragmentBaseAdapter.NumberViewHolder, position: Int) {

        val numberFragment = holder.itemView.tag as NumberFragment
        numberFragment.number = position.toString()

        Log.e(TAG, "fragment: #" + numberFragment.number + " isAdded " + numberFragment.isAdded)
        Log.e(TAG, "fragment: #" + numberFragment.number + " isDetached " + numberFragment.isDetached)
        Log.e(TAG, "fragment #" + numberFragment.number + " isInLayout " + numberFragment.isInLayout)

        if (!numberFragment.isAdded) {
            mFragmentManager.beginTransaction().replace(holder.fragmentContainer.id, numberFragment).commit()
        }

        if (mFragmentManager.fragments == null) {
            notifyAttachedFragmentChanged(0)
        } else {
            notifyAttachedFragmentChanged(mFragmentManager.fragments.size)
        }
    }

    override fun getItemCount(): Int {
        return COUNT
    }

    override fun onViewRecycled(viewHolder: NumFragmentBaseAdapter.NumberViewHolder?) {
        super.onViewRecycled(viewHolder)

        if (viewHolder!!.itemView.tag != null && viewHolder.itemView.tag is NumberFragment) {
            val fragment = viewHolder.itemView.tag as NumberFragment
            Log.e(TAG, "&&& onViewRecycled: " + fragment.number)
            Log.e(TAG, "&&& #" + fragment.number + " isAdded " + fragment.isAdded)
            Log.e(TAG, "&&& #" + fragment.number + " isDetached " + fragment.isDetached)
            Log.e(TAG, "&&& #" + fragment.number + " isInLayout " + fragment.isInLayout)
        }

    }

}
