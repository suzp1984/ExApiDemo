package suzp1984.github.io.exapidemo.app.fragments

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by suzhenxi on 9/28/2016.
 */

class FragmentSample4Activity : FragmentInRecyclerActivity() {

    override fun setupRecycler() {
        val adapter = NumFragment4Adapter(supportFragmentManager)

        mRecycler.adapter = adapter
        mRecycler.layoutManager = LinearLayoutManager(this)
        mRecycler.setItemViewCacheSize(adapter.itemCount)

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                super.onItemRangeChanged(positionStart, itemCount)
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                super.onItemRangeChanged(positionStart, itemCount, payload)
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)

                for (i in 0..itemCount - 1) {
                    val fragment = adapter.getFragment(positionStart + i)

                    if (fragment != null) {
                        supportFragmentManager.beginTransaction().remove(fragment).commit()
                    }
                }
            }

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount)
            }
        })

        adapter.registerFragmentDataObserver(object : NumFragmentBaseAdapter.FragmentAdapterDataObserver() {
            override fun onViewHolderCreate(type: Int) {
                super.onViewHolderCreate(type)
            }

            override fun onViewHolderCountChanged(count: Int) {
                super.onViewHolderCountChanged(count)

                mViewHolderCount.text = count.toString()
            }

            override fun onAttachedFragmentCountChanged(count: Int) {
                super.onAttachedFragmentCountChanged(count)

                mFragmentsCount.text = count.toString()
            }
        })

        mRemove.setOnClickListener { adapter.removeFragment(2) }

        mAdd.setOnClickListener {
            adapter.addFragment(2)
            mRecycler.setItemViewCacheSize(adapter.itemCount)
        }
    }
}
