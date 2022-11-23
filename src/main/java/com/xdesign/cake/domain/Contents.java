package com.xdesign.cake.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Contents {
    private List<Chapter> chapters;
}
