import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ContactList extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField firstNameIn, lastNameIn, numberIn;
	private JRadioButton sortFirstName, sortLastName;
	private JTextArea textArea;
	
	private List<Contact> contacts;
	private static final int MAX_LENGTH = 100;

	/**
	 * starta applikationen.
	 */
	public static void main(String[] args) {
		new ContactList();
	}

	/**
	 * skapa fönstret
	 */
	public ContactList() {
		contacts = new ArrayList<>();
		
		setResizable(false);
		setTitle("Skolsak");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(400, 330));
		setMaximumSize(new Dimension(400, 330));
		setMinimumSize(new Dimension(400, 330));
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		init();
		setContentPane(contentPane);
		setVisible(true);
	}
	
	/**
	 * skapa och lägg till swing alla objekt i fönstret
	 */
	private void init(){
		firstNameIn = new JTextField();
		firstNameIn.setBounds(83, 11, 86, 20);
		contentPane.add(firstNameIn);
		
		lastNameIn = new JTextField();
		lastNameIn.setBounds(248, 11, 86, 20);
		contentPane.add(lastNameIn);
		
		numberIn = new JTextField();
		numberIn.setBounds(83, 42, 86, 20);
		contentPane.add(numberIn);

		JLabel firstNameLabel = new JLabel("efternamn");
		firstNameLabel.setBounds(10, 14, 61, 14);
		contentPane.add(firstNameLabel);
		
		JLabel lastNameLabel = new JLabel("förnamn");
		lastNameLabel.setBounds(179, 14, 59, 14);
		contentPane.add(lastNameLabel);
		
		JLabel numberLabel = new JLabel("number");
		numberLabel.setBounds(10, 45, 63, 14);
		contentPane.add(numberLabel);
		
		JButton addButton = new JButton("lägg till");
		addButton.setBounds(248, 42, 89, 23);
		addButton.addActionListener((ae) -> addContact());
		contentPane.add(addButton);
		
		textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(10, 73, 374, 183);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textArea.setEditable(false);
		textArea.setBounds(10, 73, 364, 183);
		contentPane.add(scroll);
		
		sortFirstName = new JRadioButton("sortera efter förnamn");
		sortFirstName.setSelected(true);
		sortFirstName.setBounds(20, 263, 179, 23);
		sortFirstName.addActionListener((ae) -> {
			sortLastName.setSelected(!sortLastName.isSelected());
			sort();
			updateText();
		});
		contentPane.add(sortFirstName);
		
		sortLastName = new JRadioButton("sortera efter efternamn");
		sortLastName.setBounds(206, 263, 168, 23);
		sortLastName.addActionListener((ae) -> {
			sortFirstName.setSelected(!sortFirstName.isSelected());
			sort();
			updateText();
		});
		contentPane.add(sortLastName);
	}
	
	/**
	 * lägg till en kontakt om rätt mängd information finns tillgängligt
	 */
	private void addContact(){
		if(firstNameIn.getText().isEmpty() || lastNameIn.getText().isEmpty() || numberIn.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "du måste fylla i alla fält innan du lägger till en ny kontakt!",
					"information saknas", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(contacts.size() >= MAX_LENGTH){
			JOptionPane.showMessageDialog(this, "tyvärr så kan du enbart lägga till "+MAX_LENGTH+" kontakter",
					"för många kontakter!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//lägg till den nya kontakten
		contacts.add(new Contact(firstNameIn.getText(), lastNameIn.getText(), numberIn.getText()));
		//sortera listan
		sort();
		//töm fälten
		firstNameIn.setText("");
		lastNameIn.setText("");
		numberIn.setText("");
		updateText();
	}
	
	/**
	 * sortera kontakterna alfabetiskt
	 */
	private void sort(){
		Collections.sort(contacts, new Comparator<Contact>() {
		    public int compare(Contact c1, Contact c2) {
		        return sortFirstName.isSelected() ? c1.getFirstName().toLowerCase().compareTo(c2.getFirstName().toLowerCase())
		        		: c1.getLastName().toLowerCase().compareTo(c2.getLastName().toLowerCase());
		    }
		});
	}
	
	/**
	 * skriv ut kontakterna
	 */
	private void updateText(){
		textArea.setText("");
		for(Contact c : contacts){
			if(sortFirstName.isSelected())
				textArea.append(c.getFirstName()+"\t"+c.getLastName()+"\t"+c.getNumber()+"\n");
			else
				textArea.append(c.getLastName()+"\t"+c.getFirstName()+"\t"+c.getNumber()+"\n");
		}
	}
}
