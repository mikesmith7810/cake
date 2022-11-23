package com.xdesign.cake.task;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Task {
	final private FunctionalInterfaceType taskType;
	final private List<String> parameters;
}
