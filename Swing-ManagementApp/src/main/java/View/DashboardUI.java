package View;

import Business.CustomerController;
import Core.Helper;
import Entity.Customer;
import Entity.User;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DashboardUI extends JFrame {
    private JButton btn_exit;
    private JPanel pnl_main;
    private JLabel lbl_welcome;
    private JTabbedPane pnl_customer;
    private JPanel pnl_tapped_customer;
    private JTable tbl_customer;
    private JComboBox cmb_customer_type;
    private JButton btn_customer_filter;
    private JButton btn_customer_clear;
    private JButton btn_customer_add;
    private JLabel lbl_customer_filter;
    private JTextField fld_customer_name;
    private JLabel lbl_customer_type;
    private User user;
    private final CustomerController customerController = new CustomerController();
    private DefaultTableModel table_model_customer = new DefaultTableModel();
    private JPopupMenu popup_customer_selection = new JPopupMenu();

    public DashboardUI(User user) {
        this.user = user;


        // init table
        loadCustomerTable(null);
        loadCustomerPopUpMenu();
        loadCustomerButtons();
        // init table

        setContentPane(pnl_main);
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1500, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        this.cmb_customer_type.setModel(new DefaultComboBoxModel<>(Customer.ETYPE.values()));
        this.cmb_customer_type.setSelectedItem(null);


        this.lbl_welcome.setText("Welcome Back " + this.user.getName());
        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginUI loginUI = new LoginUI();
            }
        });

        btn_customer_filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Customer> arrayList = new ArrayList<>();
                arrayList = customerController.filter(fld_customer_name.getText(), cmb_customer_type.getSelectedItem().toString());
                System.out.println("here" + arrayList);
                loadCustomerTable(arrayList);
            }
        });
    }

    private void loadCustomerButtons() {

        this.btn_customer_add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerUI customerUI = new CustomerUI(new Customer());
                customerUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadCustomerTable(null);
                    }
                });
            }
        });
    }

    private void loadCustomerPopUpMenu() {
        this.tbl_customer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tbl_customer.rowAtPoint(e.getPoint());
                tbl_customer.setRowSelectionInterval(selectedRow, selectedRow);
            }

        });

        this.popup_customer_selection.add("Update").addActionListener(e -> {
            String selectedID = tbl_customer.getValueAt(tbl_customer.getSelectedRow(), 0).toString();

            Customer editedCustomer = customerController.findById(selectedID);

            CustomerUI customerUI = new CustomerUI(editedCustomer);
            customerUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadCustomerTable(null);
                }
            });

        });
        this.popup_customer_selection.add("Delete").addActionListener(e -> {
            String selectedID = tbl_customer.getValueAt(tbl_customer.getSelectedRow(), 0).toString();
            if (Helper.confirmDelete("sure")) {
                Customer deletedCustomer = customerController.findById(selectedID);
                customerController.delete(deletedCustomer);
                loadCustomerTable(null);
            }


        });
        this.tbl_customer.setComponentPopupMenu(popup_customer_selection);
    }

    private void loadCustomerTable(ArrayList<Customer> customers) {
        Object[] columnCustomer = {"ID", "Name", "Address", "Phone", "Email", "Type"};
        this.table_model_customer.setColumnIdentifiers(columnCustomer);


        // Clearing Table
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_customer.getModel();
        clearModel.setRowCount(0);
        // Clearing Table


        ArrayList<Customer> customersFetched = new ArrayList<>();
        if (customers == null) {
            customersFetched = customerController.findAll();
        } else
            customersFetched = customers;


        for (Customer customer : customersFetched) {
            this.table_model_customer.addRow(new Object[]{
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getPhone(),
                    customer.getMail(),
                    customer.getType(),
            });
        }
        this.tbl_customer.setModel(this.table_model_customer);
        this.tbl_customer.getTableHeader().setReorderingAllowed(false);
        this.tbl_customer.getColumnModel().getColumn(0).setMaxWidth(50);
        this.tbl_customer.setEnabled(false);

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
        pnl_main = new JPanel();
        pnl_main.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        pnl_main.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lbl_welcome = new JLabel();
        lbl_welcome.setText("Label");
        panel1.add(lbl_welcome, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_exit = new JButton();
        btn_exit.setHorizontalAlignment(4);
        btn_exit.setText("Exit");
        panel1.add(btn_exit, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnl_customer = new JTabbedPane();
        pnl_main.add(pnl_customer, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        pnl_tapped_customer = new JPanel();
        pnl_tapped_customer.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnl_customer.addTab("Customer", pnl_tapped_customer);
        final JScrollPane scrollPane1 = new JScrollPane();
        pnl_tapped_customer.add(scrollPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tbl_customer = new JTable();
        scrollPane1.setViewportView(tbl_customer);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 5, new Insets(5, 5, 5, 5), -1, -1, true, false));
        pnl_tapped_customer.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lbl_customer_filter = new JLabel();
        lbl_customer_filter.setText("Customer Name");
        panel2.add(lbl_customer_filter, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbl_customer_type = new JLabel();
        lbl_customer_type.setText("Customer Type");
        panel2.add(lbl_customer_type, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cmb_customer_type = new JComboBox();
        panel2.add(cmb_customer_type, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_customer_filter = new JButton();
        btn_customer_filter.setText("Filter");
        panel2.add(btn_customer_filter, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_customer_clear = new JButton();
        btn_customer_clear.setText("Clear");
        panel2.add(btn_customer_clear, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_customer_add = new JButton();
        btn_customer_add.setText("Add");
        panel2.add(btn_customer_add, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fld_customer_name = new JTextField();
        panel2.add(fld_customer_name, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnl_main;
    }

}
