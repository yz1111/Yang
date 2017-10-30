package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

/**
 * Created by Administrator on 2017/9/8.
 */
public class Wuziqi extends View{

    private int mPanelWidth;
    private int MAX_LINE = 10;
    private float mLineHight;
    private float ratioPieceOfLineHight = 3 * 1.0f / 4;
    private Paint  mpaint = new Paint();
    private Bitmap mWhitePiece;
    private Bitmap mBlackPiece;
    private boolean mIsGemOver;

    private boolean mIsWith = true;
    private boolean mIsWhiteWinner;
    private List<Point> mWitharry=new ArrayList<Point>();
    private List<Point> mBlackarry = new ArrayList<Point>();
    public Wuziqi(Context context) {
        super(context);
        init();
    }

    private void init() {

        //绘画抗锯齿
          mpaint.setColor(0X44ff0000);
          mpaint.setAntiAlias(true);
          mpaint.setDither(true);
          mpaint.setStyle(Paint.Style.STROKE);

        Bitmap mWhitePiece= BitmapFactory.decodeResource(getResources(),R.drawable.stone_w2);
        Bitmap mBlackPiece=BitmapFactory.decodeResource(getResources(),R.drawable.stone_b1);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
            if(mIsGemOver)
            {
                return false;
            }
        int action=event.getAction();
        if(action==MotionEvent.ACTION_UP)
        {
            int x= (int) event.getX();
            int y= (int) event.getY();
            Point p=getValidPoint(x,y);
            if(mWitharry.contains(p)||mBlackarry.contains(p))
            {
                return false;
            }
            if(mIsWith)
            {
                mWitharry.add(p);
            }else
            {
                mBlackarry.add(p);
            }
            invalidate();
            mIsWith=!mIsWith;
        }

        return true;
    }
    private Point getValidPoint(int x, int y){
        return  new Point((int) (x / mLineHight),(int) (y / mLineHight));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heighSize = MeasureSpec.getSize(heightMeasureSpec);
        int heighMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = Math.min(widthSize, heighSize);

        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = heighSize;
        } else if (heighMode == MeasureSpec.UNSPECIFIED) {
            width = widthSize;
        }
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

         mPanelWidth=w;
        mLineHight=mPanelWidth*1.0f/MAX_LINE;
        int PiceWhite= (int) (mLineHight*ratioPieceOfLineHight);

         mWhitePiece=Bitmap.createScaledBitmap(mWhitePiece,PiceWhite,PiceWhite,false);
        mBlackPiece=Bitmap.createScaledBitmap(mBlackPiece,PiceWhite,PiceWhite,false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBoard(canvas);
        draePicec(canvas);
        checkGameOver();
    }

    private void checkGameOver() {
        boolean whithWin = chechFiveInLine(mWitharry);
        boolean blickWin = chechFiveInLine(mBlackarry);
        if (whithWin || blickWin) {
            mIsGemOver = true;
            mIsWhiteWinner = whithWin;
            String text = mIsWhiteWinner ? "白棋胜利" : "黑棋胜利";
           Toast.makeText(getContext(),text,Toast.LENGTH_LONG).show();
            ;
        }
    }

    private boolean chechFiveInLine(List<Point> mWitharry2) {
        for (Point p : mWitharry2) {
            int x = p.x;
            int y = p.y;

            boolean win = checkHorizontal(x, y, mWitharry2);
            if (win) return true;
            win = checkVertIcal(x, y, mWitharry2);
            if (win) return true;
            win = checkLeftDiagonal(x, y, mWitharry2);
            if (win) return true;
            win = checkRightDiagonl(x, y, mWitharry2);
            if (win) return true;
        }
        return false;
    }
    private boolean checkHorizontal(int x, int y, List<Point> mWitharry2) {
        int count = 1;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x-i,y))) {
                count++;
            }else {
                break;
            }
        }
        if (count==5) return true;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x+i,y))) {
                count++;
            }else {
                break;
            }
            if (count==5) return true;
        }
        return false;
    }


    private boolean checkRightDiagonl(int x, int y, List<Point> mWitharry2) {
        int count = 1;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x-i,y-i))) {
                count++;
            }else {
                break;
            }
        }
        if (count==5) return true;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x+i,y+i))) {
                count++;
            }else {
                break;
            }
            if (count==5) return true;
        }
        return false;
    }
    private boolean checkLeftDiagonal(int x, int y, List<Point> mWitharry2) {
        int count = 1;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x-i,y+i))) {
                count++;
            }else {
                break;
            }
        }
        if (count==5) return true;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x+i,y-i))) {
                count++;
            }else {
                break;
            }
            if (count==5) return true;
        }
        return false;
    }
    private boolean checkVertIcal(int x, int y, List<Point> mWitharry2) {
        int count = 1;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x,y-i))) {
                count++;
            }else {
                break;
            }
        }
        if (count==5) return true;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x,y+i))) {
                count++;
            }else {
                break;
            }
            if (count==5) return true;
        }
        return false;
    }






    private void draePicec(Canvas canvas) {
        for (int i = 0, n = mWitharry.size(); i < n; i++) {
            Point whitePoint = mWitharry.get(i);
            canvas.drawBitmap(mWhitePiece, (whitePoint.x + (1 - ratioPieceOfLineHight) / 2) * mLineHight,
                    (whitePoint.y + (1 - ratioPieceOfLineHight) / 2) * mLineHight, null);
        }
        for (int i = 0, n = mBlackarry.size(); i < n; i++) {
            Point blackPoint = mBlackarry.get(i);
            canvas.drawBitmap(mBlackPiece, (blackPoint.x + (1 - ratioPieceOfLineHight) / 2) * mLineHight,
                    (blackPoint.y + (1 - ratioPieceOfLineHight) / 2) * mLineHight, null);
        }
    }

    private void drawBoard( Canvas canvas) {
        int w=mPanelWidth;
        float lineHeight = mLineHight;
        for (int i = 0; i < MAX_LINE; i++) {
            int startX = (int) (lineHeight / 2);
            int endX = (int) (w - lineHeight / 2);

            int y = (int) ((0.5 + i) * lineHeight);
            canvas.drawLine(startX, y, endX, y,mpaint);
            canvas.drawLine(y, startX, y, endX,mpaint);
        }

    }
}
