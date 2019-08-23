package net.devel.pi;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LeibnizFormulaForPi extends JFrame implements ActionListener{
	
	private JLabel label1, label2;
	private JButton button1;
	private JTextField textField1;
	
	public static void usage()
	{
		System.out.println("Usage: java MainClass [console|gui]");
		System.out.println("By default you use the console mode");
		System.out.println("If you want graphic mode, use gui parameter.");
		System.out.println("");
	}
		
    public LeibnizFormulaForPi() {	
		setLayout(null);
		setTitle("Leibniz formula for pi");
		
		label1=new JLabel("Enter the max number of iterations");
		label1.setBounds(10,20,450,30);
		add(label1);
		
		textField1=new JTextField();
		textField1.setBounds(90,60,100,30);
        textField1.setHorizontalAlignment(JTextField.RIGHT);
        add(textField1);
        
        label2=new JLabel("");
        label2.setBounds(10,100,450,30);
        add(label2);
        
        button1=new JButton("Calculate");
        button1.setBounds(260,60,100,30);
        add(button1);
        button1.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==button1)
		{
			String cadena="";
			try {
				String strRep=textField1.getText();
				int rep=Integer.parseInt(strRep);
				double rdo=leibnizPi(rep);
				cadena="Approximate value for Pi "+rdo;
				label2.setForeground(Color.BLACK);
			}
			catch (NumberFormatException ex) {
				cadena="Problem with this value";
				label2.setForeground(Color.RED);
			}
			finally {
				label2.setText(cadena);
			}
		}
	}
	
	public static double leibnizPi(long max)
	{
		double sum=0;
		for (long i=0;i<=max;i++)
		{
			if (i%2==0)
				sum=sum+(1.0/(2.0*i+1.0));
			else
				sum=sum-(1.0/(2.0*i+1.0));
		}
		return 4*sum;
	}

	public static void main(String[] args) {
		String runMode="console";
		
		if (args.length>0 && (args[0].equals("console") || args[0].equals("gui")))
			runMode=args[0];
		
		if (runMode.equals("console"))
		{
			usage();
			Scanner scanner=new Scanner(System.in);
			System.out.print("Enter the max number of iterations: ");
			long max=scanner.nextLong();
			scanner.close();
			System.out.println("Approximate value for Pi: "+leibnizPi(max));
		}
		else if (runMode.equals("gui"))
		{
			LeibnizFormulaForPi frame=new LeibnizFormulaForPi();
			frame.setBounds(0,0,500,200);
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame.setVisible(true);		
		}
	}	
}