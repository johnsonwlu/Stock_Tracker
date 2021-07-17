package gui;

import delegates.main_Delegate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import com.example.Stock_Tracker.API_Connect;

public class main_GUI extends JFrame implements ActionListener {

    private main_Delegate delegate;

    public main_GUI() {
        super();
    }

    private JButton enter_button;
    private JTextField enter_textfield;
    private JButton exit;
    private JTextArea printLines;

    public void showFrame(main_Delegate delegate) {

        this.delegate = delegate;

        enter_button = new JButton("Enter");
        enter_textfield = new JTextField(10);
        printLines = new JTextArea();
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
        contentPane.add(enter_button);
        contentPane.add(exit);
        contentPane.add(printLines);
        enter_button.addActionListener(this);
        exit.addActionListener(this);

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
            handleMaple(e);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        handleExit(e);
    }

    public void handleMaple(ActionEvent e) throws Exception {
        if (e.getActionCommand().equals("Enter")) {
            API_Connect ac = new API_Connect();
            Integer resultOne = ac.api(enter_textfield.getText());
            printLines.setText("  Stock Price: " + String.valueOf(resultOne));

        }
    }

    public void handleExit(ActionEvent e) {
        if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
    }

}
