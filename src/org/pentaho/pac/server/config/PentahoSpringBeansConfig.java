package org.pentaho.pac.server.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.pentaho.platform.util.xml.dom4j.XmlDom4JHelper;

public class PentahoSpringBeansConfig {

  public enum AuthenticationType {
    MEMORY_BASED_AUTHENTICATION, LDAP_BASED_AUTHENTICATION, DB_BASED_AUTHENTICATION
  };
  
  private static final String ROOT_ELEMENT = "beans";
  private static final String IMPORT_ELEMENT = "import";
  private static final String RESOURCE_XPATH = ROOT_ELEMENT + "/" + IMPORT_ELEMENT;
  private static final String RESOURCE_ATTR_NAME = "resource";
  
  private static final String ACEGI_SECURITY_LDAP_CONFIG_FILE = "applicationContext-acegi-security-ldap.xml";
  private static final String ACEGI_SECURITY_DB_CONFIG_FILE = "applicationContext-acegi-security-hibernate.xml";
  private static final String ACEGI_SECURITY_MEMORY_CONFIG_FILE = "applicationContext-acegi-security-memory.xml";
  private static final String PENTAHO_SECURITY_LDAP_CONFIG_FILE = "applicationContext-pentaho-security-ldap.xml";
  private static final String PENTAHO_SECURITY_DB_CONFIG_FILE = "applicationContext-pentaho-security-hibernate.xml";
  private static final String PENTAHO_SECURITY_MEMORY_CONFIG_FILE = "applicationContext-pentaho-security-memory.xml";
  private static final String LDAP_PLACEHOLDER_CONFIG_FILE = "applicationContext-placeholder.xml";
  
  Document document;
  
  public PentahoSpringBeansConfig(File pentahoXmlFile) throws IOException, DocumentException{
    this(XmlDom4JHelper.getDocFromFile(pentahoXmlFile, null));    
  }
  
  public PentahoSpringBeansConfig(String xml) throws DocumentException {
    this(DocumentHelper.parseText(xml));
  }
  
  public PentahoSpringBeansConfig(Document doc) throws DocumentException {
    Element rootElement = doc.getRootElement();
    if ((rootElement != null) &&  !doc.getRootElement().getName().equals(ROOT_ELEMENT)) {
      throw new DocumentException("Invalid root element.");
    }
    document = doc;
  }
  
  public PentahoSpringBeansConfig() {
    document = DocumentHelper.createDocument();
    document.addElement(ROOT_ELEMENT);
  }
  
  public String[] getSystemConfigFileNames() {
    ArrayList<String> fileNames = new ArrayList<String>();
    List nodes = document.selectNodes(RESOURCE_XPATH);
    for (Iterator iter = nodes.iterator(); iter.hasNext(); ) {
      Element element = (Element)iter.next();
      fileNames.add(element.attributeValue(RESOURCE_ATTR_NAME));
    }
    return fileNames.toArray(new String[0]);
  }
  
  public void setSystemConfigFileNames(String[] fileNames) {
    List nodes = document.selectNodes(RESOURCE_XPATH);
    for (Iterator iter = nodes.iterator(); iter.hasNext(); ) {
      ((Element)iter.next()).detach();
    }
    for (int i = 0; i < fileNames.length; i++) {
      document.getRootElement().addElement(IMPORT_ELEMENT).addAttribute(RESOURCE_ATTR_NAME, fileNames[i]);
    }
  }
  
  public Document getDocument() {
    return document;
  }
  
  public AuthenticationType getAuthenticationType() {
    List<String> configFiles = Arrays.asList(getSystemConfigFileNames());
    AuthenticationType authenticationType = null;
    if(configFiles.contains(ACEGI_SECURITY_MEMORY_CONFIG_FILE) && configFiles.contains(PENTAHO_SECURITY_MEMORY_CONFIG_FILE)) { 
      authenticationType = AuthenticationType.MEMORY_BASED_AUTHENTICATION;
    } else if(configFiles.contains(ACEGI_SECURITY_DB_CONFIG_FILE) && configFiles.contains(PENTAHO_SECURITY_DB_CONFIG_FILE)) { 
      authenticationType = AuthenticationType.DB_BASED_AUTHENTICATION;
    } else if(configFiles.contains(ACEGI_SECURITY_LDAP_CONFIG_FILE) && configFiles.contains(PENTAHO_SECURITY_LDAP_CONFIG_FILE)) { 
      authenticationType = AuthenticationType.LDAP_BASED_AUTHENTICATION;
    }
      
    return authenticationType;
  }
  
  public void setAuthenticationType(AuthenticationType authenticationType) {
    if ((authenticationType != getAuthenticationType()) && (authenticationType != null)) {
      ArrayList<String> configFiles = new ArrayList<String>();
      configFiles.addAll(Arrays.asList(getSystemConfigFileNames()));
      configFiles.remove(ACEGI_SECURITY_MEMORY_CONFIG_FILE);
      configFiles.remove(ACEGI_SECURITY_DB_CONFIG_FILE);
      configFiles.remove(ACEGI_SECURITY_LDAP_CONFIG_FILE);
      configFiles.remove(PENTAHO_SECURITY_MEMORY_CONFIG_FILE);
      configFiles.remove(PENTAHO_SECURITY_DB_CONFIG_FILE);
      configFiles.remove(PENTAHO_SECURITY_LDAP_CONFIG_FILE);
      configFiles.remove(LDAP_PLACEHOLDER_CONFIG_FILE);
      switch (authenticationType) {
        case MEMORY_BASED_AUTHENTICATION:
          configFiles.add(ACEGI_SECURITY_MEMORY_CONFIG_FILE);
          configFiles.add(PENTAHO_SECURITY_MEMORY_CONFIG_FILE);
          break;
        case DB_BASED_AUTHENTICATION:
          configFiles.add(ACEGI_SECURITY_DB_CONFIG_FILE);
          configFiles.add(PENTAHO_SECURITY_DB_CONFIG_FILE);
          break;
        case LDAP_BASED_AUTHENTICATION:
          configFiles.add(ACEGI_SECURITY_LDAP_CONFIG_FILE);
          configFiles.add(PENTAHO_SECURITY_LDAP_CONFIG_FILE);
          configFiles.add(LDAP_PLACEHOLDER_CONFIG_FILE);
          break;
      }
      setSystemConfigFileNames(configFiles.toArray(new String[0]));
    }
  }
  
}