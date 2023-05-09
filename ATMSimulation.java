import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

class BankAccount{
    
    private double balance;
    
    public BankAccount(){
        
        this.balance = 0.0;
    }
    
    public void deposite(double amount){
        
        this.balance += amount;
    }
    public void withdrow(double amount){
        
        this.balance -= amount;
    }
       
    public double getBalance(){
        
        return this.balance;
    }
}

class Customer{
    
    private int number;
    private int pin;
    private BankAccount checking;
    private BankAccount saving;
    
    public Customer(){
        
        this.number = 1;
        this.pin = 1234;
        this.checking = new BankAccount();
        this.saving = new BankAccount();
    }
    public Customer(int number, int pin){
        
        this.number = number;
        this.pin = pin;
        this.checking = new BankAccount();
        this.saving = new BankAccount();
    }
    
    public void setNumber(int number){
        
        this.number = number;
    }
    public void setPIN(int pin){
        
        this.pin = pin;
    }
    public void setChecking(BankAccount checking){
        
        this.checking = checking;
    }
    public void setSaving(BankAccount saving){
        
        this.saving = saving;
    }
    
    public int getNumber(){
        
        return this.number;
    }
    public int getPIN(){
        
        return this.pin;
    }
    
    public BankAccount checking(){
             
        return this.checking;
    }
    public BankAccount saving(){
        
        return this.saving;
    }
}

class Bank{
    
    private ArrayList<Customer> customers;
    
    public Bank(){
        
        this.customers = new ArrayList<>();
    }
    
    public void setCustomers(ArrayList<Customer> customers){
        
        this.customers = customers;
    }
    public ArrayList<Customer> getCustomers(){
        
        return this.customers;
    }
}

class ATM{
    
    private Scanner in = new Scanner(System.in); 
    private int num;
    private Bank bank;
    private int number;
    private int pin;
    
    public ATM(){
        
        this.num = 1;
        this.bank = new Bank();
        setCustomersInBank();
    }
    
    private void setCustomersInBank(){
        
        ArrayList<Customer> customers = new ArrayList<>();
          customers.add(new Customer(1, 1234));
          customers.add(new Customer(2, 2345));
          customers.add(new Customer(3, 4567));
          customers.add(new Customer(4, 3210));
          
        this.bank.setCustomers(customers);
    }
    
    public Bank getBank(){
        
        return this.bank;
    }
    
    // in part1
    public void machine(){
        
        while(this.num != 0){
            System.out.print("Enter acustomer number: ");
            this.number = in.nextInt();
            System.out.print("Enter PIN : ");
            this.pin = in.nextInt();
            
            boolean flag = false;
            int i=0;
            for(; i<this.bank.getCustomers().size(); i++)
                if(this.bank.getCustomers().get(i).getNumber() == this.number &&
                    this.bank.getCustomers().get(i).getPIN() == this.pin){
                    
                    flag = true;
                    break;
                }
            
            if(flag){
                char c = ' ';
                while(c != 'c' && c != 'C'){
                    System.out.print("A: Checking, B: Saving, C: Quit :: ");
                    c = in.next().charAt(0);
                    
                    if(c != 'c' && c != 'C'){
                        if(c == 'a' || c == 'A')
                            System.out.println("Balance: "+this.bank.getCustomers().get(i).checking().getBalance());
                        else
                            System.out.println("Balance: "+this.bank.getCustomers().get(i).saving().getBalance());                   
                        
                        System.out.print("A: Deposite, B: Withdrow, C: Close :: ");
                        char cc = in.next().charAt(0);
                        double am = 0;
                        switch(cc){                                  
                            case 'a'|'A':
                                System.out.print("Amount: ");
                                am = in.nextDouble();
                                if(c == 'a' || c == 'A')
                                    this.bank.getCustomers().get(i).checking().deposite(am);
                                else
                                    this.bank.getCustomers().get(i).saving().deposite(am);
                                break;
                            case 'b'|'B':
                                System.out.print("Amount: ");
                                am = in.nextDouble();
                                if(c == 'a' || c == 'A')
                                    this.bank.getCustomers().get(i).checking().withdrow(am);
                                else
                                    this.bank.getCustomers().get(i).saving().withdrow(am);
                                break; 
                        }                       
                    }
                }
            }
            else
                System.out.println("  This customer is not found");
        }
    }
}

public class ATMSimulation extends Application{

    private BorderPane bord = new BorderPane();    
    private TextField txt = new TextField();
    private TextArea txta = new TextArea();
    
