package org.acme;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;

public class Routes extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        CsvDataFormat csv = new CsvDataFormat("|");
        csv.setUseMaps("true");
        
        from("sftp://sftpgo-sftp.sftp.svc:2022?username=demo&password=password&fileName=data.csv&useUserKnownHostsFile=false&delay=3600")
            .unmarshal(csv)
            .split(simple("${body}"))
                .setHeader("record", simple("${body}"))
                .setHeader("fullname", simple("${body['NAME']}"))
                .to("log:logger")
                .to("sql:SELECT id FROM customers WHERE CONCAT(first_name,' ',last_name) = :#fullname")
                .to("sql:INSERT INTO addresses (street,city,state,zip,type,customer_id) VALUES (:#${headers.record['B_STREET']},:#${headers.record['U_CITY']},:#${headers.record['REGION_STATE']},:#${headers.record['POST_CODE']},:#${headers.record['TYPE']},:#${body[0]['id']})")
                .to("log:logger")
                ;
    }
    
}
