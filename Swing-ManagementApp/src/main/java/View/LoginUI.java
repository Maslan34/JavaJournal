package View;

import Business.UserController;
import Core.Helper;
import DAO.UserDao;
import Entity.User;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {
    private JPanel MainContainer;
    private JLabel lbl_top;
    private JPasswordField fld_password;
    private JButton btn_submit;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private JTextField fld_username;
    private UserController userController;

    public LoginUI() {
        this.userController = new UserController();
        this.setContentPane(MainContainer);
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        this.setVisible(true);


        btn_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = fld_username.getText();
                String password = new String(fld_password.getPassword());
                System.out.println(username + " " + password);
                // 🛠 Boş giriş kontrolü
                if (Helper.isTextFieldListEmpty(new JTextField[]{fld_username, fld_password})) {
                    JOptionPane.showMessageDialog(null, "Please enter a username and password!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                User user = userController.findByLogin(username, password);
                System.out.println(user);
                if (user != null) {

                    JOptionPane.showMessageDialog(null, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    DashboardUI dashboardUI = new DashboardUI(user);

                    System.out.println("User found: " + user);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        MainContainer = new JPanel();
        MainContainer.setLayout(new GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), -1, -1));
        lbl_username = new JLabel();
        lbl_username.setText("Username");
        MainContainer.add(lbl_username, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_password = new JLabel();
        lbl_password.setText("Password");
        MainContainer.add(lbl_password, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fld_password = new JPasswordField();
        MainContainer.add(fld_password, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        btn_submit = new JButton();
        btn_submit.setText("Submit");
        MainContainer.add(btn_submit, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fld_username = new JTextField();
        MainContainer.add(fld_username, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lbl_top = new JLabel();
        lbl_top.setText("Customer Management System");
        MainContainer.add(lbl_top, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainContainer;
    }
}
