package com.xdesign.cake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xdesign.cake.task.StreamsTask;
import com.xdesign.cake.task.StreamsTaskResult;
import com.xdesign.cake.teachers.StreamsTeacher;

@RestController
public class StreamsController {

	@Autowired
	private StreamsTeacher streamsTeacher;

	@GetMapping("/java/streams")
	public StreamsTaskResult runLearningMaterial(@RequestBody final StreamsTask task ) {
		return StreamsTaskResult.builder()
				.type( task.getTaskType() )
				.value( streamsTeacher.runLearningMaterial( task ).getValue() )
				.build();
	}
}
