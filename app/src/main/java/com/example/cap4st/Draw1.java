package com.example.cap4st;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

public class Draw1 extends Activity {
    Point size;
    double y0;
    double xc1,xc2,yc1,yc2;
    public double func(double ac,double bc,
                       double cc, double x)
    {
        double v=ac*x*x+bc*x+cc;
        return v;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new myview(this));
        Display display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        int [] widhei=new int[]{size.x,size.y};


    }
    class myview extends View {
        public myview(Context context) {
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            Paint paint = new Paint();
            Paint pline = new Paint();
            paint.setColor(Color.GREEN);

            pline.setColor(Color.BLUE);pline.setStrokeWidth(5);
            canvas.drawLine(50, 50, 200, 200, pline);
            canvas.drawCircle(200, 300, 20, paint);
            //columns
            float y[]=new float []{4,7,2,9,7,10};
            paint.setColor(Color.BLACK);
            float xLeft=300,yt=400,xRight=330,yb=400;
            for (int i=0;i<6;i++)
                canvas.drawRect(xLeft+i*40,yt-y[i]*20,xRight+i*40,yb, paint);
            paint.setTextSize(50f);
            canvas.drawText("value = 4",200, 50, paint);

            y0=size.x/2.0+20;
            float  yy0=Float.valueOf(String.valueOf(y0));
            canvas.drawLine(0, yy0, size.x, yy0, pline);

            canvas.drawLine(0, yy0*2-40, size.x, yy0*2-40, pline); //2 line


            canvas.drawLine(0, 20, size.x, 20, pline);//самая вверх

            //marcaje pe OX

            double a=-3;
            double b=8;
            int nmx=12;  //numarul puncte pe X
            double minx=-3; //valoarea minima x
            double maxx=8;
            int dL=30,dR=30;
            double udpx=(size.x-dL-dR)/(maxx-minx);// 720:12
            //size.x=720
            //udpx=(720-2*30)/(8-(-3))=60
            //unit dotpoint pe x (30 справа и слева),оставшееся поделили на 2==60
            double hx=(b-a)/(nmx-1);
            double x0=-a*udpx+dL;y0=size.x/2.+20;
            yy0=Float.valueOf(String.valueOf(y0));
            double [] xx=new double [nmx+2];
            canvas.drawLine(0, yy0, 720, yy0, pline);
            // draw vertical line markers
            for(int i=0;i<nmx;i++)
            {
                xx[i]=x0+(a+(i)*hx)*udpx;
                float xf=Float.valueOf(String.valueOf(xx[i]));
                canvas.drawLine(xf, yy0-5, xf, yy0+5, pline);
            }

            //values on OX
            paint.setTextSize(30f);
            paint.setColor(Color.BLACK);
            for(int i=0;i<nmx;i++)
            {
                String tt=String.valueOf(a+i*hx);
                float xf = Float.valueOf(String.valueOf(xx[i]));
                canvas.drawText(tt, xf-20, yy0+30,  paint);
            }



            //graph of a function

            int nvx=1000;   //change this value for multiple variants
            y0=size.x/2.+20;
            x0=-a*udpx+dL;
            double ap=1,bp=-8,cp=12;
            double udpy=udpx;
            paint.setColor(Color.RED);
            hx=(b-a)/nvx;
            for (int i = 0; i < nvx+1; i++)
            {
                double v = Double.parseDouble(String.valueOf(i));
                double xv = a + v * hx;
                float xf   =Float.parseFloat(String.valueOf(x0 +  xv* udpx));
                double fv=func(ap, bp, cp, xv);
                float yf = Float.parseFloat(String.valueOf(y0 + ( - fv) * udpy));
                if(yf<size.x+40 && yf>0)
                    canvas.drawCircle(xf, yf,7, paint);
            }

            // treugolniki

            double [] xp=new double [] {2,9,13};
            double [] yp=new double [] {5,11,2};
            //Folosirea câtorva linii de cod (denumirile variabilelor explică
            //esența valorilor maxx, minx, maxy, miny):
            double maxy,miny;
            maxx=-1E+5;
            minx=1E+5;
            maxy=-1E+5;
            miny=1E+5;

            for(int i=0;i<3;i++)
            {
                if(yp[i]<miny)miny=yp[i];
                if(yp[i]>maxy)maxy=yp[i];
                if(xp[i]<minx)minx=xp[i];
                if(xp[i]>maxx)maxx=xp[i];
            }
            //În baza acestor valori se definesc valorile udpx , udpy, y0,
            //x0 (semnificația acestor valori a fost explicată mai sus):
            udpx=(size.x-dL-dR)/(maxx-minx);
            udpy=udpx;
            y0=size.x/2+20+600;
            x0=-minx*udpx+dL;

            //construirea punctelelor
            for (int i = 0; i < 3; i++)
            {
                double xv=x0 + (xp[i])* udpx;
                float  x1    =Float.parseFloat(String.valueOf(xv));
                float  y1 =  Float.parseFloat(String.valueOf(y0 + ( - yp[i]) * udpy));
                paint.setColor(Color.RED);
                canvas.drawCircle(x1, y1,16, paint);//7 default radius
            }
            //Trasarea laturilor triunghiului (trei linii) (valorile x1, y1,
            //x2, y2, x3, y3 se preiau din xp[i],yp[i]):
            float x1,x2,x3,y1,y2,y3;
            udpy=udpx;
            double xv=x0 + (xp[0])* udpx;
            x1=Float.parseFloat(String.valueOf(xv));
            y1= Float.parseFloat(String.valueOf(y0 + ( - yp[0]) * udpy));
            xv=x0 + (xp[1])* udpx;
            x2 =Float.parseFloat(String.valueOf(xv));
            y2= Float.parseFloat(String.valueOf(y0 + (- yp[1]) * udpy));
            xv=x0 + (xp[2])* udpx;
            x3=Float.parseFloat(String.valueOf(xv));
            y3= Float.parseFloat(String.valueOf(y0 + ( - yp[2]) * udpy));
            paint.setColor(Color.MAGENTA);
            paint.setStrokeWidth(7); //additional
            canvas.drawLine(x1, y1, x2, y2, paint);
            canvas.drawLine(x2, y2, x3, y3, paint);
            canvas.drawLine(x3, y3, x1, y1, paint);

            //Se cunoaște că centrul cercului circumscris este punctul în care
            //se intersectează perpendicularele ridicate din mijlocul laturilor
            //triunghiului, în acest context se îndeplinesc următoarele:
            //a) se calculează coordonatele mijlocurilor a două laturi ale
            //triunghiului:

    //coord-s of the center treunghi
            xc1=(xp[0]+xp[1])/2;
            yc1=(yp[0]+yp[1])/2;
            xc2=(xp[2]+xp[1])/2;
            yc2=(yp[2]+yp[1])/2;
            //se calculează coeficienții unghiulari a două drepte
            //perpendiculare pe două laturi ale triunghiului (de exemplu, k1
            //- coeficientul unghiular al dreptei perpendiculare pe dreapta
            //care trece prin vârful xp[0],yp[0],și xp[1],yp[1]):
            double k1=-1/((yp[1]-yp[0])/(xp[1]-xp[0]));
            double k2=-1/((yp[2]-yp[1])/(xp[2]-xp[1]));

            //se calculează coordonatele punctului de intersecție a două
            //drepte: cea care trece prin punctul (xc1,yc1) și coeficientul
            //unghiular k1 și cea care trece prin punctul (xc2.yc2) și
            //coeficientul unghiular k2, astfel se obțin 2 ecuații:

            //se consideră ca un sistem de ecuații din care se calculează
            //valoarea necunoscutei x = xc:

            double xc=(yc2-yc1+k1*xc1-k2*xc2)/(k1-k2);
            //apoi, dat fiind faptul că valoarea x trebuie să satisfacă ambele
            //ecuații (1) și (2), înlocuind, de exemplu în (1), se obține y=yc:
            double yc=yc1+k1*(xc-xc1);
            //(punctul (x,y) este centrul cercului circumscris triunghiului);
            //se plasează imaginea centrului cercului:
            //add!!!!  convert float/double
            float xxc = Float.valueOf(String.valueOf(xc*udpx +x0)) ;
            float yyc = Float.valueOf(String.valueOf(-yc *udpy +y0)) ;
            ///!!!addd closed;add  *updx/y+x-y0!
            paint.setColor(Color.GREEN);
            canvas.drawCircle(xxc, yyc, 12,paint); //change with add ! xc->xxc;yc->yyc
            // 9 - default radius

            // se calculează raza cercului r ca fiind distanța de la punctul
            //găsit (x,y) până la un vârf al triunghiului, apoi se trasează 2
            //cercuri: unul cu raza r, iar altul cu raza r-2:

    //circle
            double r=Math.sqrt((xc-xp[0])*(xc-xp[0])+(yc-yp[0])*(yc-yp[0]));
            float R=Float.parseFloat(String.valueOf(r*udpx  ));
            xv=x0 + xc* udpx;
            float xbce =Float.parseFloat(String.valueOf(xv));
            xv=y0 -yc* udpy;
            float ybce =Float.parseFloat(String.valueOf(xv));
            paint.setColor(Color.BLUE);
            canvas.drawCircle(xbce, ybce, R, paint);
            paint.setColor(Color.GREEN);
            canvas.drawCircle(xbce, ybce, R-20, paint);

        }
    }
}