package suzp1984.github.io.exapidemo.app.design

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import suzp1984.github.io.exapidemo.R

/**
 * Created by suzhenxi on 9/29/2016.
 */

class HeaderAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val COUNT = 50
    private val HEADER_VIEW_TYPE = 0
    private val NUMBER_VIEW_TYPE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        var vh: RecyclerView.ViewHolder? = null
        when (viewType) {
            HEADER_VIEW_TYPE -> {
                val header = LayoutInflater.from(parent.context).inflate(R.layout.header_item_layout, null)
                vh = HeaderViewHolder(header)
            }
            NUMBER_VIEW_TYPE -> {
                val number = LayoutInflater.from(parent.context).inflate(R.layout.number_item_layout, null)
                vh = NumberViewHolder(number)
            }
            else -> {
            }
        }

        return vh
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.header.text = "Header"
        } else if (holder is NumberViewHolder) {
            holder.number.text = position.toString()
        }
    }

    override fun getItemCount(): Int {
        return COUNT
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return HEADER_VIEW_TYPE
        }

        return NUMBER_VIEW_TYPE
    }

    class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var number: TextView

        init {
            number = itemView.findViewById(R.id.number) as TextView
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var header: TextView

        init {

            header = itemView.findViewById(R.id.number) as TextView
        }
    }
}
