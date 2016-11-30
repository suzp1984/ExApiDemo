package suzp1984.github.io.exapidemo.system

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import suzp1984.github.io.exapidemo.R

/**
 * Created by suzhenxi on 11/30/2016.
 */
class SystemReportActivity : AppCompatActivity() {

    @BindView(R.id.system_report)
    lateinit var report : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_report)

        ButterKnife.bind(this)

        report.text = "java.vm.version = ${System.getProperty("java.vm.version")}"
    }
}