package com.rsystems.customer.management.repo;

import com.rsystems.customer.management.api.GenericApi;
import com.rsystems.customer.management.entity.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Repository
public class FileBaseRepository implements GenericApi<Customer, Long> {
    private static final String FILE_PATH = "src/main/resources/customers.txt";
    private final Map<Long, Customer> customerMap = new HashMap<>();
    private final ReentrantReadWriteLock readWriteLockLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLockLock.readLock();
    private final Lock writeLock = readWriteLockLock.writeLock();

    @PostConstruct
    public void init() {
        try {
            if (!Files.exists(Paths.get(FILE_PATH))) {
                Files.createFile(Paths.get(FILE_PATH));
            } else {
                loadCustomers();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomers() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Long id = Long.parseLong(parts[0]);
                    Customer customer = new Customer(id, parts[1], parts[2]);
                    customerMap.put(id, customer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCustomers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Customer customer : customerMap.values()) {
                bw.write(customer.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> findAll() {
        readLock.lock();
        try {
            return new ArrayList<>(customerMap.values());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Optional<Customer> findById(Long id) {
        readLock.lock();
        try {
            return Optional.ofNullable(customerMap.get(id));
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void save(Customer customer) {
        writeLock.lock();
        try {
            customerMap.put(customer.getId(), customer);
            saveCustomers();
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void update(Long id, Customer customer) {
        writeLock.lock();
        try {
            if (customerMap.containsKey(id)) {
                customerMap.put(id, customer);
                saveCustomers();
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void deleteById(Long id) {
        writeLock.lock();
        try {
            customerMap.remove(id);
            saveCustomers();
        } finally {
            writeLock.unlock();
        }
    }
}
