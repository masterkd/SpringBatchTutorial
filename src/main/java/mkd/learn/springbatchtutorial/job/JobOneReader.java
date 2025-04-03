package mkd.learn.springbatchtutorial.job;

import org.springframework.batch.item.ItemReader;

public class JobOneReader implements ItemReader<String> {

    private final String[] data = {"item1", "item2", "item3"};
    private int index = 0;

    @Override
    public String read() throws Exception {
        // Simulate reading data from a source
        if (index < data.length) {
            System.out.println(">>>> JobOne read " + data[index]);
            return data[index++];
        } else {
            return null; // No more items to read
        }
    }
}
