package edu.sjsu.cab.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import edu.sjsu.cab.util.CabDbConstants;

public class CabDBConfiguration {

    private static String jdbcDriver;
    private static String localDbUrl;
    private static String localDbUser;
    private static String localDbPass;

    public static void Initialize() throws CabDatabaseException {

        Properties props = null;

        try {
            props = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream(CabDbConstants.DB_PROPERTIES_FILE);
            if (stream != null) {

                props.load(stream);

            } else {
                throw new CabDatabaseException("Cannot find " + CabDbConstants.DB_PROPERTIES_FILE + " file.", new FileNotFoundException());
            }
        } catch (IOException e) {
            throw new CabDatabaseException("Cannot load from " + CabDbConstants.DB_PROPERTIES_FILE + " file.", e);
        }

        CabDBConfiguration.setJdbcDriver(props.getProperty(CabDbConstants.JDBC_DRIVER));

        CabDBConfiguration.setLocalDbUrl(props.getProperty(CabDbConstants.LOCAL_DB_URL));
        CabDBConfiguration.setLocalDbUser(props.getProperty(CabDbConstants.LOCAL_DB_USER));
        CabDBConfiguration.setLocalDbPass(props.getProperty(CabDbConstants.LOCAL_DB_PASS));
    }

    public static String getJdbcDriver() {
        return jdbcDriver;
    }

    public static void setJdbcDriver(String jdbcDriver) {
        CabDBConfiguration.jdbcDriver = jdbcDriver;
    }

    public static String getLocalDbUrl() {
        return localDbUrl;
    }

    public static void setLocalDbUrl(String localDbUrl) {
        CabDBConfiguration.localDbUrl = localDbUrl;
    }

    public static String getLocalDbUser() {
        return localDbUser;
    }

    public static void setLocalDbUser(String localDbUser) {
        CabDBConfiguration.localDbUser = localDbUser;
    }

    public static String getLocalDbPass() {
        return localDbPass;
    }

    public static void setLocalDbPass(String localDbPass) {
        CabDBConfiguration.localDbPass = localDbPass;
    }
}
