package krissy.fmi.string.search.project;


import java.io.File;
import java.util.concurrent.TimeUnit;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.*;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class MyBenchmark {
	Searcher s;
	int p, c;
	public void measureRight() {
		s = new Searcher(new File("D:\\FMIJAVA").toPath(), "Ганьо");
		s.perform(1,1);
    }
	public void reps(int p, int c) {
		s = new Searcher(new File("D:\\FMIJAVA").toPath(), "Ганьо");
		s.perform(p,c);
	}
	@Benchmark
	public void measureWrong_2() {
		 reps(2,1);
	}
	@Benchmark
	public void measureWrong_10() {
		 reps(1,1);
	}
	@Benchmark
	public void measureWrong_5() {
		 reps(3,1);
	}
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(MyBenchmark.class.getSimpleName())
				.warmupIterations(10).measurementIterations(5).forks(1).build();
		new Runner(opt).run();
	}
}
