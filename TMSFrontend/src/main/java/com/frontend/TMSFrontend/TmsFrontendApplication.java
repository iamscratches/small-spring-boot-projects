package com.frontend.TMSFrontend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@SpringBootApplication
@EnableOAuth2Client
public class TmsFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmsFrontendApplication.class, args);
	}

	private static final String AUTH_TOKEN_URL="/oauth/token";

	@Value("${iamscratches.TMS.service.url}")
	private String serviceUrl;

	@Bean
	public OAuth2RestTemplate restTemplate(){
		ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
		resource.setAccessTokenUri(serviceUrl + AUTH_TOKEN_URL);
		resource.setClientId("tms_app");
		resource.setClientSecret("secret");
		resource.setGrantType("client_credentials");
		resource.setScope(new ArrayList<>(){{add("READ_ALL_TMS"); add("WRITE_TMS"); add("UPDATE_TMS");}});
		resource.setAuthenticationScheme(AuthenticationScheme.form);
		AccessTokenRequest request = new DefaultAccessTokenRequest();

		return new OAuth2RestTemplate(resource, new DefaultOAuth2ClientContext(request));
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}
}
