package gui;

import com.example.Stock_Tracker.Stock;
import delegates.main_Delegate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import com.example.Stock_Tracker.API_Connect;

public class main_GUI extends JFrame implements ActionListener {

    public ArrayList<Stock> stocks = new ArrayList<>();
    private main_Delegate delegate;

    public main_GUI() {
        super();
    }

    private JButton enter_button;
    public JTextField enter_textfield;
    public JSpinner size_textfield;
    private JButton exit;
    private JTextArea printLines;
    private JButton print;
    DefaultListModel<String> stockList = new DefaultListModel<>();
    private JList<String> mainList;
    API_Connect ac = new API_Connect();
    public Integer overallCost = 0;

    public void showFrame(main_Delegate delegate) {

        this.delegate = delegate;

        enter_button = new JButton("Enter");
        print = new JButton("Print");
        enter_textfield = new JTextField(10);
        size_textfield = new JSpinner();
        printLines = new JTextArea();
        mainList = new JList<>( stockList );

        exit = new JButton("Exit");

//        try {
//            Image img = ImageIO.read(getClass().getResource("/exit.png"));
//            exit.setIcon(new ImageIcon(img));
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        GridBagLayout gb = new GridBagLayout();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        contentPane.add(enter_textfield);
        contentPane.add(size_textfield);
        contentPane.add(enter_button);
        contentPane.add(print);
        contentPane.add(exit);
        contentPane.add(printLines);
        contentPane.setPreferredSize(new Dimension(700,200));
        enter_button.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);
        mainList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        mainList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        mainList.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(mainList);
        listScroller.setPreferredSize(new Dimension(250,80));
        contentPane.add(listScroller);
        this.pack();

        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

        // make the window visible
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            handleAdd(e);
            handlePrint(e);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        handleExit(e);
    }

    public void handleAdd(ActionEvent e) throws Exception {
        if (e.getActionCommand().equals("Enter")) {
            String name = enter_textfield.getText();
            Integer resultOne = ac.api(name);
            Integer resultTwo = (Integer) size_textfield.getValue();

            overallCost += resultOne * resultTwo;
            boolean x = false;
            for (int i = 0; i<stocks.size(); i++) {
                System.out.println(stocks.get(i).getStockName().equals(name));
                if (stocks.get(i).getStockName().equals(name)) {
                    stocks.set(i, new Stock(name, stocks.get(i).getCount() + resultTwo));
                    stockList.setElementAt(stocks.get(i).getStockName() + " " + stocks.get(i).getCount(), i);
                    x = true;
                }
            }
            if (x == false) {
                Stock newStock = new Stock(name, resultTwo);
                stocks.add(newStock);
                stockList.addElement(newStock.getStockName() + " " + newStock.getCount());
            }
            printLines.setText("  Stock Price: " + String.valueOf(resultOne));
            printLines.setText(" Total portfolio " + overallCost);


        }
    }
    public void handlePrint(ActionEvent e) {
        if (e.getActionCommand().equals("Print")) {
            for (Stock stock: stocks) {
                System.out.println("you have " + stock.getCount() + " shares of " + stock.getStockName());
            }

        }
    }
    public void handleExit(ActionEvent e) {
        if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
    }

}
