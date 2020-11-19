package com.example.testcontainers;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.DockerComposeContainer;

public class MainNoBug {

	private static final Logger logger = LoggerFactory.getLogger(MainNoBug.class);

	public static void main(String[] args) {
		DockerComposeContainer container = new DockerComposeContainer<>(new File(MainNoBug.class
			.getClassLoader()
			.getResource("docker-compose.yml")
			.getFile()
		)).withServices(
			"broker",
			"zookeeper",
			"schema-registry",
			"connect"
		)
			.withExposedService("broker", 1, 9092)
			.withExposedService("zookeeper", 1, 2181)
			.withExposedService("schema-registry", 1, 8081)
			.withExposedService("connect", 1, 8083);

		container.start();

		logger.info("Now look at `docker network ls`");
		logger.info("You should see a network with a random network name, something like " +
			"`dykbrjg1mblt_default`");
		logger.info("Check again in a few seconds after this process has ended.");
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
