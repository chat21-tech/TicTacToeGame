import javax.swing.*;
import java.awt.*;

public class StatisticsFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;

    public StatisticsFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();

        setTitle("Tic-Tac-Toe - My Statistics");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));

        JLabel lblTitle = new JLabel("MY STATISTICS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 200, 255));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        Player updatedPlayer = playerService.getPlayerById(currentPlayer.getId());

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(6, 1, 10, 10));
        statsPanel.setBackground(new Color(30, 30, 30));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        addStatLabel(statsPanel, "👤 Username", updatedPlayer.getUsername(), Color.WHITE);
        addStatLabel(statsPanel, "🏆 Wins", String.valueOf(updatedPlayer.getWins()), new Color(0, 200, 100));
        addStatLabel(statsPanel, "💀 Losses", String.valueOf(updatedPlayer.getLosses()), new Color(255, 80, 80));
        addStatLabel(statsPanel, "🤝 Draws", String.valueOf(updatedPlayer.getDraws()), new Color(255, 200, 0));
        addStatLabel(statsPanel, "⭐ Score", String.valueOf(updatedPlayer.getScore()), new Color(0, 200, 255));

        mainPanel.add(statsPanel, BorderLayout.CENTER);

        JButton btnClose = new JButton("Tutup");
        btnClose.setBackground(new Color(255, 80, 80));
        btnClose.setForeground(Color.BLACK);
        btnClose.setFont(new Font("Arial", Font.BOLD, 14));
        btnClose.setFocusPainted(false);
        btnClose.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        btnClose.addActionListener(e -> dispose());

        mainPanel.add(btnClose, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void addStatLabel(JPanel panel, String label, String value, Color valueColor) {
        JPanel rowPanel = new JPanel(new BorderLayout());
        rowPanel.setBackground(new Color(50, 50, 50));
        rowPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel lbl = new JLabel(label);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel val = new JLabel(value, SwingConstants.RIGHT);
        val.setForeground(valueColor);
        val.setFont(new Font("Arial", Font.BOLD, 14));

        rowPanel.add(lbl, BorderLayout.WEST);
        rowPanel.add(val, BorderLayout.EAST);
        panel.add(rowPanel);
    }
}