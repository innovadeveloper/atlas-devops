import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class View {
    private List<String> matchClients;
    private boolean recursion;
    private List<Zone> zones;

    @Data
    @NoArgsConstructor
    public static class Zone {
        private String name;
        private ZoneType type;
        private String file;
    }

    public enum ZoneType {
        MASTER
    }
}

// USO DE LA CLASE View de bind9


public class Main {
    public static void main(String[] args) {
        // Configuración interna
        View internalView = new View();
        internalView.setMatchClients(Arrays.asList("localnets", "localhost", "192.168.0.0/24"));
        internalView.setRecursion(true);

        View.Zone internalZone1 = new View.Zone();
        internalZone1.setName("atlas.pe");
        internalZone1.setType(View.ZoneType.MASTER);
        internalZone1.setFile("/etc/bind/zones/internal/db.atlas.pe");

        View.Zone internalZone2 = new View.Zone();
        internalZone2.setName("64.168.192.in-addr.arpa");
        internalZone2.setType(View.ZoneType.MASTER);
        internalZone2.setFile("/etc/bind/zones/internal/db.192.168.64");

        View.Zone internalZone3 = new View.Zone();
        internalZone3.setName("1.168.192.in-addr.arpa");
        internalZone3.setType(View.ZoneType.MASTER);
        internalZone3.setFile("/etc/bind/zones/internal/db.192.168.1");

        internalView.setZones(Arrays.asList(internalZone1, internalZone2, internalZone3));

        // Configuración externa
        View externalView = new View();
        externalView.setMatchClients(Collections.singletonList("any"));
        externalView.setRecursion(false);

        View.Zone externalZone = new View.Zone();
        externalZone.setName("atlas.pe");
        externalZone.setType(View.ZoneType.MASTER);
        externalZone.setFile("/etc/bind/zones/external/db.atlas.pe");

        externalView.setZones(Collections.singletonList(externalZone));

        // Imprimir ambas configuraciones
        System.out.println("Internal View: " + internalView);
        System.out.println("External View: " + externalView);
    }
}