package dev.rifqi.downloadmanager

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat


object NotificationHelper {

    fun showProcessing(context: Context, file: DownloadedFile) {

        val builder = getBasicNotification(context, null)

        builder.setContentTitle(file.title.removePrefix((context.getString(R.string.app_name)) + ": "))
        builder.setContentText("Proses")

        show(context, file.hashCode(), builder.build())
    }

    fun showFinished(context: Context, file: DownloadedFile) {

        val builder = getBasicNotification(context, null)

        builder.setContentTitle(file.title.removePrefix((context.getString(R.string.app_name)) + ": "))
        builder.setContentText("finish")

        show(context, file.hashCode(), builder.build())
    }

    private fun show(context: Context, id: Int, notification: Notification) {
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(id, notification)
    }

    private fun getBasicNotification(context: Context, pendingIntent: PendingIntent?): NotificationCompat.Builder {

        val builder = NotificationCompat.Builder(context)
        builder.setDefaults(Notification.DEFAULT_ALL)
        builder.priority = NotificationCompat.PRIORITY_MAX
        builder.setSmallIcon(R.drawable.ic_launcher_background)
        builder.color = ContextCompat.getColor(context, R.color.colorAccent)
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        builder.setAutoCancel(true)

        pendingIntent?.let {
            builder.setContentIntent(it)
        }

        return builder
    }
}