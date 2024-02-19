package com.example.l4_nav;
import android.app.Activity
import android.app.ActivityManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext


class CerrarAppReceiver : Activity() {

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cerrarApp()
    }

    private fun cerrarApp() {
        // Asegúrate de tener el permiso necesario en el manifiesto.
        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
        am?.killBackgroundProcesses(packageName)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager?.cancel(1)

        finish()
        System.exit(0)
    }
}
