package suzp1984.github.io.exapidemo.app.fragments

import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import android.widget.ListAdapter
import android.widget.SimpleAdapter

import suzp1984.github.io.exapidemo.R

/**
 * Created by moses on 10/19/2016.
 */

class ListAdapter1(private val mFragmentManager: FragmentManager) : BaseListFragmentAdapter() {

    private val TAG = ListAdapter1::class.java.name

    private val COUNT = 100
    private val mNumFragments = arrayOfNulls<NumberFragment>(COUNT)

    private var mViewCount = 0

    init {

        for (i in 0..COUNT - 1) {
            mNumFragments[i] = NumberFragment.newInstance(i.toString())
        }
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getItem(position: Int): Any? {
        return mNumFragments[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        Log.e(TAG, "getView $position: $convertView")

        if (convertView == null) {
            convertView = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.num_fragment_item, null)
            mViewCount++
            convertView!!.id = View.generateViewId()

            notifyViewCountChanged(mViewCount)

            val fragment = mNumFragments[position]
            if (fragment != null && !fragment.isAdded()) {
                mFragmentManager.beginTransaction().replace(convertView.id, fragment).commit()
                notifyAttachedFragmentCountChanged(position + 1)
            }
        }

        return convertView
    }

    class ViewHolder
}
