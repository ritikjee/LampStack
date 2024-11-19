package tech.lampstack.api_gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Value("${services.user_service.url}")
    private String userServiceUrl;

    @Value("${variables.x_application_secret}")
    private String xApplicationSecret;

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return GatewayRouterFunctions
                .route("user_service")
                .route(RequestPredicates.path("/api/user/**"), request -> {
                    ServerRequest newRequest = ServerRequest.from(request)
                            .header("x-app-secret", xApplicationSecret)
                            .build();

                    return HandlerFunctions.http(userServiceUrl)
                            .handle(newRequest);
                })
                .build();
    }

}
