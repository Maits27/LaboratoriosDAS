package com.example.l4_nav;
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Process

class CerrarAppReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "CerrarAppAction") {
            // Cerrar la aplicación
            Process.killProcess(Process.myPid())
        }
    }
}
