package com.xdesign.cake.contents;

import org.springframework.stereotype.Component;

import com.xdesign.cake.domain.Contents;

import lombok.Data;

@Data
@Component
public class ContentsStore {
    private Contents contents;
}
