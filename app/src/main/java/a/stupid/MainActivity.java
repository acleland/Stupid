package a.stupid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

            for (int i = 0; i < 20; i++) {
                paint.setColor(random.nextInt());
                canvas.drawCircle(Math.abs(100+random.nextInt())%400, 20+Math.abs(random.nextInt())%400, 50, paint);
            }


        }
     } //DrawView
} //MainActivity
