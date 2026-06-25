import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    private Player currentPlayer;
    private JButton btnStartGame;
    private JButton btnStatistics;
    private JButton btnTopScorers;
    private JButton btnExit;

    public MainMenuFrame(Player player) {
        this.currentPlayer = player;

        setTitle("Tic-Tac-Toe - Main Menu");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));

        JLabel lblTitle = new JLabel("MAIN MENU", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 200, 255));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        JLabel lblWelcome = new JLabel("Welcome, " + currentPlayer.getUsername() + "!", SwingConstants.CENTER);
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        buttonPanel.add(lblWelcome);

        btnStartGame = new JButton("🎮 Start Game");
        btnStatistics = new JButton("📊 My Statistics");
        btnTopScorers = new JButton("🏆 Top 5 Scorers");
        btnExit = new JButton("❌ Exit");

        styleButton(btnStartGame, new Color(0, 200, 255));
        styleButton(btnStatistics, new Color(0, 200, 100));
        styleButton(btnTopScorers, new Color(255, 200, 0));
        styleButton(btnExit, new Color(255, 80, 80));

        buttonPanel.add(btnStartGame);
        buttonPanel.add(btnStatistics);
        buttonPanel.add(btnTopScorers);
        buttonPanel.add(btnExit);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        btnStartGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(currentPlayer);
            gameFrame.setVisible(true);
            dispose();
        });

        btnStatistics.addActionListener(e -> {
            StatisticsFrame statisticsFrame = new StatisticsFrame(currentPlayer);
            statisticsFrame.setVisible(true);
        });

        btnTopScorers.addActionListener(e -> {
            TopScoreFrame topFrame = new TopScoreFrame();
            topFrame.setVisible(true);
        });

        btnExit.addActionListener(e -> {
            System.exit(0);
        });
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
