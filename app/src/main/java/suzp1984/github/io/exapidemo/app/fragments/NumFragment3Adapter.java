package suzp1984.github.io.exapidemo.app.fragments;

import android.database.Observable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import suzp1984.github.io.exapidemo.R;

/**
 * Created by suzhenxi on 9/26/2016.
 */

public class NumFragment3Adapter extends NumFragmentBaseAdapter {

    private final String TAG = NumFragment3Adapter.class.getName();

    private final FragmentManager mFragmentManager;
    private final int COUNT = 100;

    private int mViewHolderCount = 0;

    public NumFragment3Adapter(FragmentManager manager) {
        mFragmentManager = manager;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mViewHolderCount++;
        Log.e(TAG, "--- onCreateViewHolder: type " + viewType + ", count: " + mViewHolderCount);

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.num_fragment_item, null);

        ViewGroup rootContainer = (ViewGroup) view;

        FrameLayout innerContainer = new FrameLayout(parent.getContext());
        innerContainer.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        innerContainer.setMinimumHeight(100);
        innerContainer.setId(View.generateViewId());

        rootContainer.addView(innerContainer);
        NumberFragment fragment = new NumberFragment();
        view.setTag(fragment);

        notifyViewHolderCreate(viewType);
        notifyViewHolderCountChanged(mViewHolderCount);

        return new NumFragment3Adapter.NumberViewHolder(view, innerContainer);
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        NumberFragment numberFragment = (NumberFragment) holder.itemView.getTag();
        numberFragment.setNumber(String.valueOf(position));

        Log.e(TAG, "fragment: #" + numberFragment.getNumber() + " isAdded " + numberFragment.isAdded());
        Log.e(TAG, "fragment: #" + numberFragment.getNumber() + " isDetached " + numberFragment.isDetached());
        Log.e(TAG, "fragment #" + numberFragment.getNumber() + " isInLayout " + numberFragment.isInLayout());

        if (!numberFragment.isAdded()) {
            mFragmentManager.beginTransaction().replace(holder.fragmentContainer.getId(), numberFragment).commit();
        }

        if (mFragmentManager.getFragments() == null) {
            notifyAttachedFragmentChanged(0);
        } else {
            notifyAttachedFragmentChanged(mFragmentManager.getFragments().size());
        }
    }

    @Override
    public int getItemCount() {
        return COUNT;
    }

    @Override
    public void onViewRecycled(NumFragment3Adapter.NumberViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);

        if (viewHolder.itemView.getTag() != null &&
                viewHolder.itemView.getTag() instanceof NumberFragment) {
            NumberFragment fragment = (NumberFragment) viewHolder.itemView.getTag();
            Log.e(TAG, "&&& onViewRecycled: " + fragment.getNumber());
            Log.e(TAG, "&&& #" + fragment.getNumber() + " isAdded " + fragment.isAdded());
            Log.e(TAG, "&&& #" + fragment.getNumber() + " isDetached " + fragment.isDetached());
            Log.e(TAG, "&&& #" + fragment.getNumber() + " isInLayout " + fragment.isInLayout());
        }
    }
}
