package org.pentaho.pac.server.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class HibernateSettingsXml {
  
  Document document;
  
  private static final String ROOT_ELEMENT = "settings";//$NON-NLS-1$
  private static final String XPATH_TO_HIBERNATE_CFG_FILE = "settings/config-file";//$NON-NLS-1$
  private static final String XPATH_TO_HIBERNATE_MANAGED = "settings/managed"; //$NON-NLS-1$
  
  public HibernateSettingsXml(File hibernateSettingsXmlFile) throws IOException, DocumentException{
    this(getContents(hibernateSettingsXmlFile));    
  }
  
  public HibernateSettingsXml(String xml) throws DocumentException {
    this(DocumentHelper.parseText(xml));
  }
  
  public HibernateSettingsXml(Document doc) throws DocumentException {
    Element rootElement = doc.getRootElement();
    if ((rootElement != null) &&  !doc.getRootElement().getName().equals(ROOT_ELEMENT)) {
      throw new DocumentException("Invalid root element.");
    }
    document = doc;
  }
  
  public HibernateSettingsXml() {
    document = DocumentHelper.createDocument();
    document.addElement(ROOT_ELEMENT);
  }
  
  public String getHibernateConfigFile() {
    return getValue(XPATH_TO_HIBERNATE_CFG_FILE);
  }
  
  public void setHibernateConfigFile(String hibernateConfigFile) {
    setValue(XPATH_TO_HIBERNATE_CFG_FILE, hibernateConfigFile);
  }
  
  public String getHibernateManaged() {
    return getValue(XPATH_TO_HIBERNATE_MANAGED);
  }
  
  public void setHibernateManaged(String hibernateManaged) {
    setValue(XPATH_TO_HIBERNATE_MANAGED, hibernateManaged);
  }
    
  private void setValue(String xPath, String value) {
    setValue(xPath, value, false);
  }
  
  private void setValue(String xPath, String value, boolean useCData) {
    Element element = (Element) document.selectSingleNode( xPath );
    if (element == null) {
      element = DocumentHelper.makeElement(document, xPath);
    }
    if (useCData) {
      element.clearContent(); 
      element.addCDATA( value );
    } else {
      element.setText( value );
    }
  }

  private String getValue(String xpath) {
    Element element = (Element)document.selectSingleNode(xpath);
    return element != null ? element.getText() : null;
  }
  
  public Document getDocument() {
    return document;
  }
  
  private static String getContents(File aFile) throws FileNotFoundException, IOException{
    StringBuilder contents = new StringBuilder();
    
    BufferedReader input =  new BufferedReader(new FileReader(aFile));
    try {
      String line = null;
      String lineSeparator = System.getProperty("line.separator");
      while (( line = input.readLine()) != null){
        contents.append(line);
        contents.append(lineSeparator);
      }
    }
    finally {
      input.close();
    }
    
    return contents.toString();
  }
}
