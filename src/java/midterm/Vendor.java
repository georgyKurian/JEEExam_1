package midterm;

/**
 * The Vendor Model Class
 *
 * @author georgi
 */
public class Vendor {

    private int vendorId;
    private String name;
    private String contactName;
    private String phoneNumber;

    /**
     * Default constructor
     */
    public Vendor() {
    }

    /**
     * Parametrized constructor
     *
     * @param vendorId
     * @param name
     * @param contactName
     * @param phoneNumber
     */
    public Vendor(int vendorId, String name, String contactName, String phoneNumber) {
        this.vendorId = vendorId;
        this.name = name;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }

    /**
     * gets contact name
     *
     * @return
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the contact name
     *
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * gets the phone number
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * gets the vendor Id
     *
     * @return
     */
    public int getVendorId() {
        return vendorId;
    }

    /**
     * Sets the vendor id
     *
     * @param vendorId
     */
    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * get the name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

}
