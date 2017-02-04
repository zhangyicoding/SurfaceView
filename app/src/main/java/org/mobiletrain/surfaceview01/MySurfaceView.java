package org.mobiletrain.surfaceview01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private Canvas canvas;
    private Paint paint;

    private SurfaceHolder surfaceHolder;

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    // 初始化
    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        Log.d("ee", "create");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 24 * 10; i++) {
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    canvas = holder.lockCanvas();
                    if (canvas != null) {
                        canvas.drawColor(Color.WHITE);
                        canvas.drawRect(100, 100 + i, 400, 200 + i, paint);
                        holder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }).start();
    }

    @Override
    public void surfaceChanged(final SurfaceHolder holder, int format, int width, int height) {
        Log.d("ee", "change");

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("ee", "destroy");
    }
}
