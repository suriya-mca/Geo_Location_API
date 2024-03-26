package com.geolocation.config.undertow;

import org.springframework.boot.web.embedded.undertow.UndertowDeploymentInfoCustomizer;
import org.springframework.context.annotation.Bean;
import java.util.concurrent.Executors;


public class UndertowConfig {

	@Bean
	public UndertowDeploymentInfoCustomizer undertowDeploymentInfoCustomizer() {
		return deploymentInfo -> deploymentInfo.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
	}

}