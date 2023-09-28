package org.arnhold.evaluation.openaire.service;


import org.apache.jena.rdf.model.Model;
import org.arnhold.evaluation.openaire.DatasourceSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class OpenAireServiceImpl implements OpenAireService {

    // https://graph.openaire.eu/develop/api.html#projects

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
