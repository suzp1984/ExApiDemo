package suzp1984.github.io.exapidemo.app.design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import suzp1984.github.io.exapidemo.R;

public class NavgationViewActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navgation_view);

        ButterKnife.bind(this);

        mRecycler.setAdapter(new HeaderAdapter());
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

    }
}
