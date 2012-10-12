package com.piotrnowicki.ejb.remote.perf.bll;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SuppressWarnings("unused")
public class Entity implements Serializable {

	private static final long serialVersionUID = 1;

	private String stringValue;
	private Integer intValue;
	private Entity parent;
	private List<BigDecimal> listValue;

	public static Entity build() {
		Entity e = new Entity();

		Random random = new Random();

		e.intValue = random.nextInt();
		e.stringValue = UUID.randomUUID().toString();
		e.parent = new Entity();
		e.listValue = new ArrayList<BigDecimal>();
		e.listValue.add(new BigDecimal(1.1));

		return e;
	}
}
