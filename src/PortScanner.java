import java.awt.EventQueue;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

public class PortScanner {

    private JFrame frame;
    private JTextField TF_Target;
    private JTextField TF_StartPort;
    private JTextField TF_EndPort;
    private JTextArea TA_Message; 
    private JButton btnScan;
    private JButton btnReset;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PortScanner window = new PortScanner();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PortScanner() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 329, 445);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        btnScan = new JButton("SCAN");
        btnScan.setBounds(35, 213, 89, 23);
        btnScan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnScanAction();
            }
        });
        frame.getContentPane().add(btnScan);

        btnReset = new JButton("RESET");
        btnReset.setBounds(182, 213, 89, 23);
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnResetAction();
            }
        });
        frame.getContentPane().add(btnReset);

        JLabel lblNewLabel = new JLabel("Port Scanner");
        lblNewLabel.setBounds(57, 11, 193, 52);
        lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 26));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("IP:");
        lblNewLabel_1.setBounds(25, 80, 48, 26);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        frame.getContentPane().add(lblNewLabel_1);

        TF_Target = new JTextField();
        TF_Target.setBounds(122, 84, 157, 20);
        frame.getContentPane().add(TF_Target);
        TF_Target.setColumns(10);

        JLabel lbStart = new JLabel("Starting Port:");
        lbStart.setBounds(10, 117, 114, 26);
        lbStart.setFont(new Font("Tahoma", Font.PLAIN, 13));
        frame.getContentPane().add(lbStart);

        TF_StartPort = new JTextField();
        TF_StartPort.setBounds(122, 121, 157, 20);
        TF_StartPort.setColumns(10);
        frame.getContentPane().add(TF_StartPort);

        TF_EndPort = new JTextField();
        TF_EndPort.setBounds(122, 158, 157, 20);
        TF_EndPort.setColumns(10);
        frame.getContentPane().add(TF_EndPort);

        JLabel lbEnd = new JLabel("Ending Port:");
        lbEnd.setBounds(10, 154, 114, 26);
        lbEnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
        frame.getContentPane().add(lbEnd);

        TA_Message = new JTextArea();
        TA_Message.setEditable(false);
        TA_Message.setBounds(25, 273, 254, 117);
        TA_Message.setBackground(Color.WHITE);
        frame.getContentPane().add(TA_Message);
        TA_Message.setAutoscrolls(true);
        
        JScrollPane scrollPane = new JScrollPane(TA_Message);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(25, 273, 254, 117);
        frame.getContentPane().add(scrollPane);
    }

    public void btnScanAction() {
        String Target;
        int PortStart;
        int PortEnd;

        if (TF_Target.getText().isEmpty()) {
            TA_Message.setText("Enter a Target.");
            return;
        } else if (TF_StartPort.getText().isEmpty()) { 
            TA_Message.setText("Enter a Starting Port.");
            return;
        } else if (TF_EndPort.getText().isEmpty()) {
            TA_Message.setText("Enter an Ending Port.");
            return;
        }

        TA_Message.setText(""); // Clear the panel

        Target = TF_Target.getText();
        PortStart = Integer.parseInt(TF_StartPort.getText()); //Converting from string to int
        PortEnd = Integer.parseInt(TF_EndPort.getText());

        TA_Message.setText("Scanning...\n");

        Thread X1 = new Thread() { // Iterates through the port range
            public void run() {
                btnScan.setEnabled(false);
                btnReset.setText("Stop");

                for (int x = PortStart; x <= PortEnd; x++) {
                    try {
                        Socket SOCK = new Socket(Target, x);
                        TA_Message.append("Port " + x + " is open\n");
                        SOCK.close();
                    } catch (IOException e) {
                        TA_Message.append("Port " + x + " is closed\n");
                    }
                }
                btnScan.setEnabled(true);
                btnReset.setText("RESET");
            }
        };

        X1.start();
    }

    public void btnResetAction() {
        TF_Target.setText("");
        TF_StartPort.setText("");
        TF_EndPort.setText("");
        TA_Message.setText("");
    }
}
