package mkd.learn.springbatchtutorial.job;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class JobTwoWriter implements ItemWriter<String> {

    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {
        // Simulate writing data to a destination
        System.out.println(">>>> JobTwo write");
        for (String item : chunk) {
            System.out.println("Writing item: " + item);
        }
    }
}
