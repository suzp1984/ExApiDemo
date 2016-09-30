package suzp1984.github.io.exapidemo.app.fragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.LinkedList;
import java.util.List;

import suzp1984.github.io.exapidemo.R;

/**
 * Created by suzhenxi on 9/30/2016.
 */

public class NumFragment5Adapter extends NumFragmentBaseAdapter {

    private final String TAG = NumFragment5Adapter.class.getName();

    private final FragmentManager mFragmentManager;
    private final int COUNT = 100;
    private final List<NumberFragment> mNumFragments = new LinkedList<>();
    private final List<NumberFragment> mRemoveFragments = new LinkedList<>();

    private int mViewHolderCount = 0;

    public NumFragment5Adapter(FragmentManager manager) {
        mFragmentManager = manager;

        for (int i = 0; i < COUNT; i++) {
            mNumFragments.add(NumberFragment.newInstance(String.valueOf(i)));
        }
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

        // innerContainer.setMinimumHeight(100);
        innerContainer.setId(View.generateViewId());

        rootContainer.addView(innerContainer);

        notifyViewHolderCreate(viewType);
        notifyViewHolderCountChanged(mViewHolderCount);

        return new NumFragmentBaseAdapter.NumberViewHolder(view, innerContainer);
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        NumberFragment numberFragment = mNumFragments.get(position);
        holder.itemView.setTag(numberFragment);

        Log.e(TAG, "fragment: #" + numberFragment.getNumber() + " isAdded " + numberFragment.isAdded());
        Log.e(TAG, "fragment: #" + numberFragment.getNumber() + " isDetached " + numberFragment.isDetached());
        Log.e(TAG, "fragment #" + numberFragment.getNumber() + " isInLayout " + numberFragment.isInLayout());

        if (!numberFragment.isAdded()) {
            mFragmentManager.beginTransaction()
                            .replace(holder.fragmentContainer.getId(), numberFragment)
                            .commit();
        }

        if (mFragmentManager.getFragments() == null) {
            notifyAttachedFragmentChanged(0);
        } else {
            notifyAttachedFragmentChanged(mFragmentManager.getFragments().size());
        }
    }

    @Override
    public int getItemCount() {
        return mNumFragments.size();
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

            if (fragment.isAdded()) {
                mFragmentManager.beginTransaction().remove(fragment).commit();
            }
        }
    }

    public void resetAdapter(int size) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        for(NumberFragment fragment : mNumFragments) {
            if (fragment.isAdded()) {
                transaction.remove(fragment);
            }
        }

        transaction.commit();

        // mRemoveFragments.addAll(mNumFragments);

        mNumFragments.clear();

        notifyDataSetChanged();
        mViewHolderCount = 0;

        for (int i = 0; i < size; i++) {
            mNumFragments.add(NumberFragment.newInstance("new " + size + " - " + i));
        }

        notifyDataSetChanged();
    }

    public void checkFragments() {
//        mFragmentManager.popBackStack();
//        FragmentTransaction transaction = mFragmentManager.beginTransaction();
//        for (NumberFragment fragment : mRemoveFragments) {
//            Log.e(TAG, "&&& #" + fragment.getNumber() + " isAdded " + fragment.isAdded());
//            Log.e(TAG, "&&& #" + fragment.getNumber() + " isRemoving " + fragment.isRemoving());
//            Log.e(TAG, "&&& #" + fragment.getNumber() + " isInLayout " + fragment.isInLayout());
//            Log.e(TAG, "&&& #" + fragment.getNumber() + " isVisible " + fragment.isVisible());
//
//            if (fragment.isAdded()) {
//                transaction.remove(fragment);
//            }
//        }
//        transaction.commitAllowingStateLoss();

        if (mFragmentManager.getFragments() == null) {
            notifyAttachedFragmentChanged(0);
        } else {
            notifyAttachedFragmentChanged(mFragmentManager.getFragments().size());
        }

        notifyViewHolderCountChanged(mViewHolderCount);
    }
}
