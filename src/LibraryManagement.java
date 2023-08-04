import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class LibraryManagement extends JFrame implements ActionListener {
    private JTextField[] textFields;
    private JButton addButton, viewButton, editButton, deleteButton, clearButton, exitButton;
    private ArrayList<String[]> books = new ArrayList<>();

    public LibraryManagement() {
        setTitle("Library Management System");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String[] labels = {"Book ID", "Book title", "Author", "Publisher", "Year of publication", "ISBN", "Number of copies"};
        textFields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            textFields[i] = new JTextField(20);
            add(new JLabel(labels[i]));
            add(textFields[i]);
        }

        addButton = new JButton("Add");
        viewButton = new JButton("View");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");

        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);
        exitButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String[] book = new String[textFields.length];
            for (int i = 0; i < textFields.length; i++) {
                book[i] = textFields[i].getText();
            }
            books.add(book);
            JOptionPane.showMessageDialog(this, "Book added successfully");
            clearFields();
        } else if (e.getSource() == viewButton) {
            showBooks();
        } else if (e.getSource() == editButton) {
            editBook();
        } else if (e.getSource() == deleteButton) {
            deleteBook();
        } else if (e.getSource() == clearButton) {
            clearFields();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    private void showBooks() {
        String[] columns = {"Book ID", "Book title", "Author", "Publisher", "Year of publication", "ISBN", "Number of copies"};
        Object[][] data = new Object[books.size()][textFields.length];
        for (int i = 0; i < books.size(); i++) {
            data[i] = books.get(i);
        }
        JTable table = new JTable(new DefaultTableModel(data, columns));
        JOptionPane.showMessageDialog(this, new JScrollPane(table), "View Books", JOptionPane.PLAIN_MESSAGE);
    }

    private void editBook() {
        String bookID = JOptionPane.showInputDialog(this, "Enter book ID to edit: ");
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i)[0].equals(bookID)) {
                String[] book = new String[textFields.length];
                for (int j = 0; j < textFields.length; j++) {
                    book[j] = textFields[j].getText();
                }
                books.set(i, book);
                JOptionPane.showMessageDialog(this, "Book edited successfully");
                clearFields();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Book not found");
    }

    private void deleteBook() {
        String bookID = JOptionPane.showInputDialog(this, "Enter book ID to delete: ");
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i)[0].equals(bookID)) {
                books.remove(i);
                JOptionPane.showMessageDialog(this, "Book deleted successfully");
                clearFields();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Book not found");
    }

    private void clearFields() {
        for (JTextField textField : textFields) {
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryManagement::new);
    }
}
