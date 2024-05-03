package View;

import Controller.ProductController;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Admin extends JDialog {
    private JPanel adminPanel;
    private JTextField tFproductNameAdmin;
    private JTextField tFpriceAdmin;
    private JTextField tFquantityAvailableAdmin;
    private JTextArea tADescriptionAdmin;
    private JTable tableProduct;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private ProductController productController;

    public Admin(JFrame parent) throws SQLException {
        super(parent);
        setContentPane(adminPanel);
        setTitle("Admin");
        setSize(750, 500);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.productController = new ProductController();
        refreshProductTable();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ketika tombol "Tambah" ditekan, buat objek Product baru dari input pengguna dan tambahkan ke database
                String productName = tFproductNameAdmin.getText();
                double price = Double.parseDouble(tFpriceAdmin.getText());
                int quantityAvailable = Integer.parseInt(tFquantityAvailableAdmin.getText());
                String description = tADescriptionAdmin.getText();

                Product newProduct = new Product(productName, price, quantityAvailable, description);
                productController.addProduct(newProduct);

                // Refresh tabel produk setelah menambahkan
                refreshProductTable();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ambil baris yang dipilih di tabel
                int selectedRow = tableProduct.getSelectedRow();
                if (selectedRow != -1) {
                    // Ambil nilai dari kolom-kolom yang sesuai di baris yang dipilih
                    String productName = tFproductNameAdmin.getText();
                    double price = Double.parseDouble(tFpriceAdmin.getText());
                    int quantityAvailable = Integer.parseInt(tFquantityAvailableAdmin.getText());
                    String description = tADescriptionAdmin.getText();

                    // Ambil nama produk yang dipilih dari tabel
                    String selectedProductName = (String) tableProduct.getValueAt(selectedRow, 0);

                    // Buat objek Product baru dengan nilai yang diperoleh
                    Product updatedProduct = new Product(productName, price, quantityAvailable, description);

                    // Panggil metode updateProduct untuk menyimpan perubahan
                    productController.updateProduct(updatedProduct, selectedProductName);

                    // Refresh tabel produk setelah memperbarui
                    refreshProductTable();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ketika tombol "Hapus" ditekan, hapus produk yang dipilih dari database
                int selectedRow = tableProduct.getSelectedRow();
                if (selectedRow != -1) {
                    String productName = (String) tableProduct.getValueAt(selectedRow, 0);
                    productController.deleteProduct(productName);

                    // Refresh tabel produk setelah menghapus
                    refreshProductTable();
                }
            }
        });
        setVisible(true);
    }
    private void refreshProductTable() {
        // Ambil data produk dari database
        List<Product> productList = productController.getAllProducts();

        // Buat model tabel baru dengan data produk yang diperoleh
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Product Name");
        model.addColumn("Price");
        model.addColumn("Quantity Available");
        model.addColumn("Description");

        for (Product product : productList) {
            Object[] row = {product.getProductName(), product.getPrice(), product.getQuantityAvailable(), product.getDescription()};
            model.addRow(row);
        }

        // Set model baru ke tabel
        tableProduct.setModel(model);
    }

    public static void main(String[] args) throws SQLException {
        Admin admin = new Admin(null);
    }
}
