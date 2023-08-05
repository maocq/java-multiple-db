package co.com.bancolombia.r2dbc.repository.account;

import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepositoryWrite;
import co.com.bancolombia.r2dbc.repository.account.data.AccountData;
import co.com.bancolombia.r2dbc.connectionwrite.account.AccountDataDAOWrite;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class AccountDataRepositoryWrite implements AccountRepositoryWrite {

    private final AccountDataDAOWrite repositoryWrite;
    private final ObjectMapper mapper;

    @Override
    public Mono<Account> findById(long id) {
        return repositoryWrite.findById(id)
                .map(this::toEntity);
    }


    private Account toEntity(AccountData data) {
        return mapper.mapBuilder(data, Account.AccountBuilder.class).build();
    }

    private AccountData toData(Account entity) {
        return mapper.map(entity, AccountData.class);
    }
}
