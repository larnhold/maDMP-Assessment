package org.arnhold.evaluation.openaire;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.jena.rdf.model.Model;
import org.arnhold.evaluation.openaire.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;


@Service
public class OpenAireServiceImpl implements OpenAireService {

    // https://graph.openaire.eu/develop/api.html#projects
    // https://api.openaire.eu/vocabularies/
    // file:///home/lukas/Downloads/OpenAIRE2020%20D8.2%20-%20LOD%20Services.pdf

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String test(Model madmp) {
        return this.getResearchData(madmp);
    }

    @Override
    public void getPublications(Model madmp) {

    }

    @Override
    public String getResearchData(Model madmp) {
        String url = "http://api.openaire.eu/search/researchProducts?doi=10.5281/zenodo.4701612&format=xml";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        String xmlResponse;

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();

            xmlResponse = EntityUtils.toString(entity);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new ByteArrayInputStream(xmlResponse.getBytes(StandardCharsets.UTF_8)));
            var test = doc.getElementsByTagName("oaf:entity");
            var node = test.item(0);

            Document newXml = builder.newDocument();
            Node importedNode = newXml.importNode(node, true);
            newXml.appendChild(importedNode);
            String entityString = getStringFromDocument(newXml);

            Entity resultEntity = parseXmlToDto(entityString, Entity.class);
            return entityString;
        } catch (IOException | ParserConfigurationException | SAXException | JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private String getStringFromDocument(Document doc)
    {
        try
        {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        }
        catch(TransformerException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void getResearchSoftware(Model madmp) {

    }

    @Override
    public void getResearchProducts(Model madmp) {

    }

    private <T> T parseXmlToDto(String xml, Class<T> tClass) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(xml));
    }
}
