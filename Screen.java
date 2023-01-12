import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Screen extends JPanel implements ActionListener{

    BinarySearchTree<Account> tree = new BinarySearchTree<Account>();

    private Boolean admin = false;
    private Boolean customer = true;

    private Boolean loggedIn = false;
    private Boolean noAccount = false;

    private Boolean hasEnough = true;

    private Boolean hasAccount = false;
    private Boolean wrongPin = false;

    private Boolean addingAccount = false;

    private Boolean showEfficiencey = false;

    //all view
    private JButton customerTab;
    private JButton adminTab;    

    //customer view
    private JTextField firstName;
    private JTextField lastName;
    private JButton logIn;
    private JTextField pin;

    //logged in
    private JTextField amountText;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton logOut;

    private Account currentAccount;

    //admin view
    private JTextArea accountList;
    private JScrollPane scrollPane;
    private JButton searchButton;
    private JTextField lastNameAdmin;
    private JTextField firstNameAdmin;
    private JButton deleteButton;
    private JButton addAccount;

    //adding new customer view
    private JTextField newFirstName;
    private JTextField newLastName;
    private JTextField newPin;
    private JTextField newBalance;
    private JButton addNewCustomer;
    private JButton cancelNewCustomer;


    

    public Screen(){
        this.setLayout(null);

        try{
            Scanner scan = new Scanner(new FileReader("names.txt"));
            while(scan.hasNext()){
                int randPin = (int)(Math.random() * 9999);
                int randBalance = (int)(Math.random() * 100000);

                String[] name = scan.next().split(",");
                tree.add(new Account(name[0], name[1], randPin, randBalance));
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }



        customerTab = new JButton();
        customerTab.setFont(new Font("Arial", Font.BOLD, 20));
        customerTab.setHorizontalAlignment(SwingConstants.CENTER);
        customerTab.setBounds(0, 0, 150, 30);
        customerTab.setText("Customer");
        this.add(customerTab);
        customerTab.addActionListener(this);
        
        adminTab = new JButton();
        adminTab.setFont(new Font("Arial", Font.BOLD, 20));
        adminTab.setHorizontalAlignment(SwingConstants.CENTER);
        adminTab.setBounds(150, 0, 150, 30);
        adminTab.setText("Admin");
        this.add(adminTab);
        adminTab.addActionListener(this);


        firstName = new JTextField();
        firstName.setFont(new Font("Arial", Font.PLAIN, 20));
        firstName.setHorizontalAlignment(SwingConstants.CENTER);
        firstName.setBounds(280, 240, 200, 30);
        firstName.setText("First Name");
        this.add(firstName);

        lastName = new JTextField();
        lastName.setFont(new Font("Arial", Font.PLAIN, 20));
        lastName.setHorizontalAlignment(SwingConstants.CENTER);
        lastName.setBounds(280, 280, 200, 30);
        lastName.setText("Last Name");
        this.add(lastName);

        logIn = new JButton();
        logIn.setFont(new Font("Arial", Font.BOLD, 20));
        logIn.setHorizontalAlignment(SwingConstants.CENTER);
        logIn.setBounds(279, 360, 200, 30);
        logIn.setText("login");
        this.add(logIn);
        logIn.addActionListener(this);

        pin = new JTextField();
        pin.setFont(new Font("Arial", Font.PLAIN, 20));
        pin.setHorizontalAlignment(SwingConstants.CENTER);
        pin.setBounds(280, 320, 200, 30);
        pin.setText("Pin");
        this.add(pin);

        amountText = new JTextField();
        amountText.setFont(new Font("Arial", Font.PLAIN, 20));
        amountText.setHorizontalAlignment(SwingConstants.CENTER);
        amountText.setBounds(280, 255, 200, 30);
        amountText.setText("amount");
        this.add(amountText);

        withdrawButton = new JButton();
        withdrawButton.setFont(new Font("Arial", Font.BOLD, 20));
        withdrawButton.setHorizontalAlignment(SwingConstants.CENTER);
        withdrawButton.setBounds(280, 295, 200, 30);
        withdrawButton.setText("Withdraw");
        this.add(withdrawButton);
        withdrawButton.addActionListener(this);

        depositButton = new JButton();
        depositButton.setFont(new Font("Arial", Font.BOLD, 20));
        depositButton.setHorizontalAlignment(SwingConstants.CENTER);
        depositButton.setBounds(280, 335, 200, 30);
        depositButton.setText("Deposit");
        this.add(depositButton);
        depositButton.addActionListener(this);

        logOut = new JButton();
        logOut.setFont(new Font("Arial", Font.BOLD, 20));
        logOut.setHorizontalAlignment(SwingConstants.CENTER);
        logOut.setBounds(280, 550, 200, 30);
        logOut.setText("Log Out");
        this.add(logOut);
        logOut.addActionListener(this);


        accountList = new JTextArea(250,500); //sets the location and size
		accountList.setText(tree.toString());
        scrollPane = new JScrollPane(accountList); 
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(50,50,200,500);
		this.add(scrollPane);


        searchButton = new JButton();
        searchButton.setFont(new Font("Arial", Font.BOLD, 20));
        searchButton.setHorizontalAlignment(SwingConstants.CENTER);
        searchButton.setBounds(455, 205, 200, 30);
        searchButton.setText("Search");
        this.add(searchButton);
        searchButton.addActionListener(this);

        lastNameAdmin = new JTextField();
        lastNameAdmin.setFont(new Font("Arial", Font.PLAIN, 20));
        lastNameAdmin.setHorizontalAlignment(SwingConstants.CENTER);
        lastNameAdmin.setBounds(455, 165, 200, 30);
        lastNameAdmin.setText("Last Name");
        this.add(lastNameAdmin);

        firstNameAdmin = new JTextField();
        firstNameAdmin.setFont(new Font("Arial", Font.PLAIN, 20));
        firstNameAdmin.setHorizontalAlignment(SwingConstants.CENTER);
        firstNameAdmin.setBounds(455, 125, 200, 30);
        firstNameAdmin.setText("First name");
        this.add(firstNameAdmin);

        deleteButton = new JButton();
        deleteButton.setFont(new Font("Arial", Font.BOLD, 20));
        deleteButton.setHorizontalAlignment(SwingConstants.CENTER);
        deleteButton.setBounds(456, 245, 200, 30);
        deleteButton.setText("Delete");
        this.add(deleteButton);
        deleteButton.addActionListener(this);

        addAccount = new JButton();
        addAccount.setFont(new Font("Arial", Font.BOLD, 20));
        addAccount.setHorizontalAlignment(SwingConstants.CENTER);
        addAccount.setBounds(456, 350, 200, 30);
        addAccount.setText("Add account");
        this.add(addAccount);
        addAccount.addActionListener(this);


        newFirstName = new JTextField();
        newFirstName.setFont(new Font("Arial", Font.PLAIN, 20));
        newFirstName.setHorizontalAlignment(SwingConstants.CENTER);
        newFirstName.setBounds(280, 240, 200, 30);
        newFirstName.setText("First Name");
        this.add(newFirstName);

        newLastName = new JTextField();
        newLastName.setFont(new Font("Arial", Font.PLAIN, 20));
        newLastName.setHorizontalAlignment(SwingConstants.CENTER);
        newLastName.setBounds(280, 280, 200, 30);
        newLastName.setText("Last Name");
        this.add(newLastName);

        newPin = new JTextField();
        newPin.setFont(new Font("Arial", Font.PLAIN, 20));
        newPin.setHorizontalAlignment(SwingConstants.CENTER);
        newPin.setBounds(280, 320, 200, 30);
        newPin.setText("Pin");
        this.add(newPin);

        newBalance = new JTextField();
        newBalance.setFont(new Font("Arial", Font.PLAIN, 20));
        newBalance.setHorizontalAlignment(SwingConstants.CENTER);
        newBalance.setBounds(280, 360, 200, 30);
        newBalance.setText("Starting Balance");
        this.add(newBalance);

        addNewCustomer = new JButton();
        addNewCustomer.setFont(new Font("Arial", Font.BOLD, 20));
        addNewCustomer.setHorizontalAlignment(SwingConstants.CENTER);
        addNewCustomer.setBounds(280, 400, 200, 30);
        addNewCustomer.setText("Add");
        this.add(addNewCustomer);
        addNewCustomer.addActionListener(this);

        cancelNewCustomer = new JButton();
        cancelNewCustomer.setFont(new Font("Arial", Font.BOLD, 20));
        cancelNewCustomer.setHorizontalAlignment(SwingConstants.CENTER);
        cancelNewCustomer.setBounds(280, 440, 200, 30);
        cancelNewCustomer.setText("Cancel");
        this.add(cancelNewCustomer);
        cancelNewCustomer.addActionListener(this);
    }

    public Dimension getPreferredSize(){
        return new Dimension(800, 600);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        firstName.setVisible(customer && !loggedIn);
        lastName.setVisible(customer && !loggedIn);
        pin.setVisible(customer && !loggedIn);
        logIn.setVisible(customer && !loggedIn);
        amountText.setVisible(customer && loggedIn);
        withdrawButton.setVisible(customer && loggedIn);
        depositButton.setVisible(customer && loggedIn);
        logOut.setVisible(customer && loggedIn);
        accountList.setVisible(admin && !addingAccount);
        scrollPane.setVisible(admin && !addingAccount);
        searchButton.setVisible(admin && !addingAccount);
        firstNameAdmin.setVisible(admin && !addingAccount);
        lastNameAdmin.setVisible(admin && !addingAccount);
        deleteButton.setVisible(admin && !addingAccount);
        addAccount.setVisible(admin && !addingAccount);
        newFirstName.setVisible(admin && addingAccount);
        newLastName.setVisible(admin && addingAccount);
        newPin.setVisible(admin && addingAccount);
        newBalance.setVisible(admin && addingAccount);
        addNewCustomer.setVisible(admin && addingAccount);
        cancelNewCustomer.setVisible(admin && addingAccount);


        if(customer && loggedIn){
            g.drawString("Passes: " + tree.getPasses(), 700, 580);
            g.drawString("Balance: " + currentAccount.getBalance(), 330, 200);
            g.drawString(currentAccount.getName(), 340, 220);
        }

        if(customer && loggedIn && !hasEnough){
            g.drawString("Not enough money", 320, 400);
        }

        if(admin && hasAccount && !addingAccount){
            if(currentAccount != null){
                g.drawString("Passes: " + tree.getPasses(), 700, 580);
                g.drawString(currentAccount.getName(), 500, 300);
                g.drawString("Pin: " + currentAccount.getPin(), 500, 320);
                g.drawString("Balance: " + currentAccount.getBalance(), 500, 340);
            }
        }

        if(customer && noAccount){
            g.drawString("No Account Found", 320, 450);
        }
        
        if(customer && wrongPin){
            g.drawString("Incorrect Pin", 330, 450);
        }

        if(showEfficiencey){
            g.drawString("Passes: " + tree.getPasses(), 700, 580);
        }

       
        repaint();
    }


    public void actionPerformed(ActionEvent e){
        if(e.getSource() == adminTab){
            showEfficiencey = false;
            admin = true;
            customer = false;
            loggedIn = false;
        }
        else if(e.getSource() == customerTab){
            showEfficiencey = false;
            hasAccount = false;
            customer = true;
            admin = false;
        }

        else if(e.getSource() == logIn){
            String firstNameString = firstName.getText();
            String lastNameString = lastName.getText();
            int pinString = Integer.parseInt(pin.getText());

            currentAccount = tree.get(new Account(lastNameString, firstNameString, 0, 0));
            if(currentAccount == null){
                noAccount = true;
                wrongPin = false;
                return;
            }
            else{
                noAccount = false;
                if(currentAccount.getPin() == pinString){
                    loggedIn = true;
                    wrongPin = false;
                }
                else{
                    wrongPin = true;
                }
            }   
        }

        else if(e.getSource() == withdrawButton){
            double amount = Double.parseDouble(amountText.getText());
            if(currentAccount.getBalance() > amount){
                hasEnough = true;
                currentAccount.setBalance(currentAccount.getBalance() - amount);
            }

            else
            hasEnough = false;
        }
        
        else if(e.getSource() == depositButton){
            double amount = Double.parseDouble(amountText.getText());
            currentAccount.setBalance(currentAccount.getBalance() + amount);
        }

        else if(e.getSource() == logOut){
            loggedIn = false;
        }

        else if(e.getSource() == searchButton){
            showEfficiencey = true;
            currentAccount = tree.get(new Account(lastNameAdmin.getText(), firstNameAdmin.getText(), 0, 0));
            hasAccount = true;
        }

        else if(e.getSource() == deleteButton){
            showEfficiencey = false;
            tree.remove(new Account(lastNameAdmin.getText(), firstNameAdmin.getText(), 0, 0));
            accountList.setText(tree.toString());
            hasAccount = false;
        }

        else if(e.getSource() == addAccount){
            showEfficiencey = false;
            addingAccount = true;
        }

        else if(e.getSource() == cancelNewCustomer){
            addingAccount = false;
        }

        else if(e.getSource() == addNewCustomer){

            showEfficiencey = true;
            String firstName = newFirstName.getText();
            String lastName = newLastName.getText();
            int pin = Integer.parseInt(newPin.getText());
            double balance = Double.parseDouble(newBalance.getText());
            tree.add(new Account(lastName, firstName, pin, balance));
            accountList.setText(tree.toString());
            addingAccount = false;
        }
        
        repaint();
    }
}
