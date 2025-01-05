package gui;

import handler.CSVHandler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class CSVUploader implements ActionListener {

    private String OPEN_FILE = "Open File";
    private String CONTINUE = "Continue";

    public enum Bank {
        ALLIANT_CHECKING,
        ALLIANT_CREDIT,
        BUSEY,
        CHASE
    }

    JFrame frame;
    JLabel fileNameLabel;

    JLabel successLabel;

    JButton openButton;
    JButton continueButton;

    JPanel panel;

    JComboBox comboBox;

    File csvFile;
    Bank bank = Bank.ALLIANT_CHECKING;


    public CSVUploader() {
        frame = new JFrame();

        openButton = new JButton(OPEN_FILE);
        openButton.addActionListener(this);

        fileNameLabel = new JLabel("File selected: ");
        continueButton = new JButton(CONTINUE);
        continueButton.addActionListener(this);

        comboBox = new JComboBox(Bank.values());
        comboBox.addActionListener(this);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(comboBox);
        panel.add(fileNameLabel);
        panel.add(openButton);
        panel.add(continueButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("CSV Parser");
        frame.setPreferredSize(new Dimension(500, 200));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CSVUploader();
    }

    @Override
    public void actionPerformed(ActionEvent action) {
        String command = action.getActionCommand();

        if (command.equals(OPEN_FILE)) {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.csv", "csv"));
            int openDialog = fileChooser.showOpenDialog(null);

            if (openDialog == JFileChooser.APPROVE_OPTION) {
                fileNameLabel.setText("File: " + fileChooser.getSelectedFile().getAbsolutePath());
                csvFile = fileChooser.getSelectedFile();
            }
        }
        else if (command.equals("comboBoxChanged")) {
            JComboBox box = (JComboBox) action.getSource();
            bank = (Bank) box.getSelectedItem();
        }
        else if (command.equals(CONTINUE)) {
            if (bank == null) {
                throw new RuntimeException("No bank provided!");
            }
            CSVHandler.parseFile(bank, csvFile);
            fileNameLabel.setText("Success!");

        }
    }
}
