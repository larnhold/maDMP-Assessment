package org.arnhold.evaluation.openaire.service;


import org.apache.jena.rdf.model.Model;
import org.arnhold.evaluation.openaire.DatasourceSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class OpenAireServiceImpl implements OpenAireService {

    // https://graph.openaire.eu/develop/api.html#projects
    // https://api.openaire.eu/vocabularies/
    // file:///home/lukas/Downloads/OpenAIRE2020%20D8.2%20-%20LOD%20Services.pdf

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void test(Model madmp) {
        this.getResearchData(madmp);
    }

    @Override
    public void getPublications(Model madmp) {

    }

    @Override
    public void getResearchData(Model madmp) {
        String url = "http://api.openaire.eu/search/researchProducts?doi=10.5281/zenodo.4701612&format=json";
        // select response.results.result[0].metadata.oaf:entity.oaf:result
        // that has form of https://www.openaire.eu/schema/1.0/oaf-1.0.xsd
        var a = restTemplate.getForObject(url, DatasourceSchema.class);
        System.out.println();
    }

    @Override
    public void getResearchSoftware(Model madmp) {

    }

    @Override
    public void getResearchProducts(Model madmp) {

    }
}
