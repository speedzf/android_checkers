package com.example.checkers;
import java.util.Vector;
import com.example.checkers.CheckersPiece; 

public class CheckersBoard {

	public Vector<CheckersPiece> light_pieces = new Vector<CheckersPiece>(12); 
	public Vector<CheckersPiece> dark_pieces = new Vector<CheckersPiece>(12);
	public CheckersPiece none_piece = new CheckersPiece();
	int turn = 0; // 0 for white 1 for black
	int white_score = 0;
	int black_score = 0;

	CheckersBoard() {
		int r = 0;
		int c = 1;
		for (int p = 0; p<12; p++) {
			light_pieces.add(p, new CheckersPiece());
			dark_pieces.add(p, new CheckersPiece());
		}

		for (int i =0; i < this.light_pieces.size(); i++) {
			//Vector<CheckersPiece> ba = this.light_pieces;
			light_pieces.elementAt(i).set_position(r, c);
			light_pieces.elementAt(i).dark = false;
			light_pieces.elementAt(i).captured = false;
			light_pieces.elementAt(i).crowned = false;
			c = c + 2;
			if (c > 7) {
				if (r % 2 == 0)
				{c = 0;} else {
					c = 1;}
				r = r + 1;
			}  
		}  
		r = 7;
		c = 0;
		for (int i =0; i < this.dark_pieces.size(); i++) {
			dark_pieces.elementAt(i).set_dark();
			dark_pieces.elementAt(i).set_position(r, c);
			dark_pieces.elementAt(i).dark = true;
			dark_pieces.elementAt(i).captured = false;
			dark_pieces.elementAt(i).crowned =false;
			c = c + 2;
			if (c > 7) {
				if (r % 2 != 0) {
					c = 1;
				} else {
					c = 0;
				}
				r = r - 1;
			}
		}
		none_piece.set_position(-1, -1);
	}

	String operatorout(CheckersBoard t) {
		// slash edit
		char[] str = new char[150];
		int index = 0;
		int row = 8;
		for (int i = 0; i < 8; i++) {
			str[index] = (char) ((char) row + (int) '0');
			row--;
			index ++;
			str[index] = ' ';
			index++;
			for (int j = 0; j<8; j++) {
				CheckersPiece p = operator(new CheckersPosition(i, j));
				str[index] = p.print();
				index++;
			}
			str[index] = '\n';
			index++;
		}
		str[index] = ' ';
		index++;
		str[index] = ' ';
		index++;
		str[index] = 'A';
		index++;
		str[index] = 'B';
		index++;
		str[index] = 'C';
		index++;
		str[index] = 'D';
		index++;
		str[index] = 'E';
		index++;
		str[index] = 'F';
		index++;
		str[index] = 'G';
		index++;
		str[index] = 'H';
		String out = "";
		for (int i = 0; i < index+1; i++) {
			out = out + str[i];
		}
		return out;
	}

	// returns piece at position pos
	CheckersPiece operator(CheckersPosition pos) {
		int f = pos.get_row();
		int g = pos.get_col();

		for (int i =0; i < this.light_pieces.size(); i++) {
			CheckersPiece q = this.light_pieces.elementAt(i);
			if (light_pieces.elementAt(i).get_position().get_row() == f &&
					light_pieces.elementAt(i).get_position().get_col() == g) {
				return light_pieces.elementAt(i);
			}        
		}

		for (int i =0; i<this.dark_pieces.size(); i++) {
			CheckersPiece qz = this.dark_pieces.elementAt(i);
			if (dark_pieces.elementAt(i).get_position().get_row() == f &&
					dark_pieces.elementAt(i).get_position().get_col() == g) {
				return dark_pieces.elementAt(i);
			}
		}
		return none_piece;    
	}

	CheckersPiece operator(int i[]) {
		int size = light_pieces.size();
		for (int i1 = 0; i1< size; i1++) {
			CheckersPiece q = light_pieces.elementAt(i1);
			if (light_pieces.elementAt(i1).get_position().get_row() == i[0] &&
					light_pieces.elementAt(i1).get_position().get_col() == i[1]) {
				return light_pieces.elementAt(i1);
			}        
		}
		for (int i1 = 0; i1< size; i1++) {
			CheckersPiece qz = dark_pieces.elementAt(i1);
			if (dark_pieces.elementAt(i1).get_position().get_row() == i[0] &&
					dark_pieces.elementAt(i1).get_position().get_col() == i[1]) {
				dark_pieces.elementAt(i1).set_dark(); 
				return dark_pieces.elementAt(i1);
			}
		}
		return none_piece;    
	}

