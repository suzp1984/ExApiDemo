package suzp1984.github.io.exapidemo

import android.content.Intent
import android.support.annotation.StringDef
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by jacobsu on 9/3/16.
 */
class ApiAdapter(private val mApiObjs: List<Map<String, Any>>) : RecyclerView.Adapter<ApiAdapter.ApiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        return ApiViewHolder(root)
    }

    override fun onBindViewHolder(holder: ApiViewHolder, position: Int) {
        val item = mApiObjs[position]

        val title = item["title"] as String
        holder.textView.text = title

        holder.itemView.setOnClickListener {
            val intent = Intent(item["intent"] as Intent)
            intent.addCategory(Intent.CATEGORY_SAMPLE_CODE)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mApiObjs.size
    }

    class ApiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var textView: TextView

        init {

            textView = itemView.findViewById(android.R.id.text1) as TextView
        }
    }
}
