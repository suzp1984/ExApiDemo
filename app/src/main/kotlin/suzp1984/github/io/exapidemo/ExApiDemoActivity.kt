package suzp1984.github.io.exapidemo

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import java.text.Collator
import java.util.ArrayList
import java.util.Collections
import java.util.Comparator
import java.util.HashMap
import java.util.Objects

import butterknife.BindView
import butterknife.ButterKnife

class ExApiDemoActivity : AppCompatActivity() {

    @BindView(R.id.list)
    lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex_api_demo)

        ButterKnife.bind(this)

        val intent = intent
        var path: String = intent.getStringExtra("com.example.android.apis.Path") ?: ""

        supportActionBar?.title = if (path == "") "ExApiDemos" else path

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = ApiAdapter(getData(path))
        mRecyclerView.addItemDecoration(DividerDecoration(this))
    }

    protected fun getData(prefix: String): List<Map<String, Any>> {
        val myData = ArrayList<Map<String, Any>>()

        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(CUSTOME_CATEGORY)

        val pm = packageManager
        val list = pm.queryIntentActivities(mainIntent, 0) ?: return myData

        val prefixPath: Array<String>?
        var prefixWithSlash = prefix

        if (prefix == "") {
            prefixPath = null
        } else {
            prefixPath = prefix.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            prefixWithSlash = prefix + "/"
        }

        val len = list.size

        val entries = HashMap<String, Boolean>()

        for (i in 0..len - 1) {
            val info = list[i]
            val labelSeq = info.loadLabel(pm)
            val label = labelSeq?.toString() ?: info.activityInfo.name

            if (prefixWithSlash.length == 0 || label.startsWith(prefixWithSlash)) {

                val labelPath = label.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                val nextLabel = if (prefixPath == null) labelPath[0] else labelPath[prefixPath.size]

                if (prefixPath?.size ?: 0 == labelPath.size - 1) {
                    addItem(myData, nextLabel, activityIntent(
                            info.activityInfo.applicationInfo.packageName,
                            info.activityInfo.name))
                } else {
                    if (entries[nextLabel] == null) {
                        addItem(myData, nextLabel, browseIntent(if (prefix == "") nextLabel else prefix + "/" + nextLabel))
                        entries.put(nextLabel, true)
                    }
                }
            }
        }

        Collections.sort(myData, sDisplayNameComparator)

        return myData
    }

    protected fun activityIntent(pkg: String, componentName: String): Intent {
        val result = Intent()
        result.setClassName(pkg, componentName)
        return result
    }

    protected fun browseIntent(path: String): Intent {
        val result = Intent()
        result.setClass(this, ExApiDemoActivity::class.java)
        result.putExtra("com.example.android.apis.Path", path)
        return result
    }

    protected fun addItem(data: MutableList<Map<String, Any>>, name: String, intent: Intent) {
        val temp = HashMap<String, Any>()
        temp.put("title", name)
        temp.put("intent", intent)
        data.add(temp)
    }

    companion object {

        private val CUSTOME_CATEGORY = "suzp1984.github.io.exapidemo.samplecode"

        private val sDisplayNameComparator = object : Comparator<Map<String, Any>> {
            private val collator = Collator.getInstance()

            override fun compare(map1: Map<String, Any>, map2: Map<String, Any>): Int {
                return collator.compare(map1["title"], map2["title"])
            }
        }
    }
}
