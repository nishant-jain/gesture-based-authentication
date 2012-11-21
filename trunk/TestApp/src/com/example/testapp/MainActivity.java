package com.example.testapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

    import android.os.*;

        
import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
import java.util.ArrayList;

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
    		FileReader rstream1,rstream2,rstream3;
    		protected BufferedReader maccreader,taccreader,authreader;
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
            		 Log.i("ACC", ""+f);
    				f.write("\n"+event.values[0]+","+event.values[1]+","+event.values[2]);
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
        

   public void master(View view){
	  
	   f=maccwriter;
	   System.out.println("master"+f);
   }
   
   public void training(View view) throws IOException{
	   f.close();
	   f=taccwriter;
	   System.out.println("training"+f);
   }
       
   public void auth(View view) throws IOException{
	   f.close();
	   f=authwriter;
	   System.out.println("auth"+f);
   }
   
   public void start(View view)
   {	System.out.println("Start");
	   mSensorManager.registerListener(this , mAcc, 100);
   	
		 mSensorManager.registerListener(this, mGyro, 100);
			try {
				f.write("Gesture started :");
				gyrowriter.write("Gesture started :");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

   }
   
   public void stop(View view)
   {    		System.out.println("stop");		
	   try {
	f.write("\n\n\n");
	//f.close();
	gyrowriter.write("\n\n\n");

} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	   mSensorManager.unregisterListener(this);
   }
    
   public void read(String s) throws IOException{
	   File path1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FS/macc.txt");
	   maccreader = new BufferedReader(new FileReader(path1));
	   String line;
	//   int count=0,count1=0,count2=0;
	   int files=0;
	   double threshold,a,b;
	   File path2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FS/tacc.txt");
	   taccreader = new BufferedReader(new FileReader(path2));
	  ArrayList<Double> x=new ArrayList<Double>();
	  ArrayList<Double> y=new ArrayList<Double>();
	  ArrayList<Double> z=new ArrayList<Double>();
	  ArrayList<Double> x1=new ArrayList<Double>();
	  ArrayList<Double> y1=new ArrayList<Double>();
	  ArrayList<Double> z1=new ArrayList<Double>();
	  ArrayList<Double> x2=new ArrayList<Double>();
	  ArrayList<Double> y2=new ArrayList<Double>();
	  ArrayList<Double> z2=new ArrayList<Double>();
	   while ((line = maccreader.readLine()) != null) {  
            String[] k=line.split(",");
            if(k.length>2){
            x.add(Double.parseDouble(k[0]));
            	//count++;
            y.add(Double.parseDouble(k[1]));
            z.add(Double.parseDouble(k[2]));	
            }
       }
	   while ((line = taccreader.readLine()) != null) {  
           String[] k=line.split(",");
           if(k.length>2){
        	if(files==0)   
           {x1.add(Double.parseDouble(k[0]));
           //	count1++;
           y1.add(Double.parseDouble(k[1]));
           z1.add(Double.parseDouble(k[2]));}
        	else
        	{	
        		x2.add(Double.parseDouble(k[0]));
               	//count2++;
               y2.add(Double.parseDouble(k[1]));
               z2.add(Double.parseDouble(k[2]));
        	}
           }
           else
        	   files++;
      }
	  /* double [] mx=new double[count];
	   double [] my=new double[count];
	   double [] mz=new double[count];
	   double [] tx1=new double[count1];
	   double [] tx2=new double[count1];
	   double [] tx3=new double[count1];
	   double [] t2=new double[count2];*/
	   Double[] mx=(Double[]) x.toArray();
	   Double[] my=(Double[]) y.toArray();
	   Double[] mz=(Double[]) z.toArray();
	   Double[] tx1=(Double[]) x1.toArray();
	   Double[] ty1=(Double[]) y1.toArray();
	   Double[] tz1=(Double[]) z1.toArray();
	   Double[] tx2=(Double[]) x2.toArray();
	   Double[] ty2=(Double[]) y2.toArray();
	   Double[] tz2=(Double[]) z2.toArray();
	   a=DTW(mx,my,mz,tx1,ty1,tz1,mx.length,tx1.length);
	   b=DTW(mx,my,mz,tx2,ty2,tz2,mx.length,tx2.length);
	   if(a>b)
		   threshold=a;
	   else
		   threshold=b;
   
   }
   

   public double DTW(Double x1[] , Double y1[] ,Double z1[],Double x2[],Double y2[],Double z2[],int n,int m){    //correct this according to implementation
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
