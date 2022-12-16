package com.xdesign.cake.teachers;

import static com.xdesign.cake.teachers.FunctionalInterfaceTeacher.TYPE_NOT_RECOGNISED;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import com.xdesign.cake.contents.ContentsStore;
import com.xdesign.cake.demonstrators.optional.CreationDemonstrator;
import com.xdesign.cake.domain.Example;
import com.xdesign.cake.task.Task;
import com.xdesign.cake.task.TaskResult;
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

	public TaskResult teachThis( final Task task ) {

		final Example example = getExampleBasedOn( task );

		return TaskResult.builder()
				.type( task.getTaskType() )
				.value( runTaskBasedUponType( task.getTaskType(), task.getParameters() ) )
				.sourceCode(
						example
						.getSourceCode() )
				.description( example
						.getDescription() )
				.build();
	}

	public String runTaskBasedUponType( final TaskType type, final List<String> input ) {

		switch ( type ) {
		case CREATION:
			return creationDemonstrator.runExampleFor( input ).get();
		default:
			return TYPE_NOT_RECOGNISED;
		}
	}

	@NotNull
	private Example getExampleBasedOn( final Task task ) {
		return contentsStore.retrieveContents()
				.getChapters()
				.stream()
				.map( chapter -> chapter.getExamples() )
				.flatMap( examples -> examples.stream()
						.filter( example -> example.getTaskType().equals( task.getTaskType() ) ) )
				.findFirst()
				.get();
	}
}
