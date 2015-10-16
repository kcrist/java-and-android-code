import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class gui extends JFrame {

	/**
	 * @param args
	 */
	private JTextField input;
	private JTextField results;
	
	public gui(){
		
		input=new JTextField("Input query and hit enter", 20);
		add(input);
	
	TextFieldHandler handler =new TextFieldHandler();
	input.addActionListener(handler);
		
	}
	private class TextFieldHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String text ="";
			e.getSource();
			
		}
		
	}
		
}
