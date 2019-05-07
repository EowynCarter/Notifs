package fr.churbain.notifs

import android.app.NotificationManager

class DndHelper {


    fun applyState(state: State, mNotificationManager: NotificationManager) {

        mNotificationManager.notificationPolicy = state.policy
        mNotificationManager.setInterruptionFilter(state.filter)

    }

}
