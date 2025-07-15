package dev.trela.gatewayserver.filters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Order(1)
@Component
@Slf4j
@RequiredArgsConstructor
public class RequestTraceFilter implements GlobalFilter {

    private final FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if(isCorrelationIdPresent(requestHeaders)){
            log.debug("trelaBank-correlation-id found in RequestTraceFilter: {}",
                    filterUtility.getCorrelationId(requestHeaders));
        }else{
            String correlationId = generateCorrelationId();
            exchange = filterUtility.setCorrelationId(exchange,correlationId);
            log.debug("trelaBank-correlation-id generated in RequestTraceFilter: {}" , correlationId);
        }
        return chain.filter(exchange);
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders){
        if(filterUtility.getCorrelationId(requestHeaders) != null){
            return true;
        }else{
            return false;
        }
    }

    private String generateCorrelationId(){
        return UUID.randomUUID().toString();
    }

}
