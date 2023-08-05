package co.com.bancolombia.api;

import co.com.bancolombia.model.account.gateways.AccountRepositoryRead;
import co.com.bancolombia.model.account.gateways.AccountRepositoryWrite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    public final AccountRepositoryWrite accountRepositoryWrite;
    public final AccountRepositoryRead accountRepositoryRead;

    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
        return accountRepositoryWrite.findById(4000)
                .flatMap(account -> ServerResponse.ok().bodyValue(account));
    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        return accountRepositoryRead.findById(4000)
                .flatMap(account -> ServerResponse.ok().bodyValue(account));
    }
}
