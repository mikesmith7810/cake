package com.xdesign.cake.task;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TaskResult {
	final private FunctionalInterfaceType type;
	final private String value;

}
