package com.uniovi.util;

/**
 * 
 * @author Sergio Faya Fernandez The intend of this class is to group all common
 *         field checking methods, that most of the times will throw runtime
 *         exception in case of failure.
 */
public class Checker {

	public static void isEmpty(String str) {
		if (str.isEmpty()) {
			throw new IllegalArgumentException("Value cannot be empty");
		}
	}

	public static void isNull(Object o) {
		if (o == null) {
			throw new IllegalArgumentException("Value cannot be empty");
		}
	}
	
	public static void isLessThanZero(int i){
		if(i < 0){
			throw new IllegalArgumentException("Value cannot be lower than 0");
		}
	}
}
