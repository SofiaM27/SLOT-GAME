
package slotgame.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameFrame extends JFrame {

    private static final Color DARK_PURPLE = new Color(38, 25, 58);
    private static final Color LIGHT_PURPLE = new Color(75, 48, 105);
    private static final Color GOLD = new Color(232, 185, 78);
    private static final Color CREAM = new Color(255, 246, 229);

    private final SlotPanel slotPanel;
    private final ControlPanel controlPanel;
    private final InfoPanel infoPanel;

    public GameFrame() {
        setTitle("SLOT GAME");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setMinimumSize(new Dimension(700, 620));
        setLocationRelativeTo(null);

        // Головна панель із градієнтним фоном
        GradientPanel background = new GradientPanel();
        background.setLayout(new BorderLayout(15, 15));
        background.setBorder(new EmptyBorder(25, 30, 25, 30));

        // Заголовок
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("SLOT GAME");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(GOLD);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("TRY YOUR LUCK!");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(CREAM);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(title);
        header.add(Box.createVerticalStrut(5));
        header.add(subtitle);

        // Інформаційна панель
        infoPanel = new InfoPanel();

        // Панель із трьома барабанами
        slotPanel = new SlotPanel();

        // Панель керування зі ставкою та кнопкою SPIN
        controlPanel = new ControlPanel();

        // Центральна частина вікна
        JPanel centerPanel = new JPanel(new BorderLayout(10, 15));
        centerPanel.setOpaque(false);

        JLabel sectionTitle = new JLabel(
                "GOOD LUCK!",
                SwingConstants.CENTER
        );
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 20));
        sectionTitle.setForeground(CREAM);

        centerPanel.add(sectionTitle, BorderLayout.NORTH);
        centerPanel.add(slotPanel, BorderLayout.CENTER);

        // Нижня частина вікна
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setOpaque(false);

        bottomPanel.add(infoPanel, BorderLayout.CENTER);
        bottomPanel.add(controlPanel, BorderLayout.SOUTH);

        // Додаємо всі компоненти до головного вікна
        background.add(header, BorderLayout.NORTH);
        background.add(centerPanel, BorderLayout.CENTER);
        background.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(background);
    }

    // Отримання панелі барабанів
    public SlotPanel getSlotPanel() {
        return slotPanel;
    }

    // Отримання панелі керування
    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    // Отримання інформаційної панелі
    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    // Оновлення балансу
    public void updateBalance(double balance) {
        infoPanel.updateBalance(balance);
    }

    // Оновлення повідомлення про результат гри
    public void setMessage(String message) {
        infoPanel.updateResult(message);
    }

    // Панель із градієнтним фоном
    private static class GradientPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();

            GradientPaint gradient = new GradientPaint(
                    0, 0, DARK_PURPLE,
                    getWidth(), getHeight(), LIGHT_PURPLE
            );

            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.dispose();
        }
    }
}
