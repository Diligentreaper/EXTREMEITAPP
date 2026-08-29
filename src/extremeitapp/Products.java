/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package extremeitapp;

/**
 *
 * @author Percy
 */

    import java.util.ArrayList;
import java.util.Scanner;

public class Products {
    // Array list
    private final ArrayList<String> productCodes = new ArrayList<>();
    private final ArrayList<String> productNames = new ArrayList<>();
    private final ArrayList<String> categories = new ArrayList<>();
    private final ArrayList<String> warranties = new ArrayList<>();
    private final ArrayList<Double> prices = new ArrayList<>();
    private final ArrayList<Integer> stockLevels = new ArrayList<>();
    private final ArrayList<String> suppliers = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void DisplayMenu() {
        while (true) {
            System.out.println("\nBRIGHT FUTURE TECHNOLOGIES APPLICATION");
            System.out.println("=================================================");
            System.out.println("Please select one of the following menu items:");
            System.out.println("(1) Capture a new product.");
            System.out.println("(2) Search for a product.");
            System.out.println("(3) Update a product.");
            System.out.println("(4) Delete a product.");
            System.out.println("(5) Print report.");
            System.out.println("(6) Exit Application.");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> CaptureProduct();
                case "2" -> SearchProduct();
                case "3" -> UpdateProduct();
                case "4" -> DeleteProduct();
                case "5" -> printReport();
                case "6" -> {
                    ExitApplication(); return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    public void CaptureProduct() {
        System.out.println("\nCAPTURE A NEW PRODUCT");
        System.out.println("*************************");
        System.out.print("Enter the product code: ");
        String code = sc.nextLine();
        System.out.print("Enter the product name: ");
        String name = sc.nextLine();
       
        // Category validation
        String category = "";
        OUTER:
        while (true) {
            System.out.println("Select product category:");
            System.out.println("1. Desktop Computer\n2. Laptop\n3. Tablet\n4. Printer\n5. Gaming Console");
            System.out.print("Category: ");
            String catChoice = sc.nextLine();
            switch (catChoice) {
                case "1" -> {
                    category = "Desktop Computer";
                    break OUTER;
                }
                case "2" -> {
                    category = "Laptop";
                    break OUTER;
                }
                case "3" -> {
                    category = "Tablet";
                    break OUTER;
                }
                case "4" -> {
                    category = "Printer";
                    break OUTER;
                }
                case "5" -> {
                    category = "Gaming Console";
                    break OUTER;
                }
                default -> System.out.println("Incorrect category! Re-enter valid category.");
            }
        }

        // Warranty
        System.out.print("Warranty - Enter 1 for 6 months, any other key for 2 years: ");
        String warChoice = sc.nextLine();
        String warranty = warChoice.equals("1") ? "6 months" : "2 years";

        System.out.print("Enter product price: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.print("Enter stock level: ");
        int stock = Integer.parseInt(sc.nextLine());
        System.out.print("Enter supplier name: ");
        String supplier = sc.nextLine();

        SaveProduct(code, name, category, warranty, price, stock, supplier);
    }

    public void SaveProduct(String code, String name, String cat, String war, double price, int stock, String supplier) {
        productCodes.add(code);
        productNames.add(name);
        categories.add(cat);
        warranties.add(war);
        prices.add(price);
        stockLevels.add(stock);
        suppliers.add(supplier);
        System.out.println("\nProduct details have been successfully saved!");
    }

    public void SearchProduct() {
        System.out.print("\nEnter product code to search: ");
        String code = sc.nextLine();
        int index = productCodes.indexOf(code);
        if (index != -1) {
            System.out.println("\nPRODUCT FOUND!");
            System.out.println("Code: " + productCodes.get(index));
            System.out.println("Name: " + productNames.get(index));
            System.out.println("Category: " + categories.get(index));
            System.out.println("Warranty: " + warranties.get(index));
            System.out.println("Price: R" + prices.get(index));
            System.out.println("Stock: " + stockLevels.get(index));
            System.out.println("Supplier: " + suppliers.get(index));
        } else {
            System.out.println("Product with code " + code + " cannot be located!");
        }
    }

    public void DeleteProduct() {
        System.out.print("\nEnter product code to delete: ");
        String code = sc.nextLine();
        int index = productCodes.indexOf(code);
        if (index != -1) {
            System.out.print("Are you sure you want to delete " + productNames.get(index) + "? (y/n): ");
            String confirm = sc.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                productCodes.remove(index);
                productNames.remove(index);
                categories.remove(index);
                warranties.remove(index);
                prices.remove(index);
                stockLevels.remove(index);
                suppliers.remove(index);
                System.out.println("Product deleted successfully!");
            }
        } else {
            System.out.println("Product cannot be located for deletion!");
        }
    }

    public void UpdateProduct() {
        System.out.print("\nEnter product code to update: ");
        String code = sc.nextLine();
        int index = productCodes.indexOf(code);
        if (index == -1) {
            System.out.println("Product cannot be located!");
            return;
        }
        System.out.println("Update options: 1-Warranty 2-Price 3-Stock");
        System.out.print("Choice: ");
        String choice = sc.nextLine();
        if (!choice.equals("1")) if (choice.equals("2")) {
            System.out.print("Enter new price: ");
            prices.set(index, Double.valueOf(sc.nextLine()));
        } else if (choice.equals("3")) {
            System.out.print("Enter new stock level: ");
            stockLevels.set(index, Integer.valueOf(sc.nextLine()));
        } else {
            System.out.print("Enter new warranty (1 for 6 months, other for 2 years): ");
            String w = sc.nextLine().equals("1") ? "6 months" : "2 years";
            warranties.set(index, w);
        }
        System.out.println("Product updated!");
    }

    public void printReport() {
        System.out.println("\nPRODUCT REPORT - Extreme IT Products");
        System.out.println("=================================================");
        double totalValue = 0;
        for (int i = 0; i < productCodes.size(); i++) {
            // Using ReportData class as required
            ReportData rd = new ReportData(productCodes.get(i), productNames.get(i), categories.get(i), warranties.get(i), prices.get(i), stockLevels.get(i), suppliers.get(i));
            System.out.println((i+1)+". "+rd.getProductCode()+" | "+rd.getProductName()+" | "+rd.getCategory()+" | R"+rd.getPrice()+" x "+rd.getStockLevel()+" = R"+(rd.getPrice()*rd.getStockLevel()));
            totalValue += rd.getPrice() * rd.getStockLevel();
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Total Products: " + productCodes.size());
        System.out.println("Total Stock Value: R" + totalValue);
        if (!productCodes.isEmpty()) System.out.println("Average Value: R" + (totalValue/productCodes.size()));
    }

    public void ExitApplication() {
        System.out.println("Exiting application... Goodbye!");
        System.exit(0);
    }
}
   
