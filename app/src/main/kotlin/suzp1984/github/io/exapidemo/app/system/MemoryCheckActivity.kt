package suzp1984.github.io.exapidemo.app.system

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import suzp1984.github.io.exapidemo.R

/**
 * Created by moses on 10/26/2016.
 */

class MemoryCheckActivity : AppCompatActivity() {

    @BindView(R.id.activity_manager_check)
    lateinit var mActivityCheckBtn: Button

    @BindView(R.id.runtime_check)
    lateinit var mRunTimeBtn: Button

    @BindView(R.id.memory_txt)
    lateinit var mMemoryTxt: TextView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_memory_check)

        ButterKnife.bind(this)
    }

    @OnClick(R.id.activity_manager_check)
    fun onActivityManagerMemory() {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        val m = manager.memoryClass
        val l = manager.largeMemoryClass

        mMemoryTxt.text = "memory class: ${m * 1024 * 1024} K = ${m}M; " +
                "large: ${l * 1024 * 1024} K = ${l}M;"
    }

    @OnClick(R.id.runtime_check)
    fun onRuntimeCheck() {
        val runtime = Runtime.getRuntime()
        val free = runtime.freeMemory()
        val max = runtime.maxMemory()
        val total = runtime.totalMemory()

        mMemoryTxt.text = "free: ${free}K = ${free / 1024L / 1024L}M; " +
                "total: ${total}K = ${total / 1024L / 1024L}M;" +
                "whole: ${max} K = ${max / 1024L / 1024L}M;"

    }
}
