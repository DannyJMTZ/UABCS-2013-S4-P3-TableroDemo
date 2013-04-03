import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class TableroDemo extends Ventana implements MouseListener, Runnable{
	
	private ImageIcon img;
	private DButton btn;
	private JLabel lblReloj;
	private Thread crono;
	private int minutos = 0, segundos = 0, horas = 0;
	private boolean stopTime = false;
	
	public TableroDemo(int w, int h, String t) {
		
		super(w, h, t);
		this.setLayout(null);
		
		btn = new DButton();
		img = new ImageIcon("img/verde.png");
		btn.setBounds(100, 100, 100, 100);
		btn.setIcon(img);
		btn.addMouseListener(this);
		
		lblReloj = new JLabel("Hola");
		lblReloj.setBounds(10, 10, 100, 30);
		
		crono = new Thread(this);
		crono.start();
		
		this.add(lblReloj);
		this.add(btn);
	}

	public void mouseClicked(MouseEvent arg0) {
		
		img = new ImageIcon("img/rojo.png");
		btn.setIcon(img);
		
		if(stopTime){
			crono.resume();
			stopTime = false;
		}
		else{
			crono.suspend();
			stopTime = true;
		}	
	}

	public void mouseEntered(MouseEvent arg0) { }
	public void mouseExited(MouseEvent arg0) { }
	public void mousePressed(MouseEvent arg0) { }
	public void mouseReleased(MouseEvent arg0) { }
	
	@Override
	public void run() {
		
		try{
			for(;;){
				if(segundos == 59){segundos = 0; minutos++;}
				if(minutos == 59){minutos = 0; horas++;}
				segundos++;
				
				this.lblReloj.setText(String.format("%02d:%02d:%02d", horas, minutos, segundos));
				crono.sleep(10);
			}
		}
		catch(InterruptedException e){

		}
	}
	
	public static void main(String[] args) {
		
		TableroDemo tD = new TableroDemo(500, 500, "Tablero Demo");
		tD.setVisible(true);
		
	}

}
