package com.xdesign.cake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xdesign.cake.task.Task;
import com.xdesign.cake.task.TaskResult;
import com.xdesign.cake.teachers.FunctionalInterfaceTeacher;

@RestController
public class FunctionalInterfacesController {

	@Autowired
	private FunctionalInterfaceTeacher functionalInterfaceTeacher;

	@GetMapping("/java/functionalinterface")
	public TaskResult runLearningMaterial( @RequestBody final Task task ) {
		return functionalInterfaceTeacher.teachThis( task );
	}
}
