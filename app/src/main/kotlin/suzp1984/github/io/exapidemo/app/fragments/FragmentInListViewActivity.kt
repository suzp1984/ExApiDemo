package suzp1984.github.io.exapidemo.app.fragments

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.widget.TextView

import butterknife.BindView
import butterknife.ButterKnife
import suzp1984.github.io.exapidemo.R

/**
 * Created by suzhenxi on 10/19/2016.
 */

abstract class FragmentInListViewActivity : AppCompatActivity() {

    @BindView(R.id.listview)
    lateinit var mListView: ListView

    @BindView(R.id.viewholder_count)
    lateinit var mViewHolderCount: TextView

    @BindView(R.id.fragments_count)
    lateinit var mFragmentsCount: TextView

    @BindView(R.id.remove)
    lateinit var mRemove: FloatingActionButton

    @BindView(R.id.add)
    lateinit var mAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fragment_in_listview)
        ButterKnife.bind(this)

        setupListView()
    }

    protected abstract fun setupListView()
}
