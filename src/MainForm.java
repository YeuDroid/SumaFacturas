import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainForm extends JFrame {

	private JPanel contentPane;
	private String textAtWorker="";
	private JTextArea totalBox;
	JTextArea textBoxMain ;
	private YClipboardManager clipMgr = new  YClipboardManager();
	private ArrayList<String> lista = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textBoxMain = new JTextArea();
		contentPane.add(textBoxMain, BorderLayout.CENTER);
	
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_1 = new JButton("To Clip");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//boton to clipboard
				clipMgr.SetText(textBoxMain.getText());
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnGettext = new JButton("GetText");
		btnGettext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// boton obtener texto
				textBoxMain.setText(clipMgr.GetText());
			}
		});
		panel.add(btnGettext);
		
		totalBox = new JTextArea();
		totalBox.setText("00.000");
		panel.add(totalBox);
		totalBox.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Limpiar Reg.");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanRegistros();
			}
		});
		
		
		panel_1.add(btnNewButton);
		
		JButton button = new JButton("Sumar Facturas");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Boolean cortar = false ;
				String tempStr="";
				//vasiar texto 
				textAtWorker = textBoxMain.getText();
				//rrecorer cadenas
				
			  // iniio de recorrido por la cadena
				for (int x=0;x<textAtWorker.length();x++)
				{
					// si ayamos el $ activamos el cut boleand
				 if (textAtWorker.charAt(x) == '$')
				 {
				   //System.out.println("SE ENCONTRO el $ ACTIVANDO CUT: " + String.valueOf(x));
				   cortar = true;
				   continue;
				 }
				 
				  // si ayamos el caracter final de los numeros desactivamos el cut
				 if(textAtWorker.charAt(x) == '	' && cortar == true) 
				 {
					 //System.out.println("SE ENCONTRO FINA DL CUT: " + String.valueOf(x));
					// ay ke agrgar el nuevo numero
					 tempStr = tempStr.trim();
					 if(!tempStr.isEmpty() && tempStr.contains(".") && tempStr.length() > 3 && isNumericSentence(tempStr))
                      {
                          
						 lista.add(tempStr);
						 System.out.println("SE AGREGO: " +  tempStr);
						 //tempStr="";
					 };
					 tempStr="";
					 continue;
				 }
				   
				 // si esta activaod el cut comerselo
				 if(cortar && isNumero(textAtWorker.charAt(x)))
				 {
					// System.out.println("SE COMIO: " + String.valueOf(textAtWorker.charAt(x)));
					 tempStr += textAtWorker.charAt(x) ;
				 }
				 
				 
				}
				
				//totales
				double total = sumarLista();
				totalBox.setText(String.valueOf(total));
			}

			private double sumarLista() 
			{
				double total=0.0;
				for(String numeroString : lista)
				{
					double numero = Double.valueOf(numeroString);
					total += numero;
					System.out.println(total);
				}
				return total;
				
			}
				
		});
		panel_1.add(button);
	}
	private Boolean isNumero(char caracter)
	{  
		if(caracter == '.')return true;
		return Character.isDigit(caracter);
	}
	public  boolean isNumericSentence(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

	private void cleanRegistros()
	{
		this.lista.clear();
	    this.textBoxMain.setText("");
	    this.textAtWorker = "";
	}

}