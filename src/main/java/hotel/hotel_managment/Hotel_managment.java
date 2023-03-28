package hotel.hotel_managment;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class Hotel_managment {
protected Void registerusers()
    {
        Scanner getUSersInput = new Scanner(System.in);
        String userName, userMobileNumber, userEmail;
        int userAge;
        System.out.println("Welcome to Our Hotel!");
        System.out.println("Dear user kindly input your full name");
        userName = getUSersInput.nextLine();
        System.out.println(userName + " Please provide your mobile number");
        userMobileNumber = getUSersInput.nextLine();
        System.out.println("Kindly input your mail");
        userEmail = getUSersInput.nextLine();
        System.out.println(userName + " Input your age");
        userAge = getUSersInput.nextInt();
        if(userAge >= 18)
        {
            Scanner pincollector = new Scanner(System.in);
            String Pin;
            System.out.println("Kindly input your pin");
            Pin = pincollector.nextLine();
            System.out.println("Credential Input valid");
            saveuser(userAge, Pin, userName, userMobileNumber, userEmail);
            pincollector.close();
        }
        else
        {
            System.out.println("Please your age is not allowed to use our service");
            System.exit(0);
        }
        getUSersInput.close();
        return null;
    }
/*Method to save information*/
void saveuser(int age, String pin, String userName, String userMobileNumber, String userEmail)
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection obj = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_managment", "root", "");
            String query = "insert into usercredentials(Age, fullName, mobileNumber, userEmail, pin)values(?,?,?,?,?)";
            PreparedStatement preparedStatementStmt = obj.prepareStatement(query);
            preparedStatementStmt.setInt(1, age);
            preparedStatementStmt.setString(2, userName);
            preparedStatementStmt.setString(3, userMobileNumber);
            preparedStatementStmt.setString(4, userEmail);
            preparedStatementStmt.setString(5, pin);
            int i = preparedStatementStmt.executeUpdate();
            if (i>=1) 
            {
                System.out.println("Data is inserted sucessfully !");  
                GetInfodb();  
            }
            else
            {
                System.out.println("Data not inserted!"); 
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
/*Get information from database*/
protected void GetInfodb()
    {
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
            Connection obj = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_managment", "root", "");
            Statement stmt = obj.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Age, fullName, mobileNumber, userEmail, pin FROM usercredentials");
            rs.next();
            // Declared variable to 
            String pin;
            String mail;
            String name;
            pin = rs.getString(5);
            mail = rs.getString(4);
            name = rs.getString(2);
            welcomePage(pin, mail, name);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
/*Welcome page*/
protected void welcomePage(String pin, String mail, String name)
    {
        try {
            Scanner login = new Scanner(System.in);
            String gottenPin;
            String gottenMail;
            mail = new String(mail);
            pin = new String(pin);
            gottenMail = new String();
            /*System.out.println("Welcome to Our Login Page");
            System.out.println("Please Input Your Email Adress");
            gottenMail = login.nextLine();
            System.out.println("Please Input Your Pin");
            gottenPin = login.nextLine();
            if(gottenMail.equals(mail) && gottenPin.equals(pin))
            {
                System.out.println("Welcome " + name);
            }
            else
            {
                System.out.println("Mail incorrect ");
            }
            login.close();*/

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
