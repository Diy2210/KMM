package net.multipul.kmm

import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import net.multipul.kmm.shared.newInstance
import net.multipul.kmm.shared.schema

interface Database : Transacter {
  val serverQueries: ServerQueries

  companion object {
    val Schema: SqlDriver.Schema
      get() = Database::class.schema

    operator fun invoke(driver: SqlDriver): Database = Database::class.newInstance(driver)}
}
