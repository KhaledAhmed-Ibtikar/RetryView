package sa.ibtikar.retryview

/**
 * Created by Mohsen Abdelkareem on 3/8/2021.
 * Email : mohsen.abdelkareem@ibtikar.net.sa
 * Github : https://github.com/MohsenAKareem
 */
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import sa.ibtikar.retryview.databinding.RetryViewBinding
import sa.ibtikar.retryview.utils.setSafeOnClickListener


open class RetryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) :
    LinearLayout(context, attrs, defStyleAttr) {

    private val defaultLayoutId = R.layout.retry_view
    private val defaultImageSrc = R.drawable.ic_connection_error
    private val defaultMessageText = R.string.connection_error
    private val defaultMessageTextColor = R.color.blue
    private val defaultMessageTextSize = R.dimen.retry_message_text_size
    private val defaultButtonText = R.string.retry
    private val defaultButtonTextSize = R.dimen.retry_button_text_size
    private val defaultButtonTextColor = R.color.white
    private val defaultButtonBackgroundColor = R.color.blue

    private var layoutId: Int = defaultLayoutId
    private var imageSrc: Int = defaultImageSrc
    private var messageText: Int = defaultMessageText
    private var messageTextColor: Int = defaultMessageTextColor
    private var messageTextSize: Int = defaultMessageTextSize
    private var buttonText: Int = defaultButtonText
    private var buttonTextSize: Int = defaultButtonTextSize
    private var buttonTextColor: Int = defaultButtonTextColor
    private var buttonBackgroundColor: Int = defaultButtonBackgroundColor
    private var calledBlock: (() -> Any)? = null

    init {
        init(attrs)
    }


    private fun init(attrs: AttributeSet?) {

        val ta = context.obtainStyledAttributes(attrs, R.styleable.RetryView)
        try {
            layoutId = ta.getResourceId(R.styleable.RetryView_layoutId, defaultLayoutId)
            imageSrc = ta.getResourceId(R.styleable.RetryView_imageSrc, defaultImageSrc)
            messageText =
                ta.getResourceId(R.styleable.RetryView_messageText, defaultMessageText)
            messageTextColor =
                ta.getResourceId(R.styleable.RetryView_messageTextColor, defaultMessageTextColor)
            messageTextSize =
                ta.getResourceId(R.styleable.RetryView_messageTextSize, defaultMessageTextSize)
            buttonText =
                ta.getResourceId(R.styleable.RetryView_buttonText, defaultButtonText)
            buttonTextSize =
                ta.getResourceId(R.styleable.RetryView_buttonTextSize, defaultButtonTextSize)
            buttonTextColor =
                ta.getResourceId(R.styleable.RetryView_buttonTextColor, defaultButtonTextColor)
            buttonBackgroundColor =
                ta.getResourceId(
                    R.styleable.RetryView_buttonBackgroundColor,
                    defaultButtonBackgroundColor
                )

        } finally {
            ta.recycle()
        }

        setupView()
    }

    private fun setupView() {

        if (layoutId == defaultLayoutId) {
            val inflater = LayoutInflater.from(context)
            val binder = RetryViewBinding.inflate(inflater, this, true)

            with(binder) {
                ivAlert.setImageResource(imageSrc)
                tvAlert.text = context.getString(messageText)
                tvAlert.setTextColor(androidx.core.content.ContextCompat.getColor(context, messageTextColor))
                tvAlert.setTextSize(
                    android.util.TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.retry_message_text_size)
                )
                btnRetry.text = context.getString(buttonText)
                btnRetry.setTextSize(
                    android.util.TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.retry_button_text_size)
                )
                btnRetry.setTextColor(androidx.core.content.ContextCompat.getColor(context, buttonTextColor))
                btnRetry.backgroundTintList = androidx.core.content.ContextCompat.getColorStateList(context, R.color.blue)

                btnRetry.setSafeOnClickListener {
                    calledLambdaBlock?.invoke()
                }
            }

        } else {
            View.inflate(context, layoutId, this)

            findViewById<View>(R.id.btn_retry)?.setSafeOnClickListener {
                calledBlock?.invoke()
            }
        }
    }

    fun callWithRetry(block : () -> Unit) {
        calledBlock = block
    }
}
