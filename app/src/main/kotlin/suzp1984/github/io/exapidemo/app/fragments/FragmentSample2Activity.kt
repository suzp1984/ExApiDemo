package suzp1984.github.io.exapidemo.app.fragments

import android.support.v7.widget.LinearLayoutManager
import android.view.View

/**
 * Created by jacobsu on 9/25/16.
 */

class FragmentSample2Activity : FragmentInRecyclerActivity() {
    override fun setupRecycler() {
        mRemove.visibility = View.GONE
        mAdd.visibility = View.GONE

        val adapter = NumFragment2Adapter(supportFragmentManager)

        mRecycler.adapter = adapter
        mRecycler.layoutManager = LinearLayoutManager(this)

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
    }
}
