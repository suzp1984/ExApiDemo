package suzp1984.github.io.exapidemo.app.system;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import suzp1984.github.io.exapidemo.R;

/**
 * Created by suzhenxi on 10/26/2016.
 */

public class MemoryCheckActivity extends AppCompatActivity {

    @BindView(R.id.activity_manager_check)
    Button mActivityCheckBtn;

    @BindView(R.id.runtime_check)
    Button mRunTimeBtn;

    @BindView(R.id.memory_txt)
    TextView mMemoryTxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_memory_check);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_manager_check)
    public void onActivityManagerMemory() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        int m = manager.getMemoryClass();
        int l = manager.getLargeMemoryClass();


        mMemoryTxt.setText("memory class: " + String.valueOf(m * 1024 * 1024) + "K = " + m + "M; "
                            + "large: " + String.valueOf(l*1024*1024) + "K = " + l + "M;");
    }

    @OnClick(R.id.runtime_check)
    public void onRuntimeCheck() {
        final Runtime runtime = Runtime.getRuntime();
        long free = runtime.freeMemory();
        long max = runtime.maxMemory();
        long total = runtime.totalMemory();

        mMemoryTxt.setText("free: " + String.valueOf(free) + "K = " + free/1024L/1024L + "M; "
                            + "total: " + String.valueOf(total) + "K = " + total/1024L/1024L + "M;"
                            + "whole: " + String.valueOf(max) + "K = " + max/1024L/1024L + "M;");

    }
}
