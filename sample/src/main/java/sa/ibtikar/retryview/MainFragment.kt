package sa.ibtikar.retryview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import sa.ibtikar.retryview.databinding.FragmentMainBinding
import sa.ibtikar.retryview.utils.RetryFragment


class MainFragment : RetryFragment() {

    private lateinit var binder: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binder =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binder.retryView.initWith(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callWithRetry { doSomething() }
    }

    private fun doSomething() {
        Toast.makeText(context, "Function Called", Toast.LENGTH_SHORT).show()
    }
}