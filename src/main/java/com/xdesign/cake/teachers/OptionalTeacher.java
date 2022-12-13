package com.xdesign.cake.teachers;

import static com.xdesign.cake.teachers.FunctionalInterfaceTeacher.TYPE_NOT_RECOGNISED;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xdesign.cake.contents.ContentsStore;
import com.xdesign.cake.demonstrators.optional.CreationDemonstrator;
import com.xdesign.cake.task.OptionalTask;
import com.xdesign.cake.task.OptionalTaskResult;
import com.xdesign.cake.task.TaskType;

@Component
public class OptionalTeacher {

	private final CreationDemonstrator creationDemonstrator;

	private final ContentsStore contentsStore;

	public OptionalTeacher( final CreationDemonstrator creationDemonstrator,
			final ContentsStore contentsStore ) {
		this.creationDemonstrator = creationDemonstrator;
		this.contentsStore = contentsStore;
	}

	public OptionalTaskResult teachThis( final OptionalTask optionalTask ) {

		return OptionalTaskResult.builder()
				.type( optionalTask.getTaskType() )
				.value( demoFunction( optionalTask.getTaskType(), optionalTask.getParameters() ) )
				.sourceCode( contentsStore.getContents()
						.getChapters()
						.stream()
						.map( chapter -> chapter.getExamples() )
						.flatMap( examples -> examples.stream()
								.filter( example -> example.getTaskType()
										.equals( optionalTask.getTaskType() ) ) )
						.findFirst()
						.get()
						.getSourceCode() )
				.build();
	}

	public String demoFunction( final TaskType type, final List<String> input ) {

		switch ( type ) {
		case CREATION:
			return creationDemonstrator.demoFunction( input ).get();
		default:
			return TYPE_NOT_RECOGNISED;
		}
	}
}
