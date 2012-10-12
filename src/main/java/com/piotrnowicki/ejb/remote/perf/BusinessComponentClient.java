package com.piotrnowicki.ejb.remote.perf;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.piotrnowicki.ejb.remote.perf.bll.BusinessComponentLocal;
import com.piotrnowicki.ejb.remote.perf.bll.BusinessComponentRemote;
import com.piotrnowicki.ejb.remote.perf.bll.Entity;

@Stateless
public class BusinessComponentClient {

	@EJB
	private BusinessComponentLocal localBC;

	@EJB
	private BusinessComponentRemote remoteBC;

	public void executeRemoteBCOperation() {
		remoteBC.doExecute();
	}

	public void executeRemoteBCOperationWithObject() {
		remoteBC.doExecute(Entity.build());
	}

	public void executeLocalBCOperation() {
		localBC.doExecute();
	}

	public void executeLocalBCOperationWithObject() {
		localBC.doExecute(Entity.build());
	}
}
