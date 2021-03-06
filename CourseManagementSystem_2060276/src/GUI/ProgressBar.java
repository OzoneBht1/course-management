package GUI;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameUI;


@SuppressWarnings("serial")
public class ProgressBar extends JFrame {
	
	
	JProgressBar bar = new JProgressBar();
	JLabel welcome = new JLabel();
	Border border = BorderFactory.createLineBorder(Color.red,3);
	ImageIcon image = new ImageIcon("../CourseManagementSystem_2060276/pngwing.com.png");
	
	
	
	
	ProgressBar(){				
		welcome.setText("WELCOME");
		welcome.setIcon(image);
		welcome.setVerticalAlignment(JLabel.TOP);
		welcome.setBorder(border);
		welcome.setHorizontalAlignment(JLabel.CENTER);
		bar.setValue(0);
		bar.setBounds(0,360,500,50);
		bar.setStringPainted(true);
		bar.setFont(new Font("MV Boli", Font.BOLD, 25));
		bar.setForeground(new Color(0x8de4ff));
		bar.setBackground(new Color(0xFFA500));
		
		
		this.setUndecorated(true);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(0x008080));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(image.getImage());		
		this.setLayout(null);
		this.add(welcome);
		this.add(bar);
		this.setSize(500,450);		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		fill();
		new Choice();
		this.dispose();
		
		
		
	}
	
	public void fill() {
		int counter = 0;
		while(counter <= 100) {
			bar.setValue(counter);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			counter+=1;
		}

	}
	
	
	
	

}
