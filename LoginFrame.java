import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private PlayerService playerService;

    public LoginFrame() {
        playerService = new PlayerService();

        setTitle("Tic-Tac-Toe - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));

        JLabel lblTitle = new JLabel("TIC-TAC-TOE", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(new Color(0, 200, 255));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(new Color(30, 30, 30));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.WHITE);
        txtUsername = new JTextField();

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);
        txtPassword = new JPasswordField();

        btnLogin = new JButton("LOGIN");
        btnLogin.setBackground(new Color(0, 200, 255));
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));

        formPanel.add(lblUsername);
        formPanel.add(txtUsername);
        formPanel.add(lblPassword);
        formPanel.add(txtPassword);
        formPanel.add(new JLabel());
        formPanel.add(btnLogin);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JLabel lblInfo = new JLabel("Enter your credentials to play", SwingConstants.CENTER);
        lblInfo.setForeground(new Color(150, 150, 150));
        lblInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        mainPanel.add(lblInfo, BorderLayout.SOUTH);

        add(mainPanel);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username dan password tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Player player = playerService.login(username, password);

                if (player != null) {
                    JOptionPane.showMessageDialog(null, "Login berhasil! Selamat datang, " + player.getUsername() + "!");
                    MainMenuFrame menuFrame = new MainMenuFrame(player);
                    menuFrame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Username atau password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
