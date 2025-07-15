package dev.trela.gatewayserver.filters;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ResponseTraceFilter {

    private final FilterUtility filterUtility;

    @Bean
    public GlobalFilter postGlobalFilter(){
        return (exchange,chain) ->{
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
                String correlationId = filterUtility.getCorrelationId(requestHeaders);
                log.debug("Updated the correlation id in to the outbound headers: {}" ,correlationId);
                exchange.getResponse().getHeaders().add(filterUtility.CORRELATION_ID, correlationId);
            }));
        };
    }

}
