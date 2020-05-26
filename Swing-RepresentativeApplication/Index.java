package RepresentativeApplication;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class Index implements ActionListener{
	JButton rep,cust;
	Index()
	{
		JFrame j=new JFrame();
		rep=new JButton("Representative");
		cust=new JButton("Customer");
		rep.addActionListener(this);
		cust.addActionListener(this);
		j.add(rep);
		j.add(cust);
		j.setSize(300,300);
		j.setVisible(true);
		j.setLayout(new GridLayout(2,1));
		j.setResizable(false);
		j.setLocationRelativeTo(null);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource()==rep)
		{
			Representative resp=new Representative();
		}
		else
		{
			Customer customer=new Customer();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Index ind=new Index();
	}

}
