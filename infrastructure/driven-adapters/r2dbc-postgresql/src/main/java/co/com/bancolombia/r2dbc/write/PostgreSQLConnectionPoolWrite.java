package co.com.bancolombia.r2dbc.write;

import java.time.Duration;

import co.com.bancolombia.r2dbc.config.PostgresqlConnectionProperties;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;

@Configuration
@EnableR2dbcRepositories(basePackages = "co.com.bancolombia.r2dbc.write", entityOperationsRef = "postgresR2dbcEntityOperationsWrite")
public class PostgreSQLConnectionPoolWrite {

	public static final int INITIAL_SIZE = 12;
	public static final int MAX_SIZE = 15;
	public static final int MAX_IDLE_TIME = 30;

	@Bean
	@Qualifier("write")
	public ConnectionPool getConnectionConfig() {

		PostgresqlConnectionProperties pgProperties = new PostgresqlConnectionProperties();
		pgProperties.setDatabase("postgres");
		pgProperties.setHost("localhost");
		pgProperties.setPort(5433);
		pgProperties.setUsername("postgres");
		pgProperties.setPassword("pass");
		pgProperties.setSchema("public");

		return buildConnectionConfiguration(pgProperties);
	}

	@Bean
	public R2dbcEntityOperations postgresR2dbcEntityOperationsWrite(@Qualifier("write") ConnectionFactory connectionFactory) {

		DatabaseClient databaseClient = DatabaseClient.create(connectionFactory);
		return new R2dbcEntityTemplate(databaseClient, PostgresDialect.INSTANCE);
	}

	private ConnectionPool buildConnectionConfiguration(PostgresqlConnectionProperties properties) {
		PostgresqlConnectionConfiguration dbConfiguration = PostgresqlConnectionConfiguration.builder()
				.host(properties.getHost())
				.port(properties.getPort())
				.database(properties.getDatabase())
				.schema(properties.getSchema())
				.username(properties.getUsername())
				.password(properties.getPassword())
				.build();

		ConnectionPoolConfiguration poolConfiguration = ConnectionPoolConfiguration.builder()
				.connectionFactory(new PostgresqlConnectionFactory(dbConfiguration))
				.name("api-postgres-connection-pool-write")
				.initialSize(INITIAL_SIZE)
				.maxSize(MAX_SIZE)
				.maxIdleTime(Duration.ofMinutes(MAX_IDLE_TIME))
				.validationQuery("SELECT 1")
				.build();

		return new ConnectionPool(poolConfiguration);
	}
}