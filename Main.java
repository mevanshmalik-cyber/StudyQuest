
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

    private static DefaultListModel<String> taskModel =
            new DefaultListModel<>();

    private static JList<String> taskList = new JList<>(taskModel);
    private static int xp = 0;

    private static JLabel xpLabel = new JLabel("⭐ XP: 0");
    private static JLabel levelLabel = new JLabel("🏆 Level: 1");
    private static final String SAVE_FILE =
        System.getProperty("user.home") + java.io.File.separator
        + "StudyQuestData.txt";

private static void saveData() {
    try (java.io.PrintWriter writer =
            new java.io.PrintWriter(new java.io.FileWriter(SAVE_FILE))) {

        writer.println(xp);

        for (int i = 0; i < taskModel.size(); i++) {
            writer.println(taskModel.getElementAt(i));
        }

    } catch (java.io.IOException e) {
        System.out.println("Data save nahi hua: " + e.getMessage());
    }
}

private static void loadData() {
    java.io.File file = new java.io.File(SAVE_FILE);

    if (!file.exists()) {
        return;
    }

    try (java.io.BufferedReader reader =
            new java.io.BufferedReader(new java.io.FileReader(file))) {

        String line = reader.readLine();

        if (line != null) {
            xp = Math.max(0, Integer.parseInt(line));
        }

        taskModel.clear();

        while ((line = reader.readLine()) != null) {
            taskModel.addElement(line);
        }

    } catch (java.io.IOException | NumberFormatException e) {
        System.out.println("Saved data load nahi hua: " + e.getMessage());
    }

    xpLabel.setText("⭐ XP: " + xp);
    levelLabel.setText("🏆 Level: " + (xp / 50 + 1));
}

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            loadData();
            JFrame frame = new JFrame("StudyQuest - Smart Study Planner");
            frame.setSize(600, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout(10, 10));

            JPanel header = new JPanel(new GridLayout(3, 1, 5, 5));
            header.setBackground(new Color(40, 60, 100));
            header.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

            JLabel title = new JLabel("StudyQuest 🎯", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 28));
            title.setForeground(Color.WHITE);

            JLabel subtitle = new JLabel(
                    "Plan your study. Complete tasks. Earn XP!",
                    SwingConstants.CENTER);
            subtitle.setForeground(Color.WHITE);

            JPanel stats = new JPanel();
            stats.setOpaque(false);
            xpLabel.setForeground(Color.WHITE);
            levelLabel.setForeground(Color.WHITE);
            xpLabel.setFont(new Font("Arial", Font.BOLD, 16));
            levelLabel.setFont(new Font("Arial", Font.BOLD, 16));
            stats.add(xpLabel);
            stats.add(Box.createHorizontalStrut(30));
            stats.add(levelLabel);

            header.add(title);
            header.add(subtitle);
            header.add(stats);

            JTextField taskField = new JTextField();
            taskField.setFont(new Font("Arial", Font.PLAIN, 16));

            JButton addButton = new JButton("Add Task");
            JButton completeButton = new JButton("Complete Task");
            JButton deleteButton = new JButton("Delete Task");

            JPanel inputPanel = new JPanel(new BorderLayout(8, 8));
            inputPanel.add(taskField, BorderLayout.CENTER);
            inputPanel.add(addButton, BorderLayout.EAST);
            inputPanel.setBorder(
                    BorderFactory.createEmptyBorder(10, 10, 5, 10));

            taskList.setFont(new Font("Arial", Font.PLAIN, 16));
            taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JScrollPane scrollPane = new JScrollPane(taskList);
            scrollPane.setBorder(BorderFactory.createTitledBorder("My Study Tasks"));

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(completeButton);
            buttonPanel.add(deleteButton);

            JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
            centerPanel.add(inputPanel, BorderLayout.NORTH);
            centerPanel.add(scrollPane, BorderLayout.CENTER);
            centerPanel.add(buttonPanel, BorderLayout.SOUTH);
            centerPanel.setBorder(
                    BorderFactory.createEmptyBorder(0, 10, 10, 10));

            addButton.addActionListener(e -> {
                String task = taskField.getText().trim();

                if (!task.isEmpty()) {
                    taskModel.addElement(task);
                    taskField.setText("");
                    saveData();
                } else {
                    JOptionPane.showMessageDialog(
                            frame, "Please enter a task first!");
                }
            });

            taskField.addActionListener(e -> addButton.doClick());

            completeButton.addActionListener(e -> {
                int index = taskList.getSelectedIndex();

                if (index != -1) {
                    String task = taskModel.getElementAt(index);
                    


                    taskModel.remove(index);

                    xp += 10;
                    xpLabel.setText("⭐ XP: " + xp);
                    levelLabel.setText("🏆 Level: " + (xp / 50 + 1));
                    saveData();

                    JOptionPane.showMessageDialog(
                            frame,
                            "Great job! You completed:\n" + task
                            + "\n\nYou earned 10 XP!");
                } else {
                    JOptionPane.showMessageDialog(
                            frame, "Please select a task to complete.");
                }
            });

           
deleteButton.addActionListener(e -> {
    int index = taskList.getSelectedIndex();

    if (index != -1) {

        taskModel.remove(index);
        saveData();

        JOptionPane.showMessageDialog(
                frame, "Task deleted successfully!");

    } else {
        JOptionPane.showMessageDialog(
                frame, "Please select a task to delete.");
    }
});
            frame.add(header, BorderLayout.NORTH);
            frame.add(centerPanel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
