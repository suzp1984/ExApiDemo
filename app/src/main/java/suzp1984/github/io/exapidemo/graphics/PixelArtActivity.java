package suzp1984.github.io.exapidemo.graphics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import suzp1984.github.io.exapidemo.R;

public class PixelArtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_pixel_art);
        PixelArtView pixel = new PixelArtView(this);
        pixel.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        setContentView(pixel);
    }
}
