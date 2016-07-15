package a.stupid;

import java.io.*;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.*;
import android.os.Bundle;
import android.view.*;
import android.util.*;

public class MainActivity extends Activity {
    DrawView drawView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        drawView = new DrawView(this);
        setContentView(drawView);
    }


    @Override public void onResume() {
        super.onResume();
        drawView.resume();
    }

    @Override public void onPause() {
        super.onPause();
        drawView.pause();
    }

    public class DrawView extends SurfaceView implements Runnable {
        Thread gameloop = null;
        SurfaceHolder surface;
        volatile boolean running = false;
        AssetManager assets = null;
        BitmapFactory.Options options = null;
        Bitmap dactyl[];
        int frame = 0;

        public DrawView(Context context) {
            super(context);
            surface = getHolder();
            assets = context.getAssets();

            // Set bitmap color depth option
            options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            dactyl = new Bitmap[12];

            try {
                for (int n=0; n<12; n++) {
                    String filename = "dactyl" + Integer.toString(n+1) + ".png";
                    InputStream istream = assets.open(filename);
                    dactyl[n] = BitmapFactory.decodeStream(istream, null, options);
                    istream.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

            public void resume() {
            running = true;
            gameloop = new Thread(this);
            gameloop.start();
        }

        public void pause() {
            running = false;
            while (true) {
                try {
                    gameloop.join();
                }
                catch (InterruptedException e) {}
            }
        }

        @Override public void run() {
            while (running) {
                if (!surface.getSurface().isValid())
                    continue;

                Canvas canvas = surface.lockCanvas();
                assert (canvas != null);

                // draw one frame of the dactyl array
                canvas.drawColor(Color.rgb(85, 107, 47));
                canvas.drawBitmap(dactyl[frame], 0, 0, null);

                // scale it larger
                Rect dest = new Rect(100, 0, 300, 200);
                canvas.drawBitmap(dactyl[frame], null, dest, null);

                // larger still
                dest = new Rect(200, 0, 800, 600);
                canvas.drawBitmap(dactyl[frame], null, dest, null);

                surface.unlockCanvasAndPost(canvas);

                frame++;
                if (frame > 11) frame = 0;

                try {
                    Thread.sleep(50);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

     } //DrawView
} //MainActivity
