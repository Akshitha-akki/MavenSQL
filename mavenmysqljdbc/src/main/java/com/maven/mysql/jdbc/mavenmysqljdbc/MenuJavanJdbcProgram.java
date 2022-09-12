package com.maven.mysql.jdbc.mavenmysqljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class MenuJavanJdbcProgram {

	public static void main(String[] args) throws ClassNotFoundException , SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");		
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost/database1","root","root");
		System.out.println("Database is connected!");
		
		int sid;
		String sname,degree,city;
		Scanner scan =new Scanner(System.in);
		while(true)
		{
			System.out.println("Click the 1 - Add details");
			System.out.println("Click the 2 - displaying of Students details");
			System.out.println("Click the 3 - Update of Students details");
			System.out.println("Click the 4 - Delete of Students details");
			System.out.println("Click the 5 - exit");
			System.out.println("Enter the choice : ");
			int choice= scan.nextInt();
			
			switch(choice)
			{
				case 1: //Adding of details.
						Scanner scan1 = new Scanner(System.in);
						
						//Entering the elements for student table.
						System.out.println("Enter the sid");
						sid = scan1.nextInt();
						System.out.println("Enter the sname");
						sname =scan1.next();
						System.out.println("Enter the degree");
						degree =scan1.next();
						System.out.println("Enter the city");
						city = scan1.next();
						String sql1="INSERT INTO student(sid,sname,degree,city) VALUES(?,?,?,?)";
						
						PreparedStatement preparedStmt = conn.prepareStatement(sql1);
						
						//Set the Parameters
						preparedStmt.setInt(1,sid);
						preparedStmt.setString(2, sname);
						preparedStmt.setString(3, degree);
						preparedStmt.setString(4,city);
						
						preparedStmt.executeUpdate();
						
						preparedStmt.close();
					    System.out.println("Record id Added!");
						break;
						
				case 2://Display the details
					sql1 = "select * from student";
					preparedStmt = conn.prepareStatement(sql1);
					ResultSet rst = preparedStmt.executeQuery();
				/*	while(rst.next()) 
					{
			            System.out.print("SID"+rst.getInt(1));
			            System.out.print("SNAME"+rst.getString(2));
			            System.out.print("DEGREE"+rst.getString(3));
			            System.out.print("CITY"+rst.getString(4));
			            System.out.println();
			         }*/
					while(rst.next()) 
					{
			            System.out.println("SID : "+rst.getInt("sid"));
			            System.out.println("SNAME : "+rst.getString("sname"));
			            System.out.println("DEGREE : "+rst.getString("degree"));
			            System.out.println("CITY : "+rst.getString("city"));
			            System.out.println();
			         }
					break;
				case 3://Update
					System.out.println("Enter the String : sname - s, degree - d, city -c");
					char ch =scan.next().charAt(0);
					switch(ch)
					{
					case 's' : 
						System.out.println("Enter the sid");
						sid = scan.nextInt();
						System.out.println("Enter the sname");
						sname =scan.next();
						
						preparedStmt = conn.prepareStatement("UPDATE student SET sname = ? WHERE sid = ?");
						
						preparedStmt.setString(1, sname);
						preparedStmt.setInt(2, sid);
						
					    preparedStmt.executeUpdate();
					    preparedStmt.close();
					    System.out.println("Details are Updated!");
					    break;
					case 'd' : 
						System.out.println("Enter the sid");
						sid = scan.nextInt();
						System.out.println("Enter the degree");
						degree =scan.next();
						
						preparedStmt = conn.prepareStatement("UPDATE student SET degree = ? WHERE sid = ?");
						
						preparedStmt.setString(1, degree);
						preparedStmt.setInt(2, sid);
						
					    preparedStmt.executeUpdate();
					    preparedStmt.close();
					    System.out.println("Details are Updated!");
					    break;
					case 'c' : 
						System.out.println("Enter the sid");
						sid = scan.nextInt();
						System.out.println("Enter the city");
						city =scan.next();
						
						preparedStmt = conn.prepareStatement("UPDATE student SET city = ? WHERE sid = ?");

						preparedStmt.setString(1,city);
						preparedStmt.setInt(2, sid);
						
					    preparedStmt.executeUpdate();
					    preparedStmt.close();
					    System.out.println("Details are Updated!");
					    break;
					default : System.out.println("Invaild Input!");
							  break;
					}
					 
				    break;
				case 4: //Delete			
					    System.out.println("Enter the sid");
					    sid=scan.nextInt();
					    
					    preparedStmt = conn.prepareStatement("DELETE FROM student WHERE sid = ?");
					    preparedStmt.setInt(1, sid);
					    preparedStmt.executeUpdate();
					    System.out.println("Details are Updated!");
				case 5 :
					  System.exit(0);
					  break;
				
				default : 
					System.out.println("Invaild Input!");
					break;		
			}
		}
	}
}
