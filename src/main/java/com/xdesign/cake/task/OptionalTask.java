package com.xdesign.cake.task;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionalTask {
    final private OptionalType taskType;
	final private List<String> parameters;
}
