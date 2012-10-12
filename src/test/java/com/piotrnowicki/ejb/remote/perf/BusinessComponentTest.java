package com.piotrnowicki.ejb.remote.perf;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.runner.RunWith;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;
import com.piotrnowicki.ejb.remote.perf.BusinessComponentClient;

@RunWith(Arquillian.class)
@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "benchmark-lists")
public class BusinessComponentTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		File[] dependencies = DependencyResolvers
				.use(MavenDependencyResolver.class)
				.includeDependenciesFromPom("pom.xml").resolveAsFiles();

		WebArchive archive = ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(true, BusinessComponentClient.class.getPackage())
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		archive.addAsLibraries(dependencies);

		return archive;
	}

	@Inject
	BusinessComponentClient bcClient;

	@Rule
	public MethodRule benchmarkRun = new BenchmarkRule();

	private final static int benchmarkRounds = 100;
	private final static int warmupRounds = 5;
	private final static boolean callgc = false;

	@Test
	@BenchmarkOptions(benchmarkRounds = benchmarkRounds, warmupRounds = warmupRounds, callgc = callgc)
	public void testLocal() {
		bcClient.executeLocalBCOperation();
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = benchmarkRounds, warmupRounds = warmupRounds, callgc = callgc)
	public void testLocalWithObject() {
		bcClient.executeLocalBCOperationWithObject();
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = benchmarkRounds, warmupRounds = warmupRounds, callgc = callgc)
	public void testRemote() {
		bcClient.executeRemoteBCOperation();
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = benchmarkRounds, warmupRounds = warmupRounds, callgc = callgc)
	public void testRemoteWithObject() {
		bcClient.executeRemoteBCOperationWithObject();
	}
}
