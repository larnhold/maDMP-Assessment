package org.arnhold.evaluation.openaire;

import org.apache.jena.rdf.model.Model;
import org.arnhold.evaluation.openaire.service.OpenAireService;
import org.springframework.stereotype.Service;

@Service
public class OpenAireServiceImpl implements OpenAireService {

    // https://graph.openaire.eu/develop/api.html#projects

    @Override
    public void test(Model madmp) {
        this.getResearchData(madmp);
    }

    @Override
    public void getPublications(Model madmp) {

    }

    @Override
    public void getResearchData(Model madmp) {

    }

    @Override
    public void getResearchSoftware(Model madmp) {

    }

    @Override
    public void getResearchProducts(Model madmp) {

    }
}
