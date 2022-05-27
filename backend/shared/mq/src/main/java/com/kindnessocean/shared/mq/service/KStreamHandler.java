package com.kindnessocean.shared.mq.service;

import com.kindnessocean.shared.model.util.ConverterUtil;
import com.kindnessocean.shared.mq.util.KafkaPropertiesBuilderUtil;
import java.util.AbstractMap;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;

@Slf4j
public abstract class KStreamHandler<K1, V1, K2, V2> implements KStreamExceptionHandler<K1, V1, K2, V2>,
        KStreamMessageHandler<K1, V1, K2, V2>{

    protected final String STREAM_NAME;

    protected final String outputTopic;

    protected KafkaStreams streams;

    protected KStreamHandler(
            final String inputTopic, final String outputTopic,
            final String applicationId, final String bootstrapServer,
            final String streamName, final StreamsBuilderFactoryBean entityEventStreamsBuilder,
            final Class<?> keySerde, final Class<?> valueSerde
    ) throws Exception {
        this.STREAM_NAME = streamName;
        this.outputTopic = outputTopic;

        configureKStream(
                inputTopic,
                applicationId,
                bootstrapServer,
                entityEventStreamsBuilder,
                keySerde,
                valueSerde
        );
    }

    protected void configureKStream(
            final String inputTopic,
            final String applicationId,
            final String bootstrapServer,
            final StreamsBuilderFactoryBean entityEventStreamsBuilder,
            final Class<?> keySerde,
            final Class<?> valueSerde
    ) throws Exception {
        log.info("Configure " + STREAM_NAME + " kafka Stream");
        StreamsBuilder streamsBuilder = entityEventStreamsBuilder.getObject();

        KStream<K1, V1> kStream =
                streamsBuilder.stream(inputTopic);

        handle(kStream);

        Topology topology = streamsBuilder.build();

        Properties properties = ConverterUtil
                .convertMapToProperties(
                        KafkaPropertiesBuilderUtil.buildKafkaStreamProperties(
                                keySerde,
                                valueSerde,
                                applicationId,
                                bootstrapServer)
                );

        streams = new KafkaStreams(topology, properties);
    }

    public void startStream() throws Exception {
        log.info(STREAM_NAME + " Kafka Streams starting...");
        streams.start();
    }

    public KStream<K1, V1> handle(KStream<K1, V1> kStream){
        kStream
                .peek(((key, value) -> log.info("Received record {} {}", key, value)))
                .map(this::handle)
                .peek(((key, value) -> log.info("Pushing to topic {} {} {}", outputTopic, key, value)))
                .to(outputTopic);

        return kStream;
    }

    private KeyValue<K2, V2> handle(K1 key, V1 value){
        AbstractMap.SimpleEntry<K2, V2> result;

        try {
            result = handleMessage(key, value);
        } catch (Exception e){
            result = handleException(key, value, e);
        }

        return new KeyValue<>(result.getKey(), result.getValue());
    }
}
