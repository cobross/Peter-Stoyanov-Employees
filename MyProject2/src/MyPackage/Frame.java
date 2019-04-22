package MyPackage;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame extends JFrame implements ActionListener{
    private String EmployeData;
    private List<PaiR> pairs = new ArrayList<PaiR>();
    private List<Employe> Employes = new ArrayList<Employe>(); 
    private JButton button=new JButton("Find pair");
    private JLabel label=new JLabel();
    public Frame() {
    	this.setName("MyProject");
    	JPanel panel = new JPanel();  
        panel.setLayout(new FlowLayout());
    	button.addActionListener(this);
    	panel.add(button);
    	panel.add(label);
    	this.add(panel);
    	this.setSize(486,96);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setVisible(true);
    }
    public void findPair(){
        int pos = 0;
        for (int i = 0; i < Employes.size(); i++){
            for (int j = 0; j < Employes.size(); j++){
                if(i!=j){
                    if (Employes.get(i).getPrjId() == Employes.get(j).getPrjId()){
                        pos=check(Employes.get(i).getPrjId(),Employes.get(j).getPrjId());
                        if (0==pos){
                            pairs.add(new PaiR(Employes.get(i).getEmpId(),
                            		Employes.get(j).getEmpId(),
                            Employes.get(i).FromDateToDate(Employes.get(j))));    
                        }
                        else{ 
                           pairs.get(pos).plus(Employes.get(i).FromDateToDate(Employes.get(j)));
                        }
                    }
                }
            }
        }
        pos = find();
        label.setText("first employe id - "+pairs.get(pos).getId1()
                + "\n second employe id - " + pairs.get(pos).getId2()+
                "\n timeSpent working together - " + pairs.get(pos).getTimeSpent());
    }
    public int check(int a, int b){
        for (int i = 0; i < pairs.size(); i++){
            if (pairs.get(i).comp(a, b)){
                return i;
            }
        }
        return 0;
    }
    public int find(){
        int pos = 0;
        long num=pairs.get(0).getTimeSpent();
        for (int i = 1; i < pairs.size(); i++){
            if (pairs.get(i).getTimeSpent() > num){
                num = pairs.get(i).getTimeSpent();
                pos = i;
            }
        }
        return pos;
    }
    	@Override
	public void actionPerformed(ActionEvent e) {
		File file=new File("info.txt");
		Scanner sc = null;
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		while(sc.hasNextLine()) {
			EmployeData=sc.nextLine();
			Employes.add(new Employe(EmployeData));
		}
		findPair();
	}
	public static void main(String[] args) {
		Frame A=new Frame();
	}
}
