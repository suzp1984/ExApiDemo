package suzp1984.github.io.exapidemo.app.fragments;

import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import suzp1984.github.io.exapidemo.R;

/**
 * Created by suzhenxi on 10/19/2016.
 */

public class ListAdapter1 extends BaseListFragmentAdapter {

    private final String TAG = ListAdapter1.class.getName();

    private final int COUNT = 100;
    private final NumberFragment[] mNumFragments = new NumberFragment[COUNT];

    private final FragmentManager mFragmentManager;

    private int mViewCount = 0;

    public ListAdapter1(FragmentManager manager) {
        mFragmentManager = manager;

        for (int i = 0; i < COUNT; i++) {
            mNumFragments[i] = NumberFragment.newInstance(String.valueOf(i));
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public Object getItem(int position) {
        return mNumFragments[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e(TAG, "getView " + position + ": " + convertView);

        if (convertView == null) {
            convertView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.num_fragment_item, null);
            mViewCount++;
            convertView.setId(View.generateViewId());

            notifyViewCountChanged(mViewCount);

            NumberFragment fragment = mNumFragments[position];
            if (!fragment.isAdded()) {
                mFragmentManager.beginTransaction().replace(convertView.getId(), fragment).commit();
                notifyAttachedFragmentCountChanged(position + 1);
            }
        }

        return convertView;
    }

    public static class ViewHolder {

    }
}
