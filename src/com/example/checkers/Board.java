package com.example.checkers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

public class Board extends View {

	int width = this.getWidth();
	int height = this.getHeight();
	public Canvas canvas;
	Bitmap myBitmap; 
	char[] a1;

	public Board(Context context) {
		super(context);
		setFocusable(true);
		//canvas.drawColor(Color.CYAN);

		// paint of -
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.BLACK);

		// paint of "b"
		Paint paint_b = new Paint();
		paint_b.setStyle(Paint.Style.FILL);
		paint_b.setColor(Color.RED);

		//paint of "B"
		Paint paint_B = new Paint();
		paint_B.setStyle(Paint.Style.FILL);
		paint_B.setColor(Color.MAGENTA);

		// paint of "W"
		Paint paint_W = new Paint();
		paint_W.setStyle(Paint.Style.FILL);
		paint_W.setColor(Color.CYAN);

		//paint of "w"
		Paint paint_w = new Paint();
		paint_w.setStyle(Paint.Style.FILL);
		paint_w.setColor(Color.BLUE);

		myBitmap = Bitmap.createBitmap(180, 200, Bitmap.Config.RGB_565);
		//myBitmap.eraseColor(0);
		canvas = new Canvas();
		//canvas.drawBitmap(myBitmap, 0, 0, null);
		canvas.setBitmap(myBitmap);
		canvas.drawRGB(255,255,255);
	}

	public void print(CheckersBoard board){

		String a = board.operatorout(board);
		a1 = new char[a.length()];
		for (int i=0; i<a.length(); i++) {
			a1[i] = 's';
		}
		for (int i =0; i<a.length(); i++) {
			a1[i] = a.charAt(i);
		}

		int rec_w = 10;
		int line = 0;
		int rec_h = (line)*10+5;
		int space = 0;
		int start =10;

		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.GRAY);

		Paint paint_b = new Paint();
		paint_b.setStyle(Paint.Style.FILL);
		paint_b.setColor(Color.RED);

		Paint paint_B = new Paint();
		paint_B.setStyle(Paint.Style.FILL);
		paint_B.setColor(Color.MAGENTA);

		Paint paint_W = new Paint();
		paint_W.setStyle(Paint.Style.FILL);
		paint_W.setColor(Color.GREEN);

		Paint paint_w = new Paint();
		paint_w.setStyle(Paint.Style.FILL);
		paint_w.setColor(Color.BLACK);

		char[] alpha = new char[]{'A','B','C','D','E','F','G','H'};
		char[] num = new char[]{'1','2','3','4','5','6','7','8'};
		int check =1;

		for (int i =0; i < a1.length; i++) {
			if (a1[i] == '8') {
				canvas.drawText("8",10,8, paint_w);
				start = start+10;
			}else if (a1[i] == '7') {
				//canvas.drawText("8",10,8, paint_w);
				canvas.drawText("7",10,(line-1)*10+8, paint_w);
				start = start + 10;
			}else if (a1[i] == '6') {
				canvas.drawText("6",10,(line-1)*10+8, paint_w);
				start = start + 10;
			}else if (a1[i] == '5') {
				canvas.drawText("5",10,(line-1)*10+8, paint_w);
				start = start + 10;
			}else if (a1[i] == '4') {
				canvas.drawText("4",10,(line-1)*10+8, paint_w);
				start = start + 10;
			}else if (a1[i] == '3') {
				canvas.drawText("3",10,(line-1)*10+8, paint_w);
				start = start + 10;
			}else if (a1[i] == '2') {
				canvas.drawText("2",10,(line-1)*10+8, paint_w);
				start = start + 10;
			}else if (a1[i] == '1') {
				canvas.drawText("1",10,(line-1)*10+8, paint_w);
				start = start + 10;
			}
			if (line <= 8 ) {
				if (a1[i] == '-') {
					canvas.drawRect(new Rect(start,(line-1)*10,start+10,rec_h), paint);
					//canvas.drawText("-", start, (line)*10, paint);
					start = start + 20;
					
				}
				if (a1[i] == 'b') {
					canvas.drawRect(new Rect(start,(line-1)*10,start+10,rec_h), paint_b);
					//canvas.drawText("b", start, (line)*10, paint);
					start = start+20;
				}
				if (a1[i] == 'B') {
					canvas.drawRect(new Rect(start,(line-1)*10,start+10,rec_h), paint_B);
					//canvas.drawText("B", start, (line)*10, paint);
					start = start+20;
				}
				if (a1[i] == 'W') {
					canvas.drawRect(new Rect(start,(line-1)*10,start+10,rec_h), paint_W);
					// canvas.drawText("W", start, (line)*10, paint);
					start = start+20;
				}
				if (a1[i] == 'w') {
					canvas.drawRect(new Rect(start,(line-1)*10,start+10,rec_h), paint_w);
					// canvas.drawText("w", start, (line)*10, paint);
					start = start+20;
				}
				if (line ==0 && a1[i] == '\n') {
					line++;
					start = 10;
					rec_h = (line-1)*10+5;
				}

				if (line > 0 && a1[i] == '\n') {
					line++;
					start = 10;
					rec_h = (line-1)*10+5;
				}
			}
			if (line >8) {
				start =0;
				canvas.drawText("A", start+20, (line-1)*10+10, paint_w);
				canvas.drawText("B", start+40, (line-1)*10+10, paint_w);
				canvas.drawText("C", start+60, (line-1)*10+10, paint_w);
				canvas.drawText("D", start+80, (line-1)*10+10, paint_w);
				canvas.drawText("E", start+100, (line-1)*10+10, paint_w);
				canvas.drawText("F", start+120, (line-1)*10+10, paint_w);
				canvas.drawText("G", start+140, (line-1)*10+10, paint_w);
				canvas.drawText("H", start+160, (line-1)*10+10, paint_w);
			}
		}

	}






	public void onDraw(Canvas canvas) {
	}
} 
