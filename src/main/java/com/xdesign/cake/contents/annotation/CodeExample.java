package com.xdesign.cake.contents.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.xdesign.cake.task.TaskType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CodeExample {
	String name();

	String description();

	String api();

	String chapter();

	String githubLocation();

	TaskType taskType();

	String slashCommand();

}
