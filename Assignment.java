
public class Assignment{

	interface Admin{
		void new_rules();
	}

	class Department implements Admin{
		public void new_rules(){					
			System.out.println("Add New Rules");
		}
		public void send_emails(){
			System.out.println("Email is sending to the Users");
		}
		public void collect_all_emails(){
			System.out.println("Collected all emails from users.");
		}
	}

	class Users extends Department{
		public void send_emails(){
			// new_rules();
			super.send_emails();
			System.out.println("Reading Email...");
			System.out.println("Email sent to the Head of Department");
			collect_all_emails();
		}		
	}

	public static void main(String[] args) { 

		Assignment obj = new Assignment(); 			//instance of outer class
		Users users = obj.new Users();				//instance of inner class
		users.send_emails();
	}

}