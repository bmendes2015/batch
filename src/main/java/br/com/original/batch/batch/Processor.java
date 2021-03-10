package br.com.original.batch.batch;

import br.com.original.batch.model.Customer;
import br.com.original.batch.model.Product;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<Line, Line> {

    private Long count = 0L;

    @AfterStep
    public void afterStep(StepExecution stepExecution) {
        System.out.println("Line Total Size Processor: " + this.count);
    }

    @Override
    public Line process(Line line) throws Exception {
        count++;
       return line;
    }

}
