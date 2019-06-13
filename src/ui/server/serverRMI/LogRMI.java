package ui.server.serverRMI;
 
import java.awt.Color;
import java.awt.Font;
import ui.server.common.CustomOutputStream;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
 
public class LogRMI extends JFrame {
    private JTextArea textArea;
  
    private JButton buttonClear = new JButton("Limpar");
    
    private PrintStream standardOut;
     
    public LogRMI() {
        super("Servidor RMI");
         
        textArea = new JTextArea(50, 10);
        textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
         
        // keeps reference of standard output stream
        standardOut = System.out;
         
        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);
 
        // creates the GUI
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        
        constraints.gridx = 1;
        add(buttonClear, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        
        add(new JScrollPane(textArea), constraints);
         
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // clears the text area
                try {
                    textArea.getDocument().remove(0,
                            textArea.getDocument().getLength());
                    standardOut.println("LIMPAR");
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });
        textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLACK);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);    // centers on screen
    }
    
    public void printLog(String msg) {
        System.out.println(msg);
    }
     
    
    /**
     * Runs the program
     * @param args NAO VAI SER NADA
     */
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LogRMI().setVisible(true);
            }
        });
    }
}
