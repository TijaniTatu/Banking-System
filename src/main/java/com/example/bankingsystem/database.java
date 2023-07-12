package com.example.bankingsystem;
import java.sql.*; 

class database{
    // connect to the database on localhost
    public static Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banks","root","");
            return con;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    // add customer details to the database
    public static void addCustomer(int customer_id, String name, String address, String email, int phone, String password){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("insert into customer values(?,?,?,?,?,?)");
            stmt.setInt(1,customer_id);
            stmt.setString(2,name);
            stmt.setString(3,address);
            stmt.setString(4,email);
            stmt.setInt(5,phone);
            stmt.setString(6,password);
            stmt.executeUpdate();

            // if successful, return true
            System.out.println("Customer added successfully");
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
       
    }

    // withdraw cash from the account. I believe more code is needed here to make sure someone does not withdraw more than they have
    public static void withdraw(int account, int amount){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("update account set balance=balance-? where account=?");
            stmt.setInt(1,amount);
            stmt.setInt(2,account);
            stmt.executeUpdate();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    // deposit cash into the account
    public static void deposit(int account, int amount){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("update account set balance=balance+? where account=?");
            stmt.setInt(1,amount);
            stmt.setInt(2,account);
            stmt.executeUpdate();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    // get transaction history for the account
    public static ResultSet getTransactionHistory(int account){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("select * from transaction where account=?");
            stmt.setInt(1,account);
            ResultSet rs=stmt.executeQuery();
            return rs;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    // update customer details 
    public static void updateCustomer(int id, String name, String address, String email, int phone, String password){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("update customer set name=?, address=?, email=?, phone=?, password=? where id=?");
            stmt.setString(1,name);
            stmt.setString(2,address);
            stmt.setString(3,email);
            stmt.setInt(4,phone);
            stmt.setString(5,password);
            stmt.setInt(6,id);
            stmt.executeUpdate();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    // get customer details
    public static ResultSet getCustomer(int id){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("select * from customer where id=?");
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            return rs;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    // get account details
    public static ResultSet getAccount(int account){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("select * from account where account=?");
            stmt.setInt(1,account);
            ResultSet rs=stmt.executeQuery();
            return rs;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    // set start date for lock_savings on lock_savings table
    public static void setStartDate(int account, String date){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("update lock_savings set start_date=? where account=?");
            stmt.setString(1,date);
            stmt.setInt(2,account);
            stmt.executeUpdate();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    // set end date for lock_savings on lock_savings table
    public static void setEndDate(int account, String date){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("update lock_savings set end_date=? where account=?");
            stmt.setString(1,date);
            stmt.setInt(2,account);
            stmt.executeUpdate();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    // register bank
    public static void registerBank(String name, String address, int phone, String email, String password){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("insert into bank(name,address,phone,email,password) values(?,?,?,?,?)");
            stmt.setString(1,name);
            stmt.setString(2,address);
            stmt.setInt(3,phone);
            stmt.setString(4,email);
            stmt.setString(5,password);
            stmt.executeUpdate();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    // register branch
    public static void registerBranch(int bank_id, String name, String address, int phone, String password){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("insert into branch(bank_id,name,address,phone,password) values(?,?,?,?,?)");
            stmt.setInt(1,bank_id);
            stmt.setString(2,name);
            stmt.setString(3,address);
            stmt.setInt(4,phone);
            stmt.setString(5,password);
            stmt.executeUpdate();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    // freeze account
    public static void freezeAccount(int account){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("update account set frozen=1 where account=?");
            stmt.setInt(1,account);
            stmt.executeUpdate();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    // unfreeze account
    public static void unfreezeAccount(int account){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("update account set frozen=0 where account=?");
            stmt.setInt(1,account);
            stmt.executeUpdate();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    // register employee
    public static void registerEmployee(int branch_id, String name, String position, String email, int phone, String password){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("insert into employee(branch_id,name,position,email,phone,password) values(?,?,?,?,?,?)");
            stmt.setInt(1,branch_id);
            stmt.setString(2,name);
            stmt.setString(3,position);
            stmt.setString(4,email);
            stmt.setInt(5,phone);
            stmt.setString(6,password);
            stmt.executeUpdate();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    // get employee details
    public static ResultSet getEmployee(int id){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("select * from employee where id=?");
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            return rs;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    // get branch details
    public static ResultSet getBranch(int id){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("select * from branch where id=?");
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            return rs;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    // get bank details
    public static ResultSet getBank(int id){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("select * from bank where id=?");
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            return rs;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    // get all banks
    public static ResultSet getAllBanks(){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("select * from bank");
            ResultSet rs=stmt.executeQuery();
            return rs;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    // get all branches
    public static ResultSet getAllBranches(){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("select * from branch");
            ResultSet rs=stmt.executeQuery();
            return rs;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    // get all employees
    public static ResultSet getAllEmployees(){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("select * from employee");
            ResultSet rs=stmt.executeQuery();
            return rs;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    // get all accounts
    public static ResultSet getAllAccounts(){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("select * from account");
            ResultSet rs=stmt.executeQuery();
            return rs;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    // login customer
    public static ResultSet loginCustomer(String account, String password){
        try{
            Connection con=connect();
            PreparedStatement stmt=con.prepareStatement("select * from customer where account=? and password=?");
            stmt.setString(1,account);
            stmt.setString(2,password);
            ResultSet rs=stmt.executeQuery();
            return rs;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    
}