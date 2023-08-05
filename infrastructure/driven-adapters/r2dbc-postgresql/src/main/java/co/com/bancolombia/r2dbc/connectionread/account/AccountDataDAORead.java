package co.com.bancolombia.r2dbc.connectionread.account;

import co.com.bancolombia.r2dbc.repository.account.data.AccountData;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AccountDataDAORead extends ReactiveCrudRepository<AccountData, Long>, ReactiveQueryByExampleExecutor<AccountData> {
}
