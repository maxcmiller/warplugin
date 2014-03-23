package com.maxcmiller.war.enums;

public enum Rank {
	GENERAL(4), SERGEANT(3), CORPORAL(2), PRIVATE(1);
	
	private final int value;
	
	private Rank(final int newValue) {
        value = newValue;
    }
	
	public int getValue() {
		return value;
	}
}
