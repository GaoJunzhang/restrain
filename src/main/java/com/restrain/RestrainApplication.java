package com.restrain;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
public class RestrainApplication {

	private static ImmutableMap<String, String> errorCodeMap = null;
	static {
		try {
			Properties prop = PropertiesLoaderUtils.loadAllProperties("error_code.properties");
			errorCodeMap = Maps.fromProperties(prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(RestrainApplication.class, args);
	}
	@Bean
	public ImmutableMap<String, String> errorCodeMap(){
		return errorCodeMap;
	}
}
