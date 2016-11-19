package suzp1984.github.io.exapidemo.app.fragments

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by suzhenxi on 9/30/2016.
 */

class FragmentSample5Activity : FragmentInRecyclerActivity() {
    override fun setupRecycler() {
        mRemove.visibility = View.VISIBLE
        mAdd.visibility = View.VISIBLE

        val adapter = NumFragment5Adapter(supportFragmentManager)

        mRecycler.adapter = adapter
        mRecycler.layoutManager = LinearLayoutManager(this)
        mRecycler.setItemViewCacheSize(adapter.itemCount)

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

        mAdd.setOnClickListener {
            val size = 50 + (Math.random() * 100.0 * 2.0).toInt()
            mRecycler.setItemViewCacheSize(size)
            adapter.resetAdapter(size)
        }

        mRemove.setOnClickListener { adapter.checkFragments() }
    }
}
