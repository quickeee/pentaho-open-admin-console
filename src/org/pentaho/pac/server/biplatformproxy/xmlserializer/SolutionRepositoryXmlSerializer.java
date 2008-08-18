package org.pentaho.pac.server.biplatformproxy.xmlserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pentaho.pac.common.SolutionRepositoryServiceException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SolutionRepositoryXmlSerializer {

  private static final Log logger = LogFactory.getLog(SolutionRepositoryXmlSerializer.class);

  public SolutionRepositoryXmlSerializer() {
    
  }

  public void detectSubscriptionRepositoryErrorInXml( String strXml ) throws SolutionRepositoryServiceException {

    SolutionRepositoryErrorParserHandler errorHandler;
    try {
      errorHandler = parseSchedulerExceptionXml( strXml );
    } catch (SAXException e) {
      logger.error( e.getMessage() );
      throw new SolutionRepositoryServiceException( e.getMessage() );
    } catch (IOException e) {
      logger.error( e.getMessage() );
      throw new SolutionRepositoryServiceException( e.getMessage() );
    } catch (ParserConfigurationException e) {
      logger.error( e.getMessage() );
      throw new SolutionRepositoryServiceException( e.getMessage() );
    }
    if ( null != errorHandler.errorMessage ) {
      throw new SolutionRepositoryServiceException( errorHandler.errorMessage );
    }
  }
  
  /**
   * 
   * @param strXml
   * @return
   * @throws SAXException
   * @throws IOException
   * @throws ParserConfigurationException
   */
  private SolutionRepositoryErrorParserHandler parseSchedulerExceptionXml( String strXml ) throws SAXException, IOException, ParserConfigurationException
  {
      SAXParser parser = XmlSerializerUtil.getSAXParserFactory().newSAXParser();
      SolutionRepositoryErrorParserHandler h = new SolutionRepositoryErrorParserHandler();
      // TODO sbarkdull, need to set encoding
//      String encoding = CleanXmlHelper.getEncoding( strXml );
//      InputStream is = new ByteArrayInputStream( strXml.getBytes( encoding ) );
      InputStream is = new ByteArrayInputStream( strXml.getBytes( "UTF-8" ) ); //$NON-NLS-1$
     
      parser.parse( is, h );
      return h;
  }
  
  /**
   * potential error msg:
   * <web-service>
   *   <error
   *     msg="SolutionRepositoryService.ERROR_0001 - Failed to complete request to SolutionRepositoryWebService." />
   * </web-service>
   */
  private static class SolutionRepositoryErrorParserHandler extends DefaultHandler {

    public String errorMessage = null;
    private boolean isWebService = false;
    
    public SolutionRepositoryErrorParserHandler() {
    }
  
    public void characters( char[] ch, int startIdx, int length ) {
      // no-op
    }
    
    public void endElement(String uri, String localName, String qName ) throws SAXException
    {
      if ( qName.equals( "web-service" ) ) { //$NON-NLS-1$
        isWebService = false;
      }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
      if ( qName.equals( "web-service" ) ) { //$NON-NLS-1$
        isWebService = true;
      } else if ( qName.equals( "error" ) ) { //$NON-NLS-1$
        if ( isWebService ) {
          errorMessage = attributes.getValue( "msg" );//$NON-NLS-1$
        }
      }
    }
  }
}
