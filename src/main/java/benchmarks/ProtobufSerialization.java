package benchmarks;

import com.googlecode.protobuf.format.XmlFormat;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;

public class ProtobufSerialization {
    static final DtoProtos.DTO dto = DtoProtos.DTO.newBuilder()
            .setId(228)
            .setName("pavlik")
            .setIsLenin(false)
            .addAllHobbies(List.of("video", "substances"))
            .build();

    static volatile Object out;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(ProtobufSerialization.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public void benchmark(){
        out = XmlFormat.printToString(dto);
    }
}
