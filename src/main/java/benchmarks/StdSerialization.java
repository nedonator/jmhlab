package benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StdSerialization {
    static final DTO dto = DTO.createDto();
    static volatile Object out;
    static ObjectOutputStream os;
    static {
        try {
            os = new ObjectOutputStream(new ByteArrayOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws RunnerException {

        Options options = new OptionsBuilder()
                .include(StdSerialization.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public void benchmark() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new ByteArrayOutputStream());
        for(int i = 0; i < 1000; i++){
            os.writeObject(dto);
        }
    }
}
