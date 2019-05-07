package fr.churbain.notifs

import android.app.NotificationManager


const val SILENCE_MODE_CATEG = NotificationManager.Policy.PRIORITY_CATEGORY_ALARMS or NotificationManager.Policy.PRIORITY_CATEGORY_MEDIA or NotificationManager.Policy.PRIORITY_CATEGORY_CALLS or NotificationManager.Policy.PRIORITY_CATEGORY_MESSAGES
var SILENCE_MODE_POLICY = NotificationManager.Policy(SILENCE_MODE_CATEG, NotificationManager.Policy.PRIORITY_SENDERS_STARRED, NotificationManager.Policy.PRIORITY_SENDERS_STARRED)

const val NIGHT_MODE_CATEG = NotificationManager.Policy.PRIORITY_CATEGORY_ALARMS or NotificationManager.Policy.PRIORITY_CATEGORY_MEDIA
var NIGHT_MODE_POLICY = NotificationManager.Policy(NIGHT_MODE_CATEG, NotificationManager.Policy.PRIORITY_SENDERS_ANY, NotificationManager.Policy.PRIORITY_SENDERS_ANY)


enum class State(val policy: NotificationManager.Policy, val filter: Int) {
    
    NIGHT(NIGHT_MODE_POLICY, NotificationManager.INTERRUPTION_FILTER_PRIORITY),
    SILENCE(SILENCE_MODE_POLICY, NotificationManager.INTERRUPTION_FILTER_PRIORITY),
    OFF(SILENCE_MODE_POLICY, NotificationManager.INTERRUPTION_FILTER_ALL),
}