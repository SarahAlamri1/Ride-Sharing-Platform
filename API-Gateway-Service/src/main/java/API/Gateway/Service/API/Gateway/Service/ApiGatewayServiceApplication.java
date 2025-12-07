package API.Gateway.Service.API.Gateway.Service;

//import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.servlet.function.RouterFunction;
//import org.springframework.web.servlet.function.ServerResponse;
//
//import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.rewritePath;
//import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
//import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.addRequestHeader;
//import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.addResponseHeader;
//import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
//import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
//import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;
import org.springframework.security.authentication.AuthenticationManager;

@SpringBootApplication
//@EnableRedisHttpSession
//@EnableDiscoveryClient
public class ApiGatewayServiceApplication {
//	private final API.Gateway.Service.API.Gateway.Service.config.test test;

//	public ApiGatewayServiceApplication(test test) {
//		this.test = test;
//	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServiceApplication.class, args);
	}

//	@Bean
//	public RouterFunction<ServerResponse> customRoutes() {
//
//
//		return
//				route("customer_route")
//						.route(path("/customer/**"), http())
//						// Downstream customer service
////						.before(uri("http://customer-service:8081"))
//						.before(uri("http://localhost:8081"))
//						.before(rewritePath("/(?<segment>.*)", "/${segment}"))
//						.filter(addResponseHeader("X-Powered-By", "Gateway-Service"))
//						.filter(addRequestHeader("X-API-KEY", "my-secret-key"))
//						.filter(addResponseHeader("X-USERNAME", test.userName()))
//						.build()
//
//						.and(route("driver_route")
//								.route(path("/driver/**"), http())
//								.before(uri("http://localhost:8082"))
////								.before(uri("http://driver-service:8082"))
//								.before(rewritePath("/(?<segment>.*)", "/${segment}"))
//								.filter(addResponseHeader("X-Powered-By", "Gateway-Service"))
//								.filter(addRequestHeader("X-API-KEY", "my-secret-key"))
//								.build());
//	}

}

