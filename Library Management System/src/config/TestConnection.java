import config.DatabaseConfig;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection con = DatabaseConfig.getConnection()) {
            System.out.println("✅ Connected to database successfully!");
        } catch (Exception e) {
            System.out.println("❌ Connection FAILED");
            e.printStackTrace();
        }
    }
}
