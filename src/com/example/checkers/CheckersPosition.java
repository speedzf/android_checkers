package com.example.checkers;

public class CheckersPosition {
int row;
int col;

void parse(String str) {
	char[] charArray = str.toCharArray(); 
     row = 7 - (charArray[1]-'1');
     col = charArray[0] - 'A';
}

CheckersPosition() {

}
CheckersPosition(String str) {
	char[] charArray = str.toCharArray(); 
    row = 7 - (charArray[1]-'1');
    col = charArray[0] - 'A';
    }
CheckersPosition(int a, int b) {
    row = a;
    col = b;
}

public CheckersPosition set_position(int a, int b) {
    row = a;
    col = b;
	return this;
}

public int get_row() {
    return row;
}

public int get_col() {
    return col;
}

public boolean isequalto(CheckersPosition q) {
    if (col == q.get_col() && row == q.get_row()) {
        return true;
    } else {
        return false;
    }
}
};