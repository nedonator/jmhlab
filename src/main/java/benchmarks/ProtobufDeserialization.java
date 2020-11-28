package benchmarks;

import com.google.protobuf.InvalidProtocolBufferException;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class ProtobufDeserialization {
    static byte[] data = ProtobufSerialization.dto.toByteArray();
    static volatile Object out;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(ProtobufDeserialization.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public void benchmark() throws InvalidProtocolBufferException {
        out = DtoProtos.DTO.parseFrom(data);
    }
}