#Port Scanner
This is a simple Java-based Port Scanner application that allows you to scan a range of ports on a specified target IP address. The application is built using Java Swing for the user interface and Java networking libraries for the actual port scanning functionality.

Features
IP Targeting: Enter the IP address of the target machine.
Port Range Scanning: Specify a range of ports to scan on the target.
Status Output: Displays whether each port in the specified range is open or closed.
Reset Functionality: Allows you to clear all input fields and start a new scan.
How It Works
Enter the target IP address in the "IP" field.
Enter the starting port number in the "Starting Port" field.
Enter the ending port number in the "Ending Port" field.
Click the "SCAN" button to start the port scanning process.
The application will display whether each port in the specified range is open or closed.
You can click the "RESET" button to clear all fields and results.

Screenshots:
![image](https://github.com/user-attachments/assets/41d471f7-3cc2-4c97-93c6-80c89d64ad5b)

![image](https://github.com/user-attachments/assets/1907deed-507c-44dd-b734-eddca2d2bb54)

Code Overview
PortScanner.java: The main class that initializes the GUI and handles user input, port scanning, and output display.
Swing Components: Uses JFrame, JTextField, JTextArea, JButton, JLabel, and JScrollPane for the user interface.
Networking: Utilizes Java's Socket class to attempt connections to each port in the specified range.
