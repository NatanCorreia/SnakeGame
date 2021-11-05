package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserTextPanel extends JPanel {

	private JTextField name, email, login;
	private JPasswordField password;
	private JLabel lbNome,lbEmail,lbLogin,lbSenha,titulo;
	private JButton submit;
	
	public UserTextPanel() {
		setLayout(null);
		titulo = new JLabel("Cadastro");
		titulo.setFont(new Font("Arial", Font.BOLD, 30));
		titulo.setBounds(220,10,150,30);
		add(titulo);
		lbNome = new JLabel("Nome: ");
		lbNome.setBounds(20, 60, 100,30);
		add(lbNome);
		name = new JTextField(10);
		name.setBounds(70, 68, 150,18);
		add(name);
		lbEmail = new JLabel("Email: ");
		lbEmail.setBounds(300,60,100,30);
		add(lbEmail);
		email = new JTextField();
		email.setBounds(370,68,150,18);
		add(email);
		lbLogin = new JLabel("Login: ");
		lbLogin.setBounds(20,90,100,30);
		add(lbLogin);
		login = new JTextField(10);
		login.setBounds(70,98, 150, 18);
		add(login);
		lbSenha = new JLabel("Senha: ");
		lbSenha.setBounds(300, 90, 100, 30);
		add(lbSenha);
		password = new JPasswordField();
		password.setBounds(370,98,150,18);
		add(password);
		submit = new JButton("Submit");
		submit.setBounds(200,500,180,25);
		add(submit);
		
	}

	public JPasswordField getPassword() {
		return password;
	}

	public JTextField getNome() {
		return name;
	}

	public JTextField getEmail() {
		return email;
	}

	public JTextField getLogin() {
		return login;
	}

	public JPasswordField getSenha() {
		return password;
	}

	public JButton getSubmit() {
		return submit;
	}
	
}
