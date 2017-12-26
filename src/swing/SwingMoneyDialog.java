package swing;

import model.Currency;
import model.Money;
import view.MoneyDialog;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SwingMoneyDialog extends JPanel implements MoneyDialog{

    private String amount;
    private Currency currency;
    private Currency[] currencies;

    public SwingMoneyDialog(Currency[] currencies) {
        this.currencies = currencies;
        this.add(amount());
        this.add(currency());
    }

    @Override
    public Money get() {
        return new Money(Double.parseDouble(amount), currency);
    }

    private Component amount() {
        JTextField textField = new JTextField();
        textField.setColumns(10);
        textField.getDocument().addDocumentListener(amountChanged());
        amount = textField.getText();
        return textField;
    }

    private DocumentListener amountChanged() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                amountChanged(e.getDocument());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                amountChanged(e.getDocument());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                amountChanged(e.getDocument());
            }

            private void amountChanged(Document document) {
                try {
                    amount = document.getText(0, document.getLength());
                } catch (BadLocationException e) {

                }
            }
        };
    }

    private Component currency() {
        JComboBox jComboBox = new JComboBox(currencies);
        jComboBox.addItemListener(currencyChanged());
        currency = (Currency) jComboBox.getSelectedItem();
        return jComboBox;
    }

    private ItemListener currencyChanged() {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED) return;
                currency = (Currency) e.getItem();
            }
        };
    }
}
