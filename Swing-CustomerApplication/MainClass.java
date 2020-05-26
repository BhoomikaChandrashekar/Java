import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class MainClass 
{
	static HashMap<Integer,Long> hm=new HashMap<>();
	static ArrayList<Item> ar=new ArrayList<>();
	String cid;
	
	JTextField tphn;
	MainClass()
	{
		JFrame frm1=new JFrame();
		JButton btn=new JButton("Check");
		JPanel jpan=new JPanel();
		String name=JOptionPane.showInputDialog(null,"Enter Name : ","Input name");
		if(name.equalsIgnoreCase("abc"))
		{
			String pwd=JOptionPane.showInputDialog(null,"Enter Password : ","Input Password");
			if(pwd.equals("abc"))
			{
				JLabel phno=new JLabel("Enter Phone Number : ");
				tphn=new JTextField(10);
				
				jpan.add(phno);
				jpan.add(tphn);
				jpan.add(btn);
				
				jpan.setVisible(true);
				
				frm1.add(jpan);
				frm1.setVisible(true);
				frm1.setSize(200,500);
				frm1.setLayout(new GridLayout(10,2));
				btn.addActionListener(new ActionListener() 
				{
				
					@Override
					public void actionPerformed(ActionEvent e)
					{
						int flag=0;
						Set<Map.Entry<Integer,Long>> set1=hm.entrySet();
						for(Map.Entry<Integer,Long>set2 : set1)
						{
							if(Long.parseLong(tphn.getText())==set2.getValue())
							{
								form(set2.getValue(),set2.getKey());
								flag=1;
								break;
							}
						}
						if(flag==0)
						{
							Long phno=Long.parseLong(tphn.getText());
							cid=JOptionPane.showInputDialog(null,"Enter Customer ID","input");
							form(phno,Integer.parseInt(cid));
							hm.put(Integer.parseInt(cid), phno);
						}
					}
				});
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Wrong User Name","Warning",JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
	public static void main(String[] args) 
	{
		hm.put(1,9087654321l);
		hm.put(2,9876543210l);
		MainClass mc=new MainClass();
		
		ar.add(new Item(1,"Bat",500.67));
		ar.add(new Item(2,"Racquet",950.67));
		ar.add(new Item(3,"Ball",200.20));
		
		//customer c=new customer();
		
	}
	
	public void form(Long phno,int cid)
	{
		JFrame jp=new JFrame("Customer Details");
		JLabel idlab=new JLabel("User ID : ");
		JLabel phlab=new JLabel("Phone Number : ");
		JLabel itemIdLab=new JLabel("Enter Item ID");
		JLabel qlab=new JLabel("Enter Quantity : ");
		JLabel itemname=new JLabel("Item Name : ");
		JLabel itemprice=new JLabel("Item Price : ");
		
		JTextField idf=new JTextField(20);
		JTextField phf=new JTextField(20);
		JTextField itemid=new JTextField(20);
		JTextField quant=new JTextField(20);
		JTextField itname=new JTextField(20);
		JTextField itprice=new JTextField(20);
		JButton submit=new JButton("Submit");
		
		jp.add(idlab);jp.add(idf);
		idf.setText(String.valueOf(cid));
		idf.setEditable(false);
		
		jp.add(phlab);jp.add(phf);
		phf.setText(String.valueOf(phno));
		phf.setEditable(false);
		
		jp.add(itemIdLab);jp.add(itemid);
		
		jp.add(qlab);jp.add(quant);
		
		jp.add(itemname);jp.add(itname);
		itname.setEditable(false);
		
		jp.add(itemprice);jp.add(itprice);
		itprice.setEditable(false);
		
		jp.add(submit);
		submit.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				// TODO Auto-generated method stub
				int item=Integer.parseInt(itemid.getText());
				int q=Integer.parseInt(quant.getText());
				for(Item i:ar)
				{
					if(i.ID==item) 
					{
						itname.setText(i.name);
						double total=q*i.price;
						itprice.setText(String.valueOf(total));
						String discounts[]= {"5","10","15"};
						int r=JOptionPane.showOptionDialog(null, "select the discount", "Discount", 0, JOptionPane.INFORMATION_MESSAGE, null, discounts, discounts[0]);
						double totalCost=total-(int)(0.01*Integer.parseInt(discounts[r])*total);
						
						JOptionPane.showMessageDialog(null,"Total Price Paid : "+totalCost,"TotalCost",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		jp.setSize(500,500);
		jp.setVisible(true);
		jp.setLayout(new GridLayout(7,2));
	}

}
