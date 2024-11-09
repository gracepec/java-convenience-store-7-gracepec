package store.service;

import store.controller.StoreController;
import store.model.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class StoreService {
    private final List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void loadProductsFromFile(String fileName) {
        try (
                InputStream inputStream = StoreController.class.getClassLoader().getResourceAsStream(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            readFileLines(reader);

            addZeroStockProduct();
        } catch (IOException e) {
            throw new IllegalStateException("파일 읽기 중 오류 발생");
        }
    }

    private void readFileLines(BufferedReader reader) {
        try {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                products.add(parseLineToProduct(line));
            }
        } catch (IOException e) {
            throw new IllegalStateException("파일 읽기 중 오류 발생");
        }
    }

    private Product parseLineToProduct(String line) {
        String[] parts = line.split(",");

        String name = parts[0];
        int price = Integer.parseInt(parts[1]);
        int quantity = Integer.parseInt(parts[2]);
        String promotion = parts[3];

        return new Product(name, price, quantity, promotion);
    }

    private void addZeroStockProduct() {
        List<Product> promotionProduct = products.stream()
                .filter(product -> !product.getPromotion().equals("null"))
                .toList();

        for (Product product : promotionProduct) {
            if (!existsStock(product)) {
                insertZeroStockProduct(product);
            }
        }
    }

    private boolean existsStock(Product product) {
        return products.stream()
                .filter(existingProduct -> existingProduct != product)
                .anyMatch(existingProduct -> existingProduct.getName().equals(product.getName()));
    }

    private void insertZeroStockProduct(Product product) {
        Product zeroStockProduct = new Product(product.getName(), product.getPrice(), 0, "null");

        int indexToInsert = findInsertIndex(product.getName());

        if (indexToInsert >= 0) {
            products.add(indexToInsert + 1, zeroStockProduct);
        }
    }

    private int findInsertIndex(String productName) {
        return IntStream.range(0, products.size())
                .filter(i -> products.get(i).getName().equals(productName))
                .findFirst()
                .orElse(-1);
    }
}
