package com.xdesign.cake.task;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OptionalTaskResult {
	final private OptionalType type;
	final private String value;

}
