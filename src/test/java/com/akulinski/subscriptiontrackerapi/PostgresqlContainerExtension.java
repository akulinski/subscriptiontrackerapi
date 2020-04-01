package com.akulinski.subscriptiontrackerapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class PostgresqlContainerExtension implements BeforeAllCallback {

  private static AtomicBoolean started = new AtomicBoolean(false);

  @Override
  public void beforeAll(ExtensionContext extensionContext) {
    if (!started.get()) {

      log.info("Starting POSTGERES container");

      PostgreSQLContainer postgres =
          new PostgreSQLContainer("postgres:12.2")
              .withDatabaseName("subscriptiontrackerapi")
              .withUsername("subscriptiontrackerapi")
              .withPassword("subscriptiontrackerapi");

      postgres.start();

      log.info("POSTGERES started with ip {}", postgres.getContainerIpAddress());

      log.info("Address {}", postgres.getJdbcUrl());
      System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
      started.set(true);
    }
  }
}
