package suzp1984.github.io.exapidemo.app.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import suzp1984.github.io.exapidemo.R

class NumberFragment : Fragment() {

    private lateinit var mNumber: String

    private lateinit var mTextView: TextView

    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mNumber = arguments.getString(ARG_NUMBER)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_number, container, false)

        mTextView = view.findViewById(R.id.number) as TextView
        mTextView.text = mNumber

        return view
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()

        mTextView.text = mNumber
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDetach() {
        super.onDetach()

    }

    var number: String
        get() = mNumber
        set(s) {
            mNumber = s

            if (isResumed) {
                mTextView.text = mNumber
            }
        }

    companion object {

        private val TAG = NumberFragment::class.java.name

        private val ARG_NUMBER = "number"

        fun newInstance(number: String): NumberFragment {
            Log.e(TAG, "==== NumberFragment newInstance: " + number)

            val fragment = NumberFragment()
            val args = Bundle()
            args.putString(ARG_NUMBER, number)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
