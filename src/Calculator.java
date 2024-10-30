import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Calculator implements ActionListener {

    JFrame jf;
    JLabel displayLabel;
    StringBuilder currentInput;
    double previousValue;
    String operation;

    public Calculator() {
        jf = new JFrame("Calculator"); // Initialize JFrame

        jf.setLayout(null);
        jf.setSize(600, 600);
        jf.setLocation(300, 150);

        // Display Label
        displayLabel = new JLabel("");
        displayLabel.setBounds(30, 50, 540, 40);
        displayLabel.setBackground(Color.gray);
        displayLabel.setOpaque(true);
        displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displayLabel.setForeground(Color.white);
        jf.add(displayLabel);

        // Buttons
        addButton("7", 30, 130, 80, 80);
        addButton("8", 130, 130, 80, 80);
        addButton("9", 230, 130, 80, 80);
        addButton("/", 330, 130, 80, 80);

        addButton("4", 30, 230, 80, 80);
        addButton("5", 130, 230, 80, 80);
        addButton("6", 230, 230, 80, 80);
        addButton("X", 330, 230, 80, 80);

        addButton("1", 30, 330, 80, 80);
        addButton("2", 130, 330, 80, 80);
        addButton("3", 230, 330, 80, 80);
        addButton("-", 330, 330, 80, 80);

        addButton(".", 30, 430, 80, 80);
        addButton("0", 130, 430, 80, 80);
        addButton("=", 230, 430, 80, 80);
        addButton("+", 330, 430, 80, 80);

        // Clear button
        JButton clearButton = new JButton("C");
        clearButton.setBounds(430, 130, 80, 80);
        clearButton.addActionListener(this);
        jf.add(clearButton);

        // Initialize calculator state
        currentInput = new StringBuilder();
        previousValue = 0;
        operation = "";

        // JFrame properties
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Helper method to add buttons
    private void addButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(this);
        jf.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("\\d") || command.equals(".")) { // If a number or dot is pressed
            currentInput.append(command);
            displayLabel.setText(currentInput.toString());
        } else if (command.equals("C")) { // Clear button pressed
            currentInput.setLength(0);
            displayLabel.setText("");
            previousValue = 0;
            operation = "";
        } else if (command.equals("=")) { // Equals button pressed
            performCalculation();
        } else { // Any operator (+, -, X, /) pressed
            if (!currentInput.toString().isEmpty()) {
                previousValue = Double.parseDouble(currentInput.toString());
                currentInput.setLength(0); // Clear input for next number
            }
            operation = command; // Store the operator
        }
    }

    // Method to perform the calculation based on the stored operator
    private void performCalculation() {
        double currentValue = Double.parseDouble(currentInput.toString());
        switch (operation) {
            case "+":
                previousValue += currentValue;
                break;
            case "-":
                previousValue -= currentValue;
                break;
            case "X":
                previousValue *= currentValue;
                break;
            case "/":
                if (currentValue != 0) {
                    previousValue /= currentValue;
                } else {
                    displayLabel.setText("Error"); // Handle division by zero
                    return;
                }
                break;
        }
        displayLabel.setText(String.valueOf(previousValue));
        currentInput.setLength(0); // Clear current input
        operation = ""; // Reset operation
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
