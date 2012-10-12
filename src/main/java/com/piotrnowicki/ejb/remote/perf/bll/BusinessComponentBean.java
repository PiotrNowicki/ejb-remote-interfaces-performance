package com.piotrnowicki.ejb.remote.perf.bll;

import javax.ejb.Stateless;

@Stateless
public class BusinessComponentBean implements BusinessComponentLocal,
		BusinessComponentRemote {

	// public int fundamentallyWrongVar = 0;

	@Override
	public void doExecute() {
		// Simulate that we're doing some business logic here
		// for (int i = 0; i < 10; i++) {
		// fundamentallyWrongVar += i;
		// }
	}

	@Override
	public Entity doExecute(Entity e) {
		// Don't waste time on creating the entity - just pass it back for
		// serialization once again
		return e;
	}
}
