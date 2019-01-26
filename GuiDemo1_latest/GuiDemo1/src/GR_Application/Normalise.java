package GR_Application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.text.DecimalFormat;

import javax.swing.JSeparator;

/**
 * Normalise class is used to show the application frame with the Normalised Scores of each student 
 * upto two decimal places along with their names. It includes methods which calculate normalized scores and format
 * the results to display scores.
 * 
 */
public class Normalise extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JSeparator separator_1;
	//public static int sum = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Normalise frame = new Normalise();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Default Constructor.
	 */
	public Normalise() {

	}
	
	/**
	 *  Parameterized Constructor for Normalise Class.
	 *  Uses the scores provided by the user on the grading window and calculates & displays the normalized scores for 
	 *  each student only if the scores entered are valid, throws exceptions otherwise.
	 *  
	 *  Parameters: String matrix 'grid' of size (Number_of_users) x (names(size = 1) + num_of_score categories), this holds all the 
	 *  score entered by the user on the grading window and the other parameter is the number of users.
	 *  
	 */
	public Normalise(String[][] grid, int ns) throws AllZerosException, NotInRangeException {
		//Set bounds and create components like Labels  and buttons.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 0, 1057, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Window title.
		setTitle("Normalization Window");
		// UB header
		JLabel lblUniversityAtBuffalo = new JLabel("University at Buffalo");
		lblUniversityAtBuffalo.setForeground(Color.BLUE);
		lblUniversityAtBuffalo.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblUniversityAtBuffalo.setBounds(345, 50, 384, 41);
		contentPane.add(lblUniversityAtBuffalo);
		JLabel lblTheStateUniversity = new JLabel("The State University of New York");
		lblTheStateUniversity.setForeground(SystemColor.windowBorder);
		lblTheStateUniversity.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTheStateUniversity.setBounds(355, 90, 274, 19);
		contentPane.add(lblTheStateUniversity);
		
		// Header for Student Name column.
		JLabel lblNewLabel = new JLabel("Student Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel.setBounds(110, 200, 250, 24);
		contentPane.add(lblNewLabel);
		// Header for Normalized Scores column.
		lblNewLabel_2 = new JLabel("Normalized Scores");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel_2.setBounds(615, 200, 300, 24);
		contentPane.add(lblNewLabel_2);
		separator_1 = new JSeparator();
		separator_1.setBounds(90, 230, 840, 3);
		contentPane.add(separator_1);
		
		// Code to format and calculate the Normalised Scores for each student.
		int y = 250;
		float[] normalised_scores = new float[ns];
        int[][] score_grid = new int[ns][3];
        String[][] score_gr = new String[ns][3];
		for (int i = 0; i < ns; i++) {
			for (int j = 1; j < 4; j++) {
				String valu = grid[i][j];
				score_grid[i][j-1] = Integer.parseInt(valu);
				score_gr[i][j-1] = valu;
			}
		}
        // Method call to calculate_normalised_scores to calculate Normalised Scores if valid scores were provided.
		// Throws Exceptions otherwise. Ex:( AllZeros, Empty Values).
		normalised_scores = calculate_normalised_scores(score_gr);
		
			for (int i = 0; i < ns; i++) {
				lblNewLabel_1 = new JLabel(grid[i][0]);
				lblNewLabel_1.setBounds(130, y, 120, 24);
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
				contentPane.add(lblNewLabel_1);
				lblNewLabel_3 = new JLabel(String.format("%.2g%n", normalised_scores[i]));
				lblNewLabel_3.setBounds(637, y, 120, 24);
				lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
				contentPane.add(lblNewLabel_3);
				y = y + 45;
			}
	}
	
	/**
	 * This method is called in the Normalised constructor, this method calculates the normalized scores of each student 
	 * on valid input and checks for cases if scores entered are valid and throws exceptions AllZerosException/
	 * NotInRangeException otherwise.
	 * 
	 * Parameters: This method has a 2D array of scores of all the students for all the categories in each row.
	 * 
	 * Returns: One dimensional array of float type having Normalized Scores of all the students 
	 * rounded off to two decimal places.
	 */
	public static float[] calculate_normalised_scores(String score_grid[][]) throws AllZerosException, NotInRangeException
	{
		int ns_row= score_grid.length;  
		int ns_col= score_grid[0].length;  
		float normalised_scores[] = new float[ns_row];
		int sum=0;
		int zeros_count = 0;
		int int_grid[][] = new int[ns_row][3];
		try {
				for(int i=0; i<ns_row;i++)
				{
					for(int j=0;j<3;j++)
					{
						if(score_grid[i][j] == null)
						{
							throw new NullPointerException();
						}
						sum = sum + Integer.parseInt(score_grid[i][j]);	
				        int_grid[i][j] = Integer.parseInt(score_grid[i][j]);
				        
					}
				}
				if(sum == 0)
				{
					throw new AllZerosException("All zeros entered : illegal");
				}
				
		}
		finally{}

		DecimalFormat twoDForm = new DecimalFormat("#.##");
		for (int i = 0; i < ns_row; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				normalised_scores[i] += int_grid[i][j];
			}
			normalised_scores[i] = normalised_scores[i] / sum;
			normalised_scores[i] = Float.valueOf(twoDForm.format(normalised_scores[i]));
		}
		return normalised_scores;
	}
}