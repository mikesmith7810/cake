package com.xdesign.cake.task;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum FunctionalInterfaceType {
	FUNCTION( "FUNCTION" ),
	CONSUMER( "CONSUMER" ),
	PREDICATE( "PREDICATE" ),
	SUPPLIER( "SUPPLIER" ),
	BIFUNCTION( "BIFUNCTION" );

	private static final Map<String, FunctionalInterfaceType> mapByValue = Arrays.stream( values() )
			.collect( Collectors.toMap( FunctionalInterfaceType::getValue,
					java.util.function.Function.identity() ) );
	private final String value;

	FunctionalInterfaceType( final String value ) {
		this.value = value;
	}

	public static FunctionalInterfaceType getByValue( final String value ) {
		return mapByValue.get( value );
	}

	public String getValue() {
		return this.value;
	}
}
