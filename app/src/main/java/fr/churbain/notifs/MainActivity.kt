package fr.churbain.notifs

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        val today = Calendar.getInstance()
        today.time = Date()

        val mNotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (!mNotificationManager.isNotificationPolicyAccessGranted) {
            startActivityForResult(Intent(ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS), 0)
        }

        var dndHelper = DndHelper()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val state = getState(today)

        dndHelper.applyState(state, mNotificationManager)

        val nextAlarm = getNextAlarm(today)

        val alamrState = getState(nextAlarm)

        val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alIntent = Intent()
        alIntent.putExtra("state", alamrState.name)
        val alarmIntent = PendingIntent.getBroadcast(this, 150, alIntent, PendingIntent.FLAG_CANCEL_CURRENT)
        alarmManager?.setExact(AlarmManager.RTC_WAKEUP, nextAlarm.timeInMillis, alarmIntent)

    }
}
