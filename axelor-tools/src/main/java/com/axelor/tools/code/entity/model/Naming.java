/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2005-2022 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.axelor.tools.code.entity.model;

import java.util.Set;

public class Naming {
  /** Check whether the given name is a reserved keyword. */
  public static boolean isReserved(String name) {
    return RESERVED_JAVA.contains(name) || RESERVED_EXTRA.contains(name);
  }

  /** Check whether the given name is SQL reserved keyword. */
  public static boolean isKeyword(String name) {
    return RESERVED_POSTGRESQL.contains(name)
        || RESERVED_MYSQL.contains(name)
        || RESERVED_ORACLE.contains(name);
  }

  /** Quote the given column name. */
  public static String quoteColumn(String name) {
    return "`" + name + "`";
  }

  // Internal Reserved
  private static final Set<String> RESERVED_EXTRA =
      Set.of("version", "archived", "selected", "constructor");

  // Java Keywords
  private static final Set<String> RESERVED_JAVA =
      Set.of(
          "abstract",
          "assert",
          "boolean",
          "break",
          "byte",
          "case",
          "catch",
          "char",
          "class",
          "const",
          "continue",
          "default",
          "do",
          "double",
          "else",
          "enum",
          "extends",
          "final",
          "finally",
          "float",
          "for",
          "goto",
          "if",
          "implements",
          "import",
          "instanceof",
          "int",
          "interface",
          "long",
          "native",
          "new",
          "package",
          "private",
          "protected",
          "public",
          "return",
          "short",
          "static",
          "strictfp",
          "super",
          "switch",
          "synchronized",
          "this",
          "throw",
          "throws",
          "transient",
          "try",
          "void",
          "volatile",
          "while");

  // PostgreSQL 9.6 to PostgreSQL 12
  private static final Set<String> RESERVED_POSTGRESQL =
      Set.of(
          "all",
          "analyse",
          "analyze",
          "and",
          "any",
          "array",
          "as",
          "asc",
          "asymmetric",
          "both",
          "case",
          "cast",
          "check",
          "collate",
          "column",
          "constraint",
          "create",
          "current_catalog",
          "current_date",
          "current_role",
          "current_time",
          "current_timestamp",
          "current_user",
          "default",
          "deferrable",
          "desc",
          "distinct",
          "do",
          "else",
          "end",
          "except",
          "false",
          "fetch",
          "for",
          "foreign",
          "from",
          "grant",
          "group",
          "having",
          "in",
          "initially",
          "intersect",
          "into",
          "lateral",
          "leading",
          "limit",
          "localtime",
          "localtimestamp",
          "not",
          "null",
          "offset",
          "on",
          "only",
          "or",
          "order",
          "placing",
          "primary",
          "references",
          "returning",
          "select",
          "session_user",
          "some",
          "symmetric",
          "table",
          "then",
          "to",
          "trailing",
          "true",
          "union",
          "unique",
          "user",
          "using",
          "variadic",
          "when",
          "where",
          "window",
          "with");

