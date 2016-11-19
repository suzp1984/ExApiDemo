package suzp1984.github.io.exapidemo.app.fragments

import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import suzp1984.github.io.exapidemo.R

/**
 * Created by jacobsu on 9/24/16.
 */

class NumFragment1Adapter(private val mFragmentManager: FragmentManager) : NumFragmentBaseAdapter() {

    private val TAG = NumFragment1Adapter::class.java.name

    private val COUNT = 100
    private val mNumFragments = arrayOfNulls<NumberFragment>(COUNT)

    private var mViewHolderCount = 0

    init {

        for (i in 0..COUNT - 1) {
            mNumFragments[i] = NumberFragment.newInstance(i.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumFragmentBaseAdapter.NumberViewHolder {
        mViewHolderCount++
        Log.e(TAG, "--- onCreateViewHolder: type $viewType, count: $mViewHolderCount")

        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.num_fragment_item, null)

        val fragment_container = view.findViewWithTag("fragment_container")
        fragment_container.id = View.generateViewId()
        // fragment_container.setMinimumHeight(50);
        val numberFragment = mNumFragments[viewType]

        view.tag = numberFragment

        notifyViewHolderCreate(viewType)
        notifyViewHolderCountChanged(mViewHolderCount)

        return NumFragmentBaseAdapter.NumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumFragmentBaseAdapter.NumberViewHolder, position: Int) {
        Log.e(TAG, "--- onBindViewHolder: " + position)

        val numberFragment = holder.itemView.tag as NumberFragment

        if (!numberFragment.isAdded) {
            mFragmentManager.beginTransaction().replace(holder.itemView.id, numberFragment).commit()
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

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onViewRecycled(holder: NumFragmentBaseAdapter.NumberViewHolder?) {
        super.onViewRecycled(holder)

        Log.e(TAG, "Number ViewHolder is recyclable : " + holder!!.isRecyclable)
    }

    override fun onFailedToRecycleView(holder: NumFragmentBaseAdapter.NumberViewHolder?): Boolean {
        Log.e(TAG, "--- onFailedTo Recycler: " + holder!!.isRecyclable)

        return super.onFailedToRecycleView(holder)
    }
}
