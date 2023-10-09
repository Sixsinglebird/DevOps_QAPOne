package com.keyin;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class TextEditor extends JFrame implements ActionListener {
    private final JTextArea textArea;
    private final JFileChooser fileChooser;

    private String getCurrentWord(String text) {
        int caretPosition = textArea.getCaretPosition();
        int end = caretPosition;
        // Find the end of the current word
        while (end < text.length() && Character.isLetterOrDigit(text.charAt(end))) {
            end++;
        }
        return text.substring(caretPosition, end);
    }


    public TextEditor() {
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SuggestionEngine suggestionEngine = new SuggestionEngine();
        try {
            suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);


        // for the open and save file options
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        fileChooser = new JFileChooser();

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Open")) {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    textArea.setText("");
                    while ((line = br.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getActionCommand().equals("Save")) {
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(textArea.getText());
                    bw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
            SwingUtilities.invokeLater(TextEditor::new);
    }

}

