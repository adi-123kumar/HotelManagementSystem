import java.sql.*;
import java.util.*;
import java.lang.String;
import java.util.concurrent.ExecutionException;

public class HotelManagementSystem {
    private static final String url="jdbc:mysql://localhost:3306/HotelManagementSystem";
    private static final String username="root";
    private static final String password="Rishav123@";
    public static void main(String[] args) throws ClassNotFoundException{
        Scanner scanner=new Scanner(System.in);
      try{
        Class.forName("com.sql.cj.jdbc.Driver");
      }catch (Exception e){
          System.out.println(e.getMessage());
      }
      try{
        Connection connection = DriverManager.getConnection(url,username,password);
        Statement statement = connection.createStatement();
        int choice=1;
        do {
            System.out.println("__________WELCOME TO HOTEL MANAGEMENT SYSTEM________");
            System.out.println("Press the given key to enjoy the facility");
            System.out.println("1.NEW RESERVATION");
            System.out.println("2.SHOW ALL RESERVATION");
            System.out.println("3.DELETE RESERVATION");
            System.out.println("4.CHECK ROOM NO");
            System.out.println("5.EXIT");
            System.out.println("Enter your choice");
            int item=scanner.nextInt();
            switch (item){
                case 1:
                    newReservation(connection,statement,scanner);
                    break;
                case 2:
                    showAllReservation(connection,statement,scanner);
                    break;
                case 3:
                    deleteReservation(connection,statement,scanner);
                    break;
                case 4:
                    getRoomNumber(connection,statement,scanner);
                    break;
                case 5:
                    choice=0;
                    break;
                default:
                    System.out.println("Please enter a valid number.");

            }
        }while(choice!=0);



//        showAllReservation(connection,statement,scanner);
//        newReservation(connection,statement,scanner);
//          getRoomNumber(connection,statement,scanner);
//          deleteReservation(connection,statement,scanner);

      }catch (Exception e){
          System.out.println(e.getMessage());
      }

    }
    public static void newReservation(Connection connection,Statement statement,Scanner scanner){
        System.out.println("Enter the name of the customer.");
        scanner.nextLine();
        String customer_name= scanner.nextLine();
        System.out.println("Enter the room no.");
        String room_no= scanner.nextLine();
        System.out.println("Enter your mobile number");
      String mobno=scanner.nextLine();
      String query="insert into hotelsystem (guest_name,mobile_no,room_no) values ('"+customer_name+"',"+mobno+","+room_no+")";
      try{
      int rowaffected=statement.executeUpdate(query);
      if(rowaffected>0){
          System.out.println("Registration successfull.");
      }
      else{
          System.out.println("Registration unsuccessfull.");
      }
      }catch (Exception e){
          System.out.println(e.getMessage());
      }
    }
//    seeing all reservation
    public static void showAllReservation(Connection connection , Statement statement , Scanner scanner){
        String query="select * from hotelsystem ;";
        try {
          ResultSet resultSet= statement.executeQuery(query);
            System.out.println("Current Reservations:");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
            System.out.println("| Reservation ID | Guest           | Room Number   | Contact Number      | Reservation Date        |");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
          while (resultSet.next()){
              int rid=resultSet.getInt("reservation_id");
              String gname=resultSet.getString("guest_name");
              String mno= resultSet.getString("mobile_no");
              String rno=resultSet.getString("room_no");
              String rdate=resultSet.getString("reservation_date").toString();
              System.out.printf("| %-14d | %-15s | %-13s | %-20s | %-19s   |\n",
                      rid, gname, rno, mno, rdate);
              System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
          }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public static void deleteReservation(Connection connection,Statement statement,Scanner scanner){
        System.out.println("Enter your reservation id :");
        int rid=scanner.nextInt();
        String query = "delete from hotelsystem where reservation_id="+rid+";";
        try {
            int affectedrows=statement.executeUpdate(query);
            if(affectedrows>0){
                System.out.println("Deletion successfull.");
            }else{
                System.out.println("deletion unsussessfull.");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void getRoomNumber(Connection connection,Statement statement,Scanner scanner){
        System.out.println("Enter your reservation id:");
        int rid=scanner.nextInt();
        String query="select room_no from hotelsystem where reservation_id ="+rid+";";
        try{
        ResultSet resultSet=statement.executeQuery(query);
        while(resultSet.next()){
            String rno=resultSet.getString("room_no");
            System.out.println("Your room number is:"+rno);
//
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }


    }
}

