package moneycalculator;

import control.Command;
import model.Currency;
import swing.SwingMoneyDialog;
import swing.SwingMoneyDisplay;
import view.MoneyDialog;
import view.MoneyDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame{
    private Currency[] currencies;
    private Map<String, Command> commands = new HashMap<>();
    private MoneyDialog moneyDialog;
    private MoneyDisplay moneyDisplay;

    public MainFrame(Currency[] currencies){
        this.currencies = currencies;
        this.setTitle("Crypto Money Calculator");
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(moneyDialog(), BorderLayout.NORTH);
        this.add(moneyDisplay(), BorderLayout.CENTER);
        this.add(toolBar(), BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void add(Command command){
        commands.put(command.name(), command);
    }

    public MoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public MoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }

    private Component moneyDialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog(currencies);
        moneyDialog = dialog;
        return dialog;
    }

    private Component moneyDisplay() {
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        moneyDisplay = display;
        return display;
    }

    private Component toolBar() {
        JPanel jPanel = new JPanel();
        jPanel.add(calculateButton());
        return jPanel;
    }

    private JButton calculateButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(calculate());
        return button;
    }

    private ActionListener calculate() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("calculate").execute();
            }
        };
    }

}
