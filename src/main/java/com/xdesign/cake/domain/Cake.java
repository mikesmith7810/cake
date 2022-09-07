package com.xdesign.cake.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cake {
	private int id;
	private String name;
}
