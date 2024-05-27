package procedure;

public class Department 
{
	private int no;
	private String d_num;
	private String d_name;
	public Department() {
		super();
	}
	public Department(int no, String d_num, String d_name) {
		super();
		this.no = no;
		this.d_num = d_num;
		this.d_name = d_name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getD_num() {
		return d_num;
	}
	public void setD_num(String s_num) {
		this.d_num = d_num;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String s_name) {
		this.d_name = d_name;
	}
	@Override
	public String toString() {
		return "일련번호:" + no + ",학과번호:" + d_num + ",학과명:" + d_name;
	}
	
}
