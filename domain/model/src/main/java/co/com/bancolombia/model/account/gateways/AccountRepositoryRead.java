package co.com.bancolombia.model.account.gateways;

import co.com.bancolombia.model.account.Account;
import reactor.core.publisher.Mono;

public interface AccountRepositoryRead {

    Mono<Account> findById(long id);
}
