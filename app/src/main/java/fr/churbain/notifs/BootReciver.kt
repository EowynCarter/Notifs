package fr.churbain.notifs

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.*

class BootReciver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        var dndHelper = DndHelper()

        Log.e("startup", "startup")

        val today = Calendar.getInstance()

        val mNotificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val stateEnum = getState(today)
        Log.e("startup", "setting state " + stateEnum.name)

        dndHelper.applyState(stateEnum, mNotificationManager)

        val nextAlarm = getNextAlarm(today)

        val alamrState = getState(nextAlarm)
        val alIntent = Intent()
        Log.e("startup", "next alarm with state " + alamrState.name + " at " + nextAlarm.toString())

        alIntent.putExtra("state", alamrState.name)
        val alarmIntent = PendingIntent.getBroadcast(context, 150, alIntent, PendingIntent.FLAG_CANCEL_CURRENT)
        alarmManager?.setExact(AlarmManager.RTC_WAKEUP, nextAlarm.timeInMillis, alarmIntent)
    }
}