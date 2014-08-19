package org.librebiz.noodleauth;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AccountDialog extends JDialog {
    private Account result = null;
    private final Configuration conf;

    public static Account doDialog(Dialog parent, Configuration conf, 
            Account account) {
        AccountDialog dlg = new AccountDialog(parent, conf, account);
        dlg.setVisible(true);
        return dlg.result;
    }

    private AccountDialog(Dialog parent, Configuration conf, Account account) {
        super(parent, true);
        this.conf = conf;
        clearWidthAll(JTextField.class);
        initComponents();
        if (account != null) {
            String sec = Codec.BASE32.encode(account.getKey());
            name.setText(account.getName());
            secret.setText(sec);
            otpLength.setText(Integer.toString(account.getOtpLength()));
            duration.setText(Integer.toString(account.getValidity()));
        } else {
            name.setText("");
            secret.setText("");
            otpLength.setText(conf.getString("default-otplength", "6"));
            duration.setText(conf.getString("default-duration", "30"));
        }
        Rectangle rc = parent == null
                ? new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())
                : parent.getBounds();
        setLocation(rc.x + (rc.width-getWidth())/2,
                rc.y + (rc.height-getHeight())/2);
        getRootPane().setDefaultButton(ok);
    }

    private void clearWidthAll(Class<? extends JComponent> clazz) {
        clearWidthAll(this, clazz);
    }

    private static void clearWidth(JComponent comp) {
        Dimension size = comp.getMinimumSize();
        size.width = 0;
        comp.setMinimumSize(size);
        size = comp.getPreferredSize();
        size.width = 0;
        comp.setPreferredSize(size);
    }

    private static void clearWidthAll(final Container cont,
            Class<? extends JComponent> clazz) {
        Component children[] = cont.getComponents();
        for (int i = 0; i < children.length; ++i) {
            Component child = children[i];
            if (clazz.isInstance(child)) {
                clearWidth((JComponent)child);
            }
            if (child instanceof Container) {
                clearWidthAll((Container)child, clazz);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        secret = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        otpLength = new javax.swing.JTextField();
        duration = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        ok = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Account properties");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Account name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 11);
        getContentPane().add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 11);
        getContentPane().add(name, gridBagConstraints);

        jLabel2.setText("Secret:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 12, 0, 11);
        getContentPane().add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 11);
        getContentPane().add(secret, gridBagConstraints);

        jLabel3.setText("OTP length:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 12, 0, 5);
        getContentPane().add(jLabel3, gridBagConstraints);

        jLabel4.setText("Duration (s):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 0, 5);
        getContentPane().add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 11);
        getContentPane().add(otpLength, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 11);
        getContentPane().add(duration, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel1.add(ok, gridBagConstraints);

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(cancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(17, 12, 11, 11);
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        String code = secret.getText();
        if (code.length() == 0 || !Codec.BASE32.isValid(code)) {
            JOptionPane.showMessageDialog(null, "Invalid secret: " + code,
                    "Error", JOptionPane.ERROR_MESSAGE);
            secret.requestFocus();
            return;
        }

        int olen;
        try {
            olen = Integer.parseInt(otpLength.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid OTP length: "
                    + otpLength.getText(), "Error", JOptionPane.ERROR_MESSAGE);
            otpLength.requestFocus();
            return;
        }

        int dur;
        try {
            dur = Integer.parseInt(duration.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid duration: "
                    + duration.getText(), "Error", JOptionPane.ERROR_MESSAGE);
            duration.requestFocus();
            return;
        }

        byte[] key = Codec.BASE32.decode(code);
        result = new Account(name.getText(), key, olen, dur);
        dispose();
    }//GEN-LAST:event_okActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        dispose();
    }//GEN-LAST:event_cancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JTextField duration;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField name;
    private javax.swing.JButton ok;
    private javax.swing.JTextField otpLength;
    private javax.swing.JTextField secret;
    // End of variables declaration//GEN-END:variables

}
