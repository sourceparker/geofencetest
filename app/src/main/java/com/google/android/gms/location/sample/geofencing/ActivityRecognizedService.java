package com.google.android.gms.location.sample.geofencing;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

/**
 * Created by Paul on 2/1/16.
 */
public class ActivityRecognizedService extends IntentService {

    private int finalActivity=-1;
    private int counter;

    public ActivityRecognizedService() {
        super("ActivityRecognizedService");
    }

    public ActivityRecognizedService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities(result.getProbableActivities());
        }
    }

    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {


            for (DetectedActivity activity : probableActivities) {
                switch (activity.getType()) {
                    case DetectedActivity.IN_VEHICLE: {
                        Log.e("ActivityRecogition", "In Vehicle: " + activity.getConfidence());
                        perceivedActivity(DetectedActivity.IN_VEHICLE);
                        break;
                    }
                    case DetectedActivity.ON_BICYCLE: {
                        Log.e("ActivityRecogition", "On Bicycle: " + activity.getConfidence());
                        perceivedActivity(DetectedActivity.ON_BICYCLE);

                        break;
                    }
                    case DetectedActivity.ON_FOOT: {
                        Log.e("ActivityRecogition", "On Foot: " + activity.getConfidence());
                        perceivedActivity(DetectedActivity.ON_FOOT);

                        //At this point, you indicate that the person has parked and decrease lot count by 1

                        break;
                    }
                    case DetectedActivity.RUNNING: {


                        //At this point, you indicate that the person has parked and decrease lot count by 1

                        Log.e("ActivityRecogition", "Running: " + activity.getConfidence());
                        perceivedActivity(DetectedActivity.RUNNING);
                        break;
                    }
                    case DetectedActivity.STILL: {


                        Log.e("ActivityRecogition", "Still: " + activity.getConfidence());
                        perceivedActivity(DetectedActivity.STILL);
                        break;
                    }
                    case DetectedActivity.TILTING: {
                        Log.e("ActivityRecogition", "Tilting: " + activity.getConfidence());
                        perceivedActivity(DetectedActivity.TILTING);
                        break;
                    }
                    case DetectedActivity.WALKING: {
                        Log.e("ActivityRecogition", "Walking: " + activity.getConfidence());

                        perceivedActivity(DetectedActivity.WALKING);
                        //At this point, you indicate that the person has parked and decrease lot count by 1



                  /*  if( activity.getConfidence() >= 75 ) {
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                        builder.setContentText( "Are you walking?" );
                     //   builder.setSmallIcon( R.mipmap.ic_launcher );
                        builder.setContentTitle( getString( R.string.app_name ) );
                        NotificationManagerCompat.from(this).notify(0, builder.build());
                    }*/
                        break;
                    }
                    case DetectedActivity.UNKNOWN: {
                        Log.e("ActivityRecogition", "Unknown: " + activity.getConfidence());
                        perceivedActivity(DetectedActivity.UNKNOWN);
                        break;
                    }

                }

        }

    }

    //this method is supposed to be run each time an activity is heard

    private void perceivedActivity(int incomingActivity) {

        //checks if old activity is equals to new one . If not old activity is written to new activity

       //21 because 60000/3000 is 20
        if(counter!=21){
        if(finalActivity!=incomingActivity){

            finalActivity=incomingActivity;

           //for now it listens to all activities
            //later it will listen to only 2
//
            }//ELSE DO NOTHING
            counter++;
        }else{

            Log.i("Final : ",Integer.toString(finalActivity));

           Toast.makeText(this,"Final  : "+Integer.toString(finalActivity),Toast.LENGTH_LONG).show();

        }



    }
}