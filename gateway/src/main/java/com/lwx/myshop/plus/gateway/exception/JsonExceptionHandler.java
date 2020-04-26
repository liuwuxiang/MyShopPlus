package com.lwx.myshop.plus.gateway.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: 全局系统 Json异常处理
 *
 * @author: 刘武祥
 * @Date: 2020/4/24 0024 09:18
 */
public class JsonExceptionHandler implements ErrorWebExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(JsonExceptionHandler.class);

    private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();
    private List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();
    private List<ViewResolver> viewResolvers = Collections.emptyList();
    private ThreadLocal<Map<String,Object>> exceptionHandlerResult = new ThreadLocal<>();

    public void setMessageReaders(List<HttpMessageReader<?>> messageReaders) {
        //断言不能为null
        Assert.notNull(messageReaders,"'messageReaders' must not be null");
        this.messageReaders = messageReaders;
    }

    public void setMessageWriters(List<HttpMessageWriter<?>> messageWriters) {
        Assert.notNull(messageWriters,"'messageWriters' must not be null");
        this.messageWriters = messageWriters;
    }

    public void setViewResolvers(List<ViewResolver> viewResolvers) {
        this.viewResolvers = viewResolvers;
    }

    /**
     * 功能描述: 读取服务器错误响应
     *
     * @param request  {@link ServerResponse} 服务器响应
     * @return Mono<ServerResponse> {@link Mono}
     * @auther 刘武祥
     * @date 2020/4/24 0024 9:47
     */
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        //获取异常处理线程返回结果
        Map<String,Object> result = exceptionHandlerResult.get();
        return ServerResponse.status((HttpStatus) result.get("httpStatus"))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(result.get("body")));
    }

    /**
     * 功能描述: 写入服务器响应
     *
     * @param exchange {@link ServerWebExchange}  服务器Web交换
     * @param response {@link ServerResponse}     服务器响应
     * @return Mono<? extends Void> {@link Mono}
     * @auther 刘武祥
     * @date 2020/4/24 0024 9:57
     */
    private Mono<? extends Void> write(ServerWebExchange exchange, ServerResponse response) {
        exchange.getResponse().getHeaders().setContentType(response.headers().getContentType());
        return response.writeTo(exchange,new ResponseContext());
    }

    /**
     * 功能描述: 响应上下文
     *
     * @auther 刘武祥
     * @date 2020/4/24 0024 10:07
     */
    private class ResponseContext implements ServerResponse.Context {
        @Override
        public List<HttpMessageWriter<?>> messageWriters() {
            return JsonExceptionHandler.this.messageWriters;
        }

        @Override
        public List<ViewResolver> viewResolvers() {
            return JsonExceptionHandler.this.viewResolvers;
        }
    }

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        //按照异常类型进行处理
        HttpStatus httpStatus;
        String body;
        // 如果找不到抛出去的异常
        if (throwable instanceof NotFoundException){
            //请求状态码为 找不到 404
            httpStatus = HttpStatus.NOT_FOUND;
            //无法提供服务
            body = "Service Not Found";
        }

        else {
            //请求状态码为 服务器内部错误 505
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            // 服务器内部错误
            body = "internal server error";
        }

        // 封装响应结果
        Map<String,Object> result = new HashMap<>(2,1);
        result.put("httpStatus",httpStatus);
        String msg = "{\"code\":" + httpStatus.value() + ",\"message\": \"" + body + "\"}";
        result.put("body",msg);

        //错误日志
        ServerHttpRequest request = serverWebExchange.getRequest();
        logger.error("[全局系统异常]\r\n请求路径: {}\r\n异常记录: {}", request.getPath(), throwable.getMessage());

        if (serverWebExchange.getResponse().isCommitted()){
            return Mono.error(throwable);
        }
        //设置异常处理响应结果
        exceptionHandlerResult.set(result);
        ServerRequest newRequest = ServerRequest.create(serverWebExchange, this.messageReaders);
        return RouterFunctions.route(RequestPredicates.all(),this::renderErrorResponse).route(newRequest)
                .switchIfEmpty(Mono.error(throwable))
                .flatMap((handle) -> handle.handle(newRequest))
                .flatMap((response) -> write(serverWebExchange,response));
    }
}
