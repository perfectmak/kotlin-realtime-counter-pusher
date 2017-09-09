package pusher.com.clickcounter

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.gson.Gson
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    companion object {
        const val SERVER_URL = "http://NODE_JS_SERVER_ENDPOINT"
        const val PUSHER_API_KEY = "PUSHER_API_KEY"
        const val PUSHER_CLUSTER = "PUSHER_APP_CLUSTER"
        const val CLICK_CHANNEL = "click-channel"
        const val CLICK_EVENT = "click-event"
    }

    val pusherOptions = PusherOptions().setCluster(PUSHER_CLUSTER)
    val pusher = Pusher(PUSHER_API_KEY, pusherOptions)
    val httpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchCurrentClickCount()
        connectToRealtimeUpdates()

        clickButton.setOnClickListener { postClick() }
    }

    private fun fetchCurrentClickCount() {
        val getClickCountRequest = Request.Builder().url("$SERVER_URL/counts").build()

        httpClient.newCall(getClickCountRequest)
            .enqueue(object: Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    runOnUiThread {
                        showError("Network error loading current count", "Retry") {
                            fetchCurrentClickCount()
                            dismiss()
                        }
                    }
                }

                override fun onResponse(call: Call?, response: Response?) {
                    response?.body()?.also { body ->
                        val clickCount = Gson().fromJson(body.string(), ClickCount::class.java)

                        runOnUiThread { countTextView.text = clickCount.count.toString() }
                    }
                }
            })
    }

    private fun showError(message: String, action: String, callback: Snackbar.(View) -> Unit) {
        val errorSnackbar = Snackbar.make(rootLayout, message, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar.setAction(action) {
            callback(errorSnackbar, it)
        }
        errorSnackbar.show()
    }

    private fun postClick() {
        val emptyBody = RequestBody.create(null, "")
        val postClickRequest = Request.Builder().url("$SERVER_URL/clicks").post(emptyBody).build()

        httpClient.newCall(postClickRequest)
            .enqueue(object: Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    runOnUiThread {
                        showError("Network error sending click","Retry") {
                            postClick()
                            dismiss()
                        }
                    }
                }

                override fun onResponse(call: Call?, response: Response?) {
                    // do nothing
                }
            } )
    }

    private fun connectToRealtimeUpdates() {
        val pusherChannel = pusher.subscribe(CLICK_CHANNEL)
        pusherChannel.bind(CLICK_EVENT) { _, _, data ->
            val clickCount = Gson().fromJson(data, ClickCount::class.java)
            runOnUiThread { countTextView.text = clickCount.count.toString() }
        }
    }

    override fun onResume() {
        super.onResume()
        pusher.connect()
    }

    override fun onPause() {
        pusher.disconnect()
        super.onPause()
    }
}