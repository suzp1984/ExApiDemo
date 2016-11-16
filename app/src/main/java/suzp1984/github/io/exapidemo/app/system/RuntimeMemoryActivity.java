package suzp1984.github.io.exapidemo.app.system;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import suzp1984.github.io.exapidemo.R;

/**
 * Created by suzhenxi on 10/27/2016.
 */

public class RuntimeMemoryActivity extends AppCompatActivity {

    @BindView(R.id.runtime_check)
    Button mRuntimeCheckBtn;

    @BindView(R.id.allocate_20_memory)
    Button mAllocate20MBtn;

    @BindView(R.id.allocate_all_memory)
    Button mAllocateAllMemory;

    @BindView(R.id.memory_txt)
    TextView mMemoryTxt;

    final List<byte[]> mArrays = new LinkedList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_runtime_memory);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.runtime_check)
    public void runtimeCheck() {
        final Runtime runtime = Runtime.getRuntime();
        long free = runtime.freeMemory();
        long max = runtime.maxMemory();
        long total = runtime.totalMemory();

        mMemoryTxt.setText("free: " + String.valueOf(free) + "K = " + free/1024L/1024L + "M; "
                + "total: " + String.valueOf(total) + "K = " + total/1024L/1024L + "M;"
                + "whole: " + String.valueOf(max) + "K = " + max/1024L/1024L + "M;");
    }

    @OnClick(R.id.allocate_all_memory)
    public void allocateAllMemory() {
        final Runtime runtime = Runtime.getRuntime();
        long remain = runtime.maxMemory() - runtime.totalMemory();

        byte[] block = new byte[(int)remain];

        mArrays.add(block);

        runtimeCheck();
    }

    @OnClick(R.id.allocate_20_memory)
    public void allocate20Memory() {

        byte[] twn = new byte[(int) (1024L*1024L*20)];

        mArrays.add(twn);

        runtimeCheck();
    }
}
