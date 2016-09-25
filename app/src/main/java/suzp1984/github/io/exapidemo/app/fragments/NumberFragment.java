package suzp1984.github.io.exapidemo.app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import suzp1984.github.io.exapidemo.R;

public class NumberFragment extends Fragment {

    private static final String TAG = NumberFragment.class.getName();

    private static final String ARG_NUMBER = "number";

    private String mNumber;

    public NumberFragment() {
        // Required empty public constructor
    }

    public static NumberFragment newInstance(String number) {
        Log.e(TAG, "==== NumberFragment newInstance: " + number);

        NumberFragment fragment = new NumberFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNumber = getArguments().getString(ARG_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_number, container, false);

        TextView textView = (TextView) view.findViewById(R.id.number);
        textView.setText(mNumber);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public String getNumber() {
        return mNumber;
    }
}
