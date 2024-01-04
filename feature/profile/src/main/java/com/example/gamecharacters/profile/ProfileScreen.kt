package com.example.gamecharacters.profile

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamecharacters.ui.ThronesTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.perf.FirebasePerformance

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var displayName by remember { mutableStateOf("Your Name") }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ProfileContent(
            name = displayName
        ) { newName ->
            displayName = newName
            sendNotification(context, newName)
        }
    }
}

@Composable
fun ProfileContent(
    name: String,
    modifier: Modifier = Modifier,
    onSaveClicked: (String) -> Unit
) {
    val context = LocalContext.current
    var enteredName by remember { mutableStateOf(name) }
    FirebaseAnalytics.getInstance(context).logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
        param(FirebaseAnalytics.Param.SCREEN_NAME, "ProfileScreen")
        param(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
    }
    FirebasePerformance.getInstance().newTrace("ProfileScreenTrace").start()
    FirebaseCrashlytics.getInstance().setCustomKey("ProfileScreenKey", "ProfileScreenValue")
    FCMTokenFetcher { token ->
        if (token != null) { Log.d("FCM Token", token) }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = Icons.Default.Person, contentDescription = null, modifier = Modifier.size(100.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text(name, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = enteredName,
            onValueChange = { newName ->
                enteredName = newName
            },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                FirebaseAnalytics.getInstance(context).logEvent("ProfileNameEntered") {
                    param("name", enteredName)
                }
                FirebasePerformance.getInstance().newTrace("ProfileScreenTrace").stop()
            })
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                FirebaseAnalytics.getInstance(context).logEvent("SaveButtonClicked") {
                    param("name", enteredName)
                }
                onSaveClicked(enteredName)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Icon(imageVector = Icons.Default.Done, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Save")
        }
    }
}

@Composable
private fun FCMTokenFetcher(onTokenReceived: (String?) -> Unit) {
    FirebaseMessaging.getInstance().token
        .addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {
                onTokenReceived(task.result)
            } else {
                Log.d("FCM Notify", "Fetching FCM registration token failed", task.exception)
            }
        })
}

@SuppressLint("ServiceCast")
private fun sendNotification(context: Context, name: String) {
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val notificationId = 1

    val notification = createNotification(context, name)

    notificationManager.notify(notificationId, notification)
}

private fun createNotification(context: Context, name: String): Notification {
    val channelId = "1"
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        notificationManager.createNotificationChannelIfNotExists(
            channelId = channelId,
            channelName = "Default"
        )
    }

    val notificationBuilder = Notification.Builder(context, channelId)
        .setSmallIcon(R.drawable.profile)
        .setContentTitle("Profile Updated")
        .setContentText("Hello, $name! Your profile has been updated.")
        .setAutoCancel(true)

    return notificationBuilder.build()
}

@RequiresApi(Build.VERSION_CODES.O)
fun NotificationManager.createNotificationChannelIfNotExists(
    channelId: String,
    channelName: String,
    importance: Int = NotificationManager.IMPORTANCE_DEFAULT
) {
    var channel = this.getNotificationChannel(channelId)

    if (channel == null) {
        channel = NotificationChannel(
            channelId,
            channelName,
            importance
        )
        this.createNotificationChannel(channel)
    }
}
