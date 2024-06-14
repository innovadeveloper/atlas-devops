import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class BindOptions {
    private String directory;
    private List<String> listenOn;
    private List<String> allowQuery;
    private List<String> forwarders;
    private boolean dnssecValidation;
    //private List<String> listenOnV6; // Comentado para corresponder con la configuración comentada
}


// named.conf.options
// USO DE LA CLASE BINDOPTIONS
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BindOptions options = new BindOptions();
        options.setDirectory("/var/cache/bind");
        options.setListenOn(Arrays.asList("any"));
        options.setAllowQuery(Arrays.asList("any"));
        options.setForwarders(Arrays.asList("8.8.8.8", "8.8.4.4"));
        options.setDnssecValidation(false);
        // options.setListenOnV6(Arrays.asList("any")); // Comentado para corresponder con la configuración comentada

        System.out.println(options);
    }
}
