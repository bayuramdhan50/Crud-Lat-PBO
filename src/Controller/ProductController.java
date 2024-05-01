package Controller;

import Model.Product;
import Connection.Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private Connections connections;

    public ProductController() {
        this.connections = new Connections();
    }

    // Method untuk mendapatkan daftar semua produk dari database
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connections.getConnection();

            // Query untuk mengambil semua produk dari tabel products
            String query = "SELECT * FROM products";
            statement = connection.prepareStatement(query);

            // Eksekusi query
            resultSet = statement.executeQuery();

            // Mengambil data produk dari hasil query dan menambahkannya ke dalam list
            while (resultSet.next()) {
                String productName = resultSet.getString("ProductName");
                double price = resultSet.getDouble("Price");
                int quantityAvailable = resultSet.getInt("QuantityAvailable");
                String description = resultSet.getString("Description");

                Product product = new Product(productName, price, quantityAvailable, description);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup sumber daya yang diperoleh
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return productList;
    }

    public void addProduct(Product product) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connections.getConnection();

            // Query untuk menambahkan produk baru ke tabel products
            String query = "INSERT INTO products (ProductName, Price, QuantityAvailable, Description) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, product.getProductName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantityAvailable());
            statement.setString(4, product.getDescription());

            // Eksekusi query
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup sumber daya yang diperoleh
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Method untuk memperbarui produk di database
    public void updateProduct(Product product, String selectedProductName) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connections.getConnection();

            // Query untuk memperbarui produk berdasarkan nama produk yang dipilih
            String query = "UPDATE products SET ProductName=?, Price=?, QuantityAvailable=?, Description=? WHERE ProductName=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, product.getProductName()); // Update nama produk baru
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantityAvailable());
            statement.setString(4, product.getDescription());
            statement.setString(5, selectedProductName); // Gunakan nama produk yang dipilih sebagai kriteria WHERE

            // Eksekusi query
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup sumber daya yang diperoleh
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Method untuk menghapus produk dari database berdasarkan nama produk
    public void deleteProduct(String productName) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connections.getConnection();

            // Query untuk menghapus produk berdasarkan nama produk
            String query = "DELETE FROM products WHERE ProductName=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, productName);

            // Eksekusi query
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup sumber daya yang diperoleh
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    // Metode lainnya dapat ditambahkan sesuai dengan kebutuhan aplikasi
}
