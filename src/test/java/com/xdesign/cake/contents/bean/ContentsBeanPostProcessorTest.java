package com.xdesign.cake.contents.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xdesign.cake.demonstrators.functionalinterface.FunctionDemonstrator;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ContentsBeanPostProcessorTest {
    private ContentsBeanPostProcessor contentsBeanPostProcessor;

    @BeforeEach
    public void setup(){
        contentsBeanPostProcessor = new ContentsBeanPostProcessor();
    }

    @Test
    public void shouldProcessAnnotatedClass(){
        Object object = contentsBeanPostProcessor.postProcessAfterInitialization(FunctionDemonstrator.class, "functionDemonstrator");

    }

}
