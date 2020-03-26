package com.br.robson.core.converter;

import java.util.ArrayList;
import java.util.List;


import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;


public class ConverterObject {
	
private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	/**
	 * Converte um Objeto Person
	 * @param <O>
	 * @param <D>
	 * @param origin
	 * @param destination
	 * @return
	 */
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}	
	
	
	/**
	 * Converte uma ListaPerson para ListaVO
	 * 
	 * @param <O>
	 * @param <D>
	 * @param origin
	 * @param destination
	 * @return
	 */
	public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();
		for (Object o : origin) {
			destinationObjects.add(mapper.map(o, destination));
		}
		return destinationObjects;
	}
}