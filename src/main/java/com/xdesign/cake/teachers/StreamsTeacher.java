package com.xdesign.cake.teachers;

import static com.xdesign.cake.teachers.FunctionalInterfaceTeacher.TYPE_NOT_RECOGNISED;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xdesign.cake.demonstrators.streams.ForEachDemonstrator;
import com.xdesign.cake.task.StreamsTask;
import com.xdesign.cake.task.StreamsTaskResult;
import com.xdesign.cake.task.StreamsType;

@Component
public class StreamsTeacher {
	private final ForEachDemonstrator forEachDemonstrator;

	public StreamsTeacher( final ForEachDemonstrator forEachDemonstrator ) {
		this.forEachDemonstrator = forEachDemonstrator;
	}

	public StreamsTaskResult runLearningMaterial( StreamsTask task ) {
		return StreamsTaskResult.builder()
				.type( task.getTaskType() )
				.value( demoFunction( task.getTaskType(), task.getParameters() ) )
				.build();
	}

	public String demoFunction( final StreamsType type, final List<String> input ) {

		switch ( type ) {
		case FOREACH:
			return forEachDemonstrator.demoFunction( input );
		default:
			return TYPE_NOT_RECOGNISED;
		}
	}
}