    private Button btnd = new Button(".");
    private Button btnc = new Button("CE");    
    private Button btn0 = new Button("0");
    private Button btn1 = new Button("1");
    private Button btn2 = new Button("2");
    private Button btn3 = new Button("3");
    private Button btn4 = new Button("4");
    private Button btn5 = new Button("5");
    private Button btn6 = new Button("6");
    private Button btn7 = new Button("7");
    private Button btn8 = new Button("8");
    private Button btn9 = new Button("9");
    
    private Button a = new Button("A");
    private Button b = new Button("B");    
    private Button c = new Button("C");    
    
    private int number = 0;
    private int pin = 0;
    private double amount = 0;
    private int i=0;
    
    private ATM atm = new ATM();
    
    private VBox top(){
        
        StackPane sp = new StackPane();
          sp.setPadding(new Insets(0, 45, 0, 45));
          sp.getChildren().add(txt);
          
        GridPane gp = new GridPane();
          gp.setAlignment(Pos.CENTER);
          gp.add(btn7, 0, 0);  gp.add(btn8, 1, 0);  gp.add(btn9, 2, 0);
          gp.add(btn4, 0, 1);  gp.add(btn5, 1, 1);  gp.add(btn6, 2, 1);
          gp.add(btn1, 0, 2);  gp.add(btn2, 1, 2);  gp.add(btn3, 2, 2);
          gp.add(btnd, 0, 3);  gp.add(btn0, 1, 3);  gp.add(btnc, 2, 3);
        
        VBox vb = new VBox(3);
          vb.setAlignment(Pos.CENTER);
          vb.setPadding(new Insets(20, 0, 20, 0));
          vb.getChildren().addAll(sp, gp);
        
        return vb;
    }
    
    private StackPane center(){
        
        StackPane sp = new StackPane();
          sp.setPadding(new Insets(0, 25, 0, 25));
          sp.getChildren().add(txta);
        
        return sp;
    }
    
    private HBox bottom(){
        
        HBox hb = new HBox(20);
           hb.setAlignment(Pos.CENTER);
           hb.setPadding(new Insets(20, 0, 20, 0));
           hb.getChildren().addAll(a, b, c);
        
        return hb;
    }
    
