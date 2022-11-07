package com.xdesign.cake.task;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Task {
	final private String taskType;
	final private String parameter;
}
