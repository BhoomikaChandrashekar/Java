package RepresentativeApplication;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Display implements ActionListener{
	JButton exit;
	JFrame j1;
	ResultSet rs;
	Display(ResultSet rs)
	{
		this.rs=rs;
		 j1=new JFrame("Representative Form");
		
		JLabel no=new JLabel("RepNo.");
		JLabel name=new JLabel("RepName");
		JLabel state=new JLabel("State");
		JLabel comm=new JLabel("Commission");
		JLabel rate=new JLabel("rate");
		
		JTextField tno=new JTextField(40);
		JTextField tname=new JTextField(40);
		JTextField tstate=new JTextField(40);
		JTextField tcomm=new JTextField(40);
		JTextField trate=new JTextField(40);
		exit=new JButton("Exit");
		
		j1.add(no); j1.add(tno);
		j1.add(name);j1.add(tname);
		j1.add(state);j1.add(tstate);
		j1.add(comm);j1.add(tcomm);
		j1.add(rate);j1.add(trate);
		j1.add(exit);
		
		tno.setEditable(false);
		tname.setEditable(false);
		tstate.setEditable(false);
		tcomm.setEditable(false);
		trate.setEditable(false);
		
		exit.addActionListener(this);
		
		try {
			tno.setText(rs.getString(1));
			tname.setText(rs.getString(2));
			tstate.setText(rs.getString(3));
			tcomm.setText(rs.getString(4));
			trate.setText(rs.getString(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if(evt.getSource()==exit)
		{
			j1.setVisible(false);
		}
		
	}
}
