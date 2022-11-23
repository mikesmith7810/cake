package com.xdesign.cake.contents.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.xdesign.cake.contents.ContentsStore;
import com.xdesign.cake.contents.annotation.CodeExample;
import com.xdesign.cake.domain.Chapter;
import com.xdesign.cake.domain.Contents;
import com.xdesign.cake.domain.Example;
import com.xdesign.cake.functionalinterface.Demonstrator;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ContentsBeanPostProcessor implements BeanPostProcessor {

	@Autowired
	private ContentsStore contentsStore;

	@Override
	public Object postProcessAfterInitialization( final Object bean, final String beanName )
			throws BeansException {
		final Class<?> beanClass = AopUtils.getTargetClass( bean );

		if ( beanClass.getAnnotations() == null || !( bean instanceof Demonstrator ) ) {
			return bean;
		}

		final List<Example> examples = Arrays.stream( beanClass.getDeclaredMethods() )
				.filter( method -> method.isAnnotationPresent( CodeExample.class ) )
				.map( method -> method.getAnnotation( CodeExample.class ) )
				.map( annotation -> Example.builder()
						.description( annotation.description() )
						.githubLocation( annotation.githubLocation() )
						.apiCall( annotation.api() )
						.build() )
				.collect( Collectors.toList() );

		examples.stream().forEach( example -> addExample( example ) );

		return bean;
	}
//Need to refactor this properly!!!!
	private void addExample( Example example ) {
		if ( contentsStore.getContents() == null )
			contentsStore.setContents( Contents.builder()
					.chapters( new ArrayList<>( List.of( Chapter.builder()
							.examples( new ArrayList<>( List.of( example ) ) )
							.build() ) ) )
					.build() );
		else
			contentsStore.getContents().getChapters().get( 0 ).getExamples().add( example );
	}
}
