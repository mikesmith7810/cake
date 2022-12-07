package com.xdesign.cake.teachers;

import static com.xdesign.cake.teachers.FunctionalInterfaceTeacher.TYPE_NOT_RECOGNISED;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xdesign.cake.demonstrators.optional.CreationDemonstrator;
import com.xdesign.cake.task.OptionalTask;
import com.xdesign.cake.task.OptionalTaskResult;
import com.xdesign.cake.task.OptionalType;

@Component
public class OptionalTeacher {

	private final CreationDemonstrator creationDemonstrator;

	public OptionalTeacher( final CreationDemonstrator creationDemonstrator ) {
		this.creationDemonstrator = creationDemonstrator;
	}

	public OptionalTaskResult runLearningMaterial( final OptionalTask optionalTask ) {

		return OptionalTaskResult.builder()
				.type( optionalTask.getTaskType() )
				.value( demoFunction( optionalTask.getTaskType(), optionalTask.getParameters() ) )
				.build();
	}

	public String demoFunction( final OptionalType type, final List<String> input ) {

		switch ( type ) {
		case CREATION:
			return creationDemonstrator.demoFunction( input ).get();
		default:
			return TYPE_NOT_RECOGNISED;
		}
	}
}
