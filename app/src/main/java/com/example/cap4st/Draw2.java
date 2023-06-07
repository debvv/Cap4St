package com.example.cap4st;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

public class Draw2  extends Activity {
    Point size = new Point();
    int dL, dR;
    public double dist2pp(double x1,double y1,double x2,double y2)
    {
        return Math.sqrt((x1-x2)*(x1-x2)+(y1- y2)*(y1-y2));
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        dL = 30;
        dR = 30;
        setContentView(new myview(this));
    }

    class myview extends View {
        public myview(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

        double [] xp=new double [] {2,9,10};
        double [] yp=new double [] {5,13,2};
        double d1,d2,d3,dmax;
// distance bet vertex
        d1=dist2pp(xp[0],yp[0],xp[1],yp[1]);
        d2=dist2pp(xp[1],yp[1],xp[2],yp[2]);
        d3=dist2pp(xp[2],yp[2],xp[0],yp[0]);
// the max length
        dmax=-1E-7;
        if(dmax<d1) dmax=d1;
        if(dmax<d2) dmax=d2;
        if(dmax<d3) dmax=d3;
        double minx=1E-7;
        if(minx>xp[0]) minx=xp[0];
        if(minx>xp[1]) minx=xp[1];
        if(minx>xp[2]) minx=xp[2];

        double udpx=(size.x-dL-dR)/(dmax);
        double udpy=udpx;
        double x0=-minx*udpx+dL;
        double y0=(dmax)*udpx/2+20+600;



        }
    }
}