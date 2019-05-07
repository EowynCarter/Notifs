package fr.churbain.notifs

import java.util.*

    fun getState(date: Calendar): State {

        val day = date.get(Calendar.DAY_OF_WEEK)
        val hour = date.get(Calendar.HOUR_OF_DAY)

        if (isNight(hour)) {
            return State.NIGHT
        }

        if (isWorkTime(day, hour)) {
            return State.SILENCE
        }


        return State.OFF

    }

    private fun isNight(hour: Int): Boolean {
        return hour >= 22 || hour <=7
    }

    private fun isMorning(hour: Int): Boolean {
        return hour == 8
    }


    private fun isEvening(hour: Int): Boolean {
        return hour in 18..22
    }


    private fun isWorkTime(day: Int, hour: Int): Boolean {
        return (day != 1 && day != 7) && hour >= 9 && hour <= 18

    }

    fun getNextAlarm(date: Calendar): Calendar {


        val retour : Calendar = Calendar.getInstance()
        retour.time = date.time

        val day = date.get(Calendar.DAY_OF_WEEK)
        val hour = date.get(Calendar.HOUR_OF_DAY)

        if (isMorning(hour)) {
            retour.set(Calendar.HOUR_OF_DAY, 9)
            retour.set(Calendar.MINUTE, 0)
            return retour
        }

        if (isWorkTime(day, hour)) {
            retour.set(Calendar.HOUR_OF_DAY, 18)
            retour.set(Calendar.MINUTE, 0)
            return retour
        }

        if (isEvening(hour)) {
            retour.set(Calendar.HOUR_OF_DAY, 22)
            retour.set(Calendar.MINUTE, 0)
            return retour
        }

        if (isNight(hour)) {
            if (hour in 22..23) {
                retour.add(Calendar.DAY_OF_YEAR, 1)
            }

            retour.set(Calendar.HOUR_OF_DAY, 8)
            retour.set(Calendar.MINUTE, 0)
            return retour
        }

        retour.set(Calendar.HOUR_OF_DAY, 22)
        retour.set(Calendar.MINUTE, 0)
        return retour

}