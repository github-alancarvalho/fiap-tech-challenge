package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;


public class ConnectionPoolManagerORM {

    public EntityManagerFactory entityManagerFactory;

    public ConnectionPoolManagerORM(){
        this.entityManagerFactory = getEntityManagerProperties();
    }

    private static HikariDataSource dataSource;

    @Bean
    public static synchronized HikariDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new HikariDataSource();
            dataSource.setJdbcUrl("jdbc:mariadb://" + System.getenv("MARIADB_DATABASE_ENDPOINT") + "/" + System.getenv("MARIADB_DATABASE"));
            dataSource.setUsername(System.getenv("MARIADB_USER"));
            dataSource.setPassword(System.getenv("MARIADB_PASSWORD"));
            dataSource.setMaximumPoolSize(10); // Tamanho máximo do pool
            dataSource.setConnectionTimeout(5000); // Tempo limite de espera por conexão
            dataSource.setValidationTimeout(30000); // Tempo limite de validação de conexão
            dataSource.setDriverClassName("org.mariadb.jdbc.Driver"); // Mariadb driver class
        }
        return dataSource;
    }


    @Bean
    public synchronized EntityManagerFactory getEntityManagerProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.driver", "org.mariadb.jdbc.Driver");
        properties.put("javax.persistence.jdbc.url", "jdbc:mariadb://" + System.getenv("MARIADB_DATABASE_ENDPOINT") + "/" + System.getenv("MARIADB_DATABASE"));
        properties.put("javax.persistence.jdbc.user", System.getenv("MARIADB_USER"));
        properties.put("javax.persistence.jdbc.password", System.getenv("MARIADB_PASSWORD"));
        properties.put("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
        properties.put("hibernate.cache.use_second_level_cache", "false");

        return Persistence.createEntityManagerFactory("fiap-tech-challenge", properties);
    }
}