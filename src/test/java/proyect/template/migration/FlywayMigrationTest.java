package proyect.template.migration;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FlywayMigrationTest {

    @Test
    void migrations_should_be_idempotent() throws Exception {

        try (PostgreSQLContainer<?> postgres =
                     new PostgreSQLContainer<>("postgres:15")) {

            postgres.start();

            Flyway flyway = Flyway.configure()
                    .dataSource(
                            postgres.getJdbcUrl(),
                            postgres.getUsername(),
                            postgres.getPassword()
                    )
                    .load();

            // Primera ejecución
            flyway.migrate();
            DatabaseState state1 = captureState(postgres);

            // Segunda ejecución
            flyway.migrate();
            DatabaseState state2 = captureState(postgres);

            // Validación
            assertEquals(state1.tables, state2.tables);
            assertEquals(state1.columns, state2.columns);
            assertEquals(state1.uniqueConstraints, state2.uniqueConstraints);
        }
    }

    private DatabaseState captureState(PostgreSQLContainer<?> postgres) throws Exception {

        try (Connection conn = DriverManager.getConnection(
                postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword())) {

            Set<String> tables = new HashSet<>();
            Set<String> columns = new HashSet<>();
            Set<String> uniqueConstraints = new HashSet<>();

            // Tablas
            try (ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT table_name FROM information_schema.tables WHERE table_schema='public'")) {

                while (rs.next()) {
                    tables.add(rs.getString("table_name"));
                }
            }

            // Columnas
            try (ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT table_name, column_name FROM information_schema.columns WHERE table_schema='public'")) {

                while (rs.next()) {
                    columns.add(rs.getString("table_name") + "." + rs.getString("column_name"));
                }
            }

            // Constraints UNIQUE
            try (ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT table_name, constraint_name FROM information_schema.table_constraints WHERE constraint_type='UNIQUE'")) {

                while (rs.next()) {
                    uniqueConstraints.add(rs.getString("table_name") + "." + rs.getString("constraint_name"));
                }
            }

            return new DatabaseState(tables, columns, uniqueConstraints);
        }
    }

    static class DatabaseState {
        Set<String> tables;
        Set<String> columns;
        Set<String> uniqueConstraints;

        DatabaseState(Set<String> tables, Set<String> columns, Set<String> uniqueConstraints) {
            this.tables = tables;
            this.columns = columns;
            this.uniqueConstraints = uniqueConstraints;
        }
    }
    void assertSingleDefaultWidget(Connection conn) throws Exception {
        var rs = conn.createStatement().executeQuery(
                "SELECT COUNT(*) FROM widget WHERE fullname = 'default'"
        );
        rs.next();
        int count = rs.getInt(1);

        assertEquals(1, count, "Debe existir un solo 'default'");
    }
}