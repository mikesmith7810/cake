package com.xdesign.cake.task;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum TaskType {
	Function( 1 );

	private static final Map<Integer, TaskType> mapByValue = Arrays.stream( values() )
			.collect( Collectors.toMap( TaskType::getValue,
					java.util.function.Function.identity() ) );
	private final int value;

	TaskType( final int value ) {
		this.value = value;
	}

	public static TaskType getByValue( final int value ) {
		return mapByValue.get( value );
	}

	public int getValue() {
		return this.value;
	}
}
