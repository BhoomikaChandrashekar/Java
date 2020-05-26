package RepresentativeApplication;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class Representative implements ActionListener{
	JFrame j1;
	JLabel no,name,state,comm,rate;
	JTextField tno,tname,tstate,tcomm,trate;
	JButton submit;
	Representative()
	{
		j1=new JFrame("Representative Form");
		
		no=new JLabel("RepNo.");
		name=new JLabel("RepName");
		state=new JLabel("State");
		comm=new JLabel("Commission");
		rate=new JLabel("rate");
		
		tno=new JTextField(40);
		tname=new JTextField(40);
		tstate=new JTextField(40);
		tcomm=new JTextField(40);
		trate=new JTextField(40);
		
		submit=new JButton("Submit");
		
		j1.add(no); j1.add(tno);
		j1.add(name);j1.add(tname);
		j1.add(state);j1.add(tstate);
		j1.add(comm);j1.add(tcomm);
		j1.add(rate);j1.add(trate);
		j1.add(submit);
		submit.addActionListener(this);
		
		j1.setSize(500,500);
		j1.setVisible(true);
		j1.setLayout(new GridLayout(6,8));
		j1.setResizable(false);
		j1.setLocationRelativeTo(null);
		j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String repno,name,state,comm,rate;
		if(evt.getSource()==submit)
		{
			repno=tno.getText();
			name=tname.getText();
			state=tstate.getText();
			comm=tcomm.getText();
			rate=trate.getText();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
			}
			try {
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Representative","root","");
				
				PreparedStatement ps=con.prepareStatement("select * from repr where repno=?");
				ps.setString(1, repno);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					JOptionPane.showMessageDialog(null, "Details with this RepNo exist","Message",JOptionPane.INFORMATION_MESSAGE);
					j1.setVisible(false);
				}
				else 
				{
					ps = con.prepareStatement("insert into repr(repno,name,state,comm,rate) values (?,?,?,?,?)");
					ps.setString(1,repno);
					ps.setString(2,name);
					ps.setString(3,state);
					ps.setString(4,comm);
					ps.setString(5,rate);
					int y=ps.executeUpdate();
					if(y>0)
					{
						JOptionPane.showMessageDialog(null, "Details Entered","message",JOptionPane.INFORMATION_MESSAGE);
						j1.setVisible(false);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
			}
			
		}
		
	}
}
