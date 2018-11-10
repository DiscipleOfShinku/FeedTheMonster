package feedthemonster;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MonstersDAO {

	@Value("${jdbc.driverClassName}")
	private String jdbcDriver;
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.username}")
	private String jdbcUsername;
	@Value("${jdbc.password}")
	private String jdbcPassword;

	public ArrayList<Monster> getMonsters() {
		ArrayList<Monster> monsters = new ArrayList<Monster>();
		Monster monster = new Monster();
		try{
			Class.forName(jdbcDriver);
			Connection conn;
			Properties connectionProps = new Properties();
			connectionProps.put("user", jdbcUsername);
			connectionProps.put("password", jdbcPassword);
			connectionProps.put("useUnicode", "yes");
			connectionProps.put("characterEncoding", "utf8");
			conn = DriverManager.getConnection(jdbcUrl, connectionProps);
			conn.setAutoCommit(false);
			if (jdbcDriver.equals("com.microsoft.sqlserver.jdbc.SQLServerDriver")) {
				PreparedStatement psmt = conn.prepareStatement("EXEC Entries.GetMonsters");
				ResultSet res = psmt.executeQuery();
				while (res.next()) {
					monster.setId(res.getInt("id"));
					monster.setName(res.getString("name"));
					monster.setLevel(res.getInt("level"));
					monster.setBirthday(res.getDate("birthday"));
					monster.setPicture(res.getInt("id"));
					monsters.add(monster);
					monster = new Monster();
				};
				conn.commit();
				res.close();
				psmt.close();

			} else if (jdbcDriver.equals("org.postgresql.Driver")) {
				CallableStatement proc = conn.prepareCall("{ ? = call getmonsters() }");
				proc.registerOutParameter(1, Types.OTHER);
				proc.execute();
				ResultSet res = (ResultSet) proc.getObject(1);
				while (res.next()) {
					monster.setId(res.getInt("id"));
					monster.setName(res.getString("name"));
					monster.setLevel(res.getInt("level"));
					monster.setBirthday(res.getDate("birthday"));
					monster.setPicture(res.getInt("id"));
					monsters.add(monster);
					monster = new Monster();
				};
				conn.commit();
				res.close();
				proc.close();

			} else {

			}
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return monsters;


	}

	public Monster feedMonster(String name) {
		Monster monster = new Monster();
		try{
			Class.forName(jdbcDriver);
			Connection conn;
			Properties connectionProps = new Properties();
			connectionProps.put("user", jdbcUsername);
			connectionProps.put("password", jdbcPassword);
			connectionProps.put("useUnicode", "yes");
			connectionProps.put("characterEncoding", "utf8");
			conn = DriverManager.getConnection(jdbcUrl, connectionProps);
			conn.setAutoCommit(false);
			if (jdbcDriver.equals("com.microsoft.sqlserver.jdbc.SQLServerDriver")) {
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
				monster.setPicture(res.getInt("id"));
				conn.commit();
				res.close();
				psmt.close();

			} else if (jdbcDriver.equals("org.postgresql.Driver")) {
				CallableStatement proc = conn.prepareCall("{ ? = call levelincrement(?) }");
				proc.registerOutParameter(1, Types.OTHER);
				proc.setString(2, name);
				proc.executeUpdate();
				proc = conn.prepareCall("{ ? = call getmonster(?) }");
				proc.registerOutParameter(1, Types.OTHER);
				proc.setString(2, name);
				proc.execute();
				ResultSet res = (ResultSet) proc.getObject(1);
				res.next();
				monster.setId(res.getInt("id"));
				monster.setName(res.getString("name"));
				monster.setLevel(res.getInt("level"));
				monster.setBirthday(res.getDate("birthday"));
				monster.setPicture(res.getInt("id"));
				conn.commit();
				res.close();
				proc.close();

			} else {

			}
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return monster;
	}

}
