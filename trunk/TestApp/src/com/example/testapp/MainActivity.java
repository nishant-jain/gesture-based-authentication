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
    	    File gyro,acc,macc,tacc,authacc;//prox,gps;
    	    //protected File fileToWrite;
    		protected BufferedWriter gyrowriter,gpswriter,proxwriter,accwriter,maccwriter,taccwriter,authwriter;
    		static BufferedWriter f;
    		FileWriter fstream1,fstream2,fstream3,fstream4,fstream5;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //manager=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE); 
            mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
            mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mGyro = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            //mProx = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
           
        	File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FS/");
    		path.mkdirs();
    		gyro = new File(path.getAbsolutePath(), "gyro.txt");
    		acc = new File(path.getAbsolutePath(), "acc.txt");
    		macc = new File(path.getAbsolutePath(),"macc.txt");
    		tacc = new File(path.getAbsolutePath(),"tacc.txt");
    		authacc = new File(path.getAbsolutePath(),"authacc.txt");
    		//prox = new File(path.getAbsolutePath(), "prox");
    		//gps = new File(path.getAbsolutePath(), "gps11");
    		try {
    			 fstream1 = new FileWriter(gyro);
    			gyrowriter = new BufferedWriter(fstream1);
    			fstream2 = new FileWriter(acc);
    			accwriter = new BufferedWriter(fstream2);
    			fstream3 = new FileWriter(macc);
    			maccwriter = new BufferedWriter(fstream3);
    			fstream4 = new FileWriter(tacc);
    			taccwriter = new BufferedWriter(fstream4);
    			fstream5 = new FileWriter(authacc);
    			authwriter = new BufferedWriter(fstream5);
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
            //mSensorManager.registerListener(this, mAcc,100);
    		 //mSensorManager.registerListener(this, mProx, 100);
    		 //mSensorManager.registerListener(this, mGyro, 100);
            //manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, this);
        	//manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,100,0, this);
            
        }

        protected void onPause() {
            super.onPause();
            mSensorManager.unregisterListener(this);
         //   manager.removeUpdates(this);
            try {
    			f.close();
    		//gpswriter.close();
    		//proxwriter.close();
    			f.close();
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
    				f.write("\nX:"+event.values[0]+",Y:"+event.values[1]+",Z:"+event.values[2]+",timestamp:"+event.timestamp+"\n");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
             }else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                 //TODO: get values
            	 try {
    				f.write("\nTime: " + event.timestamp +",Value:X :"+event.values[0]+",Y:"+event.values[1]+",Z:"+event.values[2]+"\n");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
             }
        }
        

   public void master(View view){
	   f=maccwriter;
   }
   
   public void training(View view){
	   f=taccwriter;
	   
   }
       
   public void auth(View view){
	   f=authwriter;
   }
   
   public void start(View view)
   {
	   mSensorManager.registerListener(this , mAcc, 100);
   	
		 mSensorManager.registerListener(this, mGyro, 100);
			try {
				f.write("Gesture started :");
				f.write("Gesture started :");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

   }
   
   public void stop(View view)
   {    				
	   try {
	f.write("\n\n\n");

	f.write("\n\n\n");

} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	   mSensorManager.unregisterListener(this);
   }
    
   

   public double DTW(double x1[] , double y1[] ,double z1[],double x2[],double y2[],double z2[],int n,int m){    //correct this according to implementation
	  //assuming n values for 1st reading and m values for second
	   // http://en.wikipedia.org/wiki/Dynamic_time_warping
	   Double[][] DTW = new Double[n][m]; 
	   int i,j;
	   for(i=1;i<m;i++){   
	   DTW[0][i]=Double.POSITIVE_INFINITY;
	   }
	   for(i=1;i<n;i++){
		  DTW[i][0]= Double.POSITIVE_INFINITY;
	   }
	   DTW[0][0]= 0.0;
	   double cost;
	   for(i=0;i<n;i++){
		   for(j=0;j<m;j++){
			   cost=distance(x1[n],y1[n],z1[n],x2[j],y2[j],z2[j]); // we should try to optimize this. Storing it in an array instead of calculating it everytime would be a good idea.Will implement tomorrow.
			   DTW[i][j]=cost+ Math.min(Math.min(DTW[i-1][j],DTW[i][j-1]),DTW[i-1][j-1]);
		   }
	   }
	   return DTW[n][m];
   
}

public double distance(double a,double b,double c,double a1,double b1,double c1 ){
	double dis=0;
	dis= Math.sqrt(Math.pow((a-a1),2)+Math.pow((b-b1),2)+Math.pow((c-c1),2));
	return dis;
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    } 
