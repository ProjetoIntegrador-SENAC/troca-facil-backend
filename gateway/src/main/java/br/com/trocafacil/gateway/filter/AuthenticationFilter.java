package br.com.trocafacil.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private RestTemplate template;
    public AuthenticationFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
        if(routeValidator.isSecured.test(exchange.getRequest())){
            // Header contains token or not
            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                throw new RuntimeException("Token não enviado");
            }

            String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            // Check if header is not null
            if (authHeaders != null && authHeaders.startsWith("Bearer ")){
                authHeaders = authHeaders.substring(7);
            }
            System.out.println(authHeaders);
            try{
                // REST call to authentication service
                template.getForObject("http://localhost:8080/auth/token?token=" + authHeaders, ResponseEntity.class);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
            return chain.filter(exchange);
        }));
    }

    public static class Config{
    }
}
