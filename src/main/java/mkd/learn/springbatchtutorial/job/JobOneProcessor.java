package mkd.learn.springbatchtutorial.job;

import org.springframework.batch.item.ItemProcessor;

public class JobOneProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String item) throws Exception {
        // Process the item (e.g., convert to uppercase)
        System.out.println(">>>> JobOne process " + item);
        return item.toUpperCase();
    }
}
