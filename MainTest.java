package procedure;

import java.util.Scanner;

import controller.DepartmentMenu;
import model.Department;
import view.MenuList;

public class MainTest 
{
	public static Scanner sc =new Scanner(System.in);
	public static void main (String [] args) 
	{
		
		boolean flag=false;
		
		while(!flag) 
		{
			DepartmentMenu dm= new DepartmentMenu();
			
			System.out.println("학생 정보 메뉴 번호를 입력하세요");
			System.out.println("1. 학생 정보 목록");
			System.out.println("2. 학생 정보 입력");
			System.out.println("3. 학생 정보 수정");
			System.out.println("4. 학생 정보 삭제");
			System.out.println("5  메인 메뉴");
			System.out.println("번호 선택:");
			int menu =sc.nextInt();
			switch(menu) 
			{
			case 1://학과 정보 목록 
				dm.showStudyList();
				break;
			case 2://학과 정보 입력
				Department department =dm.inputDepartment();
				dm.insertDepartment(department);
				break;
			case 3://학과 정보 수정
				dm.updateDepartment();
				break;
			case 4://학과 정보 삭제
				dm.deleteDepartment();
				break;
			case 5:
				flag=true;
				break;
			}
		}
	}	

}
