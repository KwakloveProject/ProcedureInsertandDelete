package procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import model.Department;

public class DepartmentMenu 
{

	public static Scanner sc = new Scanner(System.in);
	static {
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         System.out.println("데이터베이스 성공");
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }
	   }
	
	public DepartmentMenu() 
	{
		super();
	}
	//학과 관련 함수들
	public void deleteDepartment() 
	{
		
		showStudyList();
		System.out.println("삭제하실 일련번호 입력>>");
		int no = sc.nextInt();	
		 Connection con = null;
	        CallableStatement cstmt = null;
	        try {
	            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe", "homework", "homework");
	            cstmt = con.prepareCall("{call DELETE_DEPARTMENT(?)}");
	            cstmt.setInt(1, no);
	            cstmt.executeUpdate();
	            System.out.println("삭제됐습니다");

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (con != null) {
	                    con.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    
	}

	public void updateDepartment() {
		
		showStudyList();
		Department department =searchDepartment();
		if(department == null)
		{
			System.out.println("일련번호를 찾지못함");
			return;
		}		
		else
		{
			System.out.print("update d_num("+department.getD_num()+")>>");
			String d_num = sc.nextLine();
			System.out.print("update d_name("+department.getD_name()+")>>");
			String d_name = sc.nextLine();
			
			
			
			Connection con =null;
			PreparedStatement pstmt = null;
			try 
			{
				con =SQLConnect.makeConnection();
				String sql ="update Department set d_num=?,d_name=? where no=?";
				pstmt =con.prepareStatement(sql);
				pstmt.setString(1, d_num);
				pstmt.setString(2, d_name);
				pstmt.setInt(3, department.getNo());
				int i =pstmt.executeUpdate();
				if(i == 1) 
				{
					System.out.println("수정됐습니다.");
				}
				else 
				{
					System.out.println("수정실패하셨습니다.");
				}
				
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally 
			{
				try 
				{
					if(pstmt!=null) 
					{
						pstmt.close();
					}
					if(con!=null) 
					{
						con.close();
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			
		}
	}

	public Department searchDepartment() 
	{
		System.out.println("바꾸실 과목의 일련번호를 입력하시오>>");
		int no =sc.nextInt();
		sc.nextLine();
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		Department department =null;
		try 
		{
			con =SQLConnect.makeConnection();
			String sql = "SELECT *from Department where no = ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();

			if(rs.next()) 
			{
				int _no=rs.getInt("no");
				String d_num = rs.getString("d_num");
				String d_name = rs.getString("d_name");
				department =new Department(_no, d_num, d_name);
				System.out.println(department.toString());
			}
			else 
			{
				System.out.println(no+"일련번호가 없습니다");
				department =null;
			}
			
		}
		
		 catch (Exception e) {
			e.printStackTrace();
		}
		finally 
		{
				try 
				{
					if(rs!=null) 
					{
					rs.close();
					}
					 if(pstmt!=null) 
					{
					pstmt.close();
					}
					 if(con!=null) 
					{
					con.close();
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
		
		}
	return department;
	}
	
	public void insertDepartment(Department department) {
		  Connection con = null;
	        CallableStatement cstmt =null;
	        try {
	            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","homework", "homework");
	            cstmt= con.prepareCall("{CALL ADDDEPARTMENT(?,?)}");
	            
	            cstmt.setString(1,department.getD_num());
	            cstmt.setString(2, department.getD_name());

	            //실행하는 명령
	            cstmt.executeUpdate();
	            System.out.println("적용완료");
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("connection 실패");
	        } finally {
	            try{
	                if(con != null)
	                {
	                    con.close();
	                }
	            } catch(SQLException e){
	                e.printStackTrace();
	            }
	        }
		
	}

	public Department inputDepartment() {
		System.out.println("input d_num>>");
		String d_num = sc.nextLine();
		System.out.println("input d_name>>");
		String d_name = sc.nextLine();
		Department department = new Department(0, d_num, d_name);
		return department;
	}
	public void showStudyList() 
	{
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try 
		{
			con =SQLConnect.makeConnection();
			String sql = "SELECT * from Department" ;
			pstmt =con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) 
			{
				
				int no= rs.getInt("no");
				String d_num = rs.getString("d_num");
				String d_name = rs.getString("d_name");
				Department department =new Department(no, d_num, d_name);
				System.out.println(department.toString());
			}
		}
		
	  catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		finally 
		{
				try 
				{
					if(rs!=null) 
					{
						rs.close();
					}
					else if(pstmt!=null) 
					{
						pstmt.close();
					}
					else if(con!=null) 
					{
						con.close();
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
		
		}
	}
}
