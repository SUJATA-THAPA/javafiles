import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class ProjectB {

	private JFrame frame;
	private JTextField txtTxtbname;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectB window = new ProjectB();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProjectB() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	
	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysq.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/samjana", "root","Sujata@123thapa");
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex) 
	        {
	        	   ex.printStackTrace();
	        }

	    }	
	
	 
	 public void table_load()
	    {
	    	try 
	    	{
	    		int q;
		    pst = con.prepareStatement("select * from emp");
		    rs = pst.executeQuery();
		   ResultSetMetaData rss=(ResultSetMetaData) rs.getMetaData();
		   q=rss.getColumnCount();
		   DefaultTableModel df=(DefaultTableModel)table.getModel();
		   df.setRowCount(0);
		   while(rs.next()) {
			   Vector v2=new Vector();
			   for(int a=1;a<=q;a++) {
				   v2.add(rs.getString("name"));
				   v2.add(rs.getString("edition"));
				   v2.add(rs.getString("price"));
			   }
			   df.addRow(v2);
		   }
		   
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  }
	    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 946, 502);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(327, 11, 228, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 68, 428, 384);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Price");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(30, 154, 118, 31);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(30, 36, 118, 31);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Edition");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(30, 88, 118, 31);
		panel.add(lblNewLabel_1_2);
		
		txtTxtbname = new JTextField();
		txtTxtbname.setBounds(147, 156, 215, 31);
		panel.add(txtTxtbname);
		txtTxtbname.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(147, 38, 215, 31);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(147, 95, 215, 31);
		panel.add(textField_2);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(291, 217, 89, 56);
		panel.add(btnNewButton);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(UIManager.getColor("Button.background"));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
						
					String bname,edition,price;
					bname = txtTxtbname.getText();
					edition = textField_1.getText();
					price = textField_2.getText();
								
					 try {
						pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
						pst.setString(1, bname);
						pst.setString(2, edition);
						pst.setString(3, price);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
						table_load();
							           
						txtTxtbname.setText("");
						textField_1.setText("");
						textField_2.setText("");
						txtTxtbname.requestFocus();
					   }

					catch (SQLException e1) 
				        {
										
						e1.printStackTrace();
				    	}
				
				
				
				
			}
		});
		btnSave.setBounds(30, 217, 89, 56);
		panel.add(btnSave);
		
		JButton btnEdit = new JButton("Exit");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(158, 217, 89, 56);
		panel.add(btnEdit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 292, 408, 81);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Book Id");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(10, 11, 118, 31);
		panel_1.add(lblNewLabel_1_2_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(90, 13, 215, 29);
		panel_1.add(textField);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.setBounds(499, 379, 144, 56);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(675, 379, 150, 56);
		frame.getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(466, 80, 315, 278);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Edition", "Price"
			}
		));
		scrollPane.setViewportView(table);
	}
}
