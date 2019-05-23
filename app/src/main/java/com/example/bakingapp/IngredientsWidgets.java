package com.example.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.List;

import static com.example.bakingapp.MainActivity.ing;

public class IngredientsWidgets extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ing_widget);

            if(ing!=null)
            views.setTextViewText(R.id.widgetid, ing.substring(4));

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.widgetid, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);


        }
    }

}
