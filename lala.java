import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LALAApp {
    private Connection conn;

    public LALAApp() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lala_app", "username", "password");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUser(String username, String email, String password, String countryOfOrigin, String currentLocation, String languageLevel) {
        String query = "INSERT INTO users (username, email, password, country_of_origin, current_location, language_level) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, countryOfOrigin);
            pstmt.setString(5, currentLocation);
            pstmt.setString(6, languageLevel);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createLanguageResource(String title, String description, String resourceType, String language, String level) {
        String query = "INSERT INTO language_resources (title, description, resource_type, language, level) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setString(3, resourceType);
            pstmt.setString(4, language);
            pstmt.setString(5, level);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCulturalTip(String title, String description, String category) {
        String query = "INSERT INTO cultural_tips (title, description, category) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setString(3, category);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCommunityConnection(String title, String description, String eventDate, String location) {
        String query = "INSERT INTO community_connections (title, description, event_date, location) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setString(3, eventDate);
            pstmt.setString(4, location);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUserProgress(int userId, int languageResourceId, String progress) {
        String query = "INSERT INTO user_progress (user_id, language_resource_id, progress) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, languageResourceId);
            pstmt.setString(3, progress);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserProgress(int id, String progress) {
        String query = "UPDATE user_progress SET progress = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, progress);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserProgress(int id) {
        String query = "DELETE FROM user_progress WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getUserProgress(int id) {
        String query = "SELECT * FROM user_progress WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("user_id"));
                System.out.println("Language Resource ID: " + rs.getInt("language_resource_id"));
                System.out.println("Progress: " + rs.getString("progress"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
