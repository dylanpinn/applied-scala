package com.reagroup.appliedscala.config

import cats.data.ValidatedNel
import cats.implicits._

case class DatabaseConfig(
  host: String,
  username: String,
  password: SensitiveValue[String],
  databaseName: String
)

object DatabaseConfig {
  def apply(env: Environment): ValidatedNel[ConfigError, DatabaseConfig] = {
    val host = env.optional("DATABASE_HOST", "localhost")
    val username = env.optional("DATABASE_USERNAME", "moviedb")
    val password = env.optional("DATABASE_PASSWORD", "moviedb").map(SensitiveValue.apply)
    val databaseName = env.optional("DATABASE_NAME", "moviedb")

    (host, username, password, databaseName).mapN(DatabaseConfig.apply)
  }
}
