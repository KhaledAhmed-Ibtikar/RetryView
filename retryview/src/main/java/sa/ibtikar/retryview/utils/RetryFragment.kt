package sa.ibtikar.retryview.utils

import androidx.fragment.app.Fragment

/**
 * Created by Mohsen Abdelkareem on 3/11/2021.
 * Email : mohsen.abdelkareem@ibtikar.net.sa
 * Github : https://github.com/MohsenAKareem
 */

abstract class RetryFragment : Fragment() {

    private var retryCall: (() -> Unit?)? = null

    fun getLatestCalledFunction() = retryCall

    fun callWithRetry(call: () -> Unit) {
        call()
        retryCall = call
    }
}