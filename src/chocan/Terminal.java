package chocan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Terminal extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public Terminal() {
		ACMEAccountingServices sys = new ACMEAccountingServices();
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Add a window listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sys.saveInfo();
                System.exit(0);
            }
        });
        
        JTextField nameField = new JTextField(); 
        nameField.setBounds(140, 80, 100, 20);;
        JTextField userNumberField = new JTextField();
        userNumberField.setBounds(140, 100, 100, 20);;
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(140, 120, 100, 20);
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	verifyLogin(nameField.getText(), userNumberField.getText());
            }
        });
        
        
        this.setSize(400, 400);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.add(loginButton);
        this.add(nameField);
        this.add(userNumberField);
	}
	
	//verifies member by using card info; name (formatted "first last") and number
	
	private boolean verifyLogin(String name, String number) {
		// We can use Member.java here
		return false;
	}
	
	
    
}