	void simple_move(CheckersSimpleMove csm) {
		if (Math.abs(csm.get_start().get_row() - csm.get_end().get_row()) == 2) {
			CheckersPosition w = new CheckersPosition();
			w.row = (csm.get_start().get_row() + csm.get_end().get_row())/2; 
			w.col = (csm.get_start().get_col() + csm.get_end().get_col())/2;
			operator(w).set_captured(); 
		}
		// update piece position.
		operator(csm.get_start()).set_position(csm.get_end().get_row(), csm.get_end().get_col());
		// crown
		if (csm.get_end().get_row() == 0 || csm.get_end().get_row() == 7) {
			this.operator(csm.get_end()).set_crowned();
		}   
	}

	void move(CheckersMove m) {
		Vector<CheckersSimpleMove> var = m.get_simple_moves();
		if (var.size() == 1){ 
			for (int i = 0; i < var.size(); i++) {
				CheckersSimpleMove u = m.get_simple_moves().elementAt(i);
				simple_move(u);
			}
		} else {
			CheckersSimpleMove u = var.elementAt(0);
			CheckersSimpleMove u2 = var.elementAt(1);
			if (u.end.row == u2.start.row && u.end.col == u2.start.col) {
				CheckersPosition w = new CheckersPosition();
				w.row = u.end.row;
				w.col = u.end.col;
				operator(w).set_captured();
			}
			for (int i = 0; i < var.size(); i++) {
				u = m.get_simple_moves().elementAt(i);
				simple_move(u);
			}
		}
		//		if (var.size() > 2) {
		//			for (int i = 0; i < var.size(); i++) {
		//				CheckersSimpleMove u = m.get_simple_moves().elementAt(i);
		//				simple_move(u);
		//			}
		//		}
		if (turn == 0) {
			turn = 1;
		} else {
			turn = 0;
		}
	}

	CheckersBoard(String str) {
		String[] split = new String[2];
		split[0] = " ";
		split[1] = " ";
		split = str.split("-");
		for (int i=0; i<12;i++) { 
			light_pieces.add(i, new CheckersPiece());
			dark_pieces.add(i, new CheckersPiece());
		}
		Vector<String> vec_1 = new Vector<String>(split.length);
		for (int i =0; i< split.length;i++) {
			vec_1.add(split[i]); 			
		}
		//s = split[1].split("-");
		Vector<String> vec_2 = new Vector<String>(split.length);
		for (int i =0; i< split.length;i++) {
			vec_2.add(split[i]); 			
		}
		CheckersPiece piece = new CheckersPiece();
		piece.set_position(split[1]);
		for (int i =0; i< 12; i++) {
			if (light_pieces.elementAt(i).get_position().get_row() == piece.get_position().get_row() && light_pieces.elementAt(i).get_position().get_col() == piece.get_position().get_col()) {
				for (int w = 0; w < 12; w++) {
					if (w < vec_2.size()) {
						light_pieces.elementAt(w).set_position(vec_2.elementAt(w));
						light_pieces.elementAt(w).dark = false;
						light_pieces.elementAt(w).captured = false;
						light_pieces.elementAt(w).crowned = false;
					} else {
						light_pieces.elementAt(w).set_position(-1, -1);
						light_pieces.elementAt(w).set_captured();
						light_pieces.elementAt(w).crowned = false;
						light_pieces.elementAt(w).dark = false;
					}
				}
			}
			if( dark_pieces.elementAt(i).get_position().get_row() == piece.get_position().get_row() && dark_pieces.elementAt(i).get_position().get_col() == piece.get_position().get_col()) {
				for (int r = 0; r < 12; r++) {
					if (r < vec_1.size()) {
						dark_pieces.elementAt(r).set_position(vec_1.elementAt(r));
						dark_pieces.elementAt(r).set_dark();
						dark_pieces.elementAt(r).crowned= false;
						dark_pieces.elementAt(r).captured = false;
					} else {
						dark_pieces.elementAt(r).set_position(-1, -1);
						dark_pieces.elementAt(r).set_captured();
						dark_pieces.elementAt(r).set_dark();
						dark_pieces.elementAt(r).crowned = false;
					}
				}
			}
		}
		piece = new CheckersPiece();
		piece.set_position(split[1]);
		none_piece.set_position(-1, -1);
	}

