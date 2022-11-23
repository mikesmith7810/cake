package com.xdesign.cake.teachers;

import org.springframework.stereotype.Component;

import com.xdesign.cake.functionalinterface.FunctionDemonstrator;
import com.xdesign.cake.task.FunctionalInterfaceType;
import com.xdesign.cake.task.Task;
import com.xdesign.cake.task.TaskResult;

@Component
public class FunctionalInterfaceTeacher {

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
			FunctionDemonstrator functionDemonstrator = new FunctionDemonstrator();
			return functionDemonstrator.demoFunction( input );
		default:
			return TYPE_NOT_RECOGNISED;
		}
	}
}
