package GR_Application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.Math;

/**
 * GradeRubric class is used to show the application frame with the grading rubric depending on the input given by 
 * the User on the HomeScreen and fetch the scores for each category for all the Peers and themselves from the User.
 * This class has methods which check if the scores entered by the User are valid and sends it to the Normalise Frame.
 * 
 * 
 */
public class GradeRubric extends JFrame {
   // To retrieve and display previously entered scores if user desires and fetch the entered/altered scores to calculate 
   // normalized score of each student.
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	//Main method to initialized the GradeRubric
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradeRubric frame = new GradeRubric();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	// Default Constructor for GradeRubric Class.
	public GradeRubric() {
	}
	
	/**
	 *  Parameterized Constructor for GradeRubric Class. This uses the number of team members provided by user in previous 
	 *  screen and the check-box value whether to retrieve previous scores and creates the Panel.
	 *  
	 *  Parameters: This method uses the number of students entered by the user 'num' and the boolean value 'prev' which 
	 *  is used to check if the previous scores are to be shown or not.
	 */
	public GradeRubric(int num, boolean prev) {
		//Set bounds and create components like Labels, Combo boxes and buttons.
		int v = 170;
		JLabel names[] = new JLabel[num];
		JComboBox combo1[] = new JComboBox[num];
		JComboBox combo2[] = new JComboBox[num];
		JComboBox combo3[] = new JComboBox[num];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 0, 1057, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Grading Window");
		// Header 'Grading Rubric'
		contentPane.setLayout(null);
		JLabel lblGradingRubric = new JLabel("Grading Rubric");
		lblGradingRubric.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblGradingRubric.setBounds(385, 52, 227, 33);
		contentPane.add(lblGradingRubric);
        // Team member Name column header
		JLabel lblTeamMemberName = new JLabel("Team Member Name");
		lblTeamMemberName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTeamMemberName.setBounds(59, 102, 232, 33);
		contentPane.add(lblTeamMemberName);
		// Professionalism column header
		JLabel lblProfessionalismScore = new JLabel("Professionalism \r\n\t\t");
		lblProfessionalismScore.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProfessionalismScore.setBounds(362, 102, 164, 33);
		contentPane.add(lblProfessionalismScore);
		// Meeting Participation column header.
		JLabel lblMeeting = new JLabel("Meeting Participation\r\n");
		lblMeeting.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMeeting.setBounds(562, 102, 227, 33);
		contentPane.add(lblMeeting);
		// Work Evaluation column header.
		JLabel lblWorkEvaluation = new JLabel("Work Evaluation");
		lblWorkEvaluation.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWorkEvaluation.setBounds(805, 102, 194, 33);
		contentPane.add(lblWorkEvaluation);
		// Submit Button.
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//Action to send the scores entered for Normalisation
					String grid[][] = new String[num][4];
					for (int i = 0; i < num; i++) {
						grid[i][0] = names[i].getText();
						grid[i][1] = combo1[i].getSelectedItem().toString();
						grid[i][2] = combo2[i].getSelectedItem().toString();
						grid[i][3] = combo3[i].getSelectedItem().toString();

					}
					// calculating the sum of all the entries.
					int sum = 0;
					for (int i = 0; i < num; i++) {
						for (int j = 1; j < 4; j++) {
							String valu = grid[i][j];
							sum = sum + Integer.parseInt(valu);
						}
					}
					// Throw an exception when all scores are selected as zeros. 
					// We are allowing the user only to select values from  [0-5] from comboBox.
					// The sum will be zero only when the user selects all zeros.(Extreme Case).
					if (sum == 0) {
						throw new AllZerosException("All zeros entered : illegal");
					}
					// Send the scores and names for normalization
					Normalise nml = new Normalise(grid, num);
					contentPane.setVisible(false);
					dispose();
					nml.setVisible(true);

				} catch (NullPointerException e) {
					// Catch the exception when one or more score fields are empty. 
					JOptionPane.showMessageDialog(null, "The Score fields cannot be empty");
				} catch (AllZerosException e) {
					// Catch the exception when all the scores entered are zeros.
					JOptionPane.showMessageDialog(null, "All the Scores cannot be zeros. Select few scores");
				} catch (NotInRangeException e) {}
			}

		});
		btnSubmit.setBounds(415, 730, 137, 41);
		contentPane.add(btnSubmit);
        //Initialize the combo boxes with values and set the names to the labels.
		String[] val = { "0", "1", "2", "3", "4", "5" };
		String[] nam = { "Alex", "Bob", "Dolan", "Matt", "Obama", "Calvin", "Mr Robot" };
		if (prev == false) {
			// if the check-box to show previous scores was not selected by the user.  (Not Selected Case).
			// initialize the grading window with all scores set as empty.
			for (int i = 0; i < num; i++) {
				names[i] = new JLabel(nam[i]);
				names[i].setFont(new Font("Tahoma", Font.PLAIN, 29));
				names[i].setBounds(98, v, 500, 50);
				contentPane.add(names[i]);
				// Combo boxes to select scores for Professionalism
				combo1[i] = new JComboBox(val);
				combo1[i].setBounds(595, v, 164, 39);
				combo1[i].setSelectedIndex(-1);
				contentPane.add(combo1[i]);
				// Combo boxes to select scores for Meeting Participation
				combo2[i] = new JComboBox(val);
				combo2[i].setBounds(362, v, 164, 39);
				combo2[i].setSelectedIndex(-1);
				contentPane.add(combo2[i]);
				// Combo boxes to select scores for Work Evaluation
				combo3[i] = new JComboBox(val);
				combo3[i].setBounds(805, v, 164, 39);
				combo3[i].setSelectedIndex(-1);
				contentPane.add(combo3[i]);
				v = v + 70;
			}
		} 
		else {
			// if the check-box to show previous scores was selected by the user.  (Selected Case).
						// initialize the grading window with all scores set as empty.
			for (int i = 0; i < num; i++) {
				names[i] = new JLabel(nam[i]);
				names[i].setFont(new Font("Tahoma", Font.PLAIN, 29));
				names[i].setBounds(98, v, 500, 50);
				contentPane.add(names[i]);
				// Combo boxes to select scores for Professionalism
				combo1[i] = new JComboBox(val);
				int random = (int) (Math.random() * 5 + 1);
				combo1[i].setSelectedIndex(random);
				combo1[i].setBounds(595, v, 164, 39);
				contentPane.add(combo1[i]);
				// Combo boxes to select scores for Meeting Participation
				combo2[i] = new JComboBox(val);
				random = (int) (Math.random() * 5 + 1);
				combo2[i].setSelectedIndex(random);
				combo2[i].setBounds(362, v, 164, 39);
				contentPane.add(combo2[i]);
				// Combo boxes to select scores for Work Evaluation
				combo3[i] = new JComboBox(val);
				random = (int) (Math.random() * 5 + 1);
				combo3[i].setSelectedIndex(random);
				combo3[i].setBounds(805, v, 164, 39);
				contentPane.add(combo3[i]);
				v = v + 70;
			}
		}
	}
}
