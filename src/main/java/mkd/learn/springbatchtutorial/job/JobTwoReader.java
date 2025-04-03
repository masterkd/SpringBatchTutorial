package mkd.learn.springbatchtutorial.job;

import org.springframework.batch.item.ItemReader;

public class JobTwoReader implements ItemReader<String> {

    private final String[] data = {"Lion", "Tiger", "Elephant", "Giraffe", "Zebra", "Hippo"};
    private int index = 0;

    @Override
    public String read() throws Exception {
        // Simulate reading data from a source
        if (index < data.length) {
            System.out.println(">>>> JobTwo read " + data[index]);
            return data[index++];
        } else {
            return null; // No more items to read
        }
    }
}
