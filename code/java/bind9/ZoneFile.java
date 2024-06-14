import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class ZoneFile {
    private String ttl;
    private SoaRecord soa;
    private List<DnsRecord> records;

    @Data
    @NoArgsConstructor
    public static class SoaRecord {
        private String name;
        private String primaryNS;
        private String adminEmail;
        private int serial;
        private String refresh;
        private String retry;
        private String expire;
        private String negativeCacheTTL;
    }

    @Data
    @NoArgsConstructor
    public static class DnsRecord {
        private String name;
        private String type;
        private String value;
    }
}

// zones/internal/db.atlas.pe
// USO DE LA CLASE ZoneFile de bind9
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ZoneFile zoneFile = new ZoneFile();
        zoneFile.setTtl("1D");

        ZoneFile.SoaRecord soaRecord = new ZoneFile.SoaRecord();
        soaRecord.setName("@");
        soaRecord.setPrimaryNS("ns1.atlas.pe.");
        soaRecord.setAdminEmail("admin.atlas.pe.");
        soaRecord.setSerial(3);
        soaRecord.setRefresh("12h");
        soaRecord.setRetry("15m");
        soaRecord.setExpire("3w");
        soaRecord.setNegativeCacheTTL("2h");

        zoneFile.setSoa(soaRecord);

        ZoneFile.DnsRecord nsRecord = new ZoneFile.DnsRecord();
        nsRecord.setName("@");
        nsRecord.setType("NS");
        nsRecord.setValue("ns1.atlas.pe.");

        ZoneFile.DnsRecord ns1Record = new ZoneFile.DnsRecord();
        ns1Record.setName("ns1");
        ns1Record.setType("A");
        ns1Record.setValue("192.168.64.3");

        ZoneFile.DnsRecord wwwRecord = new ZoneFile.DnsRecord();
        wwwRecord.setName("www");
        wwwRecord.setType("A");
        wwwRecord.setValue("192.168.64.3");

        ZoneFile.DnsRecord sistemasRecord = new ZoneFile.DnsRecord();
        sistemasRecord.setName("sistemas");
        sistemasRecord.setType("A");
        sistemasRecord.setValue("192.168.1.99");

        ZoneFile.DnsRecord ventasRecord = new ZoneFile.DnsRecord();
        ventasRecord.setName("ventas");
        ventasRecord.setType("A");
        ventasRecord.setValue("192.168.1.4");

        zoneFile.setRecords(Arrays.asList(nsRecord, ns1Record, wwwRecord, sistemasRecord, ventasRecord));

        System.out.println(zoneFile);
    }
}

// zones/internal/db.192.168.1
// segunda invocaciòn del ns interno (zona inversa) , se utiliza el registro PTR
// A diferencia de los registros A, que mappean nombres de dominio a direcciones IP,
// los registros PTR mapean direcciones IP a nombres de dominio
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ZoneFile zoneFile = new ZoneFile();
        zoneFile.setTtl("1D");

        ZoneFile.SoaRecord soaRecord = new ZoneFile.SoaRecord();
        soaRecord.setName("@");
        soaRecord.setPrimaryNS("ns1.atlas.pe.");
        soaRecord.setAdminEmail("admin.atlas.pe.");
        soaRecord.setSerial(3);
        soaRecord.setRefresh("12h");
        soaRecord.setRetry("15m");
        soaRecord.setExpire("3w");
        soaRecord.setNegativeCacheTTL("2h");

        zoneFile.setSoa(soaRecord);

        ZoneFile.DnsRecord nsRecord = new ZoneFile.DnsRecord();
        nsRecord.setName("@");
        nsRecord.setType("NS");
        nsRecord.setValue("ns1.atlas.pe.");

        ZoneFile.DnsRecord ptrRecord1 = new ZoneFile.DnsRecord();
        ptrRecord1.setName("99");
        ptrRecord1.setType("PTR");
        ptrRecord1.setValue("sistemas.atlas.pe.");

        ZoneFile.DnsRecord ptrRecord2 = new ZoneFile.DnsRecord();
        ptrRecord2.setName("4");
        ptrRecord2.setType("PTR");
        ptrRecord2.setValue("ventas.atlas.pe.");

        zoneFile.setRecords(Arrays.asList(nsRecord, ptrRecord1, ptrRecord2));

        System.out.println(zoneFile);
    }
}