    private void edit(){
        
        txt.setPrefSize(210, 30);
        txt.setText("");
        txt.setDisable(true);       
        txta.setText("Enter customer number: ");
        
        btn0.setPrefSize(70, 50);
        btn1.setPrefSize(70, 50);
        btn2.setPrefSize(70, 50);
        btn3.setPrefSize(70, 50);
        btn4.setPrefSize(70, 50);
        btn5.setPrefSize(70, 50);
        btn6.setPrefSize(70, 50);
        btn7.setPrefSize(70, 50);
        btn8.setPrefSize(70, 50);
        btn9.setPrefSize(70, 50);
        btnd.setPrefSize(70, 50);
        btnc.setPrefSize(70, 50);
        
        a.setPrefSize(70, 30); a.setFont(Font.font(15));
        b.setPrefSize(70, 30); b.setFont(Font.font(15));
        c.setPrefSize(70, 30); c.setFont(Font.font(15));
    }
    private void actions(Scene sn){
        
        sn.setOnKeyPressed(e -> {
        
            if(e.getCode() == KeyCode.ENTER)
                if(number == 0){
                    if(txt.getText().equals("")){
                        txta.setText(txta.getText()+"\n"+"Fill your number, Enter number: ");
                        return;
                    }
                    number = Integer.parseInt(txt.getText());
                    txta.setText(txta.getText()+number+"\n"
                               + "Enter acustomer pin: ");
                }
                else
                if(pin == 0){
                    if(txt.getText().equals("")){
                        txta.setText(txta.getText()+"\n"+"Fill your pin, Enter pin: ");
                        return;
                    }
                    pin = Integer.parseInt(txt.getText());
                    txta.setText(txta.getText()+pin+"\n\n");
                    
                    boolean flag = false;
                    for(; i<atm.getBank().getCustomers().size(); i++)
                        if(atm.getBank().getCustomers().get(i).getNumber() == number &&
                            atm.getBank().getCustomers().get(i).getPIN() == pin)
                        {
                            flag = true;
                            break;
                        }
                    
                    if(flag)
                        txta.setText(txta.getText()+""
                                   + "A: Checking, B: Saving, C: Quit :: ");
                    else{
                        txta.setText(txta.getText()+""
                                   + "This customer is not found"+"\n\n"
                                   + "Enter acustomer number: ");
                        number = 0;
                        pin = 0;
                    }                    
                } 
                else{                  
                    if(txt.getText().equals("")){
                        txta.setText(txta.getText()+"\n"
                                   + "Fill text of amount, Enter amount: ");
                        return;
                    }
                    String arr[] = txta.getText().split("\n");
                    amount = Double.parseDouble(txt.getText());
                    
                    if(!arr[arr.length-1].equals("A: Deposit, B: Withdrow, C: Close :: ") && 
                        !arr[arr.length-1].equals("A: Checking, B: Saving, C: Quit :: "))
                    {
                        if(arr[arr.length-2].equals("A: Deposit, B: Withdrow, C: Close :: A"))
                            if(arr[arr.length-4].equals("A: Checking, B: Saving, C: Quit :: A"))
                                atm.getBank().getCustomers().get(i).checking().deposite(amount);
                            else
                                atm.getBank().getCustomers().get(i).saving().deposite(amount);
                        else
                        if(arr[arr.length-2].equals("A: Deposit, B: Withdrow, C: Close :: B"))    
                            if(arr[arr.length-4].equals("A: Checking, B: Saving, C: Quit :: A"))
                                atm.getBank().getCustomers().get(i).checking().withdrow(amount);
                            else
                                atm.getBank().getCustomers().get(i).saving().withdrow(amount);
                        else{

                        }
                        
                        txta.setText(txta.getText()+amount+"\n"
                               + "A: Checking, B: Saving, C: Quit :: ");
                    }
                }
        });
        
        btnd.setOnAction(e -> {
        
            txt.setText(txt.getText()+".");
        });
        btnc.setOnAction(e -> {
        
            txt.setText("");
        });
        btn0.setOnAction(e -> {
        
            txt.setText(txt.getText()+"0");
        });
        btn1.setOnAction(e -> {
        
            txt.setText(txt.getText()+"1");
        });
        btn2.setOnAction(e -> {
        
            txt.setText(txt.getText()+"2");
        });
        btn3.setOnAction(e -> {
        
            txt.setText(txt.getText()+"3");
        });
        btn4.setOnAction(e -> {
        
            txt.setText(txt.getText()+"4");
        });
        btn5.setOnAction(e -> {
        
            txt.setText(txt.getText()+"5");
        });
        btn6.setOnAction(e -> {
        
            txt.setText(txt.getText()+"6");
        });
        btn7.setOnAction(e -> {
        
            txt.setText(txt.getText()+"7");
        });
        btn8.setOnAction(e -> {
        
            txt.setText(txt.getText()+"8");
        });
        btn9.setOnAction(e -> {
        
            txt.setText(txt.getText()+"9");
        });
        
        a.setOnAction(e -> {
        
            String arr[] = txta.getText().split("\n");
            if(arr[arr.length-1].equals("A: Checking, B: Saving, C: Quit :: ")){
                txta.setText(txta.getText()+"A"+"\n"
                           + "Balance: "+atm.getBank().getCustomers().get(i).checking().getBalance()+"\n"
                           + "A: Deposit, B: Withdrow, C: Close :: ");
            }
            else
            if(arr[arr.length-1].equals("A: Deposit, B: Withdrow, C: Close :: ")){
                txta.setText(txta.getText()+"A"+"\n"
                           + "Amount: ");
            }
        });
        b.setOnAction(e -> {
        
            String arr[] = txta.getText().split("\n");
            if(arr[arr.length-1].equals("A: Checking, B: Saving, C: Quit :: ")){
                txta.setText(txta.getText()+"B"+"\n"
                           + "Balance: "+atm.getBank().getCustomers().get(i).saving().getBalance()+"\n"
                           + "A: Deposit, B: Withdrow, C: Close :: ");
            }
            else
            if(arr[arr.length-1].equals("A: Deposit, B: Withdrow, C: Close :: ")){
                txta.setText(txta.getText()+"B"+"\n"
                           + "Amount: ");
            }
        });
        c.setOnAction(e -> {
        
            String arr[] = txta.getText().split("\n");
            if(arr[arr.length-1].equals("A: Checking, B: Saving, C: Quit :: ")){
                number = 0;
                pin = 0;
                amount = 0;
                
                txta.setText(txta.getText()+"C"+"\n\n"
                           + "Enter other customer number: ");
                i = 0;
            }
            else
            if(arr[arr.length-1].equals("A: Deposit, B: Withdrow, C: Close :: "))    
                txta.setText(txta.getText()+"C"+"\n"
                           + "A: Checking, B: Saving, C: Quit :: ");
        });
    }
    
    @Override
    public void start(Stage stage) throws Exception {
    
        bord.setTop(top());
        bord.setCenter(center());
        bord.setBottom(bottom());
        Scene sn = new Scene(bord, 300, 450);
        
        edit();
        actions(sn);
        
        stage.setTitle("ATM SIMULATION");
        stage.setScene(sn);       
        stage.show();
    }
    
    public static void main(String[] args) {
        
        // in part1
        // ATM atm = new ATM();
        //   atm.machine();
        
        // in part2
        launch();
    }  
}