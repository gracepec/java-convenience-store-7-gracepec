package store.service;

import store.controller.StoreController;
import store.model.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StoreService {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void loadProductsFromResourceFile(String fileName) {
        try (
                InputStream inputStream = StoreController.class.getClassLoader().getResourceAsStream(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            this.products = readFileLines(reader);
        } catch (IOException e) {
            throw new IllegalStateException("리소스 파일을 읽는 중 오류 발생");
        }
    }

    private List<Product> readFileLines(BufferedReader reader) {
        List<Product> products = new ArrayList<>();
        try {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                products.add(parseLineToProduct(line));
            }
        } catch (IOException e) {
            throw new IllegalStateException("파일 읽기 중 오류 발생");
        }
        return products;
    }

    private Product parseLineToProduct(String line) {
        String[] parts = line.split(",");

        String name = parts[0];
        int price = Integer.parseInt(parts[1]);
        int quantity = Integer.parseInt(parts[2]);
        String promotion = parts[3];

        return new Product(name, price, quantity, promotion);
    }
}
