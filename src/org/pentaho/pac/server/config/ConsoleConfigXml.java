package org.pentaho.pac.server.config;

import java.io.File;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.pentaho.pac.common.config.IConsoleConfig;
import org.pentaho.platform.util.xml.dom4j.XmlDom4JHelper;

public class ConsoleConfigXml implements IConsoleConfig {

  protected static final String ROOT_ELEMENT = "console";
  protected static final String CHECK_PERIOD_XPATH = ROOT_ELEMENT +"/biserver-status-check-period-millis";
  protected static final String PLATFORM_USERNAME_XPATH = ROOT_ELEMENT +"/platform-username";
  protected static final String SOLUTION_PATH_XPATH = ROOT_ELEMENT +"/solution-path";
  protected static final String WAR_PATH_XPATH = ROOT_ELEMENT +"/war-path";
  protected static final String TEMP_DIR_PATH_XPATH = ROOT_ELEMENT +"/temp-directory";
  protected static final String BACKUP_DIR_PATH_XPATH = ROOT_ELEMENT +"/backup-directory";
  protected static final String HELP_URL_XPATH = ROOT_ELEMENT +"/help_url";
  protected static final String JDBC_DRIVERS_XPATH = ROOT_ELEMENT +"/jdbc-drivers-path";
  protected static final String DEFAULT_ROLES_XPATH = ROOT_ELEMENT +"/default-roles";
  protected static final String HOME_PAGE_TIMEOUT_XPATH = ROOT_ELEMENT +"/homepage-timeout-millis";
  protected static final String HOME_PAGE_URL_XPATH = ROOT_ELEMENT +"/homepage-url";
  
  Document document;
  
  public ConsoleConfigXml(File consoleConfigXmlFile) throws IOException, DocumentException{
    this(XmlDom4JHelper.getDocFromFile(consoleConfigXmlFile, null));    
  }
  
  public ConsoleConfigXml(String xml) throws DocumentException {
    this(DocumentHelper.parseText(xml));
  }
  
  public ConsoleConfigXml(Document doc) throws DocumentException {
    Element rootElement = doc.getRootElement();
    if ((rootElement != null) &&  !doc.getRootElement().getName().equals(ROOT_ELEMENT)) {
      throw new DocumentException("Invalid root element.");
    }
    document = doc;
  }
  
  public ConsoleConfigXml() {
    document = DocumentHelper.createDocument();
    document.addElement(ROOT_ELEMENT);
  }
  
  public Long getServerStatusCheckPeriod() {
    Long period = null;
    try {
      period = Long.parseLong(getValue(CHECK_PERIOD_XPATH));
    } catch (Exception ex) {
    }
    return period;
  }

  public void setServerStatusCheckPeriod(Long biServerStatusCheckPeriod) {
    String period = biServerStatusCheckPeriod != null ? Long.toString(biServerStatusCheckPeriod) : "";
    setValue(CHECK_PERIOD_XPATH, period);
  }

  public Integer getHomePageTimeout() {
    Integer timeout= null;
    try {
      timeout = Integer.parseInt(getValue(HOME_PAGE_TIMEOUT_XPATH));
    } catch (Exception ex) {
    }
    return timeout;
  }

  public void setHomePageTimeout(Integer timeout) {
    setValue(HOME_PAGE_TIMEOUT_XPATH, timeout != null ? Integer.toString(timeout) : "");
  }
  
  public String getHomePageUrl() {
    return getValue(HOME_PAGE_URL_XPATH);
  }

  public void setHomePageUrl(String url) {
    setValue(HOME_PAGE_URL_XPATH, url);
  }

  public String getHelpUrl() {
    return getValue(HELP_URL_XPATH);
  }

  public void setHelpUrl(String url) {
    setValue(HELP_URL_XPATH, url);
  }

  public String getJdbcDriversClassPath() {
    return getValue(JDBC_DRIVERS_XPATH);
  }

  public void setJdbcDriversClassPath(String classpath) {
    setValue(JDBC_DRIVERS_XPATH, classpath);
  }

  public String getDefaultRoles() {
    return getValue(DEFAULT_ROLES_XPATH);
  }

  public void setDefaultRoles(String defaultRoles) {
    setValue(DEFAULT_ROLES_XPATH, defaultRoles);
  }

  public String getPlatformUserName() {
    return getValue(PLATFORM_USERNAME_XPATH);
  }

  public void setPlatformUserName(String platformUserName) {
    setValue(PLATFORM_USERNAME_XPATH, platformUserName);
  }

  public String getSolutionPath() {
    return getValue(SOLUTION_PATH_XPATH);
  }

  public void setSolutionPath(String solutionPath) {
    setValue(SOLUTION_PATH_XPATH, solutionPath);
  }

  public String getWebAppPath() {
    return getValue(WAR_PATH_XPATH);
  }

  public void setWebAppPath(String warPath) {
    setValue(WAR_PATH_XPATH, warPath);
  }

  public String getTempDirectory() {
    return getValue(TEMP_DIR_PATH_XPATH);
  }

  public void setTempDirectory(String path) {
    setValue(TEMP_DIR_PATH_XPATH, path);
  }

  public String getBackupDirectory() {
    return getValue(BACKUP_DIR_PATH_XPATH);
  }

  public void setBackupDirectory(String path) {
    setValue(BACKUP_DIR_PATH_XPATH, path);
  }
  

  public void setValue(String xPath, String value) {
    Element element = (Element) document.selectSingleNode( xPath );
    if (element == null) {
      element = DocumentHelper.makeElement(document, xPath);
    }
    element.setText(value);
  }

  public String getValue(String xpath) {
    String value = null;
    Element element = (Element)document.selectSingleNode(xpath);
    return element != null ? element.getText() : null;
  }
  
  public Document getDocument() {
    return document;
  }
}