  //  MySQL 5.7 & MySQL 8.0
  private static final Set<String> RESERVED_MYSQL =
      Set.of(
          "accessible",
          "add",
          "all",
          "alter",
          "analyze",
          "and",
          "as",
          "asc",
          "asensitive",
          "before",
          "between",
          "bigint",
          "binary",
          "blob",
          "both",
          "by",
          "call",
          "cascade",
          "case",
          "change",
          "char",
          "character",
          "check",
          "collate",
          "column",
          "condition",
          "constraint",
          "continue",
          "convert",
          "create",
          "cross",
          "cube",
          "cume_dist",
          "current_date",
          "current_time",
          "current_timestamp",
          "current_user",
          "cursor",
          "database",
          "databases",
          "day_hour",
          "day_microsecond",
          "day_minute",
          "day_second",
          "dec",
          "decimal",
          "declare",
          "default",
          "delayed",
          "delete",
          "dense_rank",
          "desc",
          "describe",
          "deterministic",
          "distinct",
          "distinctrow",
          "div",
          "double",
          "drop",
          "dual",
          "each",
          "else",
          "elseif",
          "empty",
          "enclosed",
          "escaped",
          "except",
          "exists",
          "exit",
          "explain",
          "false",
          "fetch",
          "first_value",
          "float",
          "float4",
          "float8",
          "for",
          "force",
          "foreign",
          "from",
          "fulltext",
          "function",
          "generated",
          "get",
          "grant",
          "group",
          "grouping",
          "groups",
          "having",
          "high_priority",
          "hour_microsecond",
          "hour_minute",
          "hour_second",
          "if",
          "ignore",
          "in",
          "index",
          "infile",
          "inner",
          "inout",
          "insensitive",
          "insert",
          "int",
          "int1",
          "int2",
          "int3",
          "int4",
          "int8",
          "integer",
          "interval",
          "into",
          "io_after_gtids",
          "io_before_gtids",
          "is",
          "iterate",
          "join",
          "json_table",
          "key",
          "keys",
          "kill",
          "lag",
          "last_value",
          "lateral",
          "lead",
          "leading",
          "leave",
          "left",
          "like",
          "limit",
          "linear",
          "lines",
          "load",
          "localtime",
          "localtimestamp",
          "lock",
          "long",
          "longblob",
          "longtext",
          "loop",
          "low_priority",
          "master_bind",
          "master_ssl_verify_server_cert",
          "match",
          "maxvalue",
          "mediumblob",
          "mediumint",
          "mediumtext",
          "middleint",
          "minute_microsecond",
          "minute_second",
          "mod",
          "modifies",
          "natural",
          "no_write_to_binlog",
          "not",
          "nth_value",
          "ntile",
          "null",
          "numeric",
          "of",
          "on",
          "optimize",
          "optimizer_costs",
          "option",
          "optionally",
          "or",
          "order",
          "out",
          "outer",
          "outfile",
          "over",
          "partition",
          "percent_rank",
          "precision",
          "primary",
          "procedure",
          "purge",
          "range",
          "rank",
          "read",
          "read_write",
          "reads",
          "real",
          "recursive",
          "references",
          "regexp",
          "release",
          "rename",
          "repeat",
          "replace",
          "require",
          "resignal",
          "restrict",
          "return",
          "revoke",
          "right",
          "rlike",
          "row",
          "row_number",
          "rows",
          "schema",
          "schemas",
          "second_microsecond",
          "select",
          "sensitive",
          "separator",
          "set",
          "show",
          "signal",
          "smallint",
          "spatial",
          "specific",
          "sql",
          "sql_big_result",
          "sql_calc_found_rows",
          "sql_small_result",
          "sqlexception",
          "sqlstate",
          "sqlwarning",
          "ssl",
          "starting",
          "stored",
          "straight_join",
          "system",
          "table",
          "terminated",
          "then",
          "tinyblob",
          "tinyint",
          "tinytext",
          "to",
          "trailing",
          "trigger",
          "true",
          "undo",
          "union",
          "unique",
          "unlock",
          "unsigned",
          "update",
          "usage",
          "use",
          "using",
          "utc_date",
          "utc_time",
          "utc_timestamp",
          "values",
          "varbinary",
          "varchar",
          "varcharacter",
          "varying",
          "virtual",
          "when",
          "where",
          "while",
          "window",
          "with",
          "write",
          "xor",
          "year_month",
          "zerofill");

  // Oracle 12.1c && Oracle 12.2c && Oracle 18c && Oracle 19c
  private static final Set<String> RESERVED_ORACLE =
      Set.of(
          "access",
          "add",
          "all",
          "alter",
          "and",
          "any",
          "as",
          "asc",
          "audit",
          "between",
          "by",
          "char",
          "check",
          "cluster",
          "column",
          "column_value",
          "comment",
          "compress",
          "connect",
          "create",
          "current",
          "date",
          "decimal",
          "default",
          "delete",
          "desc",
          "distinct",
          "drop",
          "else",
          "exclusive",
          "exists",
          "file",
          "float",
          "for",
          "from",
          "grant",
          "group",
          "having",
          "identified",
          "immediate",
          "in",
          "increment",
          "index",
          "initial",
          "insert",
          "integer",
          "intersect",
          "into",
          "is",
          "level",
          "like",
          "lock",
          "long",
          "maxextents",
          "minus",
          "mlslabel",
          "mode",
          "modify",
          "nested_table_id",
          "noaudit",
          "nocompress",
          "not",
          "nowait",
          "null",
          "number",
          "of",
          "offline",
          "on",
          "online",
          "option",
          "or",
          "order",
          "pctfree",
          "prior",
          "public",
          "raw",
          "rename",
          "resource",
          "revoke",
          "row",
          "rowid",
          "rownum",
          "rows",
          "select",
          "session",
          "set",
          "share",
          "size",
          "smallint",
          "start",
          "successful",
          "synonym",
          "sysdate",
          "table",
          "then",
          "to",
          "trigger",
          "uid",
          "union",
          "unique",
          "update",
          "user",
          "validate",
          "values",
          "varchar",
          "varchar2",
          "view",
          "whenever",
          "where",
          "with");
}