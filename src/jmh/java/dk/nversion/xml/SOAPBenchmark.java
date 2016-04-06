package dk.nversion.xml;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;

public class SOAPBenchmark {
    @Benchmark
    public void soapRequestXMLStream() throws XMLStreamException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        outputFactory.setProperty("javax.xml.stream.isRepairingNamespaces", false);
        XMLStreamWriter xmlWriter = outputFactory.createXMLStreamWriter(outputStream, "UTF-8");
        xmlWriter.writeStartDocument("1.0");
        xmlWriter.writeCharacters("\n");
        xmlWriter.writeStartElement("soapenv", "Envelope", "http://schemas.xmlsoap.org/soap/envelope/");
        xmlWriter.writeNamespace("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
        xmlWriter.writeNamespace("stoc", "http://example.com/stockquote.xsd");
        xmlWriter.writeStartElement("soapenv", "Header", "http://schemas.xmlsoap.org/soap/envelope/");
        xmlWriter.writeEndElement();
        xmlWriter.writeStartElement("soapenv", "Body", "http://schemas.xmlsoap.org/soap/envelope/");
        xmlWriter.writeStartElement("stoc", "TradePriceRequest", "http://example.com/stockquote.xsd");
        xmlWriter.writeEndElement();
        xmlWriter.writeEndElement();
        xmlWriter.writeEndDocument();
        xmlWriter.flush();
        byte[] result = outputStream.toByteArray();

    }

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(SOAPBenchmark.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(10)
                .forks(2)
                .build();
        new Runner(options).run();
    }
}
