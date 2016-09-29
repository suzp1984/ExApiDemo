package suzp1984.github.io.exapidemo.graphics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

/**
 * Created by jacobsu on 9/29/16.
 */

public class ManuelKastenViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ManuelKastenView view = new ManuelKastenView(this);
        view.setLayoutParams(new ViewGroup.LayoutParams(1024, 1024));

        setContentView(view);
    }
}
