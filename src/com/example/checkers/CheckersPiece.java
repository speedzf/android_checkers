package com.example.checkers;

import java.util.Vector;

public class CheckersPiece {

	boolean crowned;
	boolean captured;
	boolean dark;
	CheckersPosition x = new CheckersPosition();

	public void set_position(String str) {
		x.parse(str);
	};

	public char print() {
		if (captured== true ){ return '-';}
		if (dark == false && crowned == false && x.get_row() > -1) {
			return 'w';
		} else if (dark == false && crowned == true 
				&& x.get_row() > -1) {
			return 'W';
		} else if (dark == true && crowned == false 
				&& x.get_row() > -1) {
			return 'b';
		} else if (dark == true && crowned == true 
				&& x.get_row() > -1) {
			return 'B';    
		} else {
			return '-';
		}
	}
	// friend std::ostream& operator<<(std::ostream& ost, CheckersPiece t);
	public CheckersPosition get_position() { return x;};
	public void set_position(int a, int b) { x.set_position(a, b);};
	public void set_dark(){dark = true;};
	public void set_captured() { captured = true; x.set_position(-1, -1);};
	public void set_crowned() { crowned = true;};
	public boolean is_none_piece() { 
		if (x.get_row() == -1) { 
			return true;} else {
				return false;
			} 
	};
	public boolean is_captured() { return captured;};
	public boolean is_crowned() {return crowned;};
	public boolean is_dark() { return dark;};
	public CheckersPosition[][] piece_neighbours(){
		// TODO returns CheckersPosition array of positions of neighbouring pieces;
		CheckersPosition [][] resultt = new CheckersPosition[4][2];
		int row = x.get_row();
		int col = x.get_col();
		int ind1 = 0;
		for (int a = 0; a <= 3; a++) {
			CheckersPosition[] result = new CheckersPosition[2];
			int ind = 0;
			for (int i = 1; i < 3; i++) {
				if (a == 0 && (dark == true || crowned == true)) {
					CheckersPosition pos = new CheckersPosition();
					pos.col = col +i;
					pos.row = row -i;
					result[ind] = pos;
					ind++;

				} else if (a == 1 && (dark == true || crowned == true)) {
					CheckersPosition pos = new CheckersPosition();
					pos.col = col -i;
					pos.row = row -i;
					result[ind] = pos;
					ind++;         
				} else if (a == 2 && (dark == false || crowned == true)) {
					CheckersPosition pos = new CheckersPosition();
					pos.col = col -i;
					pos.row = row +i;
					result[ind] = pos;
					ind++;                  
				} else if (a == 3 && (dark == false || crowned == true)) {
					CheckersPosition pos = new CheckersPosition();
					pos.col = col +i;
					pos.row = row +i;
					result[ind] = pos;
					ind++;
				}
			}
			resultt[ind1] = result;
			ind1++;           
		}
		return resultt;
	}
	public void CheckersPosition1(int a ,int b) {} 
};