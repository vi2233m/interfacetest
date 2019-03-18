package config;

public class WeworkConfig {

//    public String agentId = "1000005";
//    public String secret = "1JPyY9GvPLZfpvxEDjok-Xt_9v7HIBYJhZUoO6EgNGY";
//    public String corpid = "wwd6da61649bd66fea";
//    public String contactSecret="C7uGOrNyxWWzwBsUyWEbLQdOqoWPz4hNvxj9RIFv-4U";

    public String agentId = "1000002";
    public String secret = "KBrXPg7aKsvkLghfA4sHYgUVHfbXXIrPdwvzpfSnM7A";
    public String corpid = "ww262a519c6c958fa1";
    public String contactSecret="g4CGCLXvjvDCe5r4rNXrI1MP5OfRayw8zVglcSXfoJQ";



    private static WeworkConfig weworkConfig;
    public static WeworkConfig getInstance(){
        if (weworkConfig == null){
            weworkConfig = new WeworkConfig();
        }
        return weworkConfig;
    }

    public static void load(String path){
        //todo: read from yaml or json
    }


}
