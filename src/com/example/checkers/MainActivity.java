package com.example.checkers;

import java.util.Vector;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.checkers.*;

public class MainActivity extends Activity {
	
	public static Button B;
	public static TextView display;
	public static TextView log;
	public static ImageView img;
	public static int init = 0;
	public static EditText input;
	public static CheckersBoard Board;
	public static int player_turn = 1;
	public static String msg;
	public static String last_msg;
	public static Board n;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		display = (TextView) findViewById(R.id.textView2);
		log = (TextView) findViewById(R.id.textView2);
		input = (EditText) findViewById(R.id.editText1);
		B = (Button) findViewById(R.id.button1);
	 	img = (ImageView) findViewById(R.id.imageView1);
	 	
	 	n = new Board(getApplicationContext());
	 	Board = new CheckersBoard();
	 	display.setText((String) Board.operatorout(Board));
	 	
		log.setText("INSTRUCTIONS " +
				"\n" +
				"Welcome!" +
				"\n" +
				" To print the board, press the button"  +
				"\n" + "To move a piece enter coordinates in the format:" +
				"\n D4-F4" + "\n"+ " And hit the button!");
		
	}
	public void print_board() {
		//display.setText((String) Board.operatorout(Board));
		img.setImageDrawable(new BitmapDrawable(getResources(), n.myBitmap));
		n.print(Board);
	}

	public void buttononClick(View view) {
		
		String msg = input.getText().toString();
		if (init == 0) {
			Board = new CheckersBoard();
			print_board();
			log.setText("New game started" + "\n" + "White's turn");
			init = 1;
			msg = last_msg;
		} else if (msg != "Reset" || msg != null) { 
			CheckersMove move = new CheckersMove(msg);
			int l = Board.validate(move);
			// Toast.makeText(getApplicationContext(),"l: " + l, Toast.LENGTH_SHORT).show();
			if (l == 2) {
				//dir
				log.setText("Invalid move");
				Toast.makeText(getApplicationContext(),"Invalid direction", Toast.LENGTH_SHORT).show();
			}
			if (l == 1) {
				// turn
				log.setText("Invalid move");
				Toast.makeText(getApplicationContext(),"Invalid turn", Toast.LENGTH_SHORT).show();}

			if (l == 5) {log.setText("incorrect format: enter capturing move in format: A5-B4-C3");}
			if (l == 3) {// empty space jump
				log.setText("Invalid move");
				Toast.makeText(getApplicationContext(),"Empty space jump", Toast.LENGTH_SHORT).show();}
			if (l == 4) {// jump over own piece 
				log.setText("Invalid move");
				Toast.makeText(getApplicationContext(),"Jump over own piece", Toast.LENGTH_SHORT).show();
			}
			if (l == 6) {
				log.setText("Invalid move");
				Toast.makeText(getApplicationContext(),"End over another piece", Toast.LENGTH_SHORT).show();}
			if (l == 0) {
				Toast.makeText(getApplicationContext(), "Executed move: "+ msg, Toast.LENGTH_SHORT).show();
				Board.move(move);
				print_board();
				if (player_turn == 1) { player_turn = 2;} else if(player_turn == 2) { player_turn = 1;}
				if (player_turn == 1) {log.setText("Last move: "+ msg + "\n" + "Black's turn to move" + "\n" + "Black's score: " + Board.white_score + "\n" + "Red's score: " + Board.black_score); }
				if (player_turn == 2) {log.setText("Last move: "+ msg + "\n" + "Red's turn to move" + "\n" + "Black's score: " + Board.white_score + "\n" + "Red's score: " + Board.black_score); }
				last_msg = msg;
				if ( Board.winner() == 1) {
					//White won
					if (player_turn == 1) {log.setText("Last move: "+ msg + "\n" + "Black's turn to move" + "\n" + "Black's score: " + Board.white_score + "\n" + "Red's score: " + Board.black_score); }
					if (player_turn == 2) {log.setText("Last move: "+ msg + "\n" + "Red Won!" + "\n" + "Black's score: " + Board.white_score + "\n" + "Red's score: " + Board.black_score); }
					Toast.makeText(getApplicationContext(), "Black Won!", Toast.LENGTH_SHORT).show();
				}
				if ( Board.winner() == 2) {
					//Black won
					if (player_turn == 1) {log.setText("Last move: "+ msg + "\n" + "Black Won!" + "\n" + "White's score: " + Board.white_score + "\n" + "Black's score: " + Board.black_score); }
					if (player_turn == 2) {log.setText("Last move: "+ msg + "\n" + "Black's turn to move" + "\n" + "White's score: " + Board.white_score + "\n" + "Black's score: " + Board.black_score); }
					Toast.makeText(getApplicationContext(), "Red Won!", Toast.LENGTH_SHORT).show();
				}
			}
		}
		if (msg == null) {
			Board = new CheckersBoard();
			print_board();
			log.setText("New game started" + "\n" + "Black's turn");
			init = 1;
			msg = last_msg;}
		
		if (msg == "Reset") {
			Toast.makeText(getApplicationContext(), "Resetting game!", Toast.LENGTH_LONG).show();
			Board = new CheckersBoard();
			print_board();
			log.setText("New game started...");
		}
		if (msg == "print_board") {
			log.setText((String) Board.operatorout(Board));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}