package View;

import Business.ProductController;
import Core.Helper;
import Entity.Product;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductUI extends JFrame {
    private JPanel MainContainer;
    private JLabel lbl_title;
    private JLabel lbl_product_name;
    private JTextField fld_product_name;
    private JLabel lbl_code;
    private JTextField fld_product_code;
    private JLabel lbl_price;
    private JTextField fld_product_price;
    private JLabel lbl_stock;
    private JTextField fld_product_stock;
    private JButton btn_save;
    private Product product;
    private final ProductController productController = new ProductController();

    public ProductUI(Product product) {
        this.product = product;

        setContentPane(MainContainer);
        setTitle("Product Add / Edit");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);


        if (this.product.getId() == null) {
            this.setTitle("Add New Product");
            this.lbl_title.setText("Add New Product");
        } else {
            this.setTitle("Edit Product");
            this.lbl_title.setText("Edit Product" + this.product.getName());
            this.fld_product_name.setText(this.product.getName());
            this.fld_product_code.setText(this.product.getCode());
            this.fld_product_price.setText(String.valueOf(product.getPrice()));
            this.fld_product_stock.setText(String.valueOf(product.getStock()));
        }

        btn_save.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] check = {fld_product_name, fld_product_code, fld_product_price, fld_product_stock};
                if (Helper.isTextFieldListEmpty(check)) {
                    JOptionPane.showMessageDialog(null, "Fill the field properley! ", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean result = false;

                    product.setName(fld_product_name.getText());
                    product.setCode(fld_product_code.getText());
                    product.setPrice(Integer.parseInt(fld_product_price.getText()));
                    product.setStock(Integer.parseInt(fld_product_stock.getText()));

                    if (product.getId() == null || product.getId().toString().isEmpty()) {

                        result = productController.save(product);

                    } else {

                        result = productController.update(product);
                    }

                    if (result == true) {
                        Helper.showMessage("SUCCESS_UPDATE_PRODUCT");
                        dispose();

                    } else {
                        Helper.showMessage("FAIL_UPDATE_PRODUCT");
                    }
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
        MainContainer.setLayout(new GridLayoutManager(11, 1, new Insets(10, 10, 10, 10), -1, -1));
        lbl_title = new JLabel();
        lbl_title.setText("Label");
        MainContainer.add(lbl_title, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        MainContainer.add(spacer1, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        lbl_product_name = new JLabel();
        lbl_product_name.setText("Name");
        MainContainer.add(lbl_product_name, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fld_product_name = new JTextField();
        MainContainer.add(fld_product_name, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lbl_code = new JLabel();
        lbl_code.setText("Code:");
        MainContainer.add(lbl_code, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fld_product_code = new JTextField();
        MainContainer.add(fld_product_code, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lbl_price = new JLabel();
        lbl_price.setText("Price:");
        MainContainer.add(lbl_price, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fld_product_price = new JTextField();
        MainContainer.add(fld_product_price, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lbl_stock = new JLabel();
        lbl_stock.setText("Stock:");
        MainContainer.add(lbl_stock, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fld_product_stock = new JTextField();
        MainContainer.add(fld_product_stock, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        btn_save = new JButton();
        btn_save.setText("Save");
        MainContainer.add(btn_save, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainContainer;
    }

}
