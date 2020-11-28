package benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StdDeserialization {
    static byte[] data;
    static{
        ByteArrayOutputStream os = new ByteArrayOutputStream(102);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(os);
            DTO dto = DTO.createDto();
            for(int i = 0; i < 1000; i++) {
                oos.writeObject(dto);
            }
            oos.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        data = os.toByteArray();
    }

    static volatile Object out;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(StdDeserialization.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();
        new Runner(options).run();

    }

    @Benchmark
    public void benchmark() throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bis);
        for(int i = 0; i < 1000; i++) {
            out = ois.readObject();
        }
        bis.close();
        ois.close();
    }
}
