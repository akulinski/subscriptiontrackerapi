package com.akulinski.subscriptiontrackerapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class PostgresqlContainerExtension implements BeforeAllCallback, AfterAllCallback {

  private static AtomicBoolean started = new AtomicBoolean(false);
  private PostgreSQLContainer postgres;

  @Override
  public void beforeAll(ExtensionContext extensionContext) {
    if (!started.get()) {

      log.info("Starting POSTGERES container");

      postgres =
          (PostgreSQLContainer)
              new PostgreSQLContainer("postgres:12.2")
                  .withDatabaseName("subscriptiontrackerapi")
                  .withUsername("subscriptiontrackerapi")
                  .withPassword("subscriptiontrackerapi")
                  .withExposedPorts(5432);

      postgres.start();

      log.info("POSTGERES started with ip {}", postgres.getContainerIpAddress());

      log.info("Address {}", postgres.getContainerIpAddress());

      System.setProperty("DB_HOST", postgres.getContainerIpAddress());
      System.setProperty("DB_PORT", "5432");

      started.set(true);
    }
  }

  @Override
  public void afterAll(ExtensionContext extensionContext) {
    if (postgres != null) {
      postgres.close();
    }
  }
}
