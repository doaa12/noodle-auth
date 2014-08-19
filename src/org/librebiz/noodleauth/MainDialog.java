package org.librebiz.noodleauth;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class MainDialog extends javax.swing.JDialog {

    private static final Logger LOG
            = Logger.getLogger(MainDialog.class.getName());
    private static final Timer TIMER = new Timer();
    private final Configuration conf;
    private final DefaultListModel listModel = new DefaultListModel();
    private final AccountCodeRenderer renderer = new AccountCodeRenderer();
    private final TimerTask refreshTask;

    public MainDialog(Frame parent, boolean modal) throws IOException {
        super(parent, modal);
        conf = Configuration.load();
        initComponents();
        listModel.removeAllElements();
        for (Account acc : conf.getAccounts()) {
            listModel.addElement(acc);
        }
        list.setCellRenderer(renderer);
        list.setModel(listModel);
        list.setSelectedIndex(-1);
        selectionChanged();
        int width = conf.getInt("window.width", getWidth());
        int height = conf.getInt("window.height", getHeight());
        Rectangle rc = parent == null
                ? new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())
                : parent.getBounds();
        setBounds(rc.x + (rc.width - width) / 2, rc.y + (rc.height - height) / 2,
                width, height);
        refreshTask = new TimerTask() {
            @Override
            public void run() {
                renderer.captureTime();
                list.repaint();
            }
        };
        TIMER.schedule(refreshTask, 0, 1000);
    }

    @Override
    public void dispose() {
        refreshTask.cancel();
        super.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList();
        buttonPanel = new javax.swing.JPanel();
        add = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        copy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Verification codes:");
        getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);

        jScrollPane1.setBackground(getBackground());
        jScrollPane1.setBorder(null);

        list.setBackground(getBackground());
        list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        list.setMinimumSize(new java.awt.Dimension(200, 80));
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMouseClicked(evt);
            }
        });
        list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(list);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        add.setText("Add...");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        buttonPanel.add(add, new java.awt.GridBagConstraints());

        edit.setText("Edit...");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        buttonPanel.add(edit, gridBagConstraints);

        remove.setText("Remove");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        buttonPanel.add(remove, gridBagConstraints);

        copy.setText("Copy token");
        copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        buttonPanel.add(copy, gridBagConstraints);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        Account acc = AccountDialog.doDialog(this, conf, null);
        if (acc != null) {
            listModel.addElement(acc);
            save();
        }
    }//GEN-LAST:event_addActionPerformed

    private void listValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listValueChanged
        selectionChanged();
    }//GEN-LAST:event_listValueChanged

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        int ix = list.getSelectedIndex();
        if (ix >= 0) {
            Account acc = (Account) listModel.getElementAt(ix);
            acc = AccountDialog.doDialog(this, conf, acc);
            if (acc != null) {
                listModel.setElementAt(acc, ix);
                save();
            }
        }
    }//GEN-LAST:event_editActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        int ix = list.getSelectedIndex();
        if (ix >= 0) {
            listModel.removeElementAt(ix);
            save();
        }
    }//GEN-LAST:event_removeActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        save();
    }//GEN-LAST:event_formComponentResized

    private void listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseClicked
        if (evt.getClickCount() == 2) {
            int ix = list.locationToIndex(evt.getPoint());
            if (ix >= 0) {
                Account acc = (Account) listModel.getElementAt(ix);
                acc = AccountDialog.doDialog(this, conf, acc);
                if (acc != null) {
                    listModel.setElementAt(acc, ix);
                    save();
                }
            }
        }
    }//GEN-LAST:event_listMouseClicked

    private void copyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyActionPerformed
        int ix = list.getSelectedIndex();
        if (ix >= 0) {
            Account acc = (Account) listModel.getElementAt(ix);
            OTP otp = acc.generateOTP(System.currentTimeMillis());
            StringSelection sel = new StringSelection(otp.getCode());
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            clip.setContents(sel, null);
        }
    }//GEN-LAST:event_copyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton copy;
    private javax.swing.JButton edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList list;
    private javax.swing.JButton remove;
    // End of variables declaration//GEN-END:variables

    private void selectionChanged() {
        int ix = list.getSelectedIndex();
        edit.setEnabled(ix >= 0);
        remove.setEnabled(ix >= 0);
        copy.setEnabled(ix >= 0);
    }

    private void save() {
        try {
            Dimension dim = this.getSize();
            conf.setInt("window.width", dim.width);
            conf.setInt("window.height", dim.height);
            int count = listModel.getSize();
            List<Account> accounts = new ArrayList<Account>(count);
            for (int i = 0; i < count; ++i) {
                accounts.add((Account) listModel.getElementAt(i));
            }
            conf.setAccounts(accounts);
            conf.save();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
