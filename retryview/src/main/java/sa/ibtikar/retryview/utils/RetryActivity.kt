package sa.ibtikar.retryview.utils

import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Mohsen Abdelkareem on 3/11/2021.
 * Email : mohsen.abdelkareem@ibtikar.net.sa
 * Github : https://github.com/MohsenAKareem
 */

abstract class RetryActivity : AppCompatActivity() {

    var retryCall: (() -> Unit?)? = null

    fun getLatestCalledFunction() = retryCall

    fun callWithRetry(call: () -> Unit) {
        call()
        retryCall = call
    }
}