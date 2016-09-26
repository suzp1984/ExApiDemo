package suzp1984.github.io.exapidemo.graphics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

/**
 * Created by jacobsu on 9/26/16.
 */

public class MandelbrotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MandelbrotView mandelbrotView = new MandelbrotView(this);
        mandelbrotView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        setContentView(mandelbrotView);
    }
}
