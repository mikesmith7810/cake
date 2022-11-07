package com.xdesign.cake.teachers;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.xdesign.cake.task.Task;
import com.xdesign.cake.task.TaskResult;

@Component
public class FunctionalInterfaceTeacher {
	public TaskResult runLearningMaterial( final Task functionTask ) {

		return TaskResult.builder()
				.type( functionTask.getTaskType() )
				.value( demoFunction( functionTask.getParameter() ) )
				.build();
	}

	public String demoFunction( final String input ) {

		Function<String, String> reverser = s -> {
			StringBuilder stringBuilder = new StringBuilder( s );

			return stringBuilder.reverse().toString();
		};

		return reverser.apply( input );
	}
}
