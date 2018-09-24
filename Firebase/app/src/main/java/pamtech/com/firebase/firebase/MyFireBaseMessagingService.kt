package pamtech.com.firebase.firebase


import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFireBaseMessagingService : FirebaseMessagingService() {
    val Tag = "FirebaseService"

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d("$Tag token", token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(Tag, remoteMessage?.from)
        Log.d(Tag, remoteMessage?.notification?.body)
    }
}