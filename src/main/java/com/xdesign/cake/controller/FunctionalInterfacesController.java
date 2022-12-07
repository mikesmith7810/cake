package com.xdesign.cake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xdesign.cake.task.FunctionalInterfaceTask;
import com.xdesign.cake.task.FunctionalInterfaceTaskResult;
import com.xdesign.cake.teachers.FunctionalInterfaceTeacher;

@RestController
public class FunctionalInterfacesController {

	@Autowired
	private FunctionalInterfaceTeacher functionalInterfaceTeacher;

	@GetMapping("/java/functionalinterface")
	public FunctionalInterfaceTaskResult runLearningMaterial(
			@RequestBody final FunctionalInterfaceTask task ) {
		return FunctionalInterfaceTaskResult.builder()
				.type( task.getTaskType() )
				.value( functionalInterfaceTeacher.runLearningMaterial( task ).getValue() )
				.build();
	}
}
