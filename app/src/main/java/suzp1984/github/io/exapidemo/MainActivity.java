package suzp1984.github.io.exapidemo;

import android.content.Intent;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;
import suzp1984.github.io.exapidemo.layout.SlidingPanelLayoutTextViewSampleActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Log.e(TAG, "density: " + this.getResources().getDisplayMetrics().density
                + ", dpi density: " + this.getResources().getDisplayMetrics().densityDpi
                + ", width Pixels: " + this.getResources().getDisplayMetrics().widthPixels
                + ", height Pixels: " + this.getResources().getDisplayMetrics().heightPixels
                + ", xdpi: " + this.getResources().getDisplayMetrics().xdpi
                + ", ydpi: " + this.getResources().getDisplayMetrics().ydpi);

    }

    public void onDestroy() {

        super.onDestroy();
    }

    @OnClick(R.id.open_sliding_panel)
    public void openSlidingPanel() {
        Intent intent = new Intent(this, SlidingPanelLayoutTextViewSampleActivity.class);

        startActivity(intent);
    }

}
