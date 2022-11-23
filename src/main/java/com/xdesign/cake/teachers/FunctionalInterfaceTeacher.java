package com.xdesign.cake.teachers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xdesign.cake.functionalinterface.ConsumerDemonstrator;
import com.xdesign.cake.functionalinterface.FunctionDemonstrator;
import com.xdesign.cake.functionalinterface.PredicateDemonstrator;
import com.xdesign.cake.task.FunctionalInterfaceType;
import com.xdesign.cake.task.Task;
import com.xdesign.cake.task.TaskResult;

@Component
public class FunctionalInterfaceTeacher {

	@Autowired
	private FunctionDemonstrator functionDemonstrator;

	@Autowired
	private ConsumerDemonstrator consumerDemonstrator;

	@Autowired
	private PredicateDemonstrator predicateDemonstrator;

	public static final String TYPE_NOT_RECOGNISED = "Type not recognised";

	public TaskResult runLearningMaterial( final Task functionTask ) {

		return TaskResult.builder()
				.type( functionTask.getTaskType() )
				.value( demoFunction( functionTask.getTaskType(), functionTask.getParameter() ) )
				.build();
	}

	public String demoFunction( final FunctionalInterfaceType type, final String input ) {

		switch ( type ) {
		case FUNCTION:
			return functionDemonstrator.demoFunction( input );
		case CONSUMER:
			return consumerDemonstrator.demoFunction( input );
		case PREDICATE:
			return String.valueOf( predicateDemonstrator.demoFunction( input ) );
		default:
			return TYPE_NOT_RECOGNISED;
		}
	}
}
