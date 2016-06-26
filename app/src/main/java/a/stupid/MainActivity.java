package a.stupid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import java.util.Random;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Create Canvas Demo");
        setContentView(new DrawView(this));
    }
     public class DrawView extends View {
         public DrawView(Context context) {
             super(context);
         }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Fill the screen with white
            Paint paint = new Paint();
            Random random = new Random();
            paint.setAntiAlias(true);
            canvas.drawColor(Color.WHITE);

            // Draw some circles with random colors in random locations
            for (int i = 0; i < 20; i++) {
                paint.setColor(random.nextInt());
                canvas.drawCircle(Math.abs(100+random.nextInt())%400, 20+Math.abs(random.nextInt())%400, 50, paint);
            }

            // Draw a line
            paint.setColor(Color.BLACK);
            canvas.drawLine(0,0,400,400, paint);

            // Draw a box using the method definied here.
            Point p1 = new Point(20,20);
            Point p2 = new Point(100,100);
            this.drawBox(canvas, p1, p2, paint);

            //Draw a box using Canvas method
            Point p3 = new Point(50,50);
            Point p4 = new Point(200,200);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(p3.x, p3.y, p4.x, p4.y, paint);
        }

         // Method for drawing box (superfluous b/c drawRect is already a method in Canvas)
        public void drawBox(Canvas canvas, Point p1, Point p2, Paint paint) {
            canvas.drawLine(p1.x, p1.y, p1.x, p2.y, paint);
            canvas.drawLine(p1.x, p1.y, p2.x, p1.y, paint);
            canvas.drawLine(p1.x, p2.y, p2.x, p2.y, paint);
            canvas.drawLine(p2.x, p1.y, p2.x, p2.y, paint);
        }
     } //DrawView
} //MainActivity
