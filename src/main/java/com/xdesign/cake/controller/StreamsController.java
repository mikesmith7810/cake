package com.xdesign.cake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xdesign.cake.task.Task;
import com.xdesign.cake.task.TaskResult;
import com.xdesign.cake.teachers.StreamsTeacher;

@RestController
public class StreamsController {

	@Autowired
	private StreamsTeacher streamsTeacher;

	@GetMapping("/java/streams")
	public TaskResult runLearningMaterial( @RequestBody final Task task ) {
		return streamsTeacher.teachThis( task );
	}
}
