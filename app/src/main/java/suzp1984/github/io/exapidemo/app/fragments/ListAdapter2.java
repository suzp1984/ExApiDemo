package suzp1984.github.io.exapidemo.app.fragments;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by suzhenxi on 10/19/2016.
 */

public class ListAdapter2 extends BaseListFragmentAdapter {

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

        }

        return convertView;
    }
}
