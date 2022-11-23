package com.xdesign.cake.teachers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xdesign.cake.task.FunctionalInterfaceType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class FunctionalInterfaceTeacherTest {

    FunctionalInterfaceTeacher functionalInterfacesTeacher;

    @BeforeAll
    void setup(){
        functionalInterfacesTeacher = new FunctionalInterfaceTeacher();
    }

    @Test
    void shouldUseFunctionToReverseString(){
		final String result = functionalInterfacesTeacher
				.demoFunction( FunctionalInterfaceType.FUNCTION, "iamatest" );

        assertThat(result).isEqualTo("tsetamai");
    }
}
