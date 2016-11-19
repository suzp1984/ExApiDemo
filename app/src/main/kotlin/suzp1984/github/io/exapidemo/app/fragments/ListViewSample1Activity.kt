package suzp1984.github.io.exapidemo.app.fragments

import android.view.View

/**
 * Created by moses on 10/19/2016.
 */

class ListViewSample1Activity : FragmentInListViewActivity() {

    override fun setupListView() {
        mAdd.visibility = View.GONE
        mRemove.visibility = View.GONE

        val adapter = ListAdapter1(supportFragmentManager)

        mListView.adapter = adapter

        adapter.registerFragmentAdapterDataObserver(object : BaseListFragmentAdapter.FragmentAdapterDataObserver() {
            override fun onViewCountChanged(count: Int) {
                super.onViewCountChanged(count)

                mViewHolderCount.text = count.toString()
            }

            override fun onAttachedFragmentCountChanged(count: Int) {
                super.onAttachedFragmentCountChanged(count)

                mFragmentsCount.text = count.toString()
            }
        })
    }
}
