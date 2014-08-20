package org.librebiz.noodleauth;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class AccountCodeRenderer extends JPanel implements ListCellRenderer {
    private long time;

    public AccountCodeRenderer() {
        initComponents();
        setOpaque(true);
        panel.setOpaque(false);
        name.setOpaque(false);
        code.setOpaque(false);
        clock.setOpaque(false);
        ClockIcon ci = (ClockIcon)clock.getIcon();
        ci.setFillColor(clock.getBackground());
        ci.setStrokeColor(clock.getForeground());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        code = new javax.swing.JLabel();
        clock = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        setLayout(new java.awt.BorderLayout());

        panel.setLayout(new java.awt.GridLayout(2, 0));

        name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        name.setText("Account name");
        panel.add(name);

        code.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        code.setText("Verif. code");
        panel.add(code);

        add(panel, java.awt.BorderLayout.CENTER);

        clock.setBackground(new java.awt.Color(204, 255, 255));
        clock.setForeground(new java.awt.Color(102, 102, 102));
        clock.setIcon(new ClockIcon(32, 32));
        clock.setPreferredSize(new java.awt.Dimension(32, 32));
        add(clock, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clock;
    private javax.swing.JLabel code;
    private javax.swing.JLabel name;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        ClockIcon ci = null;
        if (value instanceof Token) {
            Token token = (Token)value;
            ci = (ClockIcon)clock.getIcon();
            ci.setAngle(token.getAngle());
            name.setText(token.getAccount().getName());
            code.setText(token.getCode());
        } else {
            name.setText("Account name");
            code.setText("Code");
        }
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        name.setForeground(getForeground());
        code.setForeground(getForeground());
        if (ci != null) {
            ci.setFillColor(getForeground());
            ci.setStrokeColor(null);
        }
        setEnabled(list.isEnabled());
        return this;
    }
}
