# XMLSerializationBenchmark
JMH Project to test different Java XML Serialization benchmarks 

# Setup IDE files

    gradlew eclipse
    gradlew idea

## Run Benchmark

### with Gradle

    gradlew jmh

### without Gradle

    gradlew jmhjar
    java -jar build/libs/jmh-gradle-ide-jmh.jar