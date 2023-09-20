import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class connectivity{

	static PreparedStatement ps;
	static ResultSet rs;
	
	public static boolean login(String name, String pass) {
		boolean states = false;
		try {
			ps = getConnection().prepareStatement("select * from EB where UserName=? and password=?");
			ps.setString(1, name);
			ps.setString(2, pass);
			rs = ps.executeQuery();  
			states =  rs.next();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return states;
	}
	public static boolean loginWithout(String mobile, String server) {
		boolean states = false;
		try {
			ps = getConnection().prepareStatement("select * from EB where mobile=? and ServerNo=?");
			ps.setString(1, mobile);
			ps.setString(2, server);
			rs = ps.executeQuery();  
			states =  rs.next();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return states;
	}
	
	public static boolean register(String name,String number,String service,String city) {
		boolean states = false;
		try {
			ps = getConnection().prepareStatement("insert into EB (`ServerNo`,`mobile`,`Name`,`City`)value(?,?,?,?)");
            ps.setString(1, service);
            ps.setString(2,number);
            ps.setString(3,name);
            ps.setString(4,city);
            if(ps.executeUpdate()>0){
                states=true;
            }
		} catch (Exception e) {
			System.out.println(e);
		}
		return states;
	}
	
	public static boolean billRegister(String service,String date,String unit) {
		boolean states = false;
		try {
			ps = getConnection().prepareStatement("insert into `"+service+"` (`Date`,`unitCurrent`,`status`)value(?,?,?)");
            ps.setString(1, date);
            ps.setString(2,unit);
            ps.setString(3,unit.equals("0")?"paid":"not paid");
            if(ps.executeUpdate()>0){
                states=true;
            }
		} catch (Exception e) {
			System.out.println(e);
		}
		return states;
	}
	
	public static boolean registerUser(String userName, String password, String service) {
		boolean states = false;
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement("UPDATE `EB` SET `UserName` = ?, `password` = ? WHERE (`ServerNo` = ?);");
            ps.setString(1,userName);
            ps.setString(2,password);
            ps.setString(3, service);
            if(ps.executeUpdate()>0){
                states=true;
            }
		} catch (Exception e) {
			System.out.println(e);
		}
		return states;
	}
	public static boolean registerTable (String service) {
		boolean states = false;
		try {
			String createTableSQL = "CREATE TABLE `"+service+"` ("
			        + "SlNo INT NOT NULL AUTO_INCREMENT,"
			        + "Date VARCHAR(45) NOT NULL,"
			        + "unitCurrent VARCHAR(45) NOT NULL,"
			        + "status VARCHAR(45) NOT NULL,"
			        + "PRIMARY KEY (SlNo),"
			        + "UNIQUE INDEX Date_UNIQUE (Date ASC) VISIBLE"
			        + ")";
			ps = getConnection().prepareStatement(createTableSQL); 
            if(ps.executeUpdate()==0){
                states=true;
            }
		} catch (Exception e) {
			System.out.println(e);
		}
		return states;
	}
	
	public static boolean update(String Service) {
		boolean states = false;
		PreparedStatement selectStatement = null;
		PreparedStatement updateStatement = null;
		ResultSet resultSet = null;
		try {
			selectStatement = getConnection().prepareStatement("select * from `"+Service+"` ORDER BY status");
			resultSet = selectStatement.executeQuery();

		    if (resultSet.next()) {
		        int id = resultSet.getInt("SlNo");
		        String columnNameToUpdate = resultSet.getString("status");
		        String updatedValue = "paid";
		        updateStatement = getConnection().prepareStatement("UPDATE `"+Service+"` SET status = ? WHERE SlNo = ?");
		        updateStatement.setString(1, updatedValue);
		        updateStatement.setInt(2, id);
		        if(updateStatement.executeUpdate()>0){
	                states=true;
	            }
		    }
		} catch (Exception e) {
			System.out.println(e);
		}
		return states;
	}
	
	public static String fach(String Service) {
		String temp = "start"; 
	      ResultSet rs = null;
		try {
			Statement st = getConnection().createStatement();
        	rs = st.executeQuery("select * from `"+Service+"` ORDER BY status");
        	String lastValue = null;
        	if(rs.next()) {
                lastValue = rs.getString("status");
                }
        	if (!lastValue.equals("paid")) {
        		rs = st.executeQuery("select * from `"+Service+"` ORDER BY unitCurrent DESC LIMIT 2");
	        	String lastValue1 = null;
	        	String lastValue2 = null;
	        	     while (rs.next()) {
	        	          String value = rs.getString("unitCurrent");      
	        	                if (lastValue1 == null) {
	        	                    lastValue1 = value;
	        	                } else {
	        	                    lastValue2 = value;    	                
	        	                    }
	        	            }
	        	            temp=amount(lastValue1, lastValue2);
        	}
        	else {
        		temp="0";
        	}
		}catch (Exception e) {
			System.out.println(e);
		}
		return temp;
		}
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/EBSystem","root","Vishnu@123");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
	public static String amount(String f,String s) {
		float f1=Float.parseFloat(f);
		float s1=Float.parseFloat(s);
		float fine = f1-s1;
		if (fine<=100) s="0";
		else if(fine>100&&fine<=200)s=String.valueOf(fine*2.5);
		else s=String.valueOf(fine*4);
		return s;
	}
	
}