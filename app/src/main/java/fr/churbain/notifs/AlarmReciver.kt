package fr.churbain.notifs

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.*

class AlarmReciver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        var dndHelper = DndHelper()


        val mNotificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val state : String = intent?.getStringExtra("state") ?: ""

       val stateEnum : State = State.valueOf(state)
        dndHelper.applyState(stateEnum, mNotificationManager)

        val nextAlarm = getNextAlarm(Calendar.getInstance())

        val alamrState = getState(nextAlarm)
        val alIntent = Intent()
        alIntent.putExtra("state", alamrState.name)
        val alarmIntent = PendingIntent.getBroadcast(context, 150, alIntent, PendingIntent.FLAG_CANCEL_CURRENT)
        alarmManager?.setExact(AlarmManager.RTC_WAKEUP, nextAlarm.timeInMillis, alarmIntent)
    }
}