package com.salesforce.dev.framework.soap;

import com.salesforce.dev.framework.utils.Environment;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import org.apache.log4j.Logger;

/**
 * class to connect by API to sales force.
 *
 * @author Veronica Prado
 * @author DanielGonzales
 */
public class APIConnector {

    private static final Logger LOGGER = Logger.getLogger(APIConnector.class.getName());
    private static APIConnector instance;
    private ConnectorConfig config;
    private PartnerConnection connection;
    private String primaryUserName = Environment.getInstance().getPrimaryUser();
    private String primaryUserPasswordToken = Environment.getInstance().getPrimaryUserPasswordToken();
    private String urlApi = Environment.getInstance().getUrlApi();
    private String proxyHost = Environment.getInstance().getProxyHost();
    private String proxyPort = Environment.getInstance().getProxyPort();

    private APIConnector() {
        this.initializer();
    }

    public static APIConnector getInstance() {
        if (instance == null) {
            instance = new APIConnector();
        }
        return instance;
    }

    private void initializer() {
        config = new ConnectorConfig();
        if (!(proxyHost.isEmpty() || (proxyHost.equals(null) && proxyPort.isEmpty()) || proxyPort.equals(null))) {
            config.setUsername(primaryUserName);
            config.setPassword(primaryUserPasswordToken);
            config.setAuthEndpoint(urlApi);
            config.setServiceEndpoint(urlApi);
            config.setProxy(proxyHost, Integer.valueOf(proxyPort));
        } else {
            config.setUsername(primaryUserName);
            config.setPassword(primaryUserPasswordToken);
            config.setAuthEndpoint(urlApi);
            config.setServiceEndpoint(urlApi);
        }
        try {
            connection = com.sforce.soap.partner.Connector.newConnection(config);
        } catch (ConnectionException e) {
            LOGGER.error("Error on Connect to Api :", e);
        }
    }

    public PartnerConnection getConnection() {
        return this.connection;
    }
}
