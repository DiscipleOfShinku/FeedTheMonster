package feedthemonster;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MonstersDAO {
	
	public ArrayList<Monster> getMonsters() {
		ArrayList<Monster> monsters = new ArrayList<Monster>();
		Monster monster = new Monster();
    	try{
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		Connection conn;
			Properties connectionProps = new Properties();
		    connectionProps.put("user", "sa");
		    connectionProps.put("password", "qwerty123");
		    connectionProps.put("useUnicode", "yes");
		    connectionProps.put("characterEncoding", "utf8");
			conn = DriverManager.getConnection(
					"jdbc:sqlserver://127.0.0.1:1433;databaseName=FeedTheMonster",
					connectionProps);
			PreparedStatement psmt = conn.prepareStatement("EXEC Entries.GetMonsters");
	    	ResultSet res = psmt.executeQuery();
	    	while (res.next()) {
	    		monster.setId(res.getInt("id"));
	    		monster.setName(res.getString("name"));
	    		monster.setLevel(res.getInt("level"));
	    		monster.setBirthday(res.getDate("birthday"));
	    		monsters.add(monster);
	    		monster = new Monster();
	    	};
	    	res.close();	
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return monsters;


	}

	public Monster feedMonster(String name) {
		Monster monster = new Monster();
    	try{
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		Connection conn;
			Properties connectionProps = new Properties();
		    connectionProps.put("user", "sa");
		    connectionProps.put("password", "qwerty123");
		    connectionProps.put("useUnicode", "yes");
		    connectionProps.put("characterEncoding", "utf8");
			conn = DriverManager.getConnection(
					"jdbc:sqlserver://127.0.0.1:1433;databaseName=FeedTheMonster",
					connectionProps);
			PreparedStatement psmt = conn.prepareStatement("EXEC Entries.LevelIncrement @name = ?");
			psmt.setString(1, name);
			psmt.executeUpdate();
			psmt = conn.prepareStatement("EXEC Entries.GetMonster @name = ?");
			psmt.setString(1, name);
	    	ResultSet res = psmt.executeQuery();
	    	res.next();
	    	monster.setId(res.getInt("id"));
	    	monster.setName(res.getString("name"));
	    	monster.setLevel(res.getInt("level"));
	    	monster.setBirthday(res.getDate("birthday"));
	    	res.close();	
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return monster;
	}

}