	int winner() {
		if (white_score == 12) {return 1;}
		if (black_score == 12){return 2;}
		return 0;
		
		
//		for ( CheckersPiece y : dark_pieces) {
//			if (y.get_position().get_row() != -1) {
//				// dark has one uncaptured piece
//				result = 1;
//				dark_cap = true;
//			} 
//		}
//		for (CheckersPiece x : light_pieces) {
//			if(x.get_position().get_row() != -1) {
//				// white has one uncaptured piece
//				result = 3;
//				light_cap = true;
//			}
//		}
//		for ( CheckersPiece u : light_pieces) {
//			if (u.get_position().get_row() != -1 && result == 1) {
//				// white has one uncaptured piece and dark has one uncaptured piece
//				result = 0;
//			}
//		}
//		return result;
	}

	int validate(CheckersMove cm) {

		boolean right_direction = false;
		if (cm.simple_moves.size() == 1) {
			
			CheckersPosition end_pos = new CheckersPosition();
			end_pos.col = cm.simple_moves.elementAt(0).end.col;
			end_pos.row = cm.simple_moves.elementAt(0).end.row;
			
			if (operator(end_pos).print() == '-') {
				// move over another piece
				} else { return 6;}
			if (turn == 0 && operator(cm.simple_moves.elementAt(0).start).dark == true) {
				return 1;
			} else if (turn == 1 && operator(cm.simple_moves.elementAt(0).start).dark == false) {
				return 1;
			}
			CheckersPiece P = operator(cm.simple_moves.elementAt(0).start);
			CheckersPosition[][] valid_moves = new CheckersPosition[4][2];
			valid_moves = P.piece_neighbours();
			for (CheckersPosition[] i : valid_moves ) {
				if (i!= null) {
					for (CheckersPosition o : i ) {
						if (o != null ) { 
							System.out.println("row: " + o.row + "col: " + o.col);
							if (o.row == cm.simple_moves.elementAt(0).end.row && o.col== cm.simple_moves.elementAt(0).end.col) {
								right_direction = true;
							}
						}
					}
				}
			}

			for (int iq = 0; iq < 4; iq++) {
				for (int j = 0; j<2; j++) {
//					try {
//						// if (valid_moves[i][j].row == cm.simple_moves.elementAt(0).end.row && valid_moves[i][j].col== cm.simple_moves.elementAt(0).end.col) {
//						//	right_direction = true;
//						//}
//					} catch (Exception u4) {
//						continue;
//					}
				}
			}
			if (Math.abs(cm.simple_moves.elementAt(0).start.row - cm.simple_moves.elementAt(0).end.row) == 2) {
				if (Math.abs(cm.simple_moves.elementAt(0).start.col - cm.simple_moves.elementAt(0).end.col) == 2) {
					//incorrect format D5-F7
					return 5;
				}
			}
		}

		if (cm.simple_moves.size() == 2) {
			// bug below lol
			right_direction = true;
			if (turn == 0 && operator(cm.simple_moves.elementAt(0).start).dark == true) {
				return 1;
			} else if (turn == 1 && operator(cm.simple_moves.elementAt(0).start).dark == false) {
				return 1;
			}

			CheckersPosition initialpos = new CheckersPosition();
			initialpos.row = cm.simple_moves.elementAt(0).start.row;
			initialpos.col = cm.simple_moves.elementAt(0).start.col;

			if (operator(initialpos).dark == false) { 
				CheckersPosition pos = new CheckersPosition();
				pos.row = cm.simple_moves.elementAt(0).end.row;
				pos.col = cm.simple_moves.elementAt(0).end.col;

				if (operator(pos).is_none_piece()) { return 3;}
				if (operator(pos).dark == false) {return 4;}

			}

			if (operator(initialpos).dark == true) { 
				CheckersPosition pos = new CheckersPosition();
				pos.row = cm.simple_moves.elementAt(0).end.row;
				pos.col = cm.simple_moves.elementAt(0).end.col;
				if (operator(pos).is_none_piece()) { return 3;}
				if (operator(pos).dark == true) {return 4;}

			}

		}
		if (right_direction == false) {
			return 2;
		}
		if (cm.simple_moves.size() == 2 && turn == 0) {
			// whites point
			white_score++;
		}
		if (cm.simple_moves.size() == 2 && turn == 1) {
			// black point
			black_score++;
		}
		return 0;		
	}
};