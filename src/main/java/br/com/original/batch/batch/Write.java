package br.com.original.batch.batch;

import br.com.original.batch.model.Customer;
import br.com.original.batch.model.Product;
import br.com.original.batch.repository.CustomerRepository;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Write implements ItemWriter<Line> {

    @Autowired
    private CustomerRepository customerRepository;

    private Map<String,Customer> map;

    private Long count = 0L;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        map = new HashMap<String, Customer>();
    }

    @AfterStep
    public void afterStep(StepExecution stepExecution) {
        System.out.println("Line Total Size: " + this.count);
    }

    @Override
    public void write(List<? extends Line> lines) throws Exception {
        this.count = count + lines.size();

        for(Line line : lines){
            Customer customer = map.get(line.getCpf());
            if(customer != null){
                customer.getProducts().add(new Product(customer, line.getCode(),line.getProduct(),
                        Long.parseLong(line.getDays()), new BigDecimal(line.getValue())));
                map.put(customer.getCpf(),customer);
            }else{
                customer = new Customer(line.getName(),line.getCpf(), line.getCode(),line.getProduct(),
                        line.getDays(), line.getValue());
                map.put(customer.getCpf(),customer);
            }
        }
        System.out.println("Map Data Saved for Customers: " + map.values().size());

        customerRepository.saveAll(map.values());
    }
}
