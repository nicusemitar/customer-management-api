package com.rsystems.customer.management.repo;

import com.rsystems.customer.management.entity.Customer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Repository implementation for managing customer data in a file-based storage.
 */
@Repository
public class FileBaseRepository implements GenericApi<Customer, Long> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Value("${customer.file.path}")
    private String filePath;
    private final Map<Long, Customer> customerMap = new LinkedHashMap<>();
    private final ReentrantReadWriteLock readWriteLockLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLockLock.readLock();
    private final Lock writeLock = readWriteLockLock.writeLock();

    /**
     * Initializes the repository by validating and loading the customer file.
     */
    @PostConstruct
    public void init() {
        logger.info("Validating the file");
        try {
            if (!Files.exists(Paths.get(filePath))) {
                logger.info("File is missing, starting to create a new one");
                Files.createFile(Paths.get(filePath));
            } else {
                logger.info("Loading existing file");
                loadCustomers();
            }
        } catch (IOException e) {
            logger.error("Error accessing the file: {}", e.getMessage(), e);
        }
    }

    /**
     * Loads customers from the file into the customer map.
     */
    private void loadCustomers() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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
            logger.error("Error reading the file: {}", e.getMessage(), e);
        }
    }

    /**
     * Saves all customers to the file.
     *
     * @param filePath the path of the file to save customers to
     */
    private void saveCustomers(String filePath) {
        writeLock.lock();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Customer customer : customerMap.values()) {
                bw.write(customer.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            logger.error("Error writing to the file: {}", e.getMessage(), e);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Retrieves all customers.
     *
     * @return a list of all customers
     */
    @Override
    public List<Customer> findAll() {
        readLock.lock();
        try {
            return new ArrayList<>(customerMap.values());
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer to retrieve
     * @return an Optional containing the customer if found, or empty if not found
     */
    @Override
    public Optional<Customer> findById(Long id) {
        readLock.lock();
        try {
            return Optional.ofNullable(customerMap.get(id));
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Saves a customer.
     *
     * @param customer the customer to save
     */
    @Override
    public void save(Customer customer) {
        writeLock.lock();
        try {
            customerMap.put(customer.getId(), customer);
            saveCustomers(filePath);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Deletes a customer by their ID.
     *
     * @param id the ID of the customer to delete
     */
    @Override
    public void deleteById(Long id) {
        writeLock.lock();
        try {
            customerMap.remove(id);
            saveCustomers(filePath);
        } finally {
            writeLock.unlock();
        }
    }
}
