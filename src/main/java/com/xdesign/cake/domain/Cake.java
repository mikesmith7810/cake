package com.xdesign.cake.domain;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class Cake {
	private int id;
	private String name;
}
