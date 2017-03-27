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
 * The Vendor Controller Class
 *
 * @author Georgi Kurian
 */
@Named
@ApplicationScoped
public class VendorController {

    private List<Vendor> vendors;
    private Vendor currentVendor = new Vendor();

    /**
     * Gets current vendor
     *
     * @return
     */
    public Vendor getCurrentVendor() {
        return currentVendor;
    }

    /**
     * Sets currentVendor
     *
     * @param currentVendor
     */
    public void setCurrentVendor(Vendor currentVendor) {
        this.currentVendor = currentVendor;
    }

    /**
     * Default Constructor
     */
    public VendorController() {
        refresh();
    }

    /**
     * Add vendor to db
     *
     * @return
     */
    public String add() {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "INSERT INTO Vendors (VendorId, Name, ContactName,PhoneNumber ) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentVendor.getVendorId());
            pstmt.setString(2, currentVendor.getName());
            pstmt.setString(3, currentVendor.getContactName());
            pstmt.setString(4, currentVendor.getPhoneNumber());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh();
        return "index";
    }

    /**
     * Refreshes the vendor list from db
     */
    public void refresh() {
        try {
            vendors = new ArrayList();
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Vendors");
            while (rs.next()) {
                Vendor vendor = new Vendor(
                        rs.getInt("VendorId"),
                        rs.getString("name"),
                        rs.getString("contactName"),
                        rs.getString("phoneNumber")
                );

                vendors.add(vendor);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VendorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * gets the vendor list
     *
     * @return
     */
    public List<Vendor> getVendors() {
        return vendors;
    }

    /**
     * Sets the vendor list
     *
     * @param vendors
     */
    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    /**
     * gets the vendor ids from the list
     *
     * @return
     */
    public List<Integer> getVendorIDs() {
        List<Integer> vendorIDs = new ArrayList();
        for (Vendor vendor : vendors) {
            vendorIDs.add(vendor.getVendorId());
        }
        return vendorIDs;
    }
}
