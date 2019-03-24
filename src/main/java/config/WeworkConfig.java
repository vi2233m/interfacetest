package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;

public class WeworkConfig {

    public String agentId;
    public String secret;
    public String corpid;
    public String contactSecret;

//    public String agentId = "1000002";
//    public String secret = "KBrXPg7aKsvkLghfA4sHYgUVHfbXXIrPdwvzpfSnM7A";
//    public String corpid = "ww262a519c6c958fa1";
//    public String contactSecret="g4CGCLXvjvDCe5r4rNXrI1MP5OfRayw8zVglcSXfoJQ";



    private static WeworkConfig weworkConfig;
    public static WeworkConfig getInstance(){
        if (weworkConfig == null){
            weworkConfig=load("/conf/WeworkConfig.yaml");
            System.out.println(weworkConfig);
            System.out.println(weworkConfig.agentId);
        }
        return weworkConfig;
    }

    public static WeworkConfig load(String path){
        //todo: read from yaml or json

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(WeworkConfig.class.getResourceAsStream(path), WeworkConfig.class);
            //System.out.println(mapper.writeValueAsString(WeworkConfig.getInstance()));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
