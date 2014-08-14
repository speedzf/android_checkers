package com.example.checkers;

public class CheckersSimpleMove {
    CheckersPosition start;
    CheckersPosition end;
    CheckersSimpleMove() {};
    CheckersSimpleMove(String str1, String str2) { 
    	start = new CheckersPosition(str1);
        end = new CheckersPosition(str2);
        };
    public CheckersPosition CheckersPosition1(String str1) {
		// TODO Auto-generated method stub
    	char[] charArray = str1.toCharArray(); 
        int row = 7 - (charArray[1]-'1');
        int col = charArray[0] - 'A';
        CheckersPosition x = new CheckersPosition();
        x.row = row;
        x.col = col;
        return x;

	}
	CheckersPosition get_start() {return start;};
    CheckersPosition get_end() {return end;};
    void set_start(CheckersPosition q) {start = q;};
    void set_end(CheckersPosition q) {end = q;};    
  };