public class Account implements Comparable<Account>{
    private String lastName, firstName;
    private int pin;
    private double balance;

    public Account(String lastName, String firstName, int pin, double balance){
        this.lastName = lastName;
        this.firstName = firstName;
        this.pin = pin;
        this.balance = balance;
    }

    public String getName(){
        return lastName + ", " + firstName;
    }

    public int getPin(){
        return pin;
    }

    public double getBalance(){
        return balance;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setBalance(double amount){
        balance = amount;
    }

    @Override
    public String toString(){
        return getName();
    }

    @Override
    public int compareTo(Account temp){
        if(this.lastName.compareTo(temp.getLastName()) > 0){
            return 1;
        }
        else if(this.lastName.compareTo(temp.getLastName()) < 0){
            return -1;
        }
        if(this.firstName.compareTo(temp.getFirstName()) > 0){
            return 1;
        }
        else if(this.firstName.compareTo(temp.getFirstName()) < 0){
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o){
        Account temp = (Account)(o);
        if(this.firstName.equals(temp.firstName) && this.lastName.equals(temp.lastName)){
            return true;
        }
        return false;
    }
}
