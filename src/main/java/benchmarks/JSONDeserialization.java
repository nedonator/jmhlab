package benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class JSONDeserialization {
    static byte[] data;

    static {
        try {
            data = Files.readAllBytes(Paths.get("dto.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static volatile Object out;

    public static void main(String[] args) throws IOException, RunnerException {
        Options options = new OptionsBuilder()
                .include(JSONDeserialization.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public void benchmark() throws IOException {
        out = DTO.mapper.readValue(data, DTO.class);
    }
}
