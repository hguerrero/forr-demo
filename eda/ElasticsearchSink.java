// camel-k: language=java dependency=mvn:org.apache.kafka:kafka-clients:2.7.0

import org.apache.camel.builder.RouteBuilder;
/**
 * bridge
 */
public class ElasticsearchSink extends RouteBuilder {
        @Override
        public void configure() throws Exception {
            from("kafka:dbserver-mysql.inventory.orders?brokers=broker-kafka-bootstrap.kafka.svc:9092")
                .to("log:info")
                .to("elasticsearch-rest:elasticsearch?hostAddresses=elasticsearch-es-default:9200&operation=INDEX&indexName=orders&enableSSL=false");
        }
}