package benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;

public class JSONSerialization {
    static final DTO dto = DTO.createDto();
    static volatile Object out;


    public static void main(String[] args) throws RunnerException {

        Options options = new OptionsBuilder()
                .include(JSONSerialization.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public void benchmark() throws IOException {
        out = DTO.mapper.writeValueAsString(dto);
    }
}
