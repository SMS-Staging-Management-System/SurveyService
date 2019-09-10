package com.revature.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public class DeepFieldFilter extends SimpleBeanPropertyFilter {
	private final int maxDepth;

	public DeepFieldFilter(int maxDepth) {
		super();
		this.maxDepth = maxDepth;
	}

<<<<<<< HEAD
	private int calcDepth(JsonGenerator jgen) {
=======

	private int calcDepth(JsonGenerator jgen) {

>>>>>>> c5b8422c1238fafb1631a39cb4d9c5740b082e5e
		JsonStreamContext sc = jgen.getOutputContext();
		int depth = -1;
		while (sc != null) {
			sc = sc.getParent();
			depth++;
		}
		return depth;
	}

	@Override
	public void serializeAsField(Object pojo, JsonGenerator gen, SerializerProvider provider, PropertyWriter writer)
			throws Exception {
<<<<<<< HEAD
		int depth = calcDepth(gen);
=======

		int depth = calcDepth(gen);

>>>>>>> c5b8422c1238fafb1631a39cb4d9c5740b082e5e
		if (depth <= maxDepth) {
			writer.serializeAsField(pojo, gen, provider);
		}
		// comment this if you don't want {} placeholders
		else {
			writer.serializeAsOmittedField(pojo, gen, provider);
		}
	}

}