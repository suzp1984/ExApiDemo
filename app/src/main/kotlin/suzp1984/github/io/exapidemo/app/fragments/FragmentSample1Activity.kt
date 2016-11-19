package suzp1984.github.io.exapidemo.app.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View

/**
 * Created by jacobsu on 9/25/16.
 */

class FragmentSample1Activity : FragmentInRecyclerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setupRecycler() {
        mRemove.visibility = View.GONE
        mAdd.visibility = View.GONE

        val adapter = NumFragment1Adapter(supportFragmentManager)

        mRecycler.adapter = adapter
        mRecycler.layoutManager = LinearLayoutManager(this)

        mRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                Log.e(TAG, "onScrolled: $dx, $dy")
                Log.e(TAG, "scroll extent: " + mRecycler.computeVerticalScrollExtent() +
                        ", offet: " + mRecycler.computeVerticalScrollOffset() +
                        ", range: " + mRecycler.computeVerticalScrollRange())
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
    }

    companion object {

        private val TAG = FragmentSample1Activity::class.java.name
    }
}
