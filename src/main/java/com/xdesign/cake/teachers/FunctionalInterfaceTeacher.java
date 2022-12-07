package com.xdesign.cake.teachers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xdesign.cake.demonstrators.functionalinterface.ConsumerDemonstrator;
import com.xdesign.cake.demonstrators.functionalinterface.FunctionDemonstrator;
import com.xdesign.cake.demonstrators.functionalinterface.PredicateDemonstrator;
import com.xdesign.cake.demonstrators.functionalinterface.SupplierDemonstrator;
import com.xdesign.cake.task.FunctionalInterfaceTask;
import com.xdesign.cake.task.FunctionalInterfaceTaskResult;
import com.xdesign.cake.task.FunctionalInterfaceType;

@Component
public class FunctionalInterfaceTeacher {

	private final FunctionDemonstrator functionDemonstrator;

	private final ConsumerDemonstrator consumerDemonstrator;

	private final PredicateDemonstrator predicateDemonstrator;

	private final SupplierDemonstrator supplierDemonstrator;

	public static final String TYPE_NOT_RECOGNISED = "Type not recognised";

	public FunctionalInterfaceTeacher( final FunctionDemonstrator functionDemonstrator,
			final ConsumerDemonstrator consumerDemonstrator,
			final PredicateDemonstrator predicateDemonstrator,
			final SupplierDemonstrator supplierDemonstrator ) {
		this.functionDemonstrator = functionDemonstrator;
		this.consumerDemonstrator = consumerDemonstrator;
		this.predicateDemonstrator = predicateDemonstrator;
		this.supplierDemonstrator = supplierDemonstrator;
	}

	public FunctionalInterfaceTaskResult runLearningMaterial(
			final FunctionalInterfaceTask functionTask ) {

		return FunctionalInterfaceTaskResult.builder()
				.type( functionTask.getTaskType() )
				.value( demoFunction( functionTask.getTaskType(), functionTask.getParameters() ) )
				.build();
	}

	public String demoFunction( final FunctionalInterfaceType type, final List<String> input ) {

		switch ( type ) {
		case FUNCTION:
			return functionDemonstrator.demoFunction( input );
		case CONSUMER:
			return consumerDemonstrator.demoFunction( input );
		case PREDICATE:
			return String.valueOf( predicateDemonstrator.demoFunction( input ) );
		case SUPPLIER:
			return supplierDemonstrator.demoFunction();
		default:
			return TYPE_NOT_RECOGNISED;
		}
	}
}
