package suzp1984.github.io.exapidemo.app.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import suzp1984.github.io.exapidemo.R;

public class FragmentInRecyclerActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_in_recycler);

        ButterKnife.bind(this);

        mRecycler.setAdapter(new NumFragmentAdapter(getSupportFragmentManager()));
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

    }
}
