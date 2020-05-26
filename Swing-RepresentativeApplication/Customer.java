package RepresentativeApplication;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Customer implements ActionListener{
	JFrame j2;
	JLabel custno,name,state,credit,repno;
	JTextField tcustno,tname,tstate,tcredit,trepno;
	JButton submit;
	Customer() {
		j2=new JFrame("Customer Form");
		
		custno=new JLabel("CustNo.");
		name=new JLabel("CustName");
		state=new JLabel("State");
		credit=new JLabel("Credit Limit");
		repno=new JLabel("RepNo.");
		
		tcustno=new JTextField(40);
		tname=new JTextField(40);
		tstate=new JTextField(40);
		tcredit=new JTextField(40);
		trepno=new JTextField(40);
		
		submit=new JButton("Submit");
		
		j2.add(custno); j2.add(tcustno);
		j2.add(name);j2.add(tname);
		j2.add(state);j2.add(tstate);
		j2.add(credit);j2.add(tcredit);
		j2.add(repno);j2.add(trepno);
		j2.add(submit);
		submit.addActionListener(this);
		
		j2.setSize(500,500);
		j2.setVisible(true);
		j2.setLayout(new GridLayout(6,8));
		j2.setResizable(false);
		j2.setLocationRelativeTo(null);
		j2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String cuno,cname,cstate,credit_lim,repnum;
		Connection con;
		PreparedStatement ps;
		if(evt.getSource()==submit)
		{
			cuno=tcustno.getText();
			cname=tname.getText();
			cstate=tstate.getText();
			credit_lim=tcredit.getText();
			repnum=trepno.getText();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Representative","root","");
				ps=con.prepareStatement("select * from cust where custno=?");
				ps.setString(1,cuno);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					System.out.println(rs.getString(1));
					JOptionPane.showMessageDialog(null, "Customer details with the given CustNo exist","message",JOptionPane.INFORMATION_MESSAGE);
				}
				
				else
				{
					ps=con.prepareStatement("select * from repr where repno=?");
					ps.setString(1, repnum);
					rs=ps.executeQuery();
					if(rs.next())
					{
						ps=con.prepareStatement("insert into cust values(?,?,?,?,?)");
						ps.setString(1, cuno);
						ps.setString(2, cname);
						ps.setString(3,cstate);
						ps.setString(4, credit_lim);
						ps.setString(5,repnum);
						JOptionPane.showMessageDialog(null, "Details Entered Successfully","message",JOptionPane.INFORMATION_MESSAGE);
						if(Integer.parseInt(credit_lim)>15000)
						{
							Display ob=new Display(rs);
						}
						j2.setVisible(false);
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Representative with such RepNo. doesnt exist","message",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
