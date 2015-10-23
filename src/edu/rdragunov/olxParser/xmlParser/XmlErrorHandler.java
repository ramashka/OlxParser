package edu.rdragunov.olxParser.xmlParser;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.ArrayList;
import java.util.List;

public class XmlErrorHandler implements ErrorHandler {
    private List<Integer> errors=new ArrayList<>();

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        errors.add(exception.getLineNumber());
        exception.printStackTrace();

    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        errors.add(exception.getLineNumber());
        exception.printStackTrace();
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        errors.add(exception.getLineNumber());
        exception.printStackTrace();
    }

    public List<Integer> getErrors() {
        return errors;
    }
}
