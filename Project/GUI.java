import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This is the main user interface for the system. It has three different screens: <br>
 * - An input screen that allows the user to input a file path. <br>
 * - A process screen that allows the user to begin the processing of an election. <br>
 * - A result screen that displays the results of an election and allows the generation
 * of a media report. <br>
 *
 * @author Henry Olson, CSCI 5801 Team 10, University of Minnestota
 */

public class GUI {
    /**
     * This defines the window the GUI runs from.
     */
    private JFrame plvsWindow;
    private JPanel pnl;
    /**
     * These are definitions for the "cancel", "input", "process", and "generate media report"
     * buttons used for the various screens of the window.
     */
    public JButton btnCancel, btnInput, btnSelect, btnProcess, btnMediaReport, btnAuditFile;
    /**
     * These are definitions for the "File Path:" instruction, the label displaying the currently
     * entered file name, and the label containing the results of an election.  These labels are
     * all used in the various screens of the window.
     */
    public JLabel lblInstruct, lblFilePath, lblResults;
    /**
     * This is the definition for the input text field used to pass in the file name to the system.
     */
    public JTextField txtIn;

    /**
     * Creates a new instance of the GUI, and initializes all controls required for each screen.
     */
    public GUI() {
        /**
         * Initialize the JFrame, the object used to create the window used by the application
         */
        plvsWindow = new JFrame("PLVS");
        plvsWindow.setSize(300, 350);
        plvsWindow.setLocationRelativeTo(null);
        plvsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         * Initialize the panel containing the controls in the window.
         */
        pnl = new JPanel();
        plvsWindow.add(pnl);

        GUI g = this;

        // Set up the controls
        btnInput = new JButton("Input");
        btnInput.addActionListener(new ActionListener() {
            // Define the "On Click" method for
            @Override
            public void actionPerformed(ActionEvent e) {
                PLVS.TakeInputFile(txtIn.getText(), g);
            }
        });

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            // Define the "On Click" method for
            @Override
            public void actionPerformed(ActionEvent e) {
                plvsWindow.dispose();
            }
        });

        btnSelect = new JButton("Select file from");
        btnSelect.addActionListener(new ActionListener() {
            // Define the "On Click" method for
            @Override
            public void actionPerformed(ActionEvent e) {
                // plvsWindow.dispose();
                JFileChooser fc = new JFileChooser();
                int r = fc.showOpenDialog(null);
                if(r == JFileChooser.APPROVE_OPTION){
                    PLVS.TakeInputFile(fc.getSelectedFile().getAbsolutePath(), g);
                }
            }
        });

        btnProcess = new JButton("Process and save AuditFile");
        btnProcess.addActionListener(new ActionListener() {
            // Define the "On Click" method for
            @Override
            public void actionPerformed(ActionEvent e) {
                PLVS.ProcessElection(g);
            }
        });

        btnMediaReport = new JButton("Generate Media Report");
        btnMediaReport.addActionListener(new ActionListener() {
            // Define the "On Click" method for
            @Override
            public void actionPerformed(ActionEvent e) {
                PLVS.CreateMediaFile(g);
            }
        });

        // btnAuditFile = new JButton("Generate Audit File");
        // btnAuditFile.addActionListener(new ActionListener() {
        //     // Define the "On Click" method for
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         PLVS.ProcessElection(g);
        //     }
        // });
        /**
         * Initialize the labels used with placeholder values, with the exception of the "File Name:"
         * label which is given its displayed text.
         */
        lblInstruct = new JLabel("Enter File Name:");
        lblFilePath = new JLabel("<filepath>");
        lblResults = new JLabel("<results>");
        /**
         * Initializes the input text field.  Note that a long whitespace is used to start it at
         * a workable size.
         */
        txtIn = new JTextField("                                                        ");
    }

    /**
     * This procedure displays the GUI by makning it visible.  It also displays the input screen.
     */
    public void open() {
        goToInput();
        plvsWindow.setVisible(true);
        pack();
    }

    /**
     * This procedure transitions the GUI to the input screen.
     */
    public void goToInput() {
        /**
         * Clear the prior window
         */
        plvsWindow.remove(pnl);

        /**
         * Set up a new window
         */
        pnl = new JPanel();
        pnl.setLayout(new FlowLayout());

        /**
         * Add the "File Path:" label, the input text-field, and the input/cancel buttons
         * to the window.  This creates the input screen.
         */
        pnl.add(lblInstruct);
        pnl.add(txtIn);
        pnl.add(btnInput);
        pnl.add(btnCancel);
        pnl.add(btnSelect);
        plvsWindow.add(pnl);
        /**
         * Once the transition is complete, the procedure ends.
         */
    }

    /**
     * This procedure transitions the GUI to the process screen.
     */
    public void goToProcess() {
        /**
         * Clear the prior window
         */
        plvsWindow.remove(pnl);

        /**
         * Set up a new window
         */
        pnl = new JPanel();
        pnl.setLayout(new FlowLayout());

        /**
         * Add the label containing the file path and the "process" button to the window.
         * This creates the results screen.
         */
        pnl.add(lblFilePath);
        pnl.add(btnProcess);
        plvsWindow.add(pnl);
        /**
         * Once the transition is complete, the procedure ends.
         */
    }

    /**
     * This procedure transitions the GUI to the results screen.
     */
    public void goToResults() {
        /**
         * Clear the prior window
         */
        plvsWindow.remove(pnl);

        /**
         * Set up a new window
         */
        pnl = new JPanel();
        pnl.setLayout(new FlowLayout());

        /**
         * Add the label displaying results and the media report generation button
         * to the window.  This creates the results screen.
         */
        pnl.add(lblResults);
        pnl.add(btnMediaReport);
        // pnl.add(btnAuditFile);
        plvsWindow.add(pnl);

        /**
         * Once the transition is complete, the procedure ends.
         */
    }

    /**
     * This procedure calls the JFrame's pack() method, which resizes the frame to conain its contents.
     */
    public void pack() {
        plvsWindow.pack();
    }

    /**
     * This method displays an alert dialog containing a string msg
     *
     * @param msg, contains test for alert dialog
     */
    public void alert(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    /**
     * This method closes the GUI.
     */
    public void close() {
        plvsWindow.dispose();
    }
    /**
     * Returns all the private pnl field.
     * @return The JPanel containing all active controls
     */
    public JPanel getPnl(){
        return pnl;
    }
}

