package persistence;

import model.TypeEvent;
import service.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeEventRepository {

    public List<TypeEvent> getAllTypeEvents() {
        List<TypeEvent> typeEvents = new ArrayList<>();
        String query = "SELECT * FROM type_events";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String typeId = resultSet.getString("type_id");
                String typeName = resultSet.getString("type_name");
                typeEvents.add(new TypeEvent(typeId, typeName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeEvents;
    }
}
