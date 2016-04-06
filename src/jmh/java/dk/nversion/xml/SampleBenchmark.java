package dk.nversion.xml;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.nio.charset.StandardCharsets;

public class SampleBenchmark {

    @Benchmark
    public void doASCIIEncoding() {
        String sample = "The quick brown fox jumps over the lazy dog";
        byte[] bytes = sample.getBytes(StandardCharsets.US_ASCII);
    }

    @Benchmark
    public void doUTF8Encoding() {
        String sample = "The quick brown fox jumps over the lazy dog";
        byte[] bytes = sample.getBytes(StandardCharsets.UTF_8);
    }

   public static void main(String[] args) throws Exception {
		Options options = new OptionsBuilder()
				.include(SampleBenchmark.class.getSimpleName())
				.warmupIterations(5)
				.measurementIterations(10)
				.forks(2)
				.build();
		new Runner(options).run();
	}
}
