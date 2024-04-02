import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class Bank_project_UI extends JFrame implements ActionListener {
    private JTextField nameField, accNumField, amountField;
    private JButton createAccountButton, existingAccountButton, exitButton;
    private HashMap<Integer, String> data;
    private int amount;
    private Random ra;

    public Bank_project_UI() {
        setTitle("Online Banking System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        data = new HashMap<>();
        amount = 0;
        ra = new Random();

        JLabel welcomeLabel = new JLabel("WELCOME TO THE ONLINE BANKING SYSTEM!🏦💸");
        add(welcomeLabel);

        createAccountButton = new JButton("New Customer");
        existingAccountButton = new JButton("Existing Customer");
        exitButton = new JButton("Exit");

        createAccountButton.addActionListener(this);
        existingAccountButton.addActionListener(this);
        exitButton.addActionListener(this);

        add(createAccountButton);
        add(existingAccountButton);
        add(exitButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createAccountButton) {
            createAccountDialog();
        } else if (e.getSource() == existingAccountButton) {
            existingAccountDialog();
        } else if (e.getSource() == exitButton) {
            exitBank();
        }
    }

    private void createAccountDialog() {
        JFrame createAccountFrame = new JFrame("Create Account");
        createAccountFrame.setSize(300, 200);
        createAccountFrame.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Enter Name:");
        nameField = new JTextField();
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountField = new JTextField();
        JButton createButton = new JButton("Create Account");

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int account_number = ra.nextInt(1000, 100000);
                data.put(account_number, name);
                amount += Integer.parseInt(amountField.getText());
                JOptionPane.showMessageDialog(null, "Account created successfully!\nAccount Number: " + account_number);
                createAccountFrame.dispose();
            }
        });

        createAccountFrame.add(nameLabel);
        createAccountFrame.add(nameField);
        createAccountFrame.add(amountLabel);
        createAccountFrame.add(amountField);
        createAccountFrame.add(createButton);

        createAccountFrame.setVisible(true);
    }

    private void existingAccountDialog() {
        JFrame existingAccountFrame = new JFrame("Existing Account");
        existingAccountFrame.setSize(300, 200);
        existingAccountFrame.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Enter Name:");
        JTextField nameField = new JTextField();
        JLabel accNumLabel = new JLabel("Enter Account Number:");
        accNumField = new JTextField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int acc_num = Integer.parseInt(accNumField.getText());
                if (data.containsKey(acc_num) && data.get(acc_num).equals(name)) {
                    performTransactionDialog(acc_num);
                    existingAccountFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Account Number or Name!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        existingAccountFrame.add(nameLabel);
        existingAccountFrame.add(nameField);
        existingAccountFrame.add(accNumLabel);
        existingAccountFrame.add(accNumField);
        existingAccountFrame.add(loginButton);

        existingAccountFrame.setVisible(true);
    }

    private void performTransactionDialog(int acc_num) {
        JFrame transactionFrame = new JFrame("Perform Transaction");
        transactionFrame.setSize(300, 200);
        transactionFrame.setLayout(new GridLayout(3, 1));

        JButton depositButton = new JButton("Deposit Money");
        JButton withdrawButton = new JButton("Withdraw Money");
        JButton inquiryButton = new JButton("Bank Inquiry");

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int depositAmount = Integer.parseInt(JOptionPane.showInputDialog("Enter amount to deposit:"));
                amount += depositAmount;
                JOptionPane.showMessageDialog(null, "Amount deposited successfully!");
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int withdrawAmount = Integer.parseInt(JOptionPane.showInputDialog("Enter amount to withdraw:"));
                if (withdrawAmount <= amount) {
                    amount -= withdrawAmount;
                    JOptionPane.showMessageDialog(null, "Amount withdrawn successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        inquiryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Your current account balance is: " + amount, "Bank Inquiry", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        transactionFrame.add(depositButton);
        transactionFrame.add(withdrawButton);
        transactionFrame.add(inquiryButton);

        transactionFrame.setVisible(true);
    }

    private void exitBank() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the bank?", "Exit Bank", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Thank you for visiting!", "Exit", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Bank_project_UI().setVisible(true);
    }
}
