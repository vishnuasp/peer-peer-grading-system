package GR_Application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

/**
 * HomeScreen class is used to initialize the application window and fetch 
 * the input(i.e. Number of team mates) from the User and Validate if the input 
 * is an integer value and whether it lies between 2 and 7 or not.
 * 
 */
public class HomeScreen {
    // The main/home window of the application to ask the user for no. of students and proceed to grading screen if no.of
	// students is a valid integer between 2 and 7.
	private JFrame frame;
	private JTextField textField;
	public int num;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//Main method to initialize HomeScreen.
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					//Initializing the main/home window.
					HomeScreen window = new HomeScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					// Catch an Exception.
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	//Default Constructor to initialize the Application with the frame and other components like textFields, textBoxes and Buttons
	public HomeScreen() {
		
		initialize();
	}
	/**
	 * Method to validate the input (no.of team members) given by the user, if the entered number is an integer between 
	 * 2 and 7 this method returns true, else it throws NumberFormatException and NotInRangeException(Custom Exception).
	 * 
	 * Parameters: The user entered input, String s.
	 * Returns: This method returns a boolean value proving if the input is valid or not.
	 */
	public static boolean ValidateInput(String s) throws NotInRangeException, NumberFormatException
	{
		boolean valid = false;
		    // fetch the no.of users, throws a 'NumberFormatException' if the value is not an integer.
			int user_count = Integer.parseInt(s);
			
			// Check if the no.of students are between 2 and 7.
			if (user_count >= 2 && user_count <= 7) {
			    // Set the boolean value valid to true so that it can proceed to 'Grading Window' .
				valid = true;
			} 
			else
			{
				// Throw an Exception if the scores entered are all zeros.
				throw new NotInRangeException("Number of Team Mates must be between 2 and 7");
			}
		return valid;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//To display the window frame and set the window bounds.
		frame = new JFrame();
		frame.setBounds(150, 0, 1053, 896);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
        frame.setTitle("Home-Grading Rubic");
        
        //To fetch the number of students from the User.
		JLabel lblEnterTheNumber = new JLabel("Enter the number of Students in the Team\r\n\r\n");
		lblEnterTheNumber.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblEnterTheNumber.setBounds(291, 152, 471, 66);
		frame.getContentPane().add(lblEnterTheNumber);
		textField = new JTextField();
		textField.setBounds(127, 242, 774, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
        // A Check-box to ask the user if the previous scores are to be retrieved. 
		JCheckBox chckbxShowPreviousScores = new JCheckBox("Retrieve Previous Scores ");
		chckbxShowPreviousScores.setBounds(395, 323, 582, 41);
		frame.getContentPane().add(chckbxShowPreviousScores);
		
        // To go to the Grading Rubric window if the number of students entered by the User is valid.
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String n = textField.getText();
				boolean prev = chckbxShowPreviousScores.isSelected();
				num = 0;
				boolean isvalid = false;
				try
				{
					 isvalid = ValidateInput(n);
					 if(isvalid)
						{
							num = Integer.parseInt(n);
							// Proceed to next screen (Grading Window). 
							GradeRubric gr = new GradeRubric(num, prev);
							frame.setVisible(false);
							gr.setVisible(true);						
						}
				}
				catch(NumberFormatException e)
				{
					// Show a dialog box when the number of students is not a valid integer.
					JOptionPane.showMessageDialog(null, "Enter Integer values between 2 and 7");
				}
				catch(NotInRangeException e)
				{
					// Show a dialog box when the number of students is not between 2 and 7.
					JOptionPane.showMessageDialog(null, "Number of Team Mates must be between 2 and 7");
				}			
			}
		});
		btnNewButton.setBounds(395, 382, 171, 41);
		frame.getContentPane().add(btnNewButton);
		
        // UB name place holder/label
		JLabel lblUniversityAtBuffalo = new JLabel("University at Buffalo");
		lblUniversityAtBuffalo.setForeground(Color.BLUE);
		lblUniversityAtBuffalo.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblUniversityAtBuffalo.setBounds(345, 50, 384, 41);
		frame.getContentPane().add(lblUniversityAtBuffalo);
		JLabel lblTheStateUniversity = new JLabel("The State University of New York");
		lblTheStateUniversity.setForeground(SystemColor.windowBorder);
		lblTheStateUniversity.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTheStateUniversity.setBounds(355, 90, 274, 19);
		frame.getContentPane().add(lblTheStateUniversity);

	}
}
