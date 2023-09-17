package com.jaeho.bulbul.netbackup.apiCallPreset;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

@Component
public class ReturnWebClient {

    // NetBackup 서버의 URL
    private static String netBackupURL;

    // application.yml 파일에서 NetBackup 서버의 URL을 가져온다.
    // 가져온 URL은 static 변수인 netBackupURL에 저장한다.
    @Value("${NetBackup.url}")
    public void setNetBackupURL(String netBackupURL) {
        ReturnWebClient.netBackupURL = netBackupURL;
    }

    /**
     * WebClient를 생성한다.
     * @return
     * @throws SSLException
     */
    public static WebClient preset() throws SSLException {

        // WebClient를 사용하기 위해서는 DefaultUriBuilderFactory를 사용해야 한다.
        // URI_COMPONENT 모드로 설정한다.
        // Encoding 모드 좀 더 확인해보기
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(netBackupURL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);

        // SSL 인증을 위한 설정
        // insecure 로 설정
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        // HttpClient를 생성한다.
        HttpClient httpClient = HttpClient.create()
                .secure(sslContextSpec -> sslContextSpec.sslContext(sslContext));

        // 필터 생성
        ExchangeFilterFunction errorResponseFilter = ExchangeFilterFunction
                .ofResponseProcessor(ReturnWebClient::exchangeFilterResponseProcessor);

        // WebClient를 생성한다.
        return WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(netBackupURL)
                .filter(errorResponseFilter)
                .defaultHeader("Content-Type", "application/vnd.netbackup+json;version=7.0")
                .defaultHeader("Accept", "application/vnd.netbackup+json;version=7.0")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    // 필터 생성 메서드
    private static Mono<ClientResponse> exchangeFilterResponseProcessor(ClientResponse response) {
        HttpStatus status = (HttpStatus) response.statusCode();
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new Exception(body)));
        }
        if (HttpStatus.BAD_REQUEST.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new Exception(body)));
        }
        if (HttpStatus.NOT_FOUND.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new Exception(body)));
        }
        return Mono.just(response);
    }
}
