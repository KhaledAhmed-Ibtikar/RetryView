package sa.ibtikar.retryview

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import sa.ibtikar.retryview.utils.RetryActivity

class MainActivity : RetryActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

//        callWithRetry { doSomething() }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment()).commit()

    }

    private fun doSomething() {
        Toast.makeText(this, "Function Called From Activity", Toast.LENGTH_SHORT).show()
    }
}