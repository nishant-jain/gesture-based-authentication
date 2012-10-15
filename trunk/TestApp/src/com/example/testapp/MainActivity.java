package com.example.testapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

    import android.os.*;

        
    import java.io.BufferedWriter;
    import java.io.File;
    import java.io.FileWriter;
    import java.io.IOException;

    import android.hardware.Sensor;
    import android.hardware.SensorEvent;
    import android.hardware.SensorEventListener;
    import android.hardware.SensorManager;
    import android.os.Bundle;
    import android.os.Environment;
    import android.app.Activity;
    import android.util.Log;
    import android.view.Menu;
    import android.view.View;
    import android.widget.Button;

    public class MainActivity extends Activity implements SensorEventListener{
    	 private SensorManager mSensorManager;
    	    private Sensor mAcc,mGyro;
    	    //private LocationManager manager; 
    	    File gyro,acc,prox,gps;
    	    //protected File fileToWrite;
    		protected BufferedWriter gyrowriter,gpswriter,proxwriter,accwriter;
    		FileWriter fstream1,fstream2,fstream3,fstream4;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //manager=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE); 
            mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
            mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mGyro = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            //mProx = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
           
        	File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/SensorLayer/");
    		path.mkdirs();
    		gyro = new File(path.getAbsolutePath(), "gyro");
    		acc = new File(path.getAbsolutePath(), "acc");
    		//prox = new File(path.getAbsolutePath(), "prox");
    		//gps = new File(path.getAbsolutePath(), "gps11");
    		try {
    			 fstream1 = new FileWriter(gyro);
    			gyrowriter = new BufferedWriter(fstream1);
    			fstream2 = new FileWriter(acc);
    			accwriter = new BufferedWriter(fstream2);
    		/*	fstream3 = new FileWriter(prox);
    			proxwriter = new BufferedWriter(fstream3);
    			fstream4 = new FileWriter(gps);
    			gpswriter = new BufferedWriter(fstream4);*/
    			
    		} catch (IOException e) {
    			Log.e("SensorLayer:: " , e.toString());
    			
    		}
    		
    		 //mSensorManager.registerListener(this , mAcc, 100);
    	
    		 //mSensorManager.registerListener(this, mGyro, 100);
    	
    	      
        }
        
        protected void onResume() {
            super.onResume();
            mSensorManager.registerListener(this, mAcc,100);
    		 //mSensorManager.registerListener(this, mProx, 100);
    		 mSensorManager.registerListener(this, mGyro, 100);
            //manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, this);
        	//manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,100,0, this);
            
        }

        protected void onPause() {
            super.onPause();
            mSensorManager.unregisterListener(this);
         //   manager.removeUpdates(this);
            try {
    			accwriter.close();
    		//gpswriter.close();
    		//proxwriter.close();
            gyrowriter.close();
            } catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
           // Debug.stopMethodTracing();
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {
        	 Sensor sensor = event.sensor;
        	 //Log.e("change", "enter");
             if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                 //TODO: get values
            	 try {
            		 Log.i("ACC", ""+accwriter);
    				accwriter.write("\nX:"+event.values[0]+",Y:"+event.values[1]+",Z:"+event.values[2]+",timestamp:"+event.timestamp+"\n");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
             }else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                 //TODO: get values
            	 try {
    				gyrowriter.write("\nTime: " + event.timestamp +",Value:X :"+event.values[0]+",Y:"+event.values[1]+",Z:"+event.values[2]+"\n");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
             }
        }
        


   public void start(View view)
   {
	   mSensorManager.registerListener(this , mAcc, 100);
   	
		 mSensorManager.registerListener(this, mGyro, 100);
	
   }
   
   public void stop(View view)
   {
	   mSensorManager.unregisterListener(this);
   }
    	
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
