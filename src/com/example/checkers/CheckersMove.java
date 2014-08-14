package com.example.checkers;

import java.util.Vector;

public class CheckersMove {
	public Vector<CheckersSimpleMove> simple_moves = new Vector<CheckersSimpleMove>(2);
	CheckersMove(String str) {
		String[] s = str.split("-");
		// convert to array to vector.
		// each element has one address: D5
		Vector<String> vec_1 = new Vector<String>(s.length);
		for(int i =0; i<s.length;i++) {
			vec_1.add(s[i]);					 
		}
		// vector of checkers simple move.
	    Vector<CheckersSimpleMove> vec_2 = new Vector<CheckersSimpleMove>(vec_1.size()-1);
	    for (int i =0; i < vec_1.size()-1; i++) {
	    	vec_2.add(i, new CheckersSimpleMove());
	    }
	    int count = vec_1.size();
	    int check = count - 1;
	    for (int q = 0; q < count; q++) {
	        if (q < check) {
	        	CheckersPosition x = new CheckersPosition();
	        	String star = vec_1.get(q);
	        	char[] charArray = star.toCharArray(); 
	            x.row = 7 - (charArray[1]-'1');
	            x.col = charArray[0] - 'A';
	            vec_2.elementAt(q).set_start(x);
	            //vec_2.get(q).set_start(x);
	        }
	        if (q > 0) {
	        	CheckersPosition x = new CheckersPosition();
	        	String star = vec_1.get(q);
	        	char[] charArray = star.toCharArray(); 
	            x.row = 7 - (charArray[1]-'1');
	            x.col = charArray[0] - 'A';
	            //vec_2.get(q-1).set_end(x);
	            vec_2.elementAt(q-1).set_end(x);
	        }
	    }
	    simple_moves = vec_2;
	}
	Vector<CheckersSimpleMove> get_simple_moves() {
	    return simple_moves;
	}
};