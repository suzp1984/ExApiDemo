package suzp1984.github.io.exapidemo.app.fragments

import android.view.View
import android.view.ViewGroup

/**
 * Created by moses on 10/19/2016.
 */

class ListAdapter2 : BaseListFragmentAdapter() {

    override fun getCount(): Int {
        return 0
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        if (convertView == null) {

        }

        return convertView
    }
}
