package net.baydan.whsmgc.config;

public class MagicAndThingsConfig {
    public interface ConfigAccess {

    }

    public interface ClientConfigAccess {
        int manaBarHeight();
    }

    private static ConfigAccess config = null;
    private static ClientConfigAccess clientConfig = null;

    public static ConfigAccess common() {
        return config;
    }

    public static ClientConfigAccess client () {
        return clientConfig;
    }
}
