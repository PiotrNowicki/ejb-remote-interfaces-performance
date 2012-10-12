package com.piotrnowicki.ejb.remote.perf.bll;

public interface BusinessComponent {

	public void doExecute();
	
	public Entity doExecute(Entity e);
}
