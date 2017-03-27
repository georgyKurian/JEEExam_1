package midterm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * The Product Controller Class
 *
 * @author <ENTER YOUR NAME HERE>
 */
@Named
@ApplicationScoped
public class ProductController {

    private List<Product> products = new ArrayList<>();
    private Product thisProduct = new Product(-1, "", -1);

    /**
     * Basic Constructor for Products - Retrieves from DB
     */
    public ProductController() {
        refresh();
    }

    /**
     * Refreshes the data in list from DB
     */
    public void refresh() {
        try {
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Products");
            products = new ArrayList();
            while (rs.next()) {
                Product p = new Product();
                p.setVendorId(rs.getInt("VendorId"));
                p.setName(rs.getString("Name"));
                p.setProductId(rs.getInt("ProductId"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieve the full list of Products
     *
     * @return the List of Products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Retrieve the Product Model used in Forms
     *
     * @return the Product Model used in Forms
     */
    public Product getThisProduct() {
        return thisProduct;
    }

    /**
     * Set the Product Model used in Forms
     *
     * @param thisProduct the Product Model used in Forms
     */
    public void setThisProduct(Product thisProduct) {
        this.thisProduct = thisProduct;
    }

    /**
     * Add a new Product to the Database and List
     *
     * @return
     */
    public String add() {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "INSERT INTO Products (Name, VendorId) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, thisProduct.getName());
            pstmt.setInt(2, thisProduct.getVendorId());
            pstmt.executeUpdate();
            products.add(thisProduct);
            thisProduct = new Product();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh();
        return "index";
    }

    /**
     * Sets the current product(thisProduct) and redirects to edit page
     *
     * @param pro
     * @return
     */
    public String edit(Product pro) {
        thisProduct = pro;
        return "editProduct";
    }

    /**
     * Saves the edited details
     *
     * @return
     */
    public String editProduct() {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "UPDATE Products SET Name = ?, VendorId = ? WHERE ProductId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, thisProduct.getName());
            pstmt.setInt(2, thisProduct.getVendorId());
            pstmt.setInt(3, thisProduct.getProductId());
            pstmt.executeUpdate();
            thisProduct = new Product();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh();
        return "index";
    }

    /**
     * Deletes the product
     *
     * @param prod
     */
    public void deleteProduct(Product prod) {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "DELETE FROM Products WHERE ProductId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, prod.getProductId());
            pstmt.executeUpdate();
            thisProduct = new Product();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh();
    }
}
