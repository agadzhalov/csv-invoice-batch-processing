package com.invoice.processing.model;

//@TODO Lombok can be used here
public class Invoice {

    private String buyer;
    private String imageName;
    private String invoiceImage;
    private String invoiceDueDate;
    private String invoiceNumber;
    private String invoiceAmount;
    private String invoiceCurrency;
    private String invoiceStatus;
    private String supplier;

    public Invoice() {}

    public Invoice(String buyer, String imageName, String invoiceImage, String invoiceDueDate, String invoiceNumber, String invoiceAmount, String invoiceCurrency, String invoiceStatus, String supplier) {
        this.buyer = buyer;
        this.imageName = imageName;
        this.invoiceImage = invoiceImage;
        this.invoiceDueDate = invoiceDueDate;
        this.invoiceNumber = invoiceNumber;
        this.invoiceAmount = invoiceAmount;
        this.invoiceCurrency = invoiceCurrency;
        this.invoiceStatus = invoiceStatus;
        this.supplier = supplier;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String image_name) {
        this.imageName = image_name;
    }

    public String getInvoiceDueDate() {
        return invoiceDueDate;
    }

    public void setInvoiceDueDate(String invoice_due_date) {
        this.invoiceDueDate = invoice_due_date;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoice_number) {
        this.invoiceNumber = invoice_number;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoice_amount) {
        this.invoiceAmount = invoice_amount;
    }

    public String getInvoiceCurrency() {
        return invoiceCurrency;
    }

    public void setInvoiceCurrency(String invoice_currency) {
        this.invoiceCurrency = invoice_currency;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoice_status) {
        this.invoiceStatus = invoice_status;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getInvoiceImage() {
        return invoiceImage;
    }

    public void setInvoiceImage(String invoice_image) {
        this.invoiceImage = invoice_image;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "buyer='" + buyer + '\'' +
                ", imageName='" + imageName + '\'' +
                ", invoiceDueDate='" + invoiceDueDate + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", invoiceAmount='" + invoiceAmount + '\'' +
                ", invoiceCurrency='" + invoiceCurrency + '\'' +
                ", invoiceStatus='" + invoiceStatus + '\'' +
                ", supplier='" + supplier + '\'' +
                '}';
    }

}
