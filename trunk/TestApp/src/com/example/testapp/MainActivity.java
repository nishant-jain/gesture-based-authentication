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
import android.widget.Toast;

    public class MainActivity extends Activity implements SensorEventListener{
    	 private SensorManager mSensorManager;
    	    private Sensor mAcc,mGyro;
    	    //private LocationManager manager; 
    	    File gyro,acc,macc,tacc1,tacc2,authacc,mgyro,tgyro1,tgyro2,authgyro;//prox,gps;
    	    //protected File fileToWrite;
    		protected BufferedWriter gyrowriter,gpswriter,proxwriter,accwriter,maccwriter,tacc1writer,tacc2writer,authwriter,mgyrowriter,tgyro1writer,tgyro2writer,gauthwriter;
    		static BufferedWriter f,g;
    		FileWriter fstream1,fstream2,fstream3,fstream4,fstream5,fstream6,fstream7,fstream8,fstream9,fstream10;
    		FileReader rstream1,rstream2,rstream3;
    		protected BufferedReader maccreader,tacc1reader,tacc2reader,authreader,gauthreader,mgyroreader,tgyro1reader,tgyro2reader;
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
    		tacc1 = new File(path.getAbsolutePath(),"tacc1.txt");
    		tacc2 = new File(path.getAbsolutePath(),"tacc2.txt");
    		authacc = new File(path.getAbsolutePath(),"authacc.txt");
    		mgyro = new File(path.getAbsolutePath(),"mgyro.txt");
    		tgyro1 = new File(path.getAbsolutePath(),"tgyro1.txt");
    		tgyro2 = new File(path.getAbsolutePath(),"tgyro2.txt");
    		authgyro = new File(path.getAbsolutePath(),"authgyro.txt");
    		//prox = new File(path.getAbsolutePath(), "prox");
    		//gps = new File(path.getAbsolutePath(), "gps11");
    		try {
    			 fstream1 = new FileWriter(gyro);
    			gyrowriter = new BufferedWriter(fstream1);
    			fstream2 = new FileWriter(acc, false);
    			accwriter = new BufferedWriter(fstream2);
    			fstream3 = new FileWriter(macc,false);
    			maccwriter = new BufferedWriter(fstream3);
    			fstream4 = new FileWriter(tacc1,false);
    			tacc1writer = new BufferedWriter(fstream4);
    			
    			fstream6 = new FileWriter(tacc2,false);
    			tacc2writer = new BufferedWriter(fstream6);
    			
    			fstream7 = new FileWriter(mgyro,false);
    			mgyrowriter = new BufferedWriter(fstream3);
    			fstream8 = new FileWriter(tgyro1,false);
    			tgyro1writer = new BufferedWriter(fstream4);
    			
    			fstream9 = new FileWriter(tgyro2,false);
    			tgyro2writer = new BufferedWriter(fstream6);
    			
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
    			//f.close();
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
    				g.write("\n"+event.values[0]+",Y:"+event.values[1]+",Z:"+event.values[2]);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
             }
        }
        

   public void master(View view){
	  
	   f=maccwriter;
	   g=gyrowriter;
	   System.out.println("master"+f);
   }
   
   public void training1(View view) throws IOException{
	   f.close();
	   g.close();
	   f=tacc1writer;
	   g=tgyro1writer;
	   System.out.println("training1"+f);
   }
   public void training2(View view) throws IOException{
	   f.close();
	   g.close();
	   f=tacc2writer;
	   g=tgyro2writer;
	   System.out.println("training2"+f);
   }
       
   public void auth(View view) throws IOException{
	   f.close();
	   g.close();
	   fstream5 = new FileWriter(authacc,false);
		authwriter = new BufferedWriter(fstream5);
	   f=authwriter;
	   fstream5 = new FileWriter(authgyro,false);
		gauthwriter = new BufferedWriter(fstream10);
	   g=gauthwriter;
	   System.out.println("auth"+f);
   }
   
   public void start(View view)
   {	System.out.println("Start");
	   mSensorManager.registerListener(this , mAcc, 100);
   	
		 mSensorManager.registerListener(this, mGyro, 100);
			try {
				f.write("Gesture started :");
				g.write("Gesture started :");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

   }
   
   public void stop(View view)
   {    		mSensorManager.unregisterListener(this);
   System.out.println("stop");		
	   try {
	f.write("\n\n\n");
	//f.close();
	g.write("\n\n\n");
	if(f.equals(authwriter)){
		
		f.close();
		g.close();
		double a=read();
		double b=compare();
		double d=gcompare();
		System.out.println("Threshold :"+b); 
		if(a>b)
			Toast.makeText(getApplicationContext(),"Threshold : "+a+" Current "+b+" Accepted",Toast.LENGTH_LONG).show();
		else
			Toast.makeText(getApplicationContext(),"Threshold : "+a+" Current "+b+" Not Accepted",Toast.LENGTH_LONG).show();
	}

} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	   
   }
   
   public double compare() throws NumberFormatException, IOException{
	   File path3 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FS/authacc.txt"); 
	   authreader = new BufferedReader(new FileReader(path3));
	   String line;
	   File path1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FS/macc.txt");
	   maccreader = new BufferedReader(new FileReader(path1));
	   	  ArrayList<Double> x=new ArrayList<Double>();
		  ArrayList<Double> y=new ArrayList<Double>();
		  ArrayList<Double> z=new ArrayList<Double>();
		  ArrayList<Double> x1=new ArrayList<Double>();
		  ArrayList<Double> y1=new ArrayList<Double>();
		  ArrayList<Double> z1=new ArrayList<Double>();
		   while ((line = authreader.readLine()) != null) {  
	            String[] k=line.split(",");
	            if(k.length>2){
	            x1.add(Double.parseDouble(k[0]));
	            	//count++;
	            y1.add(Double.parseDouble(k[1]));
	            z1.add(Double.parseDouble(k[2]));	
	            }
	       }
		   while ((line = maccreader.readLine()) != null) {  
	            String[] k=line.split(",");
	            if(k.length>2){
	            x.add(Double.parseDouble(k[0]));
	            	//count++;
	            y.add(Double.parseDouble(k[1]));
	            z.add(Double.parseDouble(k[2]));	
	            }
	       }
		   double [] mx=new double[x.size()];
		   double [] my=new double[y.size()];
		   double [] mz=new double[z.size()];
		   double [] tx1=new double[x1.size()];
		   double [] ty1=new double[y1.size()];
		   double [] tz1=new double[z1.size()];
		   for(int i=0;i<x.size();i++){
			   mx[i]=x.get(i);
			   my[i]=y.get(i);
			   mz[i]=z.get(i);
		   }
		   System.out.println("Checkpoint reached 4");
		   for(int i=0;i<x1.size();i++){
			   tx1[i]=x1.get(i);
			   ty1[i]=y1.get(i);
			   tz1[i]=z1.get(i);
		   }
		   System.out.println("Checkpoint reached 5");
	
		   double a=DTW(mx,my,mz,tx1,ty1,tz1,mx.length,tx1.length); 
		   return a;
   }
   
   public double gcompare() throws NumberFormatException, IOException{
	   File path3 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FS/authgyro.txt"); 
	   gauthreader = new BufferedReader(new FileReader(path3));
	   String line;
	   File path1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FS/mgyro.txt");
	   mgyroreader = new BufferedReader(new FileReader(path1));
	   	  ArrayList<Double> x=new ArrayList<Double>();
		  ArrayList<Double> y=new ArrayList<Double>();
		  ArrayList<Double> z=new ArrayList<Double>();
		  ArrayList<Double> x1=new ArrayList<Double>();
		  ArrayList<Double> y1=new ArrayList<Double>();
		  ArrayList<Double> z1=new ArrayList<Double>();
		   while ((line = gauthreader.readLine()) != null) {  
	            String[] k=line.split(",");
	            if(k.length>2){
	            x1.add(Double.parseDouble(k[0]));
	            	//count++;
	            y1.add(Double.parseDouble(k[1]));
	            z1.add(Double.parseDouble(k[2]));	
	            }
	       }
		   while ((line = mgyroreader.readLine()) != null) {  
	            String[] k=line.split(",");
	            if(k.length>2){
	            x.add(Double.parseDouble(k[0]));
	            	//count++;
	            y.add(Double.parseDouble(k[1]));
	            z.add(Double.parseDouble(k[2]));	
	            }
	       }
		   double [] mx=new double[x.size()];
		   double [] my=new double[y.size()];
		   double [] mz=new double[z.size()];
		   double [] tx1=new double[x1.size()];
		   double [] ty1=new double[y1.size()];
		   double [] tz1=new double[z1.size()];
		   for(int i=0;i<x.size();i++){
			   mx[i]=x.get(i);
			   my[i]=y.get(i);
			   mz[i]=z.get(i);
		   }
		   System.out.println("Checkpoint reached 6");
		   for(int i=0;i<x1.size();i++){
			   tx1[i]=x1.get(i);
			   ty1[i]=y1.get(i);
			   tz1[i]=z1.get(i);
		   }
		   System.out.println("Checkpoint reached 7");
	
		   double a=DTW(mx,my,mz,tx1,ty1,tz1,mx.length,tx1.length); 
		   return a;
   }
   
   public double read() throws IOException{
	   File path1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FS/macc.txt");
	   maccreader = new BufferedReader(new FileReader(path1));
	   String line;
	//   int count=0,count1=0,count2=0;
	   int files=0;
	   double threshold,a,b;
	   File path2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FS/tacc1.txt");
	   tacc1reader = new BufferedReader(new FileReader(path2));
	   File path4 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FS/tacc2.txt");
	   tacc2reader = new BufferedReader(new FileReader(path4));
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
            System.out.println(Double.parseDouble(k[0])+","+Double.parseDouble(k[1])+","+Double.parseDouble(k[2]));
            }
       }
	   while ((line = tacc1reader.readLine()) != null) {  
           String[] k=line.split(",");
           if(k.length>2)
        	
           {x1.add(Double.parseDouble(k[0]));
           //	count1++;
           y1.add(Double.parseDouble(k[1]));
           z1.add(Double.parseDouble(k[2]));
           System.out.println(Double.parseDouble(k[0])+","+Double.parseDouble(k[1])+","+Double.parseDouble(k[2]));}
        	
      }
	   while ((line = tacc2reader.readLine()) != null) {  
           String[] k=line.split(",");
           if(k.length>2)
	   {	
    		x2.add(Double.parseDouble(k[0]));
           	//count2++;
           y2.add(Double.parseDouble(k[1]));
           z2.add(Double.parseDouble(k[2]));
           System.out.println(Double.parseDouble(k[0])+","+Double.parseDouble(k[1])+","+Double.parseDouble(k[2]));
    	}
	   }
	   double [] mx=new double[x.size()];
	   double [] my=new double[y.size()];
	   double [] mz=new double[z.size()];
	   double [] tx1=new double[x1.size()];
	   double [] ty1=new double[y1.size()];
	   double [] tz1=new double[z1.size()];
	   double [] tx2=new double[x2.size()];
	   double [] ty2=new double[y2.size()];
	   double [] tz2=new double[z2.size()];
	   //double [] t2=new double[count2];*/
	   
	   for(int i=0;i<x.size();i++){
		   mx[i]=x.get(i);
		   my[i]=y.get(i);
		   mz[i]=z.get(i);
	   }
	   System.out.println("Checkpoint reached 1");
	   System.out.println(mx.length+","+x.size());
	   for(int i=0;i<x1.size();i++){
		   tx1[i]=x1.get(i);
		   ty1[i]=y1.get(i);
		   tz1[i]=z1.get(i);
	   }
	   System.out.println("Checkpoint reached 2");
	   System.out.println(tx1.length+","+x1.size());
	   for(int i=0;i<x2.size();i++){
		   tx2[i]=x2.get(i);
		   ty2[i]=y2.get(i);
		   tz2[i]=z2.get(i);
	   }
	   System.out.println("Checkpoint reached 3");
	   System.out.println(tx2.length+","+x2.size());
	   a=DTW(mx,my,mz,tx1,ty1,tz1,mx.length,tx1.length);
	   System.out.println("DTW a="+a);
	   b=DTW(mx,my,mz,tx2,ty2,tz2,mx.length,tx2.length);
	   System.out.println("DTW a="+b);
	   if(a>b)
		   threshold=a;
	   else
		   threshold=b;
   return threshold;
   }

   public double gread() throws IOException{
	   File path1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FS/mgyro.txt");
	   mgyroreader = new BufferedReader(new FileReader(path1));
	   String line;
	//   int count=0,count1=0,count2=0;
	   int files=0;
	   double threshold,a,b;
	   File path2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FS/tgyro1.txt");
	   tgyro1reader = new BufferedReader(new FileReader(path2));
	   File path4 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FS/tgyro2.txt");
	   tgyro2reader = new BufferedReader(new FileReader(path4));
	  ArrayList<Double> x=new ArrayList<Double>();
	  ArrayList<Double> y=new ArrayList<Double>();
	  ArrayList<Double> z=new ArrayList<Double>();
	  ArrayList<Double> x1=new ArrayList<Double>();
	  ArrayList<Double> y1=new ArrayList<Double>();
	  ArrayList<Double> z1=new ArrayList<Double>();
	  ArrayList<Double> x2=new ArrayList<Double>();
	  ArrayList<Double> y2=new ArrayList<Double>();
	  ArrayList<Double> z2=new ArrayList<Double>();
	   while ((line = mgyroreader.readLine()) != null) {  
            String[] k=line.split(",");
            if(k.length>2){
            x.add(Double.parseDouble(k[0]));
            	//count++;
            y.add(Double.parseDouble(k[1]));
            z.add(Double.parseDouble(k[2]));
            System.out.println(Double.parseDouble(k[0])+","+Double.parseDouble(k[1])+","+Double.parseDouble(k[2]));
            }
       }
	   while ((line = tgyro1reader.readLine()) != null) {  
           String[] k=line.split(",");
           if(k.length>2)
        	
           {x1.add(Double.parseDouble(k[0]));
           //	count1++;
           y1.add(Double.parseDouble(k[1]));
           z1.add(Double.parseDouble(k[2]));
           System.out.println(Double.parseDouble(k[0])+","+Double.parseDouble(k[1])+","+Double.parseDouble(k[2]));}
        	
      }
	   while ((line = tgyro2reader.readLine()) != null) {  
           String[] k=line.split(",");
           if(k.length>2)
	   {	
    		x2.add(Double.parseDouble(k[0]));
           	//count2++;
           y2.add(Double.parseDouble(k[1]));
           z2.add(Double.parseDouble(k[2]));
           System.out.println(Double.parseDouble(k[0])+","+Double.parseDouble(k[1])+","+Double.parseDouble(k[2]));
    	}
	   }
	   double [] mx=new double[x.size()];
	   double [] my=new double[y.size()];
	   double [] mz=new double[z.size()];
	   double [] tx1=new double[x1.size()];
	   double [] ty1=new double[y1.size()];
	   double [] tz1=new double[z1.size()];
	   double [] tx2=new double[x2.size()];
	   double [] ty2=new double[y2.size()];
	   double [] tz2=new double[z2.size()];
	   //double [] t2=new double[count2];*/
	   
	   for(int i=0;i<x.size();i++){
		   mx[i]=x.get(i);
		   my[i]=y.get(i);
		   mz[i]=z.get(i);
	   }
	   System.out.println("Checkpoint reached 1");
	   System.out.println(mx.length+","+x.size());
	   for(int i=0;i<x1.size();i++){
		   tx1[i]=x1.get(i);
		   ty1[i]=y1.get(i);
		   tz1[i]=z1.get(i);
	   }
	   System.out.println("Checkpoint reached 2");
	   System.out.println(tx1.length+","+x1.size());
	   for(int i=0;i<x2.size();i++){
		   tx2[i]=x2.get(i);
		   ty2[i]=y2.get(i);
		   tz2[i]=z2.get(i);
	   }
	   System.out.println("Checkpoint reached 3");
	   System.out.println(tx2.length+","+x2.size());
	   a=DTW(mx,my,mz,tx1,ty1,tz1,mx.length,tx1.length);
	   System.out.println("DTW a="+a);
	   b=DTW(mx,my,mz,tx2,ty2,tz2,mx.length,tx2.length);
	   System.out.println("DTW a="+b);
	   if(a>b)
		   threshold=a;
	   else
		   threshold=b;
   return threshold;
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
	   System.out.println("Checkpoint DTW reached");
	   double cost;
	   for(i=1;i<n;i++){
		   for(j=1;j<m;j++){
			   cost=distance(x1[i],y1[i],z1[i],x2[j],y2[j],z2[j]); // we should try to optimize this. Storing it in an array instead of calculating it everytime would be a good idea.Will implement tomorrow.
			   DTW[i][j]=cost+ Math.min(Math.min(DTW[i-1][j],DTW[i][j-1]),DTW[i-1][j-1]);
		   }
	   }
	   return DTW[n-1][m-1];
   
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
