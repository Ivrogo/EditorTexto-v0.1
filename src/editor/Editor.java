package editor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;


public class Editor extends JFrame implements ActionListener {
	
	//Text component
	JTextArea text;
	
	//Frame component
	JFrame frame;
	
	//Constructor
	Editor() {
		
		//Creamos el frame
		frame = new JFrame("Editor de texto");
		
		try {
			//Personalizamos como se vera
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			//Personalizamos el tema
			MetalLookAndFeel.setCurrentTheme(new OceanTheme());
		} catch (Exception e) {
		}
		
		//Text component
		text = new JTextArea();
		
		//Creamos la barra de menu
		JMenuBar mb = new JMenuBar(); 
		
		//Creamos los submenus
		JMenu menu1 = new JMenu("Archivo");
				
	    //Creamos los items
	    JMenuItem menuItem1 = new JMenuItem("Nuevo");
		JMenuItem menuItem2 = new JMenuItem("Abrir");
		JMenuItem menuItem3 = new JMenuItem("Guardar");
		JMenuItem menuItem9 = new JMenuItem("Imprimir");
		
		//Add action listener
		menuItem1.addActionListener(this);
		menuItem2.addActionListener(this);
		menuItem3.addActionListener(this);
		menuItem9.addActionListener(this);
		
		//Añadimos todo al frame
		menu1.add(menuItem1);
		menu1.add(menuItem2);
		menu1.add(menuItem3);
		menu1.add(menuItem9);
		
		//Creamos el segundo submenu
		JMenu menu2 = new JMenu("Editar");
		
		//Creamos los items
		JMenuItem menuItem4 = new JMenuItem("Cortar");
		JMenuItem menuItem5 = new JMenuItem("Copiar");
		JMenuItem menuItem6 = new JMenuItem("Pegar");
		
		//Add action listener
		menuItem4.addActionListener(this);
		menuItem5.addActionListener(this);
		menuItem6.addActionListener(this);
		
		//añadimos al frame
		menu2.add(menuItem4);
		menu2.add(menuItem5);
		menu2.add(menuItem6);
		
		//Creamos el tercer submenu
		JMenu menu3 = new JMenu("Cerrar");
		
		//action listener tercer menu
		menu3.addActionListener(this);
		
		//Añadimos todo al menu principal
		mb.add(menu1);
		mb.add(menu2);
		mb.add(menu3);
		
		frame.setJMenuBar(mb);
		frame.add(text);
		frame.setSize(500, 500);
		frame.show();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		
		if (s.equals("Cortar")) {
			text.cut();
		}
		else if (s.equals("Copiar")) {
			text.copy();
		}
		else if (s.equals("Pegar")) {
			text.paste();
		}
		else if (s.equals("Guardar")) {
			//Creamos una clase file chooser
			JFileChooser j = new JFileChooser("f:");
			
			//Invocamos el showSaveDialog
			int r = j.showSaveDialog(null);
			
			if (r == JFileChooser.APPROVE_OPTION) {
				//Especificamos la ruta del directorio seleccionado
				File f = new File(j.getSelectedFile().getAbsolutePath());
				
				try {
					//Creamos el file writer
					FileWriter fw = new FileWriter(f, false);
					
					//Creamos el buffered writer para escribir
					BufferedWriter w = new BufferedWriter(fw);
					
					//Escribimos
					w.write(text.getText());
					
					w.flush();
					w.close();
					
				} catch (Exception evt) {
					JOptionPane.showMessageDialog(frame, evt.getMessage());
				}
			}
			//Si el usuario ha cancelado la operacion
			else {
				JOptionPane.showMessageDialog(frame, "El usuario ha cancelado la operacion");
			}
			
				
			}
		else if (s.equals("Imprimir")) {
			try {
				//Imprimimos el archivo
				text.print();
				
			} catch (Exception evt) {
				JOptionPane.showMessageDialog(frame, evt.getMessage());
			}
		}
		else if (s.equals("Abrir")) {
			//Creamos un objeto de la clase JFileChooser
			JFileChooser j = new JFileChooser("f:");
			
			//Invocamos el showOpenDialog
			int r = j.showOpenDialog(null);
			
			//Si el usuario selecciona un archivo
			if (r == JFileChooser.APPROVE_OPTION) {
				//Especificamos el camino del directorio seleccionado
				File f = new File(j.getSelectedFile().getAbsolutePath());
				
				try {
					//Creamos un string
					String s1 = "", sl = "";
					
					//File reader
					FileReader fr  = new FileReader(f);
					
					//Buffered reader
					BufferedReader br = new BufferedReader(fr);
					
					//Inicializamos el string sl
					sl = br.readLine();
					
					//Cojemos el input del archivo
					while ((s1 = br.readLine()) != null) {
						sl = sl + "\n" + s1;					
						}
					
					//Set text
					text.setText(sl);
				}
				catch (Exception evt) {
					JOptionPane.showMessageDialog(frame, evt.getMessage());
				}
			}
			else 
				JOptionPane.showMessageDialog(frame, "El usuario ha cancelado la operacion");
		}
		else if (s.equals("Nuevo")) {
			text.setText("");
		}
		else if (s.equals("Cerrar")) {
			frame.setVisible(false);
		}
	}
}
