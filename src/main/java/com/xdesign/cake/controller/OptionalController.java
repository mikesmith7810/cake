package com.xdesign.cake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xdesign.cake.task.OptionalTask;
import com.xdesign.cake.task.OptionalTaskResult;
import com.xdesign.cake.teachers.OptionalTeacher;

@RestController
public class OptionalController {

	@Autowired
	private OptionalTeacher optionalTeacher;

	@GetMapping("/java/optional")
	public OptionalTaskResult runLearningMaterial( @RequestBody final OptionalTask task ) {
		return OptionalTaskResult.builder()
				.type( task.getTaskType() )
				.value( optionalTeacher.runLearningMaterial( task ).getValue() )
				.build();
	}
}
