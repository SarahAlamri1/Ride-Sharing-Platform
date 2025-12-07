package API.Gateway.Service.API.Gateway.Service.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.rewritePath;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.addRequestHeader;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.addResponseHeader;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@Configuration
public class GatewayRoutes {

    @Autowired
    private HttpSession session;

    @Bean
    public RouterFunction<ServerResponse> customRoutes() {
        return

                route("customer_route")
                        .route(path("/customer/**"), http())
                        .before(uri("http://customer-service:8081"))
                        .before(rewritePath("/(?<segment>.*)", "/${segment}"))
                        .filter((request, next) -> {
                            Object sessionUser = session.getAttribute("username");
                            String username = sessionUser.toString();
                            System.out.println("username: "+  username);
                            ServerRequest newRequest = ServerRequest.from(request)
                                    .header("X-USERNAME", username)
                                    .build();
                            return next.handle(newRequest);

                        })
                        .filter(addResponseHeader("X-Powered-By", "Gateway-Service"))
                        .filter(addRequestHeader("X-API-KEY", "my-secret-key"))
                        .build()
                        .and(

                                route("driver_route")
                                        .route(path("/driver/**"), http())
                                        .before(uri("http://driver-service:8082"))
                                        .before(rewritePath("/(?<segment>.*)", "/${segment}"))
                                        .filter((request, next) -> {
                                            Object sessionUser = session.getAttribute("username");
                                            String username = sessionUser.toString();
                                            System.out.println("username: "+  username);
                                            ServerRequest newRequest = ServerRequest.from(request)
                                                    .header("X-USERNAME", username)
                                                    .build();
                                            return next.handle(newRequest);

                                        })
                                        .filter(addResponseHeader("X-Powered-By", "Gateway-Service"))
                                        .filter(addRequestHeader("X-API-KEY", "my-secret-key"))
                                        .build()
                        );
    }
}

