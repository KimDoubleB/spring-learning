# Keycloak - Spring security integration
SSO open-source 솔루션 key cloak 사용

### Spring security Integration

```groovy
implementation 'org.springframework.boot:spring-boot-starter-security'
implementation group: 'org.keycloak', name: 'keycloak-spring-security-adapter', version: '15.0.1'
```

```java

@Configuration
@EnableWebSecurity
public class KeycloakConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        // '/api/v1/permit' 하위 주소 요청을 제외한 모든 요청은 keycloak 인증을 거쳐야 함
        http.authorizeRequests()
                .antMatchers("/api/v1/permit/**").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {
        return (facade -> {
            var configInputStream = getClass().getResourceAsStream("/keycloak.json");
            return KeycloakDeploymentBuilder.build(configInputStream);
        }
        );
    }

}

```


### keycloak.json (`src/resources/`)
```json
{
  "realm": "bb",
  "auth-server-url": "http://localhost:8080/auth",
  "ssl-required": "external",
  "resource": "bb1",
  "credentials": {
    "secret": "293c9c1a-8416-4305-97d9-2c8be9216152"
  },
  "confidential-port": 0
}
```

-----

## Example
### Permit endpoint
![1](https://user-images.githubusercontent.com/37873745/130627480-899cc658-aa20-4953-9d4a-4b5c5236c578.png)

### Restrict endpoint
![2](https://user-images.githubusercontent.com/37873745/130627490-5f6dd022-48ef-4570-994f-037f1b595a0f.png)

