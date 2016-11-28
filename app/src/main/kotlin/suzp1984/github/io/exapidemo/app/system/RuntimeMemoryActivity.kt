package suzp1984.github.io.exapidemo.app.system

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

import java.util.LinkedList

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import suzp1984.github.io.exapidemo.R

/**
 * Created by suzhenxi on 10/27/2016.
 */

class RuntimeMemoryActivity : AppCompatActivity() {

    @BindView(R.id.runtime_check)
    lateinit var mRuntimeCheckBtn: Button

    @BindView(R.id.allocate_20_memory)
    lateinit var mAllocate20MBtn: Button

    @BindView(R.id.allocate_all_memory)
    lateinit var mAllocateAllMemory: Button

    @BindView(R.id.memory_txt)
    lateinit var mMemoryTxt: TextView

    internal val mArrays: MutableList<ByteArray> = LinkedList()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_runtime_memory)

        ButterKnife.bind(this)
    }

    @OnClick(R.id.runtime_check)
    fun runtimeCheck() {
        val runtime = Runtime.getRuntime()
        val free = runtime.freeMemory()
        val max = runtime.maxMemory()
        val total = runtime.totalMemory()

        mMemoryTxt.text = "free: ${free}K = ${free / 1024L / 1024L}M; " +
                    "total: ${total}K = ${total / 1024L / 1024L}M;" +
                    "whole: ${max}K = ${max / 1024L / 1024L}M;"
    }

    @OnClick(R.id.allocate_all_memory)
    fun allocateAllMemory() {
        val runtime = Runtime.getRuntime()
        val remain = runtime.maxMemory() - runtime.totalMemory()

        try {
            val block = ByteArray(remain.toInt())

            mArrays.add(block)

            runtimeCheck()
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
        }
    }

    @OnClick(R.id.allocate_20_memory)
    fun allocate20Memory() {

        try {
            val twn = ByteArray((1024L * 1024L * 20).toInt())

            mArrays.add(twn)

            runtimeCheck()
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
        }
    }
}